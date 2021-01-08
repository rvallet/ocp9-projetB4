package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CompteComptableTest {

    // ==================== Méthodes utilisées pour les tests ====================
    private List<CompteComptable> getCompteComptableList(){
        List<CompteComptable> vCompteComptableList = Arrays.asList(
                new CompteComptable(0, "ccTest N°1"),
                new CompteComptable(1, "ccTest N°2"),
                new CompteComptable(2, "ccTest N°3"),
                new CompteComptable(3, "ccTest N°4")
        );
        return vCompteComptableList;
    }

    // ==================== Tests ====================
    @Test
    public void getCompteComptableByNumeroWhenExist(){
        List<CompteComptable> vCompteComptableList = getCompteComptableList();
        Assert.assertEquals(
                "Le compte comptable trouvé ne correspond pas à celui attendu \n",
                vCompteComptableList.get(2),
                CompteComptable.getByNumero(vCompteComptableList, 2)
        );
    }

    @Test
    public void getCompteComptableByNumeroWhenNotExist(){
        List<CompteComptable> vCompteComptableList = getCompteComptableList();
        Assert.assertNull(
                "Le compte comptable trouvé ne correspond pas à celui attendu \n",
                CompteComptable.getByNumero(vCompteComptableList,6)
        );

    }
}
