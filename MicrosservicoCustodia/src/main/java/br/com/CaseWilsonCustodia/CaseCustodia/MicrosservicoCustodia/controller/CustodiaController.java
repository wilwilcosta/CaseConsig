package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.controller;

import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.dto.CustodiaDTO;
import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.model.InfosCustodia;
import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.service.CustodiaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/custodia")
public class CustodiaController {
    @Autowired
    private CustodiaService service;

    @GetMapping
    public Page<CustodiaDTO> getAllCustodias(@PageableDefault(size = 10) Pageable paginacao){
        return service.getAll(paginacao);
    }

    @PostMapping
    public ResponseEntity createCustodia(@RequestBody @Valid InfosCustodia infosCustodia, UriComponentsBuilder uriBuilder){
        CustodiaDTO custodia = service.createCustodia(infosCustodia);
        if(custodia.getId() == null){
            return ResponseEntity.badRequest().body("Erro ao registrar custodia: id_simulação inválido");
        }
        URI uri = uriBuilder.path("/custodia/{id}").buildAndExpand(custodia.getId()).toUri();

        return ResponseEntity.created(uri).body(custodia);

    }

}
