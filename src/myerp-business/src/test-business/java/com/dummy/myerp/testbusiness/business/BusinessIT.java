package com.dummy.myerp.testbusiness.business;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import com.dummy.myerp.business.impl.TransactionManager;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.junit.*;
import org.junit.runner.RunWith;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

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

    @Before
    public void init(){
        comptabiliteManager = businessProxy.getComptabiliteManager();
    }

    @After
    public void close(){
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void getListCompteComptable() {
        List<CompteComptable> compteComptableList = comptabiliteManager.getListCompteComptable();

        Assert.assertFalse("Liste de comptes comptable", compteComptableList.isEmpty());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void getListEcritureComptable() {
        List<EcritureComptable> ecritureComptableList = comptabiliteManager.getListEcritureComptable();

        Assert.assertFalse("Liste d'écritures comptable", ecritureComptableList.isEmpty());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void getListJournalComptable() {
        List<JournalComptable> journalComptableList = comptabiliteManager.getListJournalComptable();

        Assert.assertFalse("Liste de journaux comptable", journalComptableList.isEmpty());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void addReference_InsertSequence(){
    EcritureComptable ecritureComptable = getEcritureComptableTest();

    String vCode = ecritureComptable.getJournal().getCode();
    Integer vYear = getYear(new Date());

    SequenceEcritureComptable vSequence = daoProxy.getComptabiliteDao().getSequenceEcritureComptable(vCode, vYear);

    Assert.assertNull(
        "SequenceEcritureComptable (code "+vCode+", année "+vYear+")",
            vSequence
    );

    comptabiliteManager.addReference(ecritureComptable);

    Assert.assertNotNull(
            "Création de la référence de l'écriture",
            ecritureComptable.getReference()
    );

    Integer vExpectedValue = 1;
    Assert.assertEquals(
            "Dernière valeure de la sequence(sequence_ecriture_comptable)",
            vExpectedValue,
            daoProxy.getComptabiliteDao().getSequenceEcritureComptable(vCode, vYear).getDerniereValeur()
    );

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void addReference_UpdateSequence(){
        JournalComptable vJournalComptable = comptabiliteManager.getListJournalComptable().get(0);

        Assert.assertNotNull(
                "1er journal comptable en bdd",
                vJournalComptable
        );

        EcritureComptable vEcritureComptable = comptabiliteManager.getListEcritureComptable().get(0);

        Integer vYear = getYear(vEcritureComptable.getDate());
        String vCode = vEcritureComptable.getJournal().getCode();

        SequenceEcritureComptable vSequence = daoProxy.getComptabiliteDao().getSequenceEcritureComptable(vCode, vYear);

        Assert.assertNotNull(
                "SequenceEcritureComptable (code "+vCode+", année "+vYear+")",
                vSequence
        );
        Integer expectedInitialSequence = 40;
        Assert.assertEquals(
                "Sequence initiale",
                expectedInitialSequence,
                vSequence.getDerniereValeur()
        );

        comptabiliteManager.addReference(vEcritureComptable);

        Assert.assertNotNull(
                "Création de la référence de l'écriture",
                vEcritureComptable.getReference()
        );

        SequenceEcritureComptable vUpdatedSequence = daoProxy.getComptabiliteDao().getSequenceEcritureComptable(vCode, vYear);
        Integer vUpdatedResult = vUpdatedSequence.getDerniereValeur();
        expectedInitialSequence += 1;
        Assert.assertEquals(
                "Sequence mise à jour",
                expectedInitialSequence,
                vUpdatedResult
        );

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void checkEcritureComptable_OK() throws FunctionalException {
        EcritureComptable vEcritureComptable = getEcritureComptableTest();

        comptabiliteManager.checkEcritureComptable(vEcritureComptable);
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void checkEcritureComptable_KO_WhenIdNull() {
        EcritureComptable vEcritureComptable = comptabiliteManager.getListEcritureComptable().get(0);
        vEcritureComptable.setId(null);
        Assert.assertThrows(
                "",
                FunctionalException.class,
                () -> comptabiliteManager.checkEcritureComptable(vEcritureComptable)
        );

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void checkEcritureComptable_KO_WhenIdAlreadyExist() {
        EcritureComptable vEcritureComptable = comptabiliteManager.getListEcritureComptable().get(0);
        vEcritureComptable.setId(-2);
        Assert.assertThrows(
                "RG_Compta_6",
                FunctionalException.class,
                () -> comptabiliteManager.checkEcritureComptable(vEcritureComptable)
        );

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void checkEcritureComptable_KO_WhenNotBalanced() {
        EcritureComptable vEcritureComptable = getEcritureComptableTest();
        vEcritureComptable.getListLigneEcriture().add(getLigneEcritureComptable(1,"",true,333));
        Assert.assertThrows(
                "RG_Compta_2 : Pour qu'une écriture comptable soit valide, elle doit être équilibrée",
                FunctionalException.class,
                () -> comptabiliteManager.checkEcritureComptable(vEcritureComptable)
        );

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void checkEcritureComptable_KO_WhenOnlyOneLigneEcritureComptable() {
        EcritureComptable vEcritureComptable = getEcritureComptableTest();
        vEcritureComptable.getListLigneEcriture().remove(1);
        Assert.assertThrows(
                "RG_Compta_3 : une écriture comptable doit avoir au moins 2 lignes d'écriture (1 au débit, 1 au crédit)",
                FunctionalException.class,
                () -> comptabiliteManager.checkEcritureComptable(vEcritureComptable)
        );

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void insertEcritureComptable_OK() throws FunctionalException {
        EcritureComptable vEcritureComptable = getEcritureComptableTest();

        comptabiliteManager.insertEcritureComptable(vEcritureComptable);

        EcritureComptable expectedEcritureComptable = new EcritureComptable();

        try {
            expectedEcritureComptable = daoProxy.getComptabiliteDao().getEcritureComptableByRef(vEcritureComptable.getReference());
        } catch (NotFoundException e) {
            Assert.fail(e.getMessage());
        }

        Assert.assertEquals(
                "Référence de l'écriture comptable",
                vEcritureComptable.getReference(),
                expectedEcritureComptable.getReference()
        );

        Assert.assertEquals(
                "Libellé de l'écriture comptable",
                vEcritureComptable.getLibelle(),
                expectedEcritureComptable.getLibelle()
        );

        Assert.assertEquals(
                "Nombre de ligne d'écriture",
                vEcritureComptable.getListLigneEcriture().size(),
                expectedEcritureComptable.getListLigneEcriture().size()
        );

    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void insertEcritureComptable_KO() {
        Assert.assertThrows(
                "L'écriture comptable ne peut pas être persisté",
                FunctionalException.class,
                () -> comptabiliteManager.insertEcritureComptable(new EcritureComptable())
        );
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void updateEcritureComptable_OK() throws FunctionalException {
        EcritureComptable ecritureComptable = getEcritureComptableTest();
        comptabiliteManager.insertEcritureComptable(ecritureComptable);

        String expectedLibelleUpdated = "Updated Libellé";
        ecritureComptable.setLibelle(expectedLibelleUpdated);
        ecritureComptable.getListLigneEcriture().forEach(e -> e.setLibelle(expectedLibelleUpdated));

        comptabiliteManager.updateEcritureComptable(ecritureComptable);

        EcritureComptable updatedEcritureComptable = new EcritureComptable();
        try {
            updatedEcritureComptable = daoProxy.getComptabiliteDao().getEcritureComptableByRef(ecritureComptable.getReference());
        } catch (NotFoundException e) {
            Assert.fail(e.getMessage());
        }

        Assert.assertEquals(
                "Mise à jour de Libellé d'écriture comptable",
                expectedLibelleUpdated,
                updatedEcritureComptable.getLibelle()
        );

        updatedEcritureComptable.getListLigneEcriture().forEach( e ->
                Assert.assertEquals(
                        "Mise à jour de Libellé de ligne d'écriture",
                        expectedLibelleUpdated,
                        e.getLibelle()
                )
        );
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void updateEcritureComptable_KO() {
        EcritureComptable ecritureComptable = getEcritureComptableTest();
        try {
            comptabiliteManager.insertEcritureComptable(ecritureComptable);
        } catch (FunctionalException e) {
            Assert.fail(e.getMessage());
        }

        String expectedLibelleUpdated = "Updated Libellé";
        ecritureComptable.setLibelle(expectedLibelleUpdated);
        ecritureComptable.getListLigneEcriture().forEach(e -> {
            e.setLibelle(expectedLibelleUpdated);
            e.getCompteComptable().setLibelle("null");
            e.getCompteComptable().setNumero(0);
        });

        Assert.assertThrows(
                "L'écriture comptable ne peut pas être mise à jour",
                Exception.class,
                () -> comptabiliteManager.updateEcritureComptable(ecritureComptable)
        );
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:/sql/init_dist_demo_data.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = "classpath:/sql/clean_dist_demo_data.sql")
    public void deleteEcritureComptable_OK() throws FunctionalException {
        EcritureComptable ecritureComptable = getEcritureComptableTest();
        comptabiliteManager.insertEcritureComptable(ecritureComptable);

        EcritureComptable expectedEcritureComptable = new EcritureComptable();

        try {
            expectedEcritureComptable = daoProxy.getComptabiliteDao().getEcritureComptableByRef(ecritureComptable.getReference());
        } catch (NotFoundException e) {
            Assert.fail(e.getMessage());
        }

        int id = expectedEcritureComptable.getId();
        comptabiliteManager.deleteEcritureComptable(id);

        Assert.assertThrows(
                "L'écriture comptable n'est plus en base",
                NotFoundException.class,
                () -> daoProxy.getComptabiliteDao().getEcritureComptable(id)
        );
    }

    private Integer getYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date == null ? new Date() : date);
        return calendar.get(Calendar.YEAR);
    }

    private EcritureComptable getEcritureComptableTest() {
        EcritureComptable vEcritureComptable = new EcritureComptable();
        String vReference = "AC-"+getYear(new Date())+"/00001";

        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference(vReference);
        vEcritureComptable.getListLigneEcriture().addAll(
                Arrays.asList(
                        getLigneEcritureComptable(401, "TI checkEcritureComptable_OK", true, 666),
                        getLigneEcritureComptable(411, "TI checkEcritureComptable_OK", false, 666)
                )
        );
        return vEcritureComptable;
    }

    private LigneEcritureComptable getLigneEcritureComptable(Integer pNumero, String pLibelle, Boolean isDebit, int value) {
        return new LigneEcritureComptable(
                new CompteComptable(pNumero, pLibelle),
                "TI getLigneEcritureComptable "+ (isDebit ? "Débit" : "Crédit"),
                isDebit ? new BigDecimal(value) : null,
                isDebit ? null : new BigDecimal(value)
        );
    }

}
