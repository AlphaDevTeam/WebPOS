
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Company;
import com.AlphaDevs.Web.SessionBean.CompanyController;
import java.util.List;
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
public class CompanyHandler {
    @EJB
    private CompanyController companyController;

    private Company current;
    
    public CompanyHandler() {
         if(current == null)
        {
            current = new Company();
        }
    }

    public Company getCurrent() {
        return current;
    }

    public void setCurrent(Company current) {
        this.current = current;
    }
    
    public List<Company> getListOfCompany(){
        return companyController.findAll();
    }
    
}
