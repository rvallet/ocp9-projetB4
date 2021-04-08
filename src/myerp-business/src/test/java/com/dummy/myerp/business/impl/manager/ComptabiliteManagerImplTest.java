package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;

public class ComptabiliteManagerImplTest {

    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();

    private EcritureComptable getEcritureComptable(){
        EcritureComptable vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AB", "AchatBonus_JC"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021,1,1);
        vEcritureComptable.setDate(calendar.getTime());
        vEcritureComptable.setLibelle("Libelle_EC");
        vEcritureComptable.setReference("AB-2021/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
                new CompteComptable(1),
                null,
                new BigDecimal(123),
                null)
        );
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
                new CompteComptable(2),
                null,
                null,
                new BigDecimal(123))
        );
        return vEcritureComptable;
    }
    @Test
    public void checkEcritureComptableUnit() throws Exception {
        EcritureComptable vEcritureComptable = getEcritureComptable();
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitViolation() throws Exception {
        EcritureComptable vEcritureComptable = new EcritureComptable();
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG2() throws Exception {
        EcritureComptable vEcritureComptable = getEcritureComptable();
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
                new CompteComptable(3),
                null,
                null,
                new BigDecimal(123))
        );
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG3() throws Exception {
        EcritureComptable vEcritureComptable = getEcritureComptable();
        vEcritureComptable.getListLigneEcriture().removeAll(vEcritureComptable.getListLigneEcriture());
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
                new CompteComptable(1),
                null,
                new BigDecimal(123),
                null)
        );
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG5Code() throws Exception {
        EcritureComptable vEcritureComptable = getEcritureComptable();
        vEcritureComptable.setReference("CD-2021/00001");
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG5Year() throws Exception {
        EcritureComptable vEcritureComptable = getEcritureComptable();
        vEcritureComptable.setReference("AB-2022/00001");
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

}
