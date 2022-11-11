package com.kristina.user.sectors.service;

import com.kristina.user.sectors.exception.UnauthorizedException;
import com.kristina.user.sectors.model.User;

import java.io.UnsupportedEncodingException;

public interface SessionService {

   void createAndStoreUserSessionId(User user) throws UnsupportedEncodingException;

   void validateUserSessionId(User user) throws UnsupportedEncodingException, UnauthorizedException;

   void invalidateUserSession(User user) throws UnsupportedEncodingException;
}
