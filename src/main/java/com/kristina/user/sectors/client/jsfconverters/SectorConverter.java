package com.kristina.user.sectors.client.jsfconverters;

import com.kristina.user.sectors.config.ApplicationContextProvider;
import com.kristina.user.sectors.model.Sector;
import com.kristina.user.sectors.repository.SectorRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@Component
@Scope("conversation.access")
@FacesConverter(forClass = Sector.class)
public class SectorConverter implements Converter {

    @Autowired
    private SectorRepository sectorRepository;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) throws ConverterException {

        if (!StringUtils.isEmpty(value)) {
            if (sectorRepository == null) {
                sectorRepository = ApplicationContextProvider.getApplicationContext().getBean(SectorRepository.class);
            }
            return sectorRepository.findById(Integer.parseInt(ConverterUtils.extractValue("id", value))).get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) throws ConverterException {

        if (object != null && object instanceof Sector) {
            return ((Sector)object).getId().toString();
        } else if (object != null && object instanceof String) {
            return object.toString();
        }
        return null;
    }
}
