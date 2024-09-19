package com.librare.common.exception;

import lombok.Getter;

public class DadosErroValidacao {

    @Getter
    String codigo;
    @Getter
    String descricao;

    public DadosErroValidacao(MensagemDto mensagem) {
        codigo = mensagem.getCodigo();
        descricao = mensagem.getDescricao();
    }
}