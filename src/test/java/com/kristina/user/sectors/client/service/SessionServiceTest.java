package com.kristina.user.sectors.client.service;

import com.kristina.user.sectors.exception.UnauthorizedException;
import com.kristina.user.sectors.model.User;
import com.kristina.user.sectors.service.impl.SessionServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SessionServiceTest {

    private final String mockSessionId = "12345678";

    private SessionServiceImpl service;

    @Before
    public void setUp() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> cookieMap = new HashMap<>();
        cookieMap.put("user-session", new Cookie("user-session", mockSessionId));
        ExternalContext externalContext = Mockito.mock(ExternalContext.class);
        FacesContext facesContext = Mockito.mock(FacesContext.class);
        Mockito.when(facesContext.getExternalContext()).thenReturn(externalContext);
        Mockito.when(externalContext.getRequestCookieMap()).thenReturn(cookieMap);

        Method setter = FacesContext.class.getDeclaredMethod("setCurrentInstance", new Class[]{FacesContext.class});
        setter.setAccessible(true);
        setter.invoke(null, new Object[]{facesContext});

        service = new SessionServiceImpl();
    }

    @Test
    public void testCreateAndStoreSessionId() throws UnsupportedEncodingException {
        User user = new User();
        Assert.assertNull(user.getSessionId());

        service.createAndStoreUserSessionId(user);
        Assert.assertNotNull(user.getSessionId());
        Assert.assertNotEquals(0,user.getSessionId().length());
    }

    @Test
    public void testValidateUserSessionId() throws UnsupportedEncodingException, UnauthorizedException {
        User user = new User();
        user.setSessionId(mockSessionId);
        service.validateUserSessionId(user);
    }

    @Test(expected = UnauthorizedException.class)
    public void testValidateUserSessionIdInvalidId() throws UnsupportedEncodingException, UnauthorizedException {
        User user = new User();
        user.setSessionId("InvalidId");
        service.validateUserSessionId(user);
    }

    @Test
    public void testInvalidateUserSession() throws UnsupportedEncodingException, UnauthorizedException {
        User user = new User();
        user.setSessionId(mockSessionId);
        Assert.assertEquals(mockSessionId, user.getSessionId());
        service.invalidateUserSession(user);
        Assert.assertNull(user.getSessionId());
    }
}
