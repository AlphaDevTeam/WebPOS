

package com.AlphaDevs.Web.Helpers;

import com.AlphaDevs.Web.Entities.GRN;
import com.AlphaDevs.Web.Entities.Invoice;
import com.AlphaDevs.Web.SessionBean.GRNController;
import com.AlphaDevs.Web.SessionBean.InvoiceController;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@ManagedBean
@SessionScoped
public class DaySummaryHandler {
    @EJB
    private InvoiceController invoiceController;
    @EJB
    private GRNController gRNController;
    
    private List<DataTableRow> current;
    
    public DaySummaryHandler() {
        current = new ArrayList<DataTableRow>();
    }

    public InvoiceController getInvoiceController() {
        return invoiceController;
    }

    public void setInvoiceController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }
    
    public GRNController getgRNController() {
        return gRNController;
    }

    public void setgRNController(GRNController gRNController) {
        this.gRNController = gRNController;
    }
    
    public List<DataTableRow> getCurrent() {
        return current;
    }

    public void setCurrent(List<DataTableRow> current) {
        this.current = current;
    }
    
    public List<DataTableRow> getData(){
        Double amount = 0.0;
        
        for (GRN grn : getgRNController().findAll()) {
            amount = amount + grn.getTotalAmount();
            getCurrent().add(new DataTableRow("Good Receipts - " + grn + "-" + grn.getSupplier().getFirstName() , String.valueOf(grn.getTotalAmount()) , "0"));
        }
        
        for (Invoice inv : getInvoiceController().findAll()) {
            amount = amount - inv.getTotalAmount();
            getCurrent().add(new DataTableRow("Invoice - " + inv + "-" +  inv.getCustomer().getFirstName()  , "0" , String.valueOf(inv.getTotalAmount())));
        }
       
        getCurrent().add(new DataTableRow("TOTAL", amount.toString(), null));
        
        return getCurrent();
        
    }

}
