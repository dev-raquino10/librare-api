package com.librare.common;

import com.librare.common.exception.ApiException;
import com.librare.common.exception.MensagemDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ApiExceptionTest {

    @Test
    void constructorWithLocalMensagemAndStatus_ShouldSetCodigoAndStatus() {
        ApiException exception = new ApiException(HttpStatus.BAD_REQUEST, new MensagemDto("test", "test"));
        Assertions.assertEquals("test", exception.getCodigo());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }
}