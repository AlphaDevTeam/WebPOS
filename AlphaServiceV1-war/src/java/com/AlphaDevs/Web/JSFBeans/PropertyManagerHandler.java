
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.PropertyManager;
import com.AlphaDevs.Web.SessionBean.PropertyManagerController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
@SessionScoped
public class PropertyManagerHandler {
    
    @EJB
    private PropertyManagerController propertyManagerController;

    private PropertyManager current;
    
    public PropertyManagerHandler() {
        if(current == null){
            current = new PropertyManager();
        }
    }
    public List<PropertyManager> getAll(){
        return propertyManagerController.findAll();
    }

    public PropertyManager getCurrent() {
        return current;
    }

    public void setCurrent(PropertyManager current) {
        this.current = current;
    }
    
    public String saveProperties(){
        System.out.println("Saving.");
        propertyManagerController.create(current);
        return "Home";
    }
    
    public String getPropertyManagerHandlerStatus(){
        if(propertyManagerController != null){
            return "Active";
        }else{
            return "Inactive";
        }
        
    }
    
    
}
