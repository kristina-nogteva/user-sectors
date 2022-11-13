package com.kristina.user.sectors.client.service;

import com.kristina.user.sectors.model.User;
import com.kristina.user.sectors.service.impl.PasswordValidationAndEncryptionServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import javax.validation.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class PasswordValidationAndEncryptionServiceTest {

    @Test
    public void testValidatePassword(){
        PasswordValidationAndEncryptionServiceImpl service = new PasswordValidationAndEncryptionServiceImpl();
        service.validatePassword("TestPasswor1");
    }

    @Test
    public void testValidatePasswordWithSymbols(){
        PasswordValidationAndEncryptionServiceImpl service = new PasswordValidationAndEncryptionServiceImpl();
        service.validatePassword("TestPassword123*");
    }

    @Test(expected = ValidationException.class)
    public void testInvalidPasswordTooShort(){
        PasswordValidationAndEncryptionServiceImpl service = new PasswordValidationAndEncryptionServiceImpl();
        service.validatePassword("TestPasswo1");
    }

    @Test(expected = ValidationException.class)
    public void testInvalidPasswordNoUppercaseLetters(){
        PasswordValidationAndEncryptionServiceImpl service = new PasswordValidationAndEncryptionServiceImpl();
        service.validatePassword("testpassword123");
    }

    @Test(expected = ValidationException.class)
    public void testInvalidPasswordNoNumbers(){
        PasswordValidationAndEncryptionServiceImpl service = new PasswordValidationAndEncryptionServiceImpl();
        service.validatePassword("TestPassword");
    }

    @Test(expected = ValidationException.class)
    public void testInvalidPasswordNoLowercaseLetters(){
        PasswordValidationAndEncryptionServiceImpl service = new PasswordValidationAndEncryptionServiceImpl();
        service.validatePassword("TESTPASSWORD123");
    }

    @Test()
    public void testEncryptPassword() throws NoSuchAlgorithmException, NoSuchProviderException {
        User user = new User();
        String password = "TestPassword123";
        user.setPassword(password);
        Assert.assertEquals(password, user.getPassword());
        Assert.assertNull(user.getSalt());

        PasswordValidationAndEncryptionServiceImpl service = new PasswordValidationAndEncryptionServiceImpl();
        service.encryptPassword(user);

        Assert.assertNotEquals(password, user.getPassword());
        Assert.assertNotNull(user.getPassword());
        Assert.assertNotNull(user.getSalt());
        Assert.assertNotEquals(0, user.getPassword().length());
        Assert.assertNotEquals(0, user.getSalt().length());
    }

    @Test()
    public void testVerifyPassword() throws NoSuchAlgorithmException, NoSuchProviderException {
        User user = new User();
        String password = "TestPassword123";
        user.setPassword(password);

        PasswordValidationAndEncryptionServiceImpl service = new PasswordValidationAndEncryptionServiceImpl();
        service.encryptPassword(user);
        Assert.assertTrue(service.verifyPassword(user, password));
    }

    @Test()
    public void testVerifyInvalidPassword() throws NoSuchAlgorithmException, NoSuchProviderException {
        User user = new User();
        String password = "TestPassword123";
        user.setPassword(password);

        PasswordValidationAndEncryptionServiceImpl service = new PasswordValidationAndEncryptionServiceImpl();
        service.encryptPassword(user);
        Assert.assertFalse(service.verifyPassword(user, "TestPassword321"));
    }
}
