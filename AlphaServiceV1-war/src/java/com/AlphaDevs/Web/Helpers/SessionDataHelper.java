
package com.AlphaDevs.Web.Helpers;

import java.util.Map;
import javax.faces.context.FacesContext;


/**
 *
 * Alpha Development Team ( www.AlphaDevs.com )
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2012/06/16
 * @see 
 * 
 */

public class SessionDataHelper {
    
    public static SessionData getSessionData(){
        FacesContext context = FacesContext.getCurrentInstance();
        SessionData sessionObject = (SessionData) context.getExternalContext().getSessionMap().get("SessionDataObject");
        return sessionObject;
    }
    
    public static void setSessionData(SessionData sessionData){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("SessionDataObject",sessionData);
    }
    
    public static void addToSessionMap(String key, Object object){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put(key,object);
    }
    public static Map<String, Object> getSessionMap(){
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap();
    }
}
