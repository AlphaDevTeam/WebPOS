

package com.AlphaDevs.Web.Helpers;

import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Entities.Terminal;
import com.AlphaDevs.Web.Entities.UserX;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.JSFBeans.InfoGrabber;
import java.util.Date;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

public class EntityHelper 
{
    public static Logger Logger;
    
    

    public static Logger getLogger() {
        return Logger;
    }

    public static void setLogger(Logger Logger) {
        EntityHelper.Logger = Logger;
    }
    
    public static Logger createLogger(String Description , String TrnNo,TransactionTypes TrnType )
    {
        try
        {
            FacesContext context = FacesContext.getCurrentInstance();
            UserX LogUser = (UserX) context.getExternalContext().getSessionMap().get("User");
            Terminal LogTerminal = (Terminal) context.getExternalContext().getSessionMap().get("Terminal");
            
            if( LogUser!= null || LogTerminal != null )
            {
                setLogger(new Logger(Description , new Date(),LogUser ,LogTerminal ,TrnType, TrnNo));
                return  getLogger();
            }
            else
            {
                MessageHelper.addErrorMessage("Error - createLogger", "Session Invalid , Please Login");
                return null;
            }
            
        }
        catch(Exception ex)
        {
            MessageHelper.addErrorMessage("Error - createLogger", ex.toString());
            return null;
        }
        
    }

}
