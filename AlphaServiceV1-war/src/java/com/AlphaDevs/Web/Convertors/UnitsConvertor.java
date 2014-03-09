
package com.AlphaDevs.Web.Convertors;

import com.AlphaDevs.Web.Entities.Units;
import com.AlphaDevs.Web.SessionBean.UnitsController;
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
public class UnitsConvertor implements Converter {
    @EJB
    private UnitsController unitsController;
    
    public UnitsConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return unitsController.find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null || "null".equals(value.toString())){
            return "";
        }else{
            return ((Units) value).getId().toString();
        }
        
    }
}
