

package com.AlphaDevs.Web.Helpers;

import com.AlphaDevs.Web.Entities.GRN;
import com.AlphaDevs.Web.Entities.Invoice;
import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.SessionBean.GRNController;
import com.AlphaDevs.Web.SessionBean.InvoiceController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
    
    private Date relatedDate;
    private Location relatedLocation;
    
    public DaySummaryHandler() {
        current = new ArrayList<DataTableRow>();
    }

    public Date getRelatedDate() {
        return relatedDate;
    }

    public void setRelatedDate(Date relatedDate) {
        System.out.println("Related" + relatedDate.toString());
        this.relatedDate = relatedDate;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
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
        getCurrent().clear();
        Double amount = 0.0;
        Date inputDate = null;
        try {
 
            if(getRelatedDate() == null){
                setRelatedDate(new Date());
            }
            
            String inputStr = new SimpleDateFormat("yyyy-MM-dd").format(getRelatedDate());
           
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            inputDate = dateFormat.parse(inputStr);
        } catch (ParseException ex) {
            MessageHelper.addErrorMessage("Error While Converting Date", ex.getLocalizedMessage());
        }
        
        for (GRN grn : getgRNController().findByDateRange(inputDate,getRelatedLocation())) {
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
