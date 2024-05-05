package br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
public class InfosCustodia {
    public InfosCustodia(Simulacao simulacao){
        this.data_contrato = simulacao.getData_contratacao();
        this.id_simulacao = simulacao.getId();
    }

    @NotNull
    @DateTimeFormat
    private Date data_contrato;

    @NotNull
    @Min(0)
    private Long id_simulacao;
}