
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.SessionBean.LoggerController;
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
public class SuperHandler {
    @EJB
    public LoggerController loggerController;
    
    public SuperHandler() {
    }
}
