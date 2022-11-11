package com.kristina.user.sectors.service.impl;

import com.kristina.user.sectors.exception.UnauthorizedException;
import com.kristina.user.sectors.model.User;
import com.kristina.user.sectors.service.SessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public void createAndStoreUserSessionId(User user) throws UnsupportedEncodingException {
        String sessionId = UUID.randomUUID().toString();
        createCookie(sessionId);
        user.setSessionId(sessionId);
    }

    @Override
    public void validateUserSessionId(User user) throws UnauthorizedException, UnsupportedEncodingException {
        Cookie cookie = (Cookie) FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get("user-session");
        String sessionId = URLDecoder.decode(cookie.getValue(), "UTF-8");
        if (!sessionId.equals(user.getSessionId())) throw new UnauthorizedException();
    }

    @Override
    public void invalidateUserSession(User user){
        user.setSessionId(null);
        FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().put("user-session", null);
    }

    private void createCookie(String sessionId) throws UnsupportedEncodingException{
        Map<String, Object> properties = new HashMap<>();
        properties.put("maxAge", 31536000);
        properties.put("path", "/");
        FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("user-session", URLEncoder.encode(sessionId, "UTF-8"), properties);
    }
}
