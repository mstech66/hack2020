package com.epam.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    private static final Logger log = LogManager.getLogger(LoggerUtil.class.getName());

    public static void info(String message) {
        log.info(message);
    }

    public static void error(String message) {
        log.info(message);
    }

    public static void warn(String message) {
        log.info(message);
    }

    public static void debug(String message) {
        log.info(message);
    }

    public static void startTestCase(String testCaseName) {
        log.info("*******************************");
        log.info("Started " + testCaseName);
        log.info("*******************************");
    }

    public static void endTestCase(String testCaseName) {
        log.info("*******************************");
        log.info("Ended" + testCaseName);
        log.info("*******************************");
    }
}
