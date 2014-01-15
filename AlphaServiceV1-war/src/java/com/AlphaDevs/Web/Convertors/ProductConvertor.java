

package com.AlphaDevs.Web.Convertors;

import com.AlphaDevs.Web.Entities.Product;
import com.AlphaDevs.Web.SessionBean.ProductController;
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
public class ProductConvertor implements Converter
{
    @EJB
    private ProductController productController;
    
    public ProductConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        if(value.isEmpty()){
            System.out.println("Emopty");
            return null;
        }else{
            System.out.println("Not Empty");
            return productController.find(Long.valueOf(value));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        if(value == null){
            return "";
        }else{
            return ((Product)value).getId().toString();
        }
        
    }

}
