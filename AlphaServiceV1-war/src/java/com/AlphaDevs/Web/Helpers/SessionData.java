package com.AlphaDevs.Web.Helpers;

import com.AlphaDevs.Web.Entities.Terminal;
import com.AlphaDevs.Web.Entities.UserX;

/**
 *
 * Alpha Development Team ( www.AlphaDevs.com )
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2012/06/16
 * @see 
 * 
 */

public class SessionData {
    
    private UserX loggedUser;
    private Terminal loggedTerminal;

    public UserX getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserX loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Terminal getLoggedTerminal() {
        return loggedTerminal;
    }

    public void setLoggedTerminal(Terminal loggedTerminal) {
        this.loggedTerminal = loggedTerminal;
    }
    
    
}
