package com.librare.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MensagemDto {

    private String codigo;
    private String descricao;
}
