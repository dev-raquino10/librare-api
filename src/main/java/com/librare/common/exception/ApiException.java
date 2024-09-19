package com.librare.common.exception;

import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    @Getter
    private final String codigo;
    @Getter
    private final HttpStatus status;

    public ApiException(@NotNull final HttpStatus status, @NotNull final MensagemDto mensagem) {
        super(mensagem.getDescricao());
        this.codigo = mensagem.getCodigo();
        this.status = status;
    }
}
