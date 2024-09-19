package com.librare.common;

import com.librare.common.utils.LogUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LogUtilTest {

    @Test
    @DisplayName("Imprimindo logObject NULL no STDOUT")
    void logObjectNullTest() {
        try {
            var logUtilTestMock = new LogUtilNullClassMock();
            LogUtil.logObject("[LogUtilTest.logObjectNullTest]", true, logUtilTestMock);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Imprimindo logObject NOT NULL no STDOUT")
    void logObjectNotNullTest() {
        try {
            var logUtilTestMock = new LogUtilObjectMock("[LogUtilObjectMock.stringAttribute.value]");
            LogUtil.logObject("[LogUtilTest.logObjectNotNullTest]", true, logUtilTestMock);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Imprimindo logMessage no STDOUT")
    void logMessageTest() {
        try {
            LogUtil.logMessage("STDOUT message");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Imprimindo mensagem e stackTrace no STDOUT")
    void printStackTraceTest() {
        var mensagemEsperada = "[LogUtilTest.printStackTraceTest = true]";
        try {
            logStackTraceTest(true);
        } catch (Exception e) {
            Assertions.assertEquals(mensagemEsperada, e.getMessage());
        }
    }

    @Test
    @DisplayName("Imprimindo mensagem sem stackTrace no STDOUT")
    void noPrintStackTraceTest() {
        var mensagemEsperada = "[LogUtilTest.printStackTraceTest = false]";
        try {
            logStackTraceTest(false);
        } catch (Exception e) {
            Assertions.assertEquals(mensagemEsperada, e.getMessage());
        }
    }

    private void logStackTraceTest(final boolean printStackTrace) throws Exception {
        var mensagem = String.format("[LogUtilTest.printStackTraceTest = %s]", printStackTrace);
        Exception e = new Exception(mensagem);
        LogUtil.logStackTrace(mensagem, printStackTrace, e);
        throw e;
    }

    private static class LogUtilNullClassMock {
    }

    @AllArgsConstructor
    private static class LogUtilObjectMock {
        @Getter
        private final String stringAttribute;
    }
}