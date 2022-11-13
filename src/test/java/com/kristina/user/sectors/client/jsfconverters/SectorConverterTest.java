package com.kristina.user.sectors.client.jsfconverters;

import com.kristina.user.sectors.model.Sector;
import com.kristina.user.sectors.model.UserSector;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class SectorConverterTest {

    @Test
    public void getAsString(){
        SectorConverter converter = new SectorConverter();
        Sector sector = new Sector();
        ReflectionTestUtils.setField(sector, "sectorId", 5);
        Assert.assertEquals("5", converter.getAsString(null, null, sector));
    }

    @Test
    public void getAsStringNullValue(){
        SectorConverter converter = new SectorConverter();
        Sector sector = new Sector();
        ReflectionTestUtils.setField(sector, "sectorId", null);
        Assert.assertEquals(null, converter.getAsString(null, null, sector));
    }

    @Test
    public void getAsStringAlreadyString(){
        SectorConverter converter = new SectorConverter();
        Assert.assertEquals("5", converter.getAsString(null, null, "5"));
    }

    @Test
    public void getAsStringNotASector(){
        SectorConverter converter = new SectorConverter();
        UserSector userSector = new UserSector();
        Assert.assertNull(converter.getAsString(null, null, userSector));
    }
}
