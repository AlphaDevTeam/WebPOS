

package com.AlphaDevs.Web.Convertors;

import com.AlphaDevs.Web.Entities.Items;
import com.AlphaDevs.Web.SessionBean.ItemsController;
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
public class ItemConvertor implements Converter
{
    @EJB
    private ItemsController itemsController;
    
    
    public ItemConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        if(value.isEmpty()){
            return null;
        }else{
            return itemsController.find(Long.valueOf(value));
        }
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        if(value == null || "null".equals(value.toString())){
            System.out.println("Null");
            return "";
        }else{
            System.out.println("Not Null" + value.toString());
            return ((Items) value).getId().toString();
        }
        
    }
    
    
}
