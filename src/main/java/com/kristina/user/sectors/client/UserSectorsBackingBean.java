package com.kristina.user.sectors.client;

import com.kristina.user.sectors.model.Sector;
import com.kristina.user.sectors.model.User;
import com.kristina.user.sectors.repository.SectorRepository;
import com.kristina.user.sectors.repository.UserRepository;
import com.kristina.user.sectors.service.PasswordValidationAndEncryptionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.myfaces.orchestra.viewController.annotations.InitView;
import org.apache.myfaces.orchestra.viewController.annotations.ViewController;
import org.apache.myfaces.shared.util.MessageUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
@Scope("conversation.access")
@ViewController(viewIds = "/user-sectors.xhtml")
public class UserSectorsBackingBean extends ViewBase {

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordValidationAndEncryptionService passwordValidationAndEncryptionService;

    @Valid
    private User user;
    boolean savedOk = false;
    boolean editView = false;

    @AssertTrue(message = "Please agree to terms")
    boolean agreeToTerms = false;
    private List<SelectItem> sectorsMenuItems;

    @InitView
    public void init() {
        formId = "user-sectors-form";
        if ("true".equals(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("init"))){
            savedOk = false;
            if (sectorsMenuItems == null) initializeSectorsMenuItems();
            initializeUser();
            PrimeFaces.current().ajax().update(formId);
        }
    }

    public void edit(){
        savedOk = false;
        PrimeFaces.current().ajax().update(formId);
    }

    @Transactional
    public void save() throws NoSuchAlgorithmException, NoSuchProviderException {
        validate(this);
        if (!editView){
            passwordValidationAndEncryptionService.validatePassword(user.getPassword());
            passwordValidationAndEncryptionService.encryptPassword(user);
            userRepository.save(user);
            MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "User registered",null);
        }else {
            MessageUtils.addMessage(FacesMessage.SEVERITY_INFO, "User data saved",null);
        }
        savedOk = true;

    }

    private void initializeUser(){
        String username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        if (StringUtils.isBlank(username)) {
            createNewUser();
            editView = false;
        } else {
            Optional<User> optionalUser = userRepository.findById(username);
            if (!optionalUser.isPresent()) throw new IllegalArgumentException("User not found!");
            user = optionalUser.get();
            editView = true;
            agreeToTerms = true;
        }
    }

    private void createNewUser(){
        user = new User();
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

    public boolean isEditView() {
        return editView;
    }
}
