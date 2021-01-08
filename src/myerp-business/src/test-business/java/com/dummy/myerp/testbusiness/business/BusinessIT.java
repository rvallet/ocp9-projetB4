package com.dummy.myerp.testbusiness.business;

import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import com.dummy.myerp.business.impl.BusinessProxyImpl;
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
@ContextConfiguration("classpath:../../../src/main/resources/com/dummy/myerp/business/applicationContext.xml")
public class BusinessIT {

    @Autowired
    BusinessProxyImpl businessProxy;

    private ComptabiliteManager comptabiliteManager;

    @Autowired
    DaoProxy daoProxy;

    @Test
    public void testIT() throws FunctionalException {
        comptabiliteManager = businessProxy.getComptabiliteManager();
        comptabiliteManager.insertEcritureComptable(new EcritureComptable());
        daoProxy.getComptabiliteDao().getListEcritureComptable();

    }
}
