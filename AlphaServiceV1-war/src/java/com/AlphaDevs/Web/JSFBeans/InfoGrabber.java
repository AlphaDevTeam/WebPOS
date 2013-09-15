

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.UserX;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@SessionScoped
public class InfoGrabber 
{
    private String propValue;
    

    /** Creates a new instance of InfoGrabber */
    public InfoGrabber() 
    {
       
    }

    public String getPropValue() 
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context
                        .getExternalContext().getRequest();
        String remoteHost = request.getRemoteHost();
        //System.out.println("Host : " + remoteHost);
        String remoteAddress = request.getRemoteAddr();        
        //System.out.println("Host Addr: " + remoteAddress);
        return remoteAddress + " - " + remoteHost;
    }
    
    public static String getTerminalString() 
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context
                        .getExternalContext().getRequest();
        String remoteHost = request.getRemoteHost();
        String remoteAddress = request.getRemoteAddr();        
        return remoteHost;
    }
    
    public boolean isValidTerminal() 
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context
                        .getExternalContext().getRequest();
        String remoteHost = request.getRemoteHost();
        //System.out.println("Host : " + remoteHost);
        String remoteAddress = request.getRemoteAddr();        
        //System.out.println("Host Addr: " + remoteAddress);
        
        return remoteAddress.equals("127.0.0.1");
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }
    
    
    public UserX getLoggedUser()
    {
        UserX logUser;
        FacesContext context = FacesContext.getCurrentInstance();
        if(context.getExternalContext().getSessionMap().get("User") == null)
        {
            logUser = new UserX("", "");
        }
        else
        {
            logUser = (UserX) context.getExternalContext().getSessionMap().get("User");            
        }
        
        return  logUser;
    }
    
    
    public String getLoggedTerminal()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        return  (String) context.getExternalContext().getSessionMap().get("Terminal");
    }
    
    public String logoutUser()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        return "Login";
    }
    
    public void redUser()
    {
        try 
        {
            FacesContext context = FacesContext.getCurrentInstance();
            if ( (context.getExternalContext().getSessionMap().get("User") == null) || (context.getExternalContext().getSessionMap().get("Terminal") == null))
            {
                context.getExternalContext().redirect("UI/Login.xhtml");
                context.responseComplete();                
            }
            
        
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(InfoGrabber.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
    }
    
    
    

}
