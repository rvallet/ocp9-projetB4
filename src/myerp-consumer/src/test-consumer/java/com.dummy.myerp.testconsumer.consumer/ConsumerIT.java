package com.dummy.myerp.testconsumer.consumer;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.model.bean.comptabilite.*;
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
    comptabiliteDao.insertEcritureComptable(expectedEcritureComptable);

    List<EcritureComptable> dbEcritureComptableList = comptabiliteDao.getListEcritureComptable();
    EcritureComptable dbEcritureComptable = dbEcritureComptableList.get(dbEcritureComptableList.size()-1);

    Assert.assertEquals(
            "Référence de l'écriture comptable",
            expectedEcritureComptable.getReference(),
            dbEcritureComptable.getReference()
    );

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void updateEcritureComptable() {
        Integer id = -5;

        EcritureComptable expectedEritureComptable = new EcritureComptable();
        try {
            expectedEritureComptable = comptabiliteDao.getEcritureComptable(id);
        } catch (NotFoundException e) {
            Assert.fail(e.getMessage());
        }

        EcritureComptable ecritureComptableTest = getEcritureComptableTest();
        expectedEritureComptable.setLibelle(ecritureComptableTest.getLibelle());
        for (LigneEcritureComptable e : ecritureComptableTest.getListLigneEcriture()) {
            expectedEritureComptable.getListLigneEcriture().add(e);
        }

        comptabiliteDao.updateEcritureComptable(expectedEritureComptable);
        List<EcritureComptable> ecritureComptableList = comptabiliteDao.getListEcritureComptable();
        EcritureComptable resultEcritureComptable = ecritureComptableList.get(ecritureComptableList.size()-1);

        Assert.assertEquals(
                "Libelle",
                expectedEritureComptable.getLibelle(),
                resultEcritureComptable.getLibelle()
        );

        Assert.assertEquals(
                "Référence",
                expectedEritureComptable.getReference(),
                resultEcritureComptable.getReference()
        );

        int expectedNbLigneEcriture = expectedEritureComptable.getListLigneEcriture().size();
        int resultNbLigneEcriture = resultEcritureComptable.getListLigneEcriture().size();
        int nbAjout = ecritureComptableTest.getListLigneEcriture().size();

        Assert.assertEquals(
                "Nombre de Ligne d'écriture",
                expectedNbLigneEcriture,
                resultEcritureComptable.getListLigneEcriture().size()
        );

        for(int i=1; i<=nbAjout; i++) {
            Assert.assertEquals(
                    "Ajout ligne d'écriture N°"+i,
                    expectedEritureComptable.getListLigneEcriture().get(expectedNbLigneEcriture - i).toString(),
                    resultEcritureComptable.getListLigneEcriture().get(resultNbLigneEcriture - i).toString()
            );
        }

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void deleteEcritureComptable() {
        Integer id = -5;

        EcritureComptable deletedEritureComptable = new EcritureComptable();
        try {
            deletedEritureComptable = comptabiliteDao.getEcritureComptable(id);
        } catch (NotFoundException e) {
            Assert.fail(e.getMessage());
        }

        comptabiliteDao.deleteEcritureComptable(id);

        final EcritureComptable finalDeletedEritureComptable = deletedEritureComptable;

        Assert.assertThrows(
                "La référence de l'écriture comptable n'existe plus",
                NotFoundException.class,
                () -> comptabiliteDao.getEcritureComptableByRef(finalDeletedEritureComptable.getReference())
        );

        Assert.assertThrows(
                "L'Id de l'écriture comptable n'existe plus",
                NotFoundException.class,
                () -> comptabiliteDao.getEcritureComptable(finalDeletedEritureComptable.getId())
        );
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void getSequenceEcritureComptableOK() {
     String vJournalCode ="AC";
     Integer vAnnee = 2016;
     Integer expectedDerniereValeur = 40;

     SequenceEcritureComptable sequenceEcritureComptable = comptabiliteDao.getSequenceEcritureComptable(vJournalCode, vAnnee);

     Assert.assertEquals(
             "",
             expectedDerniereValeur,
             sequenceEcritureComptable.getDerniereValeur()
     );
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void getSequenceEcritureComptableKO() {
        String vJournalCode ="";
        Integer vAnnee = 0;

        SequenceEcritureComptable sequenceEcritureComptable = comptabiliteDao.getSequenceEcritureComptable(vJournalCode, vAnnee);

        Assert.assertNull(
                "Pas de sequence comptable",
                sequenceEcritureComptable
        );
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void updateSequenceEcritureComptableIfExists() {
        String vJournalCode ="AC";
        Integer vAnnee = 2016;
        Integer expectedDerniereValeur = 40;
        Integer updatedDerniereValeur = 1;
        SequenceEcritureComptable sequenceEcritureComptable = comptabiliteDao.getSequenceEcritureComptable(vJournalCode, vAnnee);

        Assert.assertEquals(
                "Dernière valeur avant update",
                expectedDerniereValeur,
                sequenceEcritureComptable.getDerniereValeur()
        );

        sequenceEcritureComptable.setDerniereValeur(updatedDerniereValeur);

        comptabiliteDao.updateSequenceEcritureComptable(sequenceEcritureComptable, true);

        SequenceEcritureComptable updatedSequenceEcritureComptable = comptabiliteDao.getSequenceEcritureComptable(vJournalCode, vAnnee);
        Assert.assertEquals(
                "Dernière valeur après update",
                updatedDerniereValeur,
                updatedSequenceEcritureComptable.getDerniereValeur()
        );

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void updateSequenceEcritureComptableIfNotExists() {
        String vJournalCode ="AC";
        Integer vAnnee = 2021;
        Integer vDerniereValeur = 1;

        SequenceEcritureComptable sequenceEcritureComptable = comptabiliteDao.getSequenceEcritureComptable(vJournalCode, vAnnee);

        Assert.assertNull(
                "Pas de sequence comptable",
                sequenceEcritureComptable
        );

        sequenceEcritureComptable = new SequenceEcritureComptable();
        sequenceEcritureComptable.setDerniereValeur(vDerniereValeur);
        sequenceEcritureComptable.setAnnee(vAnnee);
        sequenceEcritureComptable.setJournalCode(vJournalCode);

        comptabiliteDao.updateSequenceEcritureComptable(sequenceEcritureComptable, false);

        SequenceEcritureComptable updatedSequenceEcritureComptable = comptabiliteDao.getSequenceEcritureComptable(vJournalCode, vAnnee);

        Assert.assertNotNull(
                "Sequence comptable crée",
                updatedSequenceEcritureComptable
        );

        Assert.assertEquals(
                "Dernière valeur",
                vDerniereValeur,
                updatedSequenceEcritureComptable.getDerniereValeur()
        );

        Assert.assertEquals(
                "Code journal",
                vJournalCode,
                updatedSequenceEcritureComptable.getJournalCode()
        );

        Assert.assertEquals(
                "Année",
                vAnnee,
                updatedSequenceEcritureComptable.getAnnee()
        );

    }

    private EcritureComptable getEcritureComptableTest() {
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setDate(new Date());
        ecritureComptable.setLibelle("TI EcritureComptable "+new Date());
        ecritureComptable.setReference("AA-2021/0001");
        ecritureComptable.setJournal(getJournalComptable());
        return ecritureComptable;
    }

    private JournalComptable getJournalComptable() {
        JournalComptable journalComptable = new JournalComptable("AC", "JournalComptable TI");
        return journalComptable;
    }
}
