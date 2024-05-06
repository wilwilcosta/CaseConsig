package br.com.CaseWilsonIdentificacao.CaseIdentificacao.dto;

import br.com.CaseWilsonIdentificacao.CaseIdentificacao.model.Convenio;
import br.com.CaseWilsonIdentificacao.CaseIdentificacao.model.Segmento;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTOv2 {

    @NotNull
    @NotBlank
    @Size(min = 14, max = 14)
    private String cpf;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotNull
    @NotBlank
    private boolean correntista;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Segmento segmento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Convenio convenio;
}
