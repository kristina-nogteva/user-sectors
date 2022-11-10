package com.kristina.user.sectors.client;

import com.kristina.user.sectors.model.User;
import com.kristina.user.sectors.repository.UserRepository;
import com.kristina.user.sectors.service.PasswordValidationAndEncryptionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.myfaces.orchestra.viewController.annotations.ViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Optional;

@Component
@Scope("conversation.access")
@ViewController(viewIds = "/login.xhtml")
public class LoginBackingBean extends ViewBase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordValidationAndEncryptionService passwordValidationAndEncryptionService;

    private String username;
    private String password;

    public void login() throws IOException{
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent() && passwordValidationAndEncryptionService.verifyPassword(user.get(), password)){
            redirect(username);
            return;
        }
        throw new IllegalArgumentException("Incorrect username or password");
    }

    private void redirect(String username) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        StringBuilder url = new StringBuilder("user-sectors.xhtml?init=true");
        if (StringUtils.isNotBlank(username)) url.append("&username="+username);
        externalContext.redirect(url.toString());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
