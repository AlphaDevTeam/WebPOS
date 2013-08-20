
package com.AlphaDevs.Web.Convertors;

import com.AlphaDevs.Web.Enums.Document;
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
public class DocumentEnumConvertor implements Converter {
    
    public DocumentEnumConvertor() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return Document.valueOf(value).getFieldName();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Document)value).getFieldName();
    }
}
