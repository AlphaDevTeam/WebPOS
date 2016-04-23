
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Enums.ChequeStatus;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Mihindu Karunarathne
 */
@ManagedBean
@ViewScoped
public class ChequeStatusHandler {


    public ChequeStatusHandler() {
    }
    
    
    public ChequeStatus[] getChequeStatus(){
        return ChequeStatus.values();
    }
}
