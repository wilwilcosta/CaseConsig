package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.controller;

import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.model.InfosCustodia;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustodiaControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private String cpf = "111.111.111-11";
    ObjectMapper om = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void getAllCustodias() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/custodia")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createCustodia() throws Exception {
        var dataAtual = new Date(new java.util.Date().getTime());

        InfosCustodia infosCustodia = new InfosCustodia();
        infosCustodia.setData_contrato(dataAtual);
        infosCustodia.setId_simulacao(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/custodia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(infosCustodia))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}