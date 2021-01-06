package com.dummy.myerp.technical.exception;

import org.junit.Assert;
import org.junit.Test;

public class NotFoundExceptionTest {
    final String notFoundExceptionMessage = "NotFound Exception Message";
    final Throwable notFoundExceptionCause = new Throwable("NotFound Exception Cause");

    @Test
    public void getMessageNotFoundException(){
        NotFoundException notFoundException = new NotFoundException(notFoundExceptionMessage);
        Assert.assertTrue(notFoundExceptionMessage.equalsIgnoreCase(notFoundException.getMessage()));
    }

    @Test
    public void getCauseNotFoundException(){
        NotFoundException notFoundException = new NotFoundException(notFoundExceptionCause);
        Assert.assertTrue(notFoundExceptionCause.equals(notFoundException.getCause()));
    }

    @Test
    public void getNotFoundException(){
        NotFoundException notFoundException = new NotFoundException(notFoundExceptionMessage, notFoundExceptionCause);
        Assert.assertTrue(notFoundExceptionMessage.equalsIgnoreCase(notFoundException.getMessage()));
        Assert.assertTrue(notFoundExceptionCause.equals(notFoundException.getCause()));
    }

}