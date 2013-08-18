

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.AdditionalFields;
import com.AlphaDevs.Web.Entities.Company;
import com.AlphaDevs.Web.Entities.Invoice;
import com.AlphaDevs.Web.Entities.UserX;
import com.AlphaDevs.Web.Enums.DocType;
import com.AlphaDevs.Web.SessionBean.AdditionalFieldsController;
import com.AlphaDevs.Web.SessionBean.InvoiceController;
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
public class InvoiceHandler 
{
    @EJB
    private AdditionalFieldsController additionalFieldsController;
  
    @EJB
    private InvoiceController invoiceController;
   
    
    private Invoice current;
    
    public InvoiceHandler() 
    {
        if(current == null)
        {
            current = new Invoice();
            
        }
    }

    public AdditionalFieldsController getAdditionalFieldsController() {
        return additionalFieldsController;
    }

    public void setAdditionalFieldsController(AdditionalFieldsController additionalFieldsController) {
        this.additionalFieldsController = additionalFieldsController;
        System.out.println("AdditionalFeildController Set ");
    }
    
    public Invoice getCurrent() {
        return current;
    }

    public void setCurrent(Invoice current) {
        this.current = current;
    }

    public InvoiceController getInvoiceController() {
        return invoiceController;
    }

    public void setInvoiceController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }
    
    
    
    public List<Invoice> getList()
    {
        return invoiceController.findAll();
    } 
    
   

}
