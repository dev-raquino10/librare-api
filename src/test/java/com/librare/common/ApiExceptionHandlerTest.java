package com.librare.common;

import com.librare.common.exception.ApiException;
import com.librare.common.exception.ApiExceptionHandler;
import com.librare.common.exception.DadosErroValidacao;
import com.librare.common.exception.MensagemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiExceptionHandlerTest {

    private ApiExceptionHandler apiExceptionHandler;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @BeforeEach
    void setUp() {
        apiExceptionHandler = new ApiExceptionHandler();
    }

    @Test
    void handleDefaultMethodArgumentNotValidException_shouldReturnBadRequest() {
        List<FieldError> fieldErrors = new ArrayList<>();
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");
        fieldErrors.add(fieldError);

        when(methodArgumentNotValidException.getFieldErrors()).thenReturn(fieldErrors);

        ResponseEntity<List<DadosErroValidacao>> response = apiExceptionHandler.handleDefaultMethodArgumentNotValidException(methodArgumentNotValidException);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void handleApiException_shouldReturnStatusAndMessage() {
        ApiException apiException = new ApiException(HttpStatus.NOT_FOUND, new MensagemDto("400", "Error message"));

        ResponseEntity<String> response = apiExceptionHandler.handleApiException(apiException);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Error message", response.getBody());
    }
}
