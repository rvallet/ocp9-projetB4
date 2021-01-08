package com.dummy.myerp.model.bean.comptabilite;

import java.math.BigDecimal;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.Test;


public class EcritureComptableTest {

    // ==================== Méthodes utilisées pour les tests ====================

    /**  Création d'une ligne d'écriture comptable (Factorisation) */
    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(
                new CompteComptable(pCompteComptableNumero),
                vLibelle,
                vDebit,
                vCredit
        );
        return vRetour;
    }

    /** L'écriture comptable équilibré pour effectuer les tests */
    private EcritureComptable getEcritureComptableEquilibre(){
        EcritureComptable vEcriture = new EcritureComptable();

        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33.50"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301.50"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "41", "7"));

        return vEcriture;
    }

    /** Le résultat total attendu de l'écriture comptable équilibré (Débit ou Crédit) */
    private BigDecimal expectedResult = new BigDecimal(342);

    // ==================== Tests ====================

    @Test
    public void isEquilibree() {
        EcritureComptable vEcriture = getEcritureComptableEquilibre();

        Assert.assertTrue(
                "Le calcul d'équilibre d'écriture ne s'est pas effectué correctement :\n"+vEcriture.toString(),
                vEcriture.isEquilibree()
        );

        vEcriture.getListLigneEcriture().clear();
        vEcriture.setLibelle("Non équilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1.50"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "1.50", "2"));
        Assert.assertFalse(
                "Le calcul d'équilibre d'écriture ne s'est pas effectué correctement :\n"+vEcriture.toString(),
                vEcriture.isEquilibree()
        );
    }

    @Test
    public void getTotalDebit(){
        EcritureComptable vEcriture = getEcritureComptableEquilibre();

        Assert.assertTrue(
                "Le total débit ne s'est pas effectué correctement :\n"+vEcriture.toString(),
                this.expectedResult.compareTo(vEcriture.getTotalDebit())==0
        );
    }

    @Test
    public void getTotalCredit(){
        EcritureComptable vEcriture = getEcritureComptableEquilibre();

        Assert.assertTrue(
                "Le total crédit ne s'est pas effectué correctement :\n"+vEcriture.toString(),
                this.expectedResult.compareTo(vEcriture.getTotalCredit())==0
        );
    }

}
