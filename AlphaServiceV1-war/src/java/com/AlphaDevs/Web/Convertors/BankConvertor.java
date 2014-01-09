
package com.AlphaDevs.Web.Convertors;

import com.AlphaDevs.Web.Entities.Bank;
import com.AlphaDevs.Web.SessionBean.BankController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@ManagedBean
@RequestScoped
public class BankConvertor implements Converter {
    @EJB
    private BankController bankController;

    public BankConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return bankController.find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Bank)value).getId().toString();
    }

}
