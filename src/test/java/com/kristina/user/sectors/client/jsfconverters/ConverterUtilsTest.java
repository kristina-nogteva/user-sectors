package com.kristina.user.sectors.client.jsfconverters;

import org.junit.Assert;
import org.junit.Test;

public class ConverterUtilsTest {

    @Test
    public void testExtractValue(){
        String id = ConverterUtils.extractValue("id", "Sector@1447499999[id=5]");
        Assert.assertEquals("5",id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExtractValueInvalidField(){
        String id = ConverterUtils.extractValue("name", "Sector@1447499999[id=5]");
    }

    @Test
    public void testExtractNullValue(){
        String id = ConverterUtils.extractValue("id", "Sector@1447499999[id=null]");
        Assert.assertNull(id);
    }
}
