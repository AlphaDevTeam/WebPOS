
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.AdditionalFields;
import com.AlphaDevs.Web.Entities.Company;
import com.AlphaDevs.Web.Entities.UserX;
import com.AlphaDevs.Web.Enums.DocType;
import com.AlphaDevs.Web.SessionBean.AdditionalFieldsController;
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
public class AdditionalFieldsHandler {
    @EJB
    private AdditionalFieldsController additionalFieldsController;
    
    private AdditionalFields current;
    
    public AdditionalFieldsHandler() {
        if(current == null){
            current = new AdditionalFields();
        }
    }

    public AdditionalFieldsController getAdditionalFieldsController() {
        return additionalFieldsController;
    }

    public void setAdditionalFieldsController(AdditionalFieldsController additionalFieldsController) {
        this.additionalFieldsController = additionalFieldsController;
    }

    public AdditionalFields getCurrent() {
        return current;
    }

    public void setCurrent(AdditionalFields current) {
        this.current = current;
        
    }
    
    public List<AdditionalFields> getList(DocType docType){
        FacesContext context = FacesContext.getCurrentInstance();
        UserX logedUser = (UserX) context.getExternalContext().getSessionMap().get("User");
        Company company = logedUser.getAssociatedCompany();
        System.out.println("Doc : " + docType.name() + " Company : " + company.getCompanyName());
        List<AdditionalFields> listOfDocumentDeils = getAdditionalFieldsController().findAll(docType, company);
        System.out.println("C : " + listOfDocumentDeils);
        return listOfDocumentDeils;
    }
    
    public int getEndCount(){
        return getAdditionalFieldsController().findAll().size();
    }
    
}
