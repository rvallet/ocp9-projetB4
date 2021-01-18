package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.cache.JournalComptableDaoCache;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConsumerHelper.class)
public class EcritureComptableRMTest {

    @Mock
    ResultSet resultSet;

    @Mock
    DaoProxy daoProxy;

    @Mock
    ComptabiliteDao comptabiliteDao;

    @Mock
    JournalComptableDaoCache journalComptableDaoCache;

    @InjectMocks
    EcritureComptableRM ecritureComptableRM;

    @Test
    public void testEcritureComptableRowMapping() throws SQLException {
        PowerMockito.mockStatic(ConsumerHelper.class);
        when(ConsumerHelper.getDaoProxy()).thenReturn(daoProxy);
        when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);

        Integer vExpectedInt = 5;
        String vExpectedLibelle = "Libellé";
        String vExpectedReference = "Référence";
        Date vExpectedSqlDate = Date.valueOf("2021-01-15");
        JournalComptable journalComptable = new JournalComptable("JC", "LibelleJournalComptable");

        doReturn(vExpectedInt).when(resultSet).getInt(anyString());
        doReturn(vExpectedLibelle).when(resultSet).getString("libelle");
        doReturn(vExpectedReference).when(resultSet).getString("reference");
        doReturn(vExpectedSqlDate).when(resultSet).getDate("date");
        doReturn("journal_code").when(resultSet).getString("journal_code");
        doReturn(journalComptable).when(journalComptableDaoCache).getByCode(anyString());

        EcritureComptable ecritureComptable = ecritureComptableRM.mapRow(resultSet, 1);

        assertEquals("testEcritureComptableRowMapping getId()", vExpectedInt, ecritureComptable.getId());
        assertEquals("testEcritureComptableRowMapping getLibelle()", vExpectedLibelle, ecritureComptable.getLibelle());
        assertEquals("testEcritureComptableRowMapping getReference()", vExpectedReference, ecritureComptable.getReference());
        assertEquals("testEcritureComptableRowMapping getDate()", vExpectedSqlDate, ecritureComptable.getDate());
        assertEquals("testEcritureComptableRowMapping getJournal()", journalComptable, ecritureComptable.getJournal());

    }
}
