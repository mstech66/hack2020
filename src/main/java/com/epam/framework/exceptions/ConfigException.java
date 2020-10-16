package com.epam.framework.exceptions;

import com.epam.framework.constants.LoggerConstants;
import com.epam.framework.utilities.LoggerUtils;

public class ConfigException extends RuntimeException {
    public ConfigException(String msg) {
        super(msg);
        LoggerUtils.error(LoggerConstants.FRAMEWORK_EXCEPTION + msg + " is not specified in Properties File");
    }
}
