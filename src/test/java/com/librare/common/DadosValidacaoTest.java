package com.librare.common;

import com.librare.common.exception.DadosErroValidacao;
import com.librare.common.exception.MensagemDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DadosValidacaoTest {

    @Test
    void testDadosErroValidacao() {
        MensagemDto mensagem = new MensagemDto("123", "Erro de validação");
        DadosErroValidacao dadosErro = new DadosErroValidacao(mensagem);

        assertNotNull(dadosErro);
        assertEquals("123", dadosErro.getCodigo());
        assertEquals("Erro de validação", dadosErro.getDescricao());
    }
}