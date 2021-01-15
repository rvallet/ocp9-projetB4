package com.dummy.myerp.consumer.dao.impl.cache;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;



@ExtendWith(MockitoExtension.class)
public class CompteComptableDaoCacheTest {

    @Mock
    DaoProxy daoProxy;

    @Mock
    ComptabiliteDao comptabiliteDao;

    @InjectMocks
    CompteComptableDaoCache compteComptableDaoCache;

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testgetByNumero(){
    when(daoProxy.getComptabiliteDao()).thenReturn(daoProxy);
    }


}
