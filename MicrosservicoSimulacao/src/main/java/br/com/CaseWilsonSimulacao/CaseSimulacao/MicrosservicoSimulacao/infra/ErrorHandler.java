package br.com.CaseWilsonSimulacao.CaseSimulacao.MicrosservicoSimulacao.infra;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    public ResponseEntity HandleErrorSQL(SQLException e){
        return ResponseEntity.internalServerError().body("Erro SQL: " + e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity HandleErrorValidation(ValidationException e){
        return ResponseEntity.badRequest().body("Erro de validação" + e.getMessage());
    }
}
