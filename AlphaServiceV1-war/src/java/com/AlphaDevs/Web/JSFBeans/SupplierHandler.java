
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.*;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.Helpers.MessageHelper;
import com.AlphaDevs.Web.SessionBean.CustomerBalanceController;
import com.AlphaDevs.Web.SessionBean.LoggerController;
import com.AlphaDevs.Web.SessionBean.SupplierController;
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
public class SupplierHandler 
{
    @EJB
    private LoggerController loggerController;
    @EJB
    private CustomerBalanceController customerBalanceController;
    @EJB
    private SupplierController supplierController;
    
    
    private Supplier current;
    private Address currentAddress;
    private ContactInfo currentContact;
    
    public SupplierHandler() 
    {
        if(current == null)
        {
            current = new Supplier();
            
        }
        if(currentAddress==null)
        {
            currentAddress = new Address();
        }
        if(currentContact == null )
        {
            currentContact = new ContactInfo();
        }
    }

    public Supplier getCurrent() {
        return current;
    }

    public void setCurrent(Supplier current) {
        this.current = current;
    }
    
    public String createSup()
    {
        Logger Log = EntityHelper.createLogger("Create Supplier", current.getFirstName(), TransactionTypes.SUPPLIER);
        loggerController.create(Log);
        current.setLogger(Log);
        supplierController.create(current);
        CustomerBalance CustBal = new CustomerBalance();
        CustBal.setBalance(0);
        CustBal.setSupplier(current);
        customerBalanceController.create(CustBal);
        MessageHelper.addSuccessMessage("Supplier Added!");
        return "CreateSup";
    }

    public List<Supplier> allSuppliers()
    {
        return supplierController.findAll();
    }
    
    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    public ContactInfo getCurrentContact() {
        return currentContact;
    }

    public void setCurrentContact(ContactInfo currentContact) {
        this.currentContact = currentContact;
    }

    public SupplierController getSupplierController() {
        return supplierController;
    }

    public void setSupplierController(SupplierController supplierController) {
        this.supplierController = supplierController;
    }
    
    
}
