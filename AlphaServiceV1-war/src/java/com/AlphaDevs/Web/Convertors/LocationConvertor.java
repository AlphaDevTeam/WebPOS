
package com.AlphaDevs.Web.Convertors;

import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.SessionBean.LocationController;
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
public class LocationConvertor implements Converter
{
    @EJB
    private LocationController locationController;
    

    /** Creates a new instance of LocationConvertor */
    public LocationConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) 
    {
        return locationController.find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) 
    {
        return ((Location) value).getId().toString();
    }

}
