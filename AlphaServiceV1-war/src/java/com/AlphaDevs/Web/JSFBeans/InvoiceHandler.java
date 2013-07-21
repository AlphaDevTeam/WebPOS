

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Invoice;
import com.AlphaDevs.Web.Entities.InvoiceDetails;
import com.AlphaDevs.Web.SessionBean.InvoiceController;
import java.util.ArrayList;
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
public class InvoiceHandler 
{
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
