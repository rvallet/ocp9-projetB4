package com.dummy.myerp.technical.util.spring;

import org.junit.Assert;
import org.junit.Test;

public class NullFactoryBeanTest {

    @Test
    public void getNullBean() {
        class NullBeanClass{}
        NullFactoryBean<NullBeanClass> nullBeanClass = new NullFactoryBean<>(NullBeanClass.class);

        try {
            Assert.assertNull(nullBeanClass.getObject());
        } catch (Exception e) {
            Assert.fail("Object Echec :\n" +e);
        }

        Assert.assertNotNull("ObjectType", nullBeanClass.getObjectType());
        Assert.assertFalse("Singleton", nullBeanClass.isSingleton());

    }
}
