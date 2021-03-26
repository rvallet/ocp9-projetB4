package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

public class SequenceEcritureComptableTest {

    @Test
    public void SequenceEcritureComptable() {
        String pJournalCode = "pJournalCode";
        Integer pAnnee = 2021;
        Integer pDerniereValeur = 1;
        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(pJournalCode, pAnnee, pDerniereValeur);

        Assert.assertEquals(
                "pJournalCode",
                pJournalCode,
                sequenceEcritureComptable.getJournalCode()
        );
        Assert.assertEquals(
                "pAnnee",
                pAnnee,
                sequenceEcritureComptable.getAnnee()
        );
        Assert.assertEquals(
                "pDerniereValeur",
                pDerniereValeur,
                sequenceEcritureComptable.getDerniereValeur()
        );
    }
}
