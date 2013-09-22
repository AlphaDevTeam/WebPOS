
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Systems;
import com.AlphaDevs.Web.SessionBean.SystemsController;
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
public class SystemsHandler extends SuperHandler{
    
    @EJB
    private SystemsController systemsController;
    
    private Systems current;
    
    public SystemsHandler() {
        if(current == null){
            current = new Systems();
        }
    }

    public SystemsController getSystemsController() {
        return systemsController;
    }

    public void setSystemsController(SystemsController systemsController) {
        this.systemsController = systemsController;
    }

    public Systems getCurrent() {
        return current;
    }

    public void setCurrent(Systems current) {
        this.current = current;
    }
    
    public List<Systems> getList(){
        return getSystemsController().findAll();
    }
    
    public String createSystem(){
        getSystemsController().create(current);
        return "Home";
    }
}
