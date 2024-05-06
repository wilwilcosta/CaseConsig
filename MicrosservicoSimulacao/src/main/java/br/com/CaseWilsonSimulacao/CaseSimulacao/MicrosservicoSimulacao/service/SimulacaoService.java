package br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.service;

import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.dto.SimulacaoDTO;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.dto.SimulacaoDTOv2;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.http.ClientCustodia;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.*;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.repository.SimulacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.function.Function;

@Service
public class SimulacaoService {
    @Autowired
    private SimulacaoRepository repository;

    @Autowired
    private ClientCustodia clientCustodia;

    public Page<SimulacaoDTO> getAll(Pageable paginacao){
        try{
            return repository.findAll(paginacao).map(new Function<Simulacao, SimulacaoDTO>() {
                @Override
                public SimulacaoDTO apply(Simulacao simulacao) {
                    var dto = new SimulacaoDTO(simulacao);

                    return dto;
                }
            });
        }
        catch (Exception e){
            throw e;
        }
    }

    public Page<SimulacaoDTOv2> getAllv2(Pageable paginacao){
        try{
            return repository.findAll(paginacao).map(new Function<Simulacao, SimulacaoDTOv2>() {
                @Override
                public SimulacaoDTOv2 apply(Simulacao simulacao) {
                    var dto = new SimulacaoDTOv2(simulacao);

                    return dto;
                }
            });
        }
        catch (Exception e){
            throw e;
        }
    }

    public ResponseEntity createSimulacao(InfosCliente infosCliente){
        try{
            var documento = infosCliente.getCpf();
            var convenio = infosCliente.getConvenio();
            var valor_solicitado = infosCliente.getValor_solicitado();
            var numero_parcelas = infosCliente.getNumero_parcelas();

            var taxa = getTaxaValue(infosCliente);
            var numeroParcelaLimite = getNumeroParcelaLimite(infosCliente);
            if(numeroParcelaLimite < numero_parcelas){
                return ResponseEntity.badRequest().body("Número de parcelas requisitadas é maior do que o número permitido para o cliente: "+ numeroParcelaLimite);
            }

            var valorTotal = calculateValorTotal(infosCliente, taxa);
            DecimalFormat df = new DecimalFormat("####0.00");
            df.setRoundingMode(RoundingMode.DOWN);
            var formatado = df.format(valorTotal / numero_parcelas).replace(',','.');
            var valorParcela = Double.valueOf(formatado);

            var dataAtual = new Date(new java.util.Date().getTime());
            Simulacao simulacao = new Simulacao(documento, convenio, dataAtual, taxa, valorParcela, numero_parcelas, valor_solicitado, valorTotal);

            repository.save(simulacao);

            InfosCustodia infosCustodia = new InfosCustodia(simulacao);
            return clientCustodia.createCustodia(infosCustodia);
        }
        catch (Exception e){
            throw e;
        }
    }

    private double getTaxaValue(InfosCliente infosCliente){
        var taxa = 0.0;
        switch (Convenio.valueOf(infosCliente.getConvenio().toString())){
            case EP:
                taxa = 0.026;
                break;
            case OP:
                taxa = 0.022;
                break;
            case INSS:
                taxa = 0.016;
                break;
            default:
                break;
        }

        if(infosCliente.isCorrentista())
            taxa -= 0.05 * taxa;

        return taxa;
    }

    private int getNumeroParcelaLimite(InfosCliente infosCliente){
        var limite = 0;

        if(!infosCliente.isCorrentista())
            return 12;

        switch (Segmento.valueOf(infosCliente.getSegmento().toString())){
            case Varejo:
                limite = 24;
                break;
            case Uniclass:
                limite = 36;
                break;
            case Person:
                limite = 48;
                break;
            default:
                break;
        }

        return limite;
    }

    private double calculateValorTotal(InfosCliente infosCliente, double taxa){
        var juros = infosCliente.getValor_solicitado() * (taxa) *  infosCliente.getNumero_parcelas();
        return infosCliente.getValor_solicitado() + juros;
    }
}
