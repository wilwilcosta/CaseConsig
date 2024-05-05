package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.service;

import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.dto.CustodiaDTO;
import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.model.Custodia;
import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.model.InfosCustodia;
import br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.repository.CustodiaRepository;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustodiaService {

    @Autowired
    private CustodiaRepository repository;


    public Page<CustodiaDTO> getAll(Pageable paginacao) {
        return repository.findAll(paginacao).map(new Function<Custodia, CustodiaDTO>() {
            @Override
            public CustodiaDTO apply(Custodia custodia) {
                var dto = new CustodiaDTO(custodia);

                return dto;
            }
        });
    }

    public ResponseEntity createCustodia(InfosCustodia infosCustodia) {
        try {
            Custodia custodia = new Custodia(infosCustodia);
            repository.save(custodia);

            return new ResponseEntity(new CustodiaDTO(custodia), HttpStatus.CREATED);

        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao registrar custodia: " + getMensagemErro(e));
        }

    }

    private String getMensagemErro(Throwable t){
        String erro = t.getCause().toString();
        if(t.getCause().toString().contains("a foreign key constraint fails")){
            erro = "id_simulação inválido";
        }
        return erro;
    }

}
