
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Enums.PropertieType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Karunarathne
 */
@ManagedBean
@ViewScoped
public class PropertieTypesHandler {

    /**
     * Creates a new instance of PropertieTypesHandler
     */
    public PropertieTypesHandler() {
    }
    
    public PropertieType[] getPropertieTypes(){
        return PropertieType.values();
    }
    
}
