package com.dummy.myerp.technical.exception;

import org.junit.Assert;
import org.junit.Test;

public class TechnicalExceptionTest {

    final String technicalExceptionMessage = "Technical Exception Message";
    final Throwable technicalExceptionCause = new Throwable("Technical Exception Cause");

    @Test
    public void getMessageTechnicalException(){
        TechnicalException technicalException = new TechnicalException(technicalExceptionMessage);
        Assert.assertTrue(technicalExceptionMessage.equalsIgnoreCase(technicalException.getMessage()));
    }

    @Test
    public void getCauseTechnicalException(){
        TechnicalException technicalException = new TechnicalException(technicalExceptionCause);
        Assert.assertTrue(technicalExceptionCause.equals(technicalException.getCause()));
    }

    @Test
    public void getTechnicalException(){
        TechnicalException technicalException = new TechnicalException(technicalExceptionMessage, technicalExceptionCause);
        Assert.assertTrue(technicalExceptionMessage.equalsIgnoreCase(technicalException.getMessage()));
        Assert.assertTrue(technicalExceptionCause.equals(technicalException.getCause()));
    }
}