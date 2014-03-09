
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.CashBook;
import com.AlphaDevs.Web.Entities.CashBookBalance;
import com.AlphaDevs.Web.Entities.CashPaymentVoucher;
import com.AlphaDevs.Web.Entities.CashPaymentVoucherExpenses;
import com.AlphaDevs.Web.Entities.CustomerBalance;
import com.AlphaDevs.Web.Entities.CustomerTransaction;
import com.AlphaDevs.Web.Entities.Expenses;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Entities.SystemNumbers;
import com.AlphaDevs.Web.Entities.UserX;
import com.AlphaDevs.Web.Enums.BillStatus;
import com.AlphaDevs.Web.Enums.Document;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.Helpers.SessionDataHelper;
import com.AlphaDevs.Web.SessionBean.CashBookBalanceController;
import com.AlphaDevs.Web.SessionBean.CashPaymentVoucherController;
import com.AlphaDevs.Web.SessionBean.CashPaymentVoucherExpensesController;
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
public class CashPaymentVoucherExpensesHandler {
    @EJB
    private CashPaymentVoucherExpensesController cashPaymentVoucherExpensesController;
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
   
    private CashPaymentVoucherExpenses current;
    
    private SystemNumbers currentSystemNumber;
    private Document currentDocument;
    
    public CashPaymentVoucherExpensesHandler() {
        
        setCurrentDocument(Document.CASH_PAYMENT_EXP);
        if(current == null){
            current = new CashPaymentVoucherExpenses();
        }
    }

    public CashPaymentVoucherExpensesController getCashPaymentVoucherExpensesController() {
        return cashPaymentVoucherExpensesController;
    }

    public void setCashPaymentVoucherExpensesController(CashPaymentVoucherExpensesController cashPaymentVoucherExpensesController) {
        this.cashPaymentVoucherExpensesController = cashPaymentVoucherExpensesController;
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
    
    public CashPaymentVoucherExpenses getCurrent() {
        return current;
    }

    public void setCurrent(CashPaymentVoucherExpenses current) {
        this.current = current;
    }
    
    public List<CashPaymentVoucherExpenses> getList(){
        return getCashPaymentVoucherExpensesController().findAll();
    }
     
    public String getPaymentNumber(){
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get("User");
        if(loggedUser != null && getCurrent().getPaymentLocation() != null){
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getPaymentLocation(), getCurrentDocument());
            if(systemNumbers != null && !systemNumbers.isEmpty()){
                setCurrentSystemNumber(systemNumbers.get(0));
                System.out.println("Set Payment Number");
                getCurrent().setPaymentNumber(getCurrentSystemNumber().getDocumentSystemNo());
            }
            
        }
        return  getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";
    }
    
    public void setPaymentNumber(String billNumber){
        getCurrent().setPaymentNumber(billNumber);
    }    
    
    public String createPayment(){
        
        //Creating Logger
        Logger log = EntityHelper.createLogger("Cash Payament Voucher Expenses - " + getCurrent().getPaymentDescription() , getCurrent().getPaymentNumber(), TransactionTypes.CASHPAYEXP);
        loggerController.create(log);
        getCurrent().setRelatedLogger(log);
        
        getCurrent().setRelatedCompany(EntityHelper.getLoggedCompany()); 
        
        //Cashbook
        CashBook cashBook = new CashBook();
        cashBook.setDescription("Cash Payament Voucher Expenses - " + current.getPaymentNumber() + "-" + getCurrent().getPaymentRefNumber() + " - "+ getCurrent().getPaymentDescription());
        cashBook.setCR(getCurrent().getPaymentAmount());
        cashBook.setDR(0);
        cashBook.setLocation(getCurrent().getPaymentLocation());
        cashBook.setLogger(log);
        
        CashBookBalance cashBalance = cashBookBalanceController.getCashBookBalanceObject(getCurrent().getPaymentLocation(), BillStatus.TAX);
        
        if(cashBalance != null)
        {
            cashBalance.setCashBalance(cashBalance.getCashBalance() - getCurrent().getPaymentAmount());
            getCashBookBalanceController().edit(cashBalance);
            cashBook.setBalance(cashBalance.getCashBalance());            
        }
        else
        {
            cashBalance = new CashBookBalance(getCurrent().getPaymentLocation(),getCurrent().getPaymentAmount(),0,BillStatus.TAX );
            getCashBookBalanceController().create(cashBalance);
            cashBook.setBalance(getCurrent().getPaymentAmount());
        }

        getCashbookController().create(cashBook);
        
        //Increment the the Document No 
        getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getSystemNumber() + 1);
        getSystemNumbersController().edit(getCurrentSystemNumber());
        
        getCashPaymentVoucherExpensesController().create(getCurrent());
        
        return "Home";
    }
}
