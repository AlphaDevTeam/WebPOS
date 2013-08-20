
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Properties;
import com.AlphaDevs.Web.SessionBean.PropertiesController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
public class PropertiesHandler {
    @EJB
    private PropertiesController propertiesController;
    
    private Properties current;
    
   
    public PropertiesHandler() {
        if(current == null){
            current = new Properties();
        }
    }
}
