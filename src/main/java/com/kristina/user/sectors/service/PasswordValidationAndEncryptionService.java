package com.kristina.user.sectors.service;

import com.kristina.user.sectors.model.User;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public interface PasswordValidationAndEncryptionService {

    void validatePassword(String password);

    void encryptPassword(User user) throws NoSuchAlgorithmException, NoSuchProviderException;

    boolean verifyPassword(User user, String password);
}
