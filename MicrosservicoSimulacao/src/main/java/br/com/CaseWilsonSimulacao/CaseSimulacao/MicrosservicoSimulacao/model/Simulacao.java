package br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model;

import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.dto.SimulacaoDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "simulacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Simulacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 14, max = 14)
    private String cpf;

    @NotNull
    private Date data_contratacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Convenio convenio;

    @NotNull
    @Valid()
    private double valor_solicitado;

    @NotNull
    private double taxa_aplicada;

    @NotNull
    private int quantidade_parcelas;

    @NotNull
    private double valor_total;

    @NotNull
    private double valor_parcela;

    public Simulacao(SimulacaoDTO dto) {
        var simulacao = new Simulacao(){};
        simulacao.setId(id);
        simulacao.setQuantidade_parcelas(quantidade_parcelas);
        simulacao.setCpf(cpf);
        simulacao.setConvenio(convenio);
        simulacao.setData_contratacao(data_contratacao);
        simulacao.setValor_parcela(valor_parcela);
        simulacao.setValor_solicitado(valor_solicitado);
        simulacao.setValor_total(valor_total);

    }

    public Simulacao(String _cpf, Convenio _convenio, Date _data_contratacao,double _taxa_aplicada, double _valor_parcela, int _quantidade_parcelas, double _valor_solicitado, double _valor_total) {

        this.setData_contratacao(_data_contratacao);
        this.setCpf(_cpf);
        this.setConvenio(_convenio);
        this.setValor_solicitado(_valor_solicitado);
        this.setTaxa_aplicada(_taxa_aplicada);
        this.setValor_parcela(_valor_parcela);
        this.setQuantidade_parcelas(_quantidade_parcelas);
        this.setValor_total(_valor_total);

    }

    public SimulacaoDTO ConvertToDTO(){

        var dto = new SimulacaoDTO(){};
        dto.setId(id);
        dto.setCpf(cpf);
        dto.setConvenio(convenio);
        dto.setData_contratacao(data_contratacao);
        dto.setConvenio(convenio);
        dto.setValor_parcela(valor_parcela);
        dto.setQuantidade_parcelas(quantidade_parcelas);
        dto.setValor_solicitado(valor_solicitado);
        dto.setValor_total(valor_total);

        return dto;
    }
}
