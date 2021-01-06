package com.dummy.myerp.technical.exception;

import org.junit.Assert;
import org.junit.Test;

public class FunctionalExceptionTest {

    final String functionalExceptionMessage = "Functional Exception Message";
    final Throwable functionalExceptionCause = new Throwable("Functional Exception Cause");

    @Test
    public void getMessageFunctionalException(){
        FunctionalException functionalException = new FunctionalException(functionalExceptionMessage);
        Assert.assertTrue(functionalExceptionMessage.equalsIgnoreCase(functionalException.getMessage()));
    }

    @Test
    public void getCauseFunctionalException(){
        FunctionalException functionalException = new FunctionalException(functionalExceptionCause);
        Assert.assertTrue(functionalExceptionCause.equals(functionalException.getCause()));
    }

    @Test
    public void getFunctionalException(){
        FunctionalException functionalException = new FunctionalException(functionalExceptionMessage, functionalExceptionCause);
        Assert.assertTrue(functionalExceptionMessage.equalsIgnoreCase(functionalException.getMessage()));
        Assert.assertTrue(functionalExceptionCause.equals(functionalException.getCause()));
    }
}