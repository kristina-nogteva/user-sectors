package com.kristina.user.sectors.client;

import org.apache.myfaces.orchestra.conversation.ConversationUtils;
import org.apache.myfaces.orchestra.conversation.annotations.ConversationName;
import org.apache.myfaces.shared.util.MessageUtils;
import org.primefaces.application.exceptionhandler.ExceptionInfo;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.*;

public class ViewBase implements Serializable {

    protected String formId;

    protected void leaveModule(final Class<? extends ViewBase> clazz){
        final ConversationName conversationName = clazz.getAnnotation(ConversationName.class);
        ConversationUtils.invalidateIfExists(conversationName.value());
    }

    public boolean validate(Object... objects) {

        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        Arrays.stream(objects).forEach(object -> violations.addAll(Validation.buildDefaultValidatorFactory().getValidator().validate(object, Default.class)));

        if (!violations.isEmpty()) {
            StringBuilder messages = new StringBuilder();
            violations.stream()
                    .forEach(violation -> {
                        messages.append(violation.getMessage());
                        messages.append("<br>");
                    });

            throw new ValidationException(messages.toString());
        }
        return true;
    }
}
