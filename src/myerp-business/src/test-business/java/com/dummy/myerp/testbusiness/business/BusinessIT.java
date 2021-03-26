package com.dummy.myerp.testbusiness.business;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import com.dummy.myerp.business.impl.TransactionManager;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Classe de tests d'intégration de la couche Business
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:bootstrapContext.xml")
public class BusinessIT {

    @Autowired
    BusinessProxy businessProxy;

    @Autowired
    DaoProxy daoProxy;

    @Autowired
    TransactionManager transactionManager;

    private ComptabiliteManager comptabiliteManager;

    @Test
    public void getListCompteComptable() {
        comptabiliteManager = businessProxy.getComptabiliteManager();
        List<CompteComptable> compteComptableList = comptabiliteManager.getListCompteComptable();

        Assert.assertFalse("Liste de comptes comptable", compteComptableList.isEmpty());
    }

    @Test
    public void getListEcritureComptable() {
        comptabiliteManager = businessProxy.getComptabiliteManager();
        List<EcritureComptable> ecritureComptableList = comptabiliteManager.getListEcritureComptable();

        Assert.assertFalse("Liste d'écritures comptable", ecritureComptableList.isEmpty());
    }

    @Test(expected = FunctionalException.class)
    public void testIT() throws FunctionalException {
        comptabiliteManager = businessProxy.getComptabiliteManager();
        comptabiliteManager.insertEcritureComptable(new EcritureComptable());
        daoProxy.getComptabiliteDao().getListEcritureComptable();
    }
}
