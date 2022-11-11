package com.kristina.user.sectors.client;

import org.apache.myfaces.orchestra.conversation.ConversationUtils;
import org.apache.myfaces.orchestra.conversation.annotations.ConversationName;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.*;

public class ViewBase implements Serializable {

    protected String formId;

    public void validate(Object... objects) {

        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        Arrays.stream(objects).forEach(object -> violations.addAll(Validation.buildDefaultValidatorFactory().getValidator().validate(object, Default.class)));

        if (!violations.isEmpty()) {
            StringBuilder messages = new StringBuilder();
            violations.stream().forEach(violation -> {
                messages.append(violation.getMessage());
                messages.append("<br>");
            });
            throw new ValidationException(messages.toString());
        }
    }

    public String getFormId() {
        return formId;
    }
}
