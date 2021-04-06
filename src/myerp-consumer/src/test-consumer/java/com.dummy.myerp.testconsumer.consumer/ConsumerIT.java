package com.dummy.myerp.testconsumer.consumer;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:bootstrapContext.xml")
public class ConsumerIT {

    @Autowired
    ComptabiliteDao comptabiliteDao;

    @Before
    public void init(){

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void getListCompteComptable(){
        List<CompteComptable> compteComptableList = comptabiliteDao.getListCompteComptable();

        Assert.assertEquals(
                "Liste des comptes comptables",
                7,
                compteComptableList.size()
        );
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
