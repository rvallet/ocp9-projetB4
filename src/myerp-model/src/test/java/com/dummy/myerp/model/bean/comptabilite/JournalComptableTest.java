package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JournalComptableTest {

    // ==================== Méthodes utilisées pour les tests ====================
    private List<JournalComptable> getJournalComptableList(){
        List<JournalComptable> vJournalComptableList = Arrays.asList(
                new JournalComptable("A", "jcTest N°1"),
                new JournalComptable("BC", "jcTest N°2"),
                new JournalComptable("DEF", "jcTest N°3"),
                new JournalComptable("GHIJ", "jcTest N°4"),
                new JournalComptable("KLMNO", "jcTest N°5")
        );
        return vJournalComptableList;
    }
    // ==================== Tests ====================
    @Test
    public void getJournalComptableByCodeWhenExist(){
        List<JournalComptable> vJournalComptableList = getJournalComptableList();
        Assert.assertEquals(
                "Le journal comptable trouvé ne correspond pas à celui attendu \n",
                vJournalComptableList.get(3),
                JournalComptable.getByCode(vJournalComptableList, "GHIJ")
        );
    }

    @Test
    public void getJournalComptableByCodeWhenNotExist(){
        List<JournalComptable> vJournalComptableList = getJournalComptableList();
        Assert.assertNull(
                "Le journal comptable trouvé ne correspond pas à celui attendu \n",
                JournalComptable.getByCode(vJournalComptableList, "XYZ")
        );

    }
}
