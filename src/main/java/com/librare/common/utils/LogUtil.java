package com.librare.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

@Slf4j
public class LogUtil {

    private static final String SEPARA = "-".repeat(120);
    private static final String START = ">" + SEPARA;
    private static final String END = SEPARA + "<";

    private LogUtil() {
    }

    /**
     * Imprime um log (level INFO) com um objeto em formato json.
     * Parametro printObject = TRUE, somente antes da publicação em produção (DEV ou HML) ou quando há suspeita de erro.
     * Parametro printObject = FALSE, apenas para saber se passou pelo ponto no programa.
     *
     * @param message     Texto ou nome do objeto, para identificação do objeto.
     * @param printObject TRUE, imprime o objeto; False, imprime somente a mensagem.
     * @param obj         Objeto a ser impresso no log.
     */
    public static void logObject(String message, boolean printObject, Object obj) {
        String strObj;
        if (printObject) {

            try {
                strObj = new ObjectMapper()
                        .writer()
                        .withDefaultPrettyPrinter()
                        .writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                strObj = obj.toString();
            }
            log.info(printLog(message, strObj));
        } else {
            log.info("--> " + message);
        }
    }

    /**
     * Imprime somente uma linha com a mensagem.
     *
     * @param message mensagem a ser impressa.
     */
    public static void logMessage(final String message) {
        logObject(message, false, false);
    }


    /**
     * Imprime um log (level ERROR) com um objeto stackTrace.
     * Parametro printStackTrace = TRUE, somente antes da publicação em produção (DEV ou HML) ou quando há suspeita de erro.
     * Parametro printStackTrace = FALSE, apenas para saber se passou pelo ponto no programa.
     *
     * @param message         Texto ou nome do objeto, para identificação do objeto.
     * @param printStackTrace TRUE, imprime a stackTrace; False, imprime somente a mensagem.
     * @param throwable       Objeto stackTrace a ser impresso no log.
     */
    public static void logStackTrace(String message, final boolean printStackTrace, Throwable throwable) {
        var stackTrace = printStackTrace ?
                ExceptionUtils.getStackTrace(throwable) :
                throwable.getMessage();

        log.error(printLog(message, stackTrace));
    }

    /**
     * Constrói o bloco de log de mensagem no formato padrão.
     *
     * @param message Texto ou nome do objeto, para identificação do objeto.
     * @param strObj  Texto que representa o objeto logado.
     * @return Bloco de log de mensagem no formato padrão.
     */
    private static String printLog(String message, String strObj) {
        return "\n" + START + "\n" + message + ":\n" + strObj + "\n" + END;
    }
}