package com.kristina.user.sectors.service.impl;

import com.kristina.user.sectors.model.User;
import com.kristina.user.sectors.service.PasswordValidationAndEncryptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

@Service
public class PasswordValidationAndEncryptionServiceImpl implements PasswordValidationAndEncryptionService {

    @Override
    public void validatePassword(String password) {
        final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-z-Z\\d\\w\\W]{12,}$";
        if (!password.matches(passwordRegex)) throw new ValidationException("Password must be at least 12 characters long and must contain at least one lowercase letter, one uppercase letter and one number");
    }

    @Override
    public void encryptPassword(User user) throws NoSuchAlgorithmException, NoSuchProviderException{
        String salt = getSalt();
        user.setPassword(getSecurePassword(user.getPassword(), salt));
        user.setSalt(salt);
    }

    @Override
    public boolean verifyPassword(User user, String password) {
        String generatedPassword = getSecurePassword(password, user.getSalt());
        return StringUtils.equals(generatedPassword, user.getPassword());
    }

    // Found algorithm on https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
    private static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }

    // Found algorithm on https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
    private static String getSecurePassword(String passwordToHash,
                                            String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }

            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
