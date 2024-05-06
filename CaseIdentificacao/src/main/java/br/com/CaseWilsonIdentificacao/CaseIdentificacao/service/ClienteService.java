package br.com.CaseWilsonIdentificacao.CaseIdentificacao.service;

import br.com.CaseWilsonIdentificacao.CaseIdentificacao.dto.ClienteDTO;
import br.com.CaseWilsonIdentificacao.CaseIdentificacao.dto.ClienteDTOv2;
import br.com.CaseWilsonIdentificacao.CaseIdentificacao.http.SimulacaoClient;
import br.com.CaseWilsonIdentificacao.CaseIdentificacao.model.*;
import br.com.CaseWilsonIdentificacao.CaseIdentificacao.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.BadAttributeValueExpException;
import java.util.List;
import java.util.function.Function;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private SimulacaoClient simulacaoClient;

    public Page<ClienteDTO> getAll(Pageable paginacao){
        return repository.findAll(paginacao).map(new Function<Cliente, ClienteDTO>() {
            @Override
            public ClienteDTO apply(Cliente cliente) {
                var dto = new ClienteDTO(){};
                dto.setId(cliente.getId());
                dto.setCpf(cliente.getCpf());
                dto.setNome(cliente.getNome());
                dto.setCorrentista(cliente.isCorrentista());
                dto.setSegmento(cliente.getSegmento());
                dto.setConvenio(cliente.getConvenio());

                return dto;
            }
        });

    }

    public Page<ClienteDTOv2> getAllv2(Pageable paginacao){
        return repository.findAll(paginacao).map(new Function<Cliente, ClienteDTOv2>() {
            @Override
            public ClienteDTOv2 apply(Cliente cliente) {
                var dto = new ClienteDTOv2(){};
                dto.setCpf(cliente.getCpf());
                dto.setNome(cliente.getNome());
                dto.setCorrentista(cliente.isCorrentista());
                dto.setSegmento(cliente.getSegmento());
                dto.setConvenio(cliente.getConvenio());

                return dto;
            }
        });

    }

    public ClienteDTO getById(long id){
        var cliente = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return cliente.ConvertToDTO();
    }

    public ClienteDTO getByCpf(String cpf){
        var cliente = repository.findByCpf(cpf);
        if(cliente == null){
            return null;
        }
        return cliente.ConvertToDTO();
    }

    public ResponseEntity clienteValid(InfosSimulacao infosSimulacao){
        var cpf = infosSimulacao.getCpf();
        var valorSolicitado = infosSimulacao.getValor_solicitado();
        var numeroParcelas = infosSimulacao.getNumero_parcelas();

        //a. Verificar se o CPF informado tem o formato válido DDD.DDD.DDD-DD;
        var cpfValido = cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$");
        if(!cpfValido)
            return ResponseEntity.badRequest().body("CPF digitado na forma errada, exemplo: 123.345.678-99");

        //b. Verificar se o CPF informado existe na base de clientes;
        var cliente = getByCpf(cpf);
        if(cliente == null)
            return ResponseEntity.badRequest().body("Cliente não existe na base");

        //c. Verificar se o cliente é um correntista ou não correntista;
        var correntista = cliente.isCorrentista();

        //d. Caso seja correntista, qual o segmento do cliente (Varejo, Uniclass ou Person);
        Segmento segmento = null;
        if(correntista){
            segmento = cliente.getSegmento();
        }

        //e. Qual o convênio pertence o cliente (Empresa Privada (EP), Orgão Público (OP) ou INSS);
        Convenio convenio = cliente.getConvenio();

        InfosCliente infos = new InfosCliente(cliente, valorSolicitado, numeroParcelas);

        return simulacaoClient.createSimulacao(infos);
    }
}
