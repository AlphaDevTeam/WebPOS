
package com.AlphaDevs.Web.Convertors;

import com.AlphaDevs.Web.Entities.Design;
import com.AlphaDevs.Web.SessionBean.DesignController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@RequestScoped
public class DesignConvertor implements Converter
{
    @EJB
    private DesignController designController;
    
    

    /** Creates a new instance of DesignConvertor */
    public DesignConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.isEmpty()) {
            return null;
        } else {
            return designController.find(Long.valueOf(value));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        if(value == null || "null".equals(value.toString())){
            return "";
        }else{
            return ((Design)value).getId().toString();
        }
        
    }

}
