
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Enums.PurchaseType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author mkarunarathne
 */
@ManagedBean
@ViewScoped
public class PurchaseTypeHandler {

    /**
     * Creates a new instance of PurchaseTypeHandler
     */
    public PurchaseTypeHandler() {
    }
    
    public PurchaseType[] getPurchaseType(){
        return PurchaseType.values();
    }
}
