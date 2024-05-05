package br.com.CaseWilsonIdentificacao.CaseIdentificacao.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfosSimulacao {
    @NotNull
    @NotBlank
    @Size(min = 14, max = 14)
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$")
    @JsonAlias("cpf")
    private String cpf;

    @NotNull
    @DecimalMin("0.00")
    @JsonAlias("valor_solicitado")
    private double valor_solicitado;

    @NotNull
    @Min(0)
    @JsonAlias("numero_parcelas")
    private int numero_parcelas;
}
