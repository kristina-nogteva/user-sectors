package com.kristina.user.sectors.client.model;

import com.kristina.user.sectors.model.Sector;
import com.kristina.user.sectors.model.UserSector;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class SectorTest {

    @Test
    public void testToString(){
        Sector sector = new Sector();
        int id = 5;
        ReflectionTestUtils.setField(sector, "sectorId", id);
        Assert.assertEquals( "Sector@"+sector.hashCode()+"[id="+id+"]",sector.toString());
    }

    @Test
    public void testToStringNullValue(){
        Sector sector = new Sector();
        Assert.assertEquals("Sector@"+sector.hashCode()+"[id="+null+"]",sector.toString());
    }

    @Test
    public void testEquals(){
        Sector sector1 = new Sector();
        Sector sector2 = new Sector();

        ReflectionTestUtils.setField(sector1, "sectorId", 1);
        ReflectionTestUtils.setField(sector2, "sectorId", 1);
        Assert.assertTrue(sector1.equals(sector2));
    }

    @Test
    public void testNotEqualsDifferentSectors(){
        Sector sector1 = new Sector();
        Sector sector2 = new Sector();

        ReflectionTestUtils.setField(sector1, "sectorId", 1);
        ReflectionTestUtils.setField(sector2, "sectorId", 2);
        Assert.assertFalse(sector1.equals(sector2));
    }

    @Test
    public void testNotEqualsDifferentObjects(){
        Sector sector1 = new Sector();
        UserSector userSector1 = new UserSector();

        ReflectionTestUtils.setField(sector1, "sectorId", 1);
        ReflectionTestUtils.setField(userSector1, "id", 1);
        Assert.assertFalse(sector1.equals(userSector1));
    }

    @Test
    public void testNotEqualsNullValue(){
        Sector sector1 = new Sector();

        ReflectionTestUtils.setField(sector1, "sectorId", 1);
        Assert.assertFalse(sector1.equals(null));
    }
}
