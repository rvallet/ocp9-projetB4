package com.dummy.myerp.testconsumer.consumer;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
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
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void getListJournalComptable() {
        List<JournalComptable> journalComptableList = comptabiliteDao.getListJournalComptable();

        Assert.assertEquals(
                "Liste des journaux comptables",
                4,
                journalComptableList.size()
        );
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void getListEcritureComptable() {
        List<EcritureComptable> ecritureComptableList = comptabiliteDao.getListEcritureComptable();

        Assert.assertEquals(
                "Liste des écritures comptables",
                5,
                ecritureComptableList.size()
        );
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void getEcritureComptable_OK() {
    Integer id = -5;
    String expectedReference = "BQ-2016/00005";
    String expectedDate = "2016-12-27";
    String expectedLibelle = "Paiement Facture C110002";

    EcritureComptable ecritureComptable = new EcritureComptable();
        try {
            ecritureComptable = comptabiliteDao.getEcritureComptable(id);
        } catch (NotFoundException e) {
            Assert.fail(e.getMessage());
        }

        Assert.assertEquals(
                "Id",
                id,
                ecritureComptable.getId()
        );

        Assert.assertEquals(
                "Référence",
                expectedReference,
                ecritureComptable.getReference()
        );

        Assert.assertEquals(
                "Date",
                expectedDate,
                ecritureComptable.getDate().toString()
        );

        Assert.assertEquals(
                "Libellé",
                expectedLibelle,
                ecritureComptable.getLibelle()
        );
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void getEcritureComptable_KO() {
        Integer id = 0;

        Assert.assertThrows(
                "Id de l'écriture comptable n'existe pas",
                NotFoundException.class,
                () -> comptabiliteDao.getEcritureComptable(id)
        );

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void getEcritureComptableByRef_OK() {
        Integer expectedId = -5;
        String reference = "BQ-2016/00005";

        EcritureComptable ecritureComptable = new EcritureComptable();
        try {
            ecritureComptable = comptabiliteDao.getEcritureComptableByRef(reference);
        } catch (NotFoundException e) {
            Assert.fail(e.getMessage());
        }

        Assert.assertEquals(
                "id",
                expectedId,
                ecritureComptable.getId()
        );
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void getEcritureComptableByRef_KO() {
        String reference = "";

        Assert.assertThrows(
                "La référence de l'écriture comptable n'existe pas",
                NotFoundException.class,
                () -> comptabiliteDao.getEcritureComptableByRef(reference)
        );
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void insertEcritureComptable() {
    EcritureComptable expectedEcritureComptable = getEcritureComptableTest();
    // TODO : feed journal comptable with expected rules
    comptabiliteDao.insertEcritureComptable(expectedEcritureComptable);

    List<EcritureComptable> dbEcritureComptableList = comptabiliteDao.getListEcritureComptable();
    EcritureComptable dbEcritureComptable = dbEcritureComptableList.get(dbEcritureComptableList.size());

    Assert.assertEquals(
            "Référence de l'écriture comptable",
            expectedEcritureComptable.getReference(),
            dbEcritureComptable.getReference()
    );

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

    private EcritureComptable getEcritureComptableTest() {
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setDate(new Date());
        ecritureComptable.setLibelle("TI EcritureComptable "+new Date());
        ecritureComptable.setReference("AA-2021/0001");
        ecritureComptable.setJournal(new JournalComptable());
        return ecritureComptable;
    }
}
