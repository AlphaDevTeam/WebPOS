

package com.AlphaDevs.Web.Convertors;

import com.AlphaDevs.Web.Entities.Supplier;
import com.AlphaDevs.Web.SessionBean.SupplierController;
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
public class SupplierConvertor implements Converter
{
    @EJB
    private SupplierController supplierController;

    
    /** Creates a new instance of SupplierConvertor */
    public SupplierConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        return supplierController.find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        return ((Supplier) value).getId().toString();
    }

}
