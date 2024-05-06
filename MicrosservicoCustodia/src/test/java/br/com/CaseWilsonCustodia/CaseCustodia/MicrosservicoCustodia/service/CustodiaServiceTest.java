package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.service;

import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.model.InfosCustodia;
import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.repository.CustodiaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustodiaServiceTest {

    private String cpf = "111.111.111-11";

    @MockBean
    CustodiaRepository repository;

    @Autowired
    CustodiaService custodiaService;

    ObjectMapper om = new ObjectMapper();

    @Test
    void getAll() {
        Pageable pageable = PageRequest.of(0, 20);
        var result = custodiaService.getAll(pageable);
        Assert.assertTrue(result.hasContent());
    }

    @Test
    void createCustodia() {
        var dataAtual = new Date(new java.util.Date().getTime());

        InfosCustodia infosCustodia = new InfosCustodia();
        infosCustodia.setId_simulacao(1L);
        infosCustodia.setData_contrato(dataAtual);

        var resultado = custodiaService.createCustodia(infosCustodia);
        Assert.assertEquals(HttpStatusCode.valueOf(201), resultado.getStatusCode());
    }
}