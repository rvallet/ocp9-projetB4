package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import com.dummy.myerp.consumer.dao.impl.db.dao.ComptabiliteDaoImpl;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class SequenceEcritureComptableRMTest {

    @Mock
    ResultSet resultSet;

    @InjectMocks
    SequenceEcritureComptableRM sequenceEcritureComptableRM;

    @Test
    public void testSequenceEcritureComptablemapRow() throws SQLException {
        String vExpectedJournalCode ="XY";
        Integer vExpectedAnnee = 2021;
        Integer vExpectedValeur = 666;

        doReturn(vExpectedJournalCode).when(resultSet).getString(ComptabiliteDaoImpl.dbColumn.JOURNAL_CODE.getValue());
        doReturn(vExpectedAnnee).when(resultSet).getInt(ComptabiliteDaoImpl.dbColumn.ANNEE.getValue());
        doReturn(vExpectedValeur).when(resultSet).getInt(ComptabiliteDaoImpl.dbColumn.DERNIERE_VALEUR.getValue());

        SequenceEcritureComptable sequenceEcritureComptable = sequenceEcritureComptableRM.mapRow(resultSet, 1);

        assertEquals(
                "Code du journal",
                vExpectedJournalCode,
                sequenceEcritureComptable.getJournalCode());
        assertEquals(
                "Année du Journal",
                vExpectedAnnee,
                sequenceEcritureComptable.getAnnee());
        assertEquals(
                "Dernière valeure du Jounal",
                vExpectedValeur,
                sequenceEcritureComptable.getDerniereValeur());

    }
}
