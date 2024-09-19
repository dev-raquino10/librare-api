package com.librare.common;

import com.librare.common.exception.MensagemDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MensagemDtoTest {

    @Test
    void testMensagemDtoBuilder() {
        MensagemDto mensagem = MensagemDto.builder()
                .codigo("200")
                .descricao("Sucesso")
                .build();

        assertNotNull(mensagem);
        assertEquals("200", mensagem.getCodigo());
        assertEquals("Sucesso", mensagem.getDescricao());
    }

    @Test
    void testMensagemDtoAllArgsConstructor() {
        MensagemDto mensagem = new MensagemDto("404", "Não encontrado");

        assertNotNull(mensagem);
        assertEquals("404", mensagem.getCodigo());
        assertEquals("Não encontrado", mensagem.getDescricao());
    }
}
