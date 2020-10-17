package com.epam.utility;

public class PageException extends RuntimeException {

    public PageException(String msg) {
        super(msg);
        LoggerUtil.error(msg);
    }
}
