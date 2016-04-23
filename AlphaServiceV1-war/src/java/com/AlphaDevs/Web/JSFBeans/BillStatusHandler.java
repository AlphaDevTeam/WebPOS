
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Enums.BillStatus;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author mkarunarathne
 */
@ManagedBean
@ViewScoped
public class BillStatusHandler {

    /**
     * Creates a new instance of BillStatusHandler
     */
    public BillStatusHandler() {
    }
    
    public BillStatus[] getBillStatus(){
        return BillStatus.values();
    }
    
}
