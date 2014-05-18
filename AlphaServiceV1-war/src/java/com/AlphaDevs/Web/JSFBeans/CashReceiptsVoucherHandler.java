

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.CashBook;
import com.AlphaDevs.Web.Entities.CashBookBalance;
import com.AlphaDevs.Web.Entities.CashReceivedVoucher;
import com.AlphaDevs.Web.Entities.CustomerBalance;
import com.AlphaDevs.Web.Entities.CustomerTransaction;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Entities.SystemNumbers;
import com.AlphaDevs.Web.Entities.UserX;
import com.AlphaDevs.Web.Enums.BillStatus;
import com.AlphaDevs.Web.Enums.Document;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.Helpers.SessionDataHelper;
import com.AlphaDevs.Web.SessionBean.CashBookBalanceController;
import com.AlphaDevs.Web.SessionBean.CashReceivedVoucherController;
import com.AlphaDevs.Web.SessionBean.CashbookController;
import com.AlphaDevs.Web.SessionBean.CustomerBalanceController;
import com.AlphaDevs.Web.SessionBean.CustomerTransactionController;
import com.AlphaDevs.Web.SessionBean.LoggerController;
import com.AlphaDevs.Web.SessionBean.SystemNumbersController;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
public class CashReceiptsVoucherHandler {
   
    @EJB
    private CashReceivedVoucherController cashReceivedVoucherController;
    
    @EJB
    private SystemNumbersController systemNumbersController;
    @EJB
    private CashbookController cashbookController;
    @EJB
    private CashBookBalanceController cashBookBalanceController;
    @EJB
    private CustomerTransactionController customerTransactionController;
    @EJB
    private CustomerBalanceController customerBalanceController;
    @EJB
    private LoggerController loggerController;
    
    
    private CashReceivedVoucher current;
    private SystemNumbers currentSystemNumber;
    private Document currentDocument;
    

    public CashReceiptsVoucherHandler() {
        setCurrentDocument(Document.CASH_RECEIPT_CUST);
        current = new CashReceivedVoucher();
    }

    public CashReceivedVoucherController getCashReceivedVoucherController() {
        return cashReceivedVoucherController;
    }

    public void setCashReceivedVoucherController(CashReceivedVoucherController cashReceivedVoucherController) {
        this.cashReceivedVoucherController = cashReceivedVoucherController;
    }

    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public CashbookController getCashbookController() {
        return cashbookController;
    }

    public void setCashbookController(CashbookController cashbookController) {
        this.cashbookController = cashbookController;
    }

    public CashBookBalanceController getCashBookBalanceController() {
        return cashBookBalanceController;
    }

    public void setCashBookBalanceController(CashBookBalanceController cashBookBalanceController) {
        this.cashBookBalanceController = cashBookBalanceController;
    }

    public CustomerTransactionController getCustomerTransactionController() {
        return customerTransactionController;
    }

    public void setCustomerTransactionController(CustomerTransactionController customerTransactionController) {
        this.customerTransactionController = customerTransactionController;
    }

    public CustomerBalanceController getCustomerBalanceController() {
        return customerBalanceController;
    }

    public void setCustomerBalanceController(CustomerBalanceController customerBalanceController) {
        this.customerBalanceController = customerBalanceController;
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    public CashReceivedVoucher getCurrent() {
        return current;
    }

    public void setCurrent(CashReceivedVoucher current) {
        this.current = current;
    }

    public SystemNumbers getCurrentSystemNumber() {
        return currentSystemNumber;
    }

    public void setCurrentSystemNumber(SystemNumbers currentSystemNumber) {
        this.currentSystemNumber = currentSystemNumber;
    }

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public void setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
    }
    
    public String getReceiptNumber(){
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get("User");
        if(loggedUser != null && getCurrent().getReceiptLocation() != null){
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getReceiptLocation(), getCurrentDocument());
            if(systemNumbers != null && !systemNumbers.isEmpty()){
                setCurrentSystemNumber(systemNumbers.get(0));
                getCurrent().setReceiptNumber(getCurrentSystemNumber().getDocumentSystemNo());
            }
        }
        return  getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";
    }
    
    public void setReceiptNumber(String receiptNumber){
        getCurrent().setReceiptNumber(receiptNumber);
    }    
    
    public String createReceipt(){
        
        //Creating Logger
        Logger log = EntityHelper.createLogger("Cash Receipt Voucher - " + getCurrent().getReceiptDescription() , getCurrent().getReceiptNumber(), TransactionTypes.CASHREC);
        loggerController.create(log);
        getCurrent().setRelatedLogger(log);
        
        getCurrent().setRelatedCompany(EntityHelper.getLoggedCompany()); 
        
        //Customer Transaction
        CustomerTransaction custTran = new CustomerTransaction();
        custTran.setDescription("Cash Receipt Voucher - " + getCurrent().getReceiptNumber() + "-" + getCurrent().getReceiptRefNumber()+ " - "+ getCurrent().getReceiptDescription());
        custTran.setSupplier(getCurrent().getRelatedSupplier());
        custTran.setDR(0);
        custTran.setCR(getCurrent().getReceiptAmount());
        
        //Getting Cust Balance
        CustomerBalance Balance = customerBalanceController.getCustomerBalanceObject(getCurrent().getRelatedSupplier());
        if(Balance != null)
        {
            Balance.setBalance(Balance.getBalance() - getCurrent().getReceiptAmount() );
            customerBalanceController.edit(Balance);
            custTran.setBalance(Balance.getBalance());
            
        }
        else
        {
            custTran.setBalance(getCurrent().getReceiptAmount());
        }
        
        custTran.setLogger(log);
        customerTransactionController.create(custTran);
        
        //Cashbook
        CashBook cashBook = new CashBook();
        cashBook.setDescription("Cash Receipt Voucher - " + getCurrent().getReceiptNumber()+ "-" + getCurrent().getReceiptRefNumber() + " - "+ getCurrent().getReceiptDescription());
        cashBook.setCR(0);
        cashBook.setDR(getCurrent().getReceiptAmount());
        cashBook.setRelatedDate(getCurrent().getReceiptDate());
        cashBook.setLocation(getCurrent().getReceiptLocation());
        cashBook.setLogger(log);

        CashBookBalance cashBalance = cashBookBalanceController.getCashBookBalanceObject(getCurrent().getReceiptLocation(), BillStatus.TAX);
        
        if(cashBalance != null)
        {
            cashBalance.setCashBalance(cashBalance.getCashBalance() + getCurrent().getReceiptAmount());
            cashBookBalanceController.edit(cashBalance);
            cashBook.setBalance(cashBalance.getCashBalance());            
        }
        else
        {
            cashBook.setBalance(getCurrent().getReceiptAmount());
        }

        cashbookController.create(cashBook);
        
        //Increment the the Document No 
        getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
        getSystemNumbersController().edit(getCurrentSystemNumber());
        
        getCashReceivedVoucherController().create(current);
        
        return "Home";
    }

}
