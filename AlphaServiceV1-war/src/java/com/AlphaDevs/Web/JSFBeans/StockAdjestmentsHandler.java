
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.StockAdjestments;
import com.AlphaDevs.Web.SessionBean.StockAdjestmentsController;
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
public class StockAdjestmentsHandler {
    
    @EJB
    private StockAdjestmentsController stockAdjestmentsController;

    private StockAdjestments current;
    
    public StockAdjestmentsHandler() {
        if(current == null){
            current = new StockAdjestments();
        }
    }

    public StockAdjestmentsController getStockAdjestmentsController() {
        return stockAdjestmentsController;
    }

    public void setStockAdjestmentsController(StockAdjestmentsController stockAdjestmentsController) {
        this.stockAdjestmentsController = stockAdjestmentsController;
    }

    public StockAdjestments getCurrent() {
        return current;
    }

    public void setCurrent(StockAdjestments current) {
        this.current = current;
    }
    
}
