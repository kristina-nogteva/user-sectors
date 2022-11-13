package com.kristina.user.sectors.client.model;

import com.kristina.user.sectors.model.Sector;
import com.kristina.user.sectors.model.User;
import com.kristina.user.sectors.model.UserSector;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

public class UserTest {

    @Test
    public void testIsUsernameValid(){
        User user = new User();
        user.setUsername("TestUser123");
        Assert.assertTrue(user.isUsernameValid());
    }

    @Test
    public void isInvalidUsernameSpace(){
        User user = new User();
        user.setUsername("Test User123");
        Assert.assertFalse(user.isUsernameValid());
    }

    @Test
    public void isInvalidUsernameCharacter(){
        User user = new User();
        user.setUsername("TestUser123*");
        Assert.assertFalse(user.isUsernameValid());
    }

    @Test
    public void testFullNameValid(){
        User user = new User();
        user.setFullName("Kristina Nogteva");
        Assert.assertTrue(user.isFullNameValid());
    }

    @Test
    public void testInvalidFullNameNumbers(){
        User user = new User();
        user.setFullName("Kristina Nogteva7");
        Assert.assertFalse(user.isFullNameValid());
    }

    @Test
    public void testInvalidFullNameCharacters(){
        User user = new User();
        user.setFullName("Kristina Nogteva&");
        Assert.assertFalse(user.isFullNameValid());
    }

    @Test
    public void testSetSectors(){
        User user = new User();
        Assert.assertTrue(user.getSectors().isEmpty());
        List<UserSector> userSectorsInBeginning = (List<UserSector>) ReflectionTestUtils.getField(user, "userSectors");
        Assert.assertTrue(userSectorsInBeginning.isEmpty());


        List<Sector> sectorList = new ArrayList<>();
        sectorList.add(new Sector());
        user.setSectors(sectorList);

        List<UserSector> userSectorsInTheEnd = (List<UserSector>) ReflectionTestUtils.getField(user, "userSectors");
        Assert.assertFalse(user.getSectors().isEmpty());
        Assert.assertFalse(userSectorsInTheEnd.isEmpty());
    }
}
