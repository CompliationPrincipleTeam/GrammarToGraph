package com.nuaa.compliation.exception;

/**
 * @author dmrfcoder
 * @date 2018/11/1
 */
public class GrammarPhaseException extends Exception {
    private String exceptionInfo;

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public GrammarPhaseException(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }
}
