package com.dummy.myerp.testconsumer.consumer;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:bootstrapContext.xml")
public class ConsumerIT {

    @Autowired
    ComptabiliteDao comptabiliteDao;

    @Before
    public void init(){

    }

    @Test
    public void getListCompteComptable(){

    }

    @Test
    public void getListJournalComptable() {

    }

    @Test
    public void getListEcritureComptable() {

    }

    @Test
    public void getEcritureComptable_OK() {

    }

    @Test
    public void getEcritureComptable_KO() {

    }

    @Test
    public void getEcritureComptableByRef_OK() {

    }

    @Test
    public void getEcritureComptableByRef_KO() {

    }

    @Test
    public void insertEcritureComptable() {

    }

    @Test
    public void insertListLigneEcritureComptable() {

    }

    @Test
    public void updateEcritureComptable() {

    }

    @Test
    public void deleteEcritureComptable() {

    }

    @Test
    public void deleteListLigneEcritureComptable() {

    }

    @Test
    public void getSequenceEcritureComptable() {

    }

    @Test
    public void updateSequenceEcritureComptable() {

    }


}
