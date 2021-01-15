package com.dummy.myerp.consumer.dao.impl.cache;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;



@RunWith(PowerMockRunner.class)
@PrepareForTest({ConsumerHelper.class})
public class CompteComptableDaoCacheTest {

    @Mock
    DaoProxy daoProxy;

    @Mock
    ComptabiliteDao comptabiliteDao;

    @InjectMocks
    CompteComptableDaoCache compteComptableDaoCache;

    @Test
    public void testCompteComptableDaoCacheGetByNumero(){
        PowerMockito.mockStatic(ConsumerHelper.class);
        when(ConsumerHelper.getDaoProxy()).thenReturn(daoProxy);
        when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);

        PowerMockito.spy(compteComptableDaoCache);
        int i = 0;
        int nbAppel = 5;
        while(i<nbAppel) {
            compteComptableDaoCache.getByNumero(i);
            i+=1;
            }

        verify(comptabiliteDao, Mockito.times(1)).getListCompteComptable();
        assertEquals(
                "Le nombre d'appel au cache du CompteComptable ne correspond pas \n",
                5,
                i
        );
    }


}
