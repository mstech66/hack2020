package com.epam.framework.exceptions;

import com.epam.framework.constants.LoggerConstants;
import com.epam.framework.utilities.LoggerUtils;

public class PageException extends RuntimeException {

    public PageException(String msg) {
        super(msg);
        LoggerUtils.error(LoggerConstants.FRAMEWORK_EXCEPTION+msg);
    }
}
