package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.model;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
public class InfosCustodia {
    @NotNull
    @DateTimeFormat
    private Date data_contrato;

    @NotNull
    @Min(0)
    private Long id_simulacao;
}
