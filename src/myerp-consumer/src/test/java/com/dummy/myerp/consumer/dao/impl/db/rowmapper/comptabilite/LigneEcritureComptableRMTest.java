package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.cache.CompteComptableDaoCache;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConsumerHelper.class)
public class LigneEcritureComptableRMTest {

    @Mock
    ResultSet resultSet;

    @Mock
    CompteComptableDaoCache compteComptableDaoCache;

    @Mock
    DaoProxy daoProxy;

    @Mock
    ComptabiliteDao comptabiliteDao;

    @InjectMocks
    LigneEcritureComptableRM ligneEcritureComptableRM;

    @Test
    public void testLigneEcritureComptablemapRow() throws SQLException {
        PowerMockito.mockStatic(ConsumerHelper.class);

        /* variables */
        BigDecimal vExpectedCredit = new BigDecimal(666);
        BigDecimal vExpectedDebit = new BigDecimal(-666);
        Integer vExcpectedNumero = 1;
        String vExcpectedLibelle = "Libelle du compte comptable";

        CompteComptable compteComptable = new CompteComptable(vExcpectedNumero, vExcpectedLibelle);

        /* Définition des retours de méthodes impliquées dans le tests */
        when(ConsumerHelper.getDaoProxy()).thenReturn(daoProxy);
        when(ConsumerHelper.getDaoProxy().getComptabiliteDao()).thenReturn(comptabiliteDao);
        when(resultSet.getObject("compte_comptable_numero", Integer.class)).thenReturn(vExcpectedNumero);
        when(compteComptableDaoCache.getByNumero(1)).thenReturn(compteComptable);
        when(daoProxy.getComptabiliteDao().getListCompteComptable()).thenReturn(Arrays.asList(compteComptable));

        doReturn(vExpectedCredit).when(resultSet).getBigDecimal("credit");
        doReturn(vExpectedDebit).when(resultSet).getBigDecimal("debit");
        doReturn(vExcpectedNumero).when(resultSet).getInt("numero");
        doReturn(vExcpectedLibelle).when(resultSet).getString("libelle");

        /* Lancement de la méthode à tester */
        LigneEcritureComptable ligneEcritureComptable = ligneEcritureComptableRM.mapRow(resultSet, 1);

        /* Assertions */
        assertEquals("Libellé de la ligne d'écriture comptable", vExcpectedLibelle, ligneEcritureComptable.getLibelle());
        assertEquals("Montant du crédit de la ligne d'écriture comptable", vExpectedCredit, ligneEcritureComptable.getCredit());
        assertEquals("Montant du dédit de la linge d'écriture comptable", vExpectedDebit, ligneEcritureComptable.getDebit());
        assertEquals("CompteComptable correspondant à la ligne comptable", compteComptable, ligneEcritureComptable.getCompteComptable());
        assertEquals("Libellé du compte comptable concerné", vExcpectedLibelle, ligneEcritureComptable.getCompteComptable().getLibelle());
        assertEquals("Numéros du compte comptable concerné", vExcpectedNumero, ligneEcritureComptable.getCompteComptable().getNumero());

    }
}
