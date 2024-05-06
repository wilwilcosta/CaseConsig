package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.infra;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.SQLException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity HandleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity HandleError400(){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity HandleErroSQL(SQLException e){
        return ResponseEntity.internalServerError().body("Erro SQL: " + e.getMessage());
    }
}
