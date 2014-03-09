
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Items;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Entities.Pump;
import com.AlphaDevs.Web.Entities.Units;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.Helpers.MessageHelper;
import com.AlphaDevs.Web.SessionBean.ItemsController;
import com.AlphaDevs.Web.SessionBean.LoggerController;
import com.AlphaDevs.Web.SessionBean.PumpController;
import com.AlphaDevs.Web.SessionBean.UnitsController;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
public class PumpHandler {
    @EJB
    private LoggerController loggerController;
    
    @EJB
    private PumpController pumpController;
    
    private Pump current;
    
    public PumpHandler() {
        if (current == null){
            current = new Pump();
        }
    }
    
    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    
    public PumpController getPumpController() {
        return pumpController;
    }

    public void setPumpController(PumpController pumpController) {
        this.pumpController = pumpController;
    }

    public Pump getCurrent() {
        return current;
    }

    public void setCurrent(Pump current) {
        this.current = current;
    }
    
    public List<Pump> getList(){
        return getPumpController().findAll();
    }
    
    public List<Pump> getPumpListAccordingToLocation(){
        if(getCurrent() != null && getCurrent().getRelatedLocation() != null ){
            return getPumpController().findReadingByPump(getCurrent().getRelatedLocation());
        }else{
            return new ArrayList<Pump>();
        }
        
    }
    
    public String savePump(){
        Logger Log = EntityHelper.createLogger("Created Pump", current.getPumpCode(), TransactionTypes.PUMP);
        loggerController.create(Log);
        current.setLogger(Log);
        MessageHelper.addSuccessMessage("Pump Added!");
        getPumpController().create(current);
        return "Home";
    }
    
    public void savePumpAndStay(){
        Logger Log = EntityHelper.createLogger("Created Pump", current.getPumpCode(), TransactionTypes.PUMP);
        loggerController.create(Log);
        current.setLogger(Log);
        MessageHelper.addSuccessMessage("Pump Added!");
        getPumpController().create(current);
        setCurrent(new Pump());
    }
    
    public String createPumpCreateLink(){
        return "CreatePump";
    }
    public String createPumpListLink(){
        return "ListPump";
    }
    

}
