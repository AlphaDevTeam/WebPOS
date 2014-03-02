
package com.AlphaDevs.Web.Convertors;

import com.AlphaDevs.Web.Entities.BankAccounts;
import com.AlphaDevs.Web.SessionBean.BankAccountsController;
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
public class BankAccountConvertor implements Converter {
    @EJB
    private BankAccountsController bankAccountsController;

    
    
    public BankAccountConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return bankAccountsController.find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((BankAccounts)value).getId().toString();
    }

}
