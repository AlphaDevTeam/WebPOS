

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.SessionBean.LoggerController;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@SessionScoped
public class LoggerHandler 
{
    @EJB
    private LoggerController loggerController;
    
    private Logger current;

    
    public LoggerHandler() 
    {
        if(current == null)
        {
            current = new Logger();
        }
            
    }

    public Logger getCurrent() {
        return current;
    }

    public void setCurrent(Logger current) {
        this.current = current;
    }

    public String getLogID()
    {
        loggerController.create(current);
        return "#";
    }
    

}
