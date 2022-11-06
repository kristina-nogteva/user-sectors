package com.kristina.user.sectors.client;

import com.kristina.user.sectors.repository.SectorRepository;
import org.apache.myfaces.orchestra.conversation.annotations.ConversationName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
@Scope("conversation.access")
@ConversationName("userSectorsBackingBean")
public class UserSectorsBackingBean implements Serializable {

    @Autowired
    private SectorRepository sectorRepository;

    private List<SelectItem> sectorsMenuItems;

    @PostConstruct
    public void init(){
        initializeSectorsMenuItems();
    }

    @Transactional
    public void save(){
        //TODO
    }

    private void initializeSectorsMenuItems(){
        sectorsMenuItems = StreamSupport.stream(sectorRepository.findAll().spliterator(), false)
                .map(sector -> new SelectItem(sector, sector.getDescription()))
                .collect(toList());
    }

    public List<SelectItem> getSectorsMenuItems() {
        return sectorsMenuItems;
    }
}
