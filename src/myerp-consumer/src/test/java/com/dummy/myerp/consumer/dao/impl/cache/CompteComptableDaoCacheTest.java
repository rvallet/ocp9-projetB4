package com.dummy.myerp.consumer.dao.impl.cache;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
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

/*    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }*/

    @Test
    public void testgetByNumero(){
    mockStatic(ConsumerHelper.class);
    when(ConsumerHelper.getDaoProxy()).thenReturn(daoProxy);
    when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
    
    }


}
