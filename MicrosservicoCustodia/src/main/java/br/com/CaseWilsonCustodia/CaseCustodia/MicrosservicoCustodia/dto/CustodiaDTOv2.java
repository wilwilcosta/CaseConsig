package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.dto;

import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.model.Custodia;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CustodiaDTOv2 {
    public CustodiaDTOv2(){}
    public CustodiaDTOv2(Custodia custodia){
        this.setData_contrato(custodia.getData_contrato());
        this.setId_simulacao(custodia.getId_simulacao());
    }


    @NotNull
    private Date data_contrato;

    @NotNull
    private Long id_simulacao;
}
