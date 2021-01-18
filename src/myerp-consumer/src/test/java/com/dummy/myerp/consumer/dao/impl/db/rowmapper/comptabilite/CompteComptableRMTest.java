package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class CompteComptableRMTest {

    @Mock
    ResultSet resultSet;

    @InjectMocks
    CompteComptableRM compteComptableRM;

    @Test
    public void testCompteComptableRM() throws SQLException {
        Integer expectedInt = 5;
        String expectedString = "Intitulé du compte";

        doReturn(expectedInt).when(resultSet).getInt(anyString());
        doReturn(expectedString).when(resultSet).getString(anyString());

        CompteComptable compteComptable = compteComptableRM.mapRow(resultSet, 1);

        assertEquals("RowMapping", expectedInt, compteComptable.getNumero());
        assertEquals("RowMapping",expectedString, compteComptable.getLibelle());

    }
}
