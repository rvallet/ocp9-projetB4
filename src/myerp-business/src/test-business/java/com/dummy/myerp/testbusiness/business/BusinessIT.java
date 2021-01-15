package com.dummy.myerp.testbusiness.business;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import com.dummy.myerp.business.impl.BusinessProxyImpl;
import com.dummy.myerp.business.impl.TransactionManager;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Classe de tests d'intégration de la couche Business
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class BusinessIT {

    @Autowired
    BusinessProxy businessProxy;

    @Autowired
    DaoProxy daoProxy;

    @Autowired
    TransactionManager transactionManager;

    private ComptabiliteManager comptabiliteManager;

    @Test
    public void testIT() throws FunctionalException {
        comptabiliteManager = businessProxy.getComptabiliteManager();
        comptabiliteManager.insertEcritureComptable(new EcritureComptable());
        daoProxy.getComptabiliteDao().getListEcritureComptable();

    }
}
