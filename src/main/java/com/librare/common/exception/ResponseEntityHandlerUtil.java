package com.librare.common.exception;

import com.librare.common.utils.LogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseEntityHandlerUtil {

    public void handleResponse(final String local, final String mensagem, final ResponseEntity<String> response) {
        var status = response.getStatusCode();
        if (status != HttpStatus.OK) {
            LogUtil.logObject(local, true, response);
            throw new ApiException(
                    HttpStatus.valueOf(status.value()),
                    new MensagemDto("400", mensagem + response.getBody()));
        }
    }
}