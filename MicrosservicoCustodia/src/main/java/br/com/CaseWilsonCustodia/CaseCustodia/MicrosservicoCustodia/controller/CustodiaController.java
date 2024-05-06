package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.controller;

import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.dto.CustodiaDTO;
import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.dto.CustodiaDTOv2;
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

    @GetMapping(headers = "API-Version=1")
    public Page<CustodiaDTO> getAllCustodias(@PageableDefault(size = 10) Pageable paginacao){
        return service.getAll(paginacao);
    }
    @GetMapping(headers = "API-Version=2")
    public Page<CustodiaDTOv2> getAllClientesv2(@PageableDefault(size = 10) Pageable paginacao){
        return service.getAllv2(paginacao);
    }

    @PostMapping
    public ResponseEntity createCustodia(@RequestBody @Valid InfosCustodia infosCustodia, UriComponentsBuilder uriBuilder){
        return  service.createCustodia(infosCustodia);
    }
}
