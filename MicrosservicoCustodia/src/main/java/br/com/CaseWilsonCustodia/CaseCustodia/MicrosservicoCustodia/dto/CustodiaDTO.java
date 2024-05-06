package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.dto;

import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.model.Custodia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CustodiaDTO {
    public CustodiaDTO(){}
    public CustodiaDTO(Custodia custodia){
        this.setId(custodia.getId());
        this.setData_contrato(custodia.getData_contrato());
        this.setId_simulacao(custodia.getId_simulacao());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date data_contrato;

    @NotNull
    private Long id_simulacao;
}
