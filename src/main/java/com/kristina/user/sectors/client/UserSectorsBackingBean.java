package com.kristina.user.sectors.client;

import org.apache.myfaces.orchestra.conversation.annotations.ConversationName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("conversation.access")
@ConversationName("editActivityBackingBean")
public class UserSectorsBackingBean {

    private List<SelectItem> sectorsMenuItems;

    @PostConstruct
    private void init(){
        initializeSectorsMenuItems();
    }

    @Transactional
    public void save(){

    }

    private void initializeSectorsMenuItems(){
        sectorsMenuItems = new ArrayList<>();
        // TODO initialize
    }

    public List<SelectItem> getSectorsMenuItems() {
        return sectorsMenuItems;
    }
}
