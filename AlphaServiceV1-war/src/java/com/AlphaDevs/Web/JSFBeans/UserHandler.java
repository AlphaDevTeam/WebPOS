
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Terminal;
import com.AlphaDevs.Web.Entities.UserX;
import com.AlphaDevs.Web.Helpers.MessageHelper;
import com.AlphaDevs.Web.Helpers.SessionData;
import com.AlphaDevs.Web.Helpers.SessionDataHelper;
import com.AlphaDevs.Web.SessionBean.TerminalController;
import com.AlphaDevs.Web.SessionBean.UserController;
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
public class UserHandler 
{
    @EJB
    private TerminalController terminalController;
    @EJB
    private UserController userController;
    
    private UserX current;
    
    
    public UserHandler() 
    {
        if(current == null)
        {
            current = new UserX();
        }
    }
    
     public String  prePop()
    {
        getCurrent().setUserName("PreMihinduchanged");
        System.out.println("Pre : " + getCurrent().getUserName());
        return "done pre";
    }
    
     public String postPop()
    {
        getCurrent().setUserName("Mihindu");
        System.out.println("Post : " + getCurrent().getUserName());
        return "done post";
    }

    public UserHandler(UserX current) {
        this.current = current;
    }

    public UserX getCurrent() {
        return current;
    }

    public void setCurrent(UserX current) {
        this.current = current;
    }

    public TerminalController getTerminalController() {
        return terminalController;
    }

    public void setTerminalController(TerminalController terminalController) {
        this.terminalController = terminalController;
    }

    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }
    
    public String validateUser()
    {
        List<UserX> LoggedUser;
        LoggedUser = userController.ValidateUser(current);
        if(!LoggedUser.isEmpty() && validateTerminal())
        {
            MessageHelper.addSuccessMessage("Welcome");
            FacesContext context = FacesContext.getCurrentInstance();
            SessionDataHelper.addToSessionMap("User", LoggedUser.get(0));
            System.out.println("User Set & Redirecting.,,.");
            return "Home";
        }
        else
        {
            MessageHelper.addErrorMessage("User not Found", "User Not Found");
            return "Login";
        }
        
    }
    
    public boolean validateTerminal()
    {
        Terminal term = new Terminal("", "", InfoGrabber.getTerminalString());
        List<Terminal> LoggedTerminal;
        LoggedTerminal = terminalController.ValidateTerminal(term);
        System.out.println("Trying to Login Using Terminal : " + term);
        System.out.println("Terminal String : " + InfoGrabber.getTerminalString());
        
        if(!LoggedTerminal.isEmpty())
        {
            SessionDataHelper.addToSessionMap("Terminal", LoggedTerminal.get(0));
            return true;
        }
        else
        {
            MessageHelper.addErrorMessage("UnAuthorized", "UnAuthorized Access");
            return false;
                        
        }
        
    }
    
    public List<UserX> getList(){
        return getUserController().findAll();
    }
    

}

