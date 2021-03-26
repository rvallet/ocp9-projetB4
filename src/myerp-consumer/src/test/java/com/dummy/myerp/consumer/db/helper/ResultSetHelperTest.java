package com.dummy.myerp.consumer.db.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
public class ResultSetHelperTest {

    @Mock
    ResultSet resultSet;

    @Test
    public void testGetIntegerOK() throws SQLException {
        Integer expectedResult = 1;
        doReturn(expectedResult).when(resultSet).getInt(anyString());
        doReturn(false).when(resultSet).wasNull();

        Integer result = ResultSetHelper.getInteger(resultSet,"pColName");

        assertNotNull(
                "ResultSetHelper : renvoi un résultat Non-Null avec resultSet Non-Null",
                result
        );

        assertEquals(
                "ResultSetHelper : valeur en Integer de la colonne pColName avec resultSet Non-Null",
                expectedResult,
                result
        );
    }

    @Test
    public void testGetIntegerKO() throws SQLException {
        Integer expectedResult = 1;
        doReturn(expectedResult).when(resultSet).getInt(anyString());
        doReturn(true).when(resultSet).wasNull();

        Integer result = ResultSetHelper.getInteger(resultSet,"pColName");

        assertNull(
                "ResultSetHelper : valeur en Integer de la colonne pColName avec resultSet Null",
                    result
        );
    }

    @Test
    public void testGetLongOK() throws SQLException {
        Long expectedResult = 1L;
        doReturn(expectedResult).when(resultSet).getLong(anyString());
        doReturn(false).when(resultSet).wasNull();

        Long result = ResultSetHelper.getLong(resultSet,"pColName");

        assertNotNull(
                "ResultSetHelper : renvoi un résultat Long Non-Null avec resultSet Non-Null",
                result
        );

        assertEquals(
                "ResultSetHelper : valeur en Long de la colonne pColName avec resultSet Non-Null",
                expectedResult,
                result
        );
    }

    @Test
    public void testGetLongKO() throws SQLException {
        Long expectedResult = 1L;
        doReturn(expectedResult).when(resultSet).getLong(anyString());
        doReturn(true).when(resultSet).wasNull();

        Long result = ResultSetHelper.getLong(resultSet,"pColName");

        assertNull(
                "ResultSetHelper : valeur en Long de la colonne pColName avec resultSet Non-Null",
                result
        );
    }

    @Test
    public void testGetDateOK() throws SQLException {
        java.sql.Date expectedResult = java.sql.Date.valueOf(LocalDate.now());
        expectedResult.setTime(System.currentTimeMillis());

        doReturn(expectedResult).when(resultSet).getDate(anyString());

        Date result = ResultSetHelper.getDate(resultSet,"pColName");

        assertNotNull(
                "ResultSetHelper : renvoi une Date Non-Null avec resultSet Non-Null",
                result
        );

        assertEquals(
                "ResultSetHelper : Date de la colonne pColName avec resultSet Non-Null",
                String.valueOf(expectedResult),
                String.valueOf(new java.sql.Date(result.getTime()))
        );
    }

    @Test
    public void testGetDateKO() throws SQLException {
        Date expectedResult = null;
        doReturn(expectedResult).when(resultSet).getDate(anyString());

        Date result = ResultSetHelper.getDate(resultSet,"pColName");

        assertNull(
                "ResultSetHelper : Date de la colonne pColName avec resultSet Null",
                result
        );
    }

}
