

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Cheques;
import com.AlphaDevs.Web.SessionBean.ChequesController;
import java.util.List;
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
public class ChequesHandler {
    @EJB
    private ChequesController chequesController;

    private Cheques current;
    
    public ChequesHandler() {
        current = new Cheques();
    }

    public ChequesController getChequesController() {
        return chequesController;
    }

    public void setChequesController(ChequesController chequesController) {
        this.chequesController = chequesController;
    }

    public Cheques getCurrent() {
        return current;
    }

    public void setCurrent(Cheques current) {
        this.current = current;
    }
    
    public List<Cheques> getList(){
        return getChequesController().findAll();
    }

}
