package br.com.CaseWilsonIdentificacao.CaseIdentificacao.controller;

import br.com.CaseWilsonIdentificacao.CaseIdentificacao.dto.ClienteDTO;
import br.com.CaseWilsonIdentificacao.CaseIdentificacao.dto.ClienteDTOv2;
import br.com.CaseWilsonIdentificacao.CaseIdentificacao.model.InfosSimulacao;
import br.com.CaseWilsonIdentificacao.CaseIdentificacao.service.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping(headers = "API-Version=1")
    public Page<ClienteDTO> getAllClientesv1(@PageableDefault(size = 10) Pageable paginacao){
        return service.getAll(paginacao);
    }

    @GetMapping(headers = "API-Version=2")
    public Page<ClienteDTOv2> getAllClientesv2(@PageableDefault(size = 10) Pageable paginacao){
        return service.getAllv2(paginacao);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity getByCpf(@PathVariable @NotNull String cpf){

        if(!cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número documento digitado na forma errada, exemplo: 123.345.678-99");
        }
        var cliente = service.getByCpf(cpf);
        if(cliente == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente não encontrado no sistema");
        }
        return ResponseEntity.ok(service.getByCpf(cpf));
    }

    @PostMapping
    public ResponseEntity simulate(@RequestBody @Valid InfosSimulacao infosSimulacao){
        return service.clienteValid(infosSimulacao);
    }
}
