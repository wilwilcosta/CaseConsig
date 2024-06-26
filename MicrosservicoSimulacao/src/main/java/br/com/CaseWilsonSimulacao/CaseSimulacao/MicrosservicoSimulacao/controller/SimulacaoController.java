package br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.controller;

import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.dto.SimulacaoDTO;
import br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.dto.SimulacaoDTOv2;
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

    @GetMapping(headers = "API-Version=1")
    public Page<SimulacaoDTO> getAllSimulacoes(@PageableDefault(size = 10) Pageable paginacao){
        return service.getAll(paginacao);
    }

    @GetMapping(headers = "API-Version=2")
    public Page<SimulacaoDTOv2> getAllSimulacoesv2(@PageableDefault(size = 10) Pageable paginacao){
        return service.getAllv2(paginacao);
    }

    @PostMapping
    public ResponseEntity createSimulacao(@RequestBody @Valid InfosCliente infosCliente, UriComponentsBuilder uriBuilder){
        return service.createSimulacao(infosCliente);
    }

}
