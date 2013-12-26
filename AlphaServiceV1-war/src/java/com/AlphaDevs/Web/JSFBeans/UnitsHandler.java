
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Units;
import com.AlphaDevs.Web.SessionBean.UnitsController;
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
public class UnitsHandler {
    @EJB
    private UnitsController unitsController;
    
    private Units current;
    

    public UnitsHandler() {
        if(current == null){
            current = new Units();
        }
    }

    public UnitsController getUnitsController() {
        return unitsController;
    }

    public void setUnitsController(UnitsController unitsController) {
        this.unitsController = unitsController;
    }

    public Units getCurrent() {
        return current;
    }

    public void setCurrent(Units current) {
        this.current = current;
    }
    
    public List<Units> getList(){
        return unitsController.findAll();
    }
    
}
