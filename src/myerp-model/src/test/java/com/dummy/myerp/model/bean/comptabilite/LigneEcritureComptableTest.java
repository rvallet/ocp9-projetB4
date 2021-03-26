package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class LigneEcritureComptableTest {

    @Test
    public void newLigneEcritureComptable() {

        String vLibelle = "pLibelle";
        BigDecimal vDebit = new BigDecimal(-666);
        BigDecimal vCredit = new BigDecimal(666);
        Integer vNumCompteComptable = 1;
        String vLibelleCompteComptable = "libellé";
        CompteComptable vCompteComptable = new CompteComptable(vNumCompteComptable, vLibelleCompteComptable);

        LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable(vCompteComptable, vLibelle, vDebit, vCredit);

        Assert.assertEquals(
                "vLibelle",
                vLibelle,
                ligneEcritureComptable.getLibelle()
        );
        Assert.assertEquals(
                "vDebit",
                vDebit,
                ligneEcritureComptable.getDebit()
        );
        Assert.assertEquals(
                "vCredit",
                vCredit,
                ligneEcritureComptable.getCredit()
        );
        Assert.assertEquals(
                "vLibelleCompteComptable",
                vLibelleCompteComptable,
                ligneEcritureComptable.getCompteComptable().getLibelle()
        );
        Assert.assertEquals(
                "vNumCompteComptable",
                vNumCompteComptable,
                ligneEcritureComptable.getCompteComptable().getNumero()
        );

    }
}
