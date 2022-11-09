package com.kristina.user.sectors.client;

import com.kristina.user.sectors.model.Sector;
import com.kristina.user.sectors.model.User;
import com.kristina.user.sectors.repository.SectorRepository;
import com.kristina.user.sectors.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.myfaces.orchestra.viewController.annotations.InitView;
import org.apache.myfaces.orchestra.viewController.annotations.ViewController;
import org.apache.myfaces.shared.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
@Scope("conversation.access")
@ViewController(viewIds = "/register.xhtml")
public class UserSectorsBackingBean extends ViewBase {

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private UserRepository userRepository;

    @Valid
    private User user;
    boolean savedOk = false;

    @AssertTrue(message = "Please agree to terms")
    boolean agreeToTerms = false;
    private List<SelectItem> sectorsMenuItems;

    @InitView
    public void init(){
        formId = "user-sectors-form";
        if (sectorsMenuItems == null) initializeSectorsMenuItems();
        //String username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        if (user == null) {
            createNewUser();
        }
    }

    @Transactional
    public void save(){
        validate(this);
        userRepository.save(user);
        savedOk = true;
        MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "User registered",null);
    }

    public void cancel() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("login.xhtml");
    }

    private void initializeSectorsMenuItems(){
        sectorsMenuItems = StreamSupport.stream(sectorRepository.getAllGeneralSectors().spliterator(), false)
                .filter(sector -> sector.getParentSector() == null)
                .sorted(new SectorDescriptionComparator())
                .map(generalSector -> {
                    SelectItemGroup sectorGroup =  new SelectItemGroup(generalSector.getDescription());
                    List<SelectItem> sectorGroupSelectItems = collectAllSubSectorSelectItems(generalSector,"");
                    sectorGroup.setSelectItems(sectorGroupSelectItems.toArray(new SelectItem[sectorGroupSelectItems.size()]));
                    return sectorGroup;
                })
                .collect(toList());
    }

    private List<SelectItem> collectAllSubSectorSelectItems(Sector sector, String levelSymbol){
        List<SelectItem> selectItems = new ArrayList<>();

        // Recursive algorithm that gets select items for all sub categories in sorted order.
        List<Sector> sortedSubCategories =  sector.getSubCategories().stream()
                .sorted(new SectorDescriptionComparator()).collect(toList());

        for (Sector subSector : sortedSubCategories){
            selectItems.add(new SelectItem(subSector, levelSymbol + " " + subSector.getDescription()));
            if (!subSector.getSubCategories().isEmpty()){
                selectItems.addAll(collectAllSubSectorSelectItems(subSector, levelSymbol + "---"));
            }
        }
        return selectItems;
    }

    private class SectorDescriptionComparator implements Comparator<Sector> {
        @Override
        public int compare(Sector sector1, Sector sector2) {
            if (StringUtils.startsWithIgnoreCase(sector1.getDescription(),"other")) return 1;
            else if (StringUtils.startsWithIgnoreCase(sector2.getDescription(), "other")) return -1;
            else return sector1.getDescription().compareTo(sector2.getDescription());
        }
    }

    private void createNewUser(){
        user = new User();
    }

    public List<SelectItem> getSectorsMenuItems() {
        return sectorsMenuItems;
    }

    public User getUser() {
        return user;
    }

    public boolean isSavedOk() {
        return savedOk;
    }

    public boolean isAgreeToTerms() {
        return agreeToTerms;
    }

    public void setAgreeToTerms(boolean agreeToTerms) {
        this.agreeToTerms = agreeToTerms;
    }

    public String getFormId(){
        return formId;
    }
}
