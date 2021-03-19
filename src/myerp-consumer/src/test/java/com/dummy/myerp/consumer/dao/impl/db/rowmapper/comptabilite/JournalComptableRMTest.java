package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
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
public class JournalComptableRMTest {

    @Mock
    ResultSet resultSet;

    @InjectMocks
    JournalComptableRM journalComptableRM;

    @Test
    public void testJournalComptableRowMapping() throws SQLException {
        String vExpectedLibelle = "Libellé du journal";
        String vExpectedCode = "Code du journal";

        doReturn(vExpectedCode).when(resultSet).getString("code");
        doReturn(vExpectedLibelle).when(resultSet).getString("libelle");

        JournalComptable journalComptable = journalComptableRM.mapRow(resultSet, 1);

        assertEquals(
                "Le Code journal obtenu n'est pas correcte",
                vExpectedCode,
                journalComptable.getCode());

        assertEquals(
                "Le Libellé journal obtenu n'est pas correcte",
                vExpectedLibelle,
                journalComptable.getLibelle());

    }
}
