package br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.dto;

import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.Convenio;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.Simulacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class SimulacaoDTOv2 {
    public SimulacaoDTOv2(){}
    public SimulacaoDTOv2(Simulacao simulacao){
        this.setCpf(simulacao.getCpf());
        this.setConvenio(simulacao.getConvenio());
        this.setData_contratacao(simulacao.getData_contratacao());
        this.setConvenio(simulacao.getConvenio());
        this.setValor_parcela(simulacao.getValor_parcela());
        this.setQuantidade_parcelas(simulacao.getQuantidade_parcelas());
        this.setValor_solicitado(simulacao.getValor_solicitado());
        this.setValor_total(simulacao.getValor_total());
    }

    @NotNull
    @NotBlank
    @Size(min = 14, max = 14)
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$")
    private String cpf;

    @NotBlank
    private Date data_contratacao;

    @NotNull
    @NotBlank
    private String cpf_cliente;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Convenio convenio;

    @NotNull
    @DecimalMin("0.00")
    private double valor_solicitado;

    @NotNull
    @DecimalMin("0.00")
    private double taxa_aplicada;

    @NotNull
    @Min(0)
    private int quantidade_parcelas;

    @NotNull
    @DecimalMin("0.00")
    private double valor_total;

    @NotNull
    @DecimalMin("0.00")
    private double valor_parcela;

    public SimulacaoDTOv2 ConvertToDTO(Simulacao simulacao){

        var dto = new SimulacaoDTOv2(){};
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
