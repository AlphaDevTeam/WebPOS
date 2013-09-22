
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Company;
import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.Entities.SystemNumbers;
import com.AlphaDevs.Web.Entities.Systems;
import com.AlphaDevs.Web.SessionBean.SystemNumbersController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@RequestScoped
public class SystemNumbersHandler extends SuperHandler{
    
    @EJB
    private SystemNumbersController systemNumbersController;
    
    private SystemNumbers current;
    
    public SystemNumbersHandler() {
        if(current == null ){
            current = new SystemNumbers();
        }
    }

    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public SystemNumbers getCurrent() {
        return current;
    }

    public void setCurrent(SystemNumbers current) {
        this.current = current;
    }
    
    public List<SystemNumbers> getList(){
        return systemNumbersController.findAll();
    }
    
    public String createSystemNumber(){
        getSystemNumbersController().create(current);
        return "Home";
    }
    
    public List<SystemNumbers> getSpecific(Location relatedLocation , Company relatedCompany){
        return getSystemNumbersController().findSpecific(relatedCompany, relatedLocation);
    }
    
}
