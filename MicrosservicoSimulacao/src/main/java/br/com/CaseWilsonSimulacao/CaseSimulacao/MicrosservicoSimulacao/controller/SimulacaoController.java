package br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.controller;

import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.dto.SimulacaoDTO;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.model.InfosCliente;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.service.SimulacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/simulacao")
public class SimulacaoController {

    @Autowired
    private SimulacaoService service;

    @GetMapping
    public Page<SimulacaoDTO> getAllSimulacoes(@PageableDefault(size = 10) Pageable paginacao){
        return service.getAll(paginacao);
    }

    @PostMapping
    public ResponseEntity createSimulacao(@RequestBody @Valid InfosCliente infosCliente, UriComponentsBuilder uriBuilder){
        return service.createSimulacao(infosCliente);
    }

}
