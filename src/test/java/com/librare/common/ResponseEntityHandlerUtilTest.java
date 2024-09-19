package com.librare.common;

import com.librare.common.exception.ApiException;
import com.librare.common.exception.ResponseEntityHandlerUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ResponseEntityHandlerUtilTest {

    @InjectMocks
    private ResponseEntityHandlerUtil responseEntityHandlerUtil;

    @Test
    void handleResponse_whenStatusIsOk_shouldNotThrowException() {
        String local = "local";
        String mensagem = "mensagem";
        ResponseEntity<String> response = new ResponseEntity<>("body", HttpStatus.OK);

        assertDoesNotThrow(() -> responseEntityHandlerUtil.handleResponse(local, mensagem, response));
    }

    @Test
    void handleResponse_whenStatusIsNotOk_shouldThrowApiException() {
        String local = "local";
        String mensagem = "mensagem";
        ResponseEntity<String> response = new ResponseEntity<>("body", HttpStatus.INTERNAL_SERVER_ERROR);

        assertThrows(ApiException.class, () -> responseEntityHandlerUtil.handleResponse(local, mensagem, response));
    }
}