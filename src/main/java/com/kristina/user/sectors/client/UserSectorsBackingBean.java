package com.kristina.user.sectors;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

public class UserSectorsBackingBean {

    private List<SelectItem> sectorsMenuItems;

    @PostConstruct
    private void init(){
        initializeSectorsMenuItems();
    }

    private void initializeSectorsMenuItems(){
        sectorsMenuItems = new ArrayList<>();
        // TODO initialize
    }

    public List<SelectItem> getSectorsMenuItems() {
        return sectorsMenuItems;
    }
}
