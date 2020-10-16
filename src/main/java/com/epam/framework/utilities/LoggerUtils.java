package com.epam.framework.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;

public class LoggerUtils {

    private LoggerUtils() {
    }

    private static final Logger log = LogManager.getLogger(LoggerUtils.class.getName());

    public static void info(String message, Object... params) {
        log.info(message, params);
    }

    public static void error(String message, Object... params) {
        log.error(message, params);
    }

    public static void warn(String message, Object... params) {
        log.warn(message, params);
    }

    public static void debug(String message, Object... params) {
        log.debug(message, params);
    }

    public static void startTestCase(ITestResult result) {
        Object[] parameters = result.getParameters();
        if (parameters != null) {
            for (Object parameter : parameters) log.info("{}", parameter);
        }
        log.info("TestCase {} Started", result.getMethod().getMethodName());
    }

    public static void endTestCase(String testCaseName) {
        log.info("{} Ended", testCaseName);
    }
}
