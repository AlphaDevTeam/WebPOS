
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Terminal;
import com.AlphaDevs.Web.Entities.UserX;
import com.AlphaDevs.Web.Helpers.MessageHelper;
import com.AlphaDevs.Web.SessionBean.TerminalController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@SessionScoped
public class TerminalHandler 
{
    @EJB
    private TerminalController terminalController;
    
    private Terminal current;

    /** Creates a new instance of TerminalHandler */
    public TerminalHandler() 
    {
        if(current == null)
        {
            current = new Terminal();
        }
    }

    public Terminal getCurrent() {
        return current;
    }

    public void setCurrent(Terminal current) {
        this.current = current;
    }
    
    
    public String validateTerminal()
    {
        Terminal term = new Terminal("", "", InfoGrabber.getTerminalString());
        List<Terminal> LoggedTerminal;
        LoggedTerminal = terminalController.ValidateTerminal(term);
        if(!LoggedTerminal.isEmpty())
        {
            MessageHelper.addSuccessMessage("Welcome");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("Terminal", LoggedTerminal.get(0) );
            return "";
        }
        else
        {
            MessageHelper.addErrorMessage("UnAuthorized", "UnAuthorized Access");
            return "Login";
        }
        
    }
    
    public void validateTerminalSession()
    {
        Terminal term = new Terminal("", "", InfoGrabber.getTerminalString());
        List<Terminal> LoggedTerminal;
        LoggedTerminal = terminalController.ValidateTerminal(term);
        if(!LoggedTerminal.isEmpty())
        {
            MessageHelper.addSuccessMessage("Welcome");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("Terminal", LoggedTerminal.get(0) );
            
        }
        else
        {
            MessageHelper.addErrorMessage("UnAuthorized", "UnAuthorized Access");
            
        }
        
    }

}
