package com.librare.common.exception;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public final class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> handleDefaultMethodArgumentNotValidException(
            @NotNull final MethodArgumentNotValidException methodArgumentNotValidException) {
        var erros = methodArgumentNotValidException.getFieldErrors();
        List<DadosErroValidacao> list = new ArrayList<>();
        for (FieldError fieldError : erros) {
            var mensagem = new MensagemDto("400", "Erro ao processar a proposta: " + fieldError);
            DadosErroValidacao dadosErroValidacao = new DadosErroValidacao(mensagem);
            list.add(dadosErroValidacao);
        }
        return ResponseEntity.badRequest().body(list);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

}