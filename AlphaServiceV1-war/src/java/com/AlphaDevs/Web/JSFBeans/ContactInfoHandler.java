

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.ContactInfo;
import com.AlphaDevs.Web.SessionBean.ContactInfoController;
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
public class ContactInfoHandler 
{
    @EJB
    private ContactInfoController contactInfoController;
    
    private ContactInfo current;
    
    /** Creates a new instance of ContactInfoHandler */
    public ContactInfoHandler() {
    }

    public ContactInfoController getContactInfoController() {
        return contactInfoController;
    }

    public void setContactInfoController(ContactInfoController contactInfoController) {
        this.contactInfoController = contactInfoController;
    }

    public ContactInfo getCurrent() {
        return current;
    }

    public void setCurrent(ContactInfo current) {
        this.current = current;
    }
    

}
