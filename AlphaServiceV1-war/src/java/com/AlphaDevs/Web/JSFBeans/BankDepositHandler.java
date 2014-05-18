

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.BankDeposit;
import com.AlphaDevs.Web.Entities.CashBook;
import com.AlphaDevs.Web.Entities.CashBookBalance;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Entities.SystemNumbers;
import com.AlphaDevs.Web.Entities.UserX;
import com.AlphaDevs.Web.Enums.BillStatus;
import com.AlphaDevs.Web.Enums.Document;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.Helpers.SessionDataHelper;
import com.AlphaDevs.Web.SessionBean.BankBalanceController;
import com.AlphaDevs.Web.SessionBean.BankDepositController;
import com.AlphaDevs.Web.SessionBean.CashBookBalanceController;
import com.AlphaDevs.Web.SessionBean.CashbookController;
import com.AlphaDevs.Web.SessionBean.LoggerController;
import com.AlphaDevs.Web.SessionBean.SystemNumbersController;
import java.util.List;
import java.util.Map;
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
public class BankDepositHandler {
    @EJB
    private CashbookController cashbookController;
    @EJB
    private CashBookBalanceController cashBookBalanceController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private SystemNumbersController systemNumbersController;
    
    @EJB
    private BankDepositController bankDepositController;
    @EJB
    private BankBalanceController bankBalanceController;
    
    
    private BankDeposit current;
    private SystemNumbers currentSystemNumber;
    private Document currentDocument;
    
    public BankDepositHandler() {
        setCurrentDocument(Document.BANK_DEPOSIT);
        if(current == null){
            current = new BankDeposit();
        }
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
    
    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public BankDepositController getBankDepositController() {
        return bankDepositController;
    }

    public void setBankDepositController(BankDepositController bankDepositController) {
        this.bankDepositController = bankDepositController;
    }

    public BankBalanceController getBankBalanceController() {
        return bankBalanceController;
    }

    public void setBankBalanceController(BankBalanceController bankBalanceController) {
        this.bankBalanceController = bankBalanceController;
    }

    public BankDeposit getCurrent() {
        return current;
    }

    public void setCurrent(BankDeposit current) {
        this.current = current;
    }
    
    
    
    public String getRefNumber(){
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get("User");
        if(loggedUser != null && getCurrent().getRelatedLocation() != null){
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getRelatedLocation(), getCurrentDocument());
            if(systemNumbers != null && !systemNumbers.isEmpty()){
                setCurrentSystemNumber(systemNumbers.get(0));
                getCurrent().setRefNumber(getCurrentSystemNumber().getDocumentSystemNo());
            }
            
        }
        return  getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";
    }
    
    public void setRefNumber(String billNumber){
        getCurrent().setRefNumber(billNumber);
    }    
    
    public String createDeposit(){
        
        //Creating Logger
        Logger log = EntityHelper.createLogger("Bank Deposit - " + getCurrent().getDescription(), getCurrent().getRefNumber(), TransactionTypes.BANK_DEPOSIT);
        getLoggerController().create(log);
        getCurrent().setRelatedLogger(log);
        
        getCurrent().setRelatedCompany(EntityHelper.getLoggedCompany()); 
        
        //Cashbook
        CashBook cashBook = new CashBook();
        cashBook.setDescription("Bank Deposit - " + getCurrent().getRelatedAccount()+ "-" + getCurrent().getRefNumber()+ " - "+ getCurrent().getDescription());
        cashBook.setCR(getCurrent().getAmount().doubleValue());
        cashBook.setDR(0);
        cashBook.setRelatedDate(getCurrent().getDepositDate());
        cashBook.setLocation(getCurrent().getRelatedLocation());
        cashBook.setLogger(log);
        
        CashBookBalance cashBalance = cashBookBalanceController.getCashBookBalanceObject(getCurrent().getRelatedLocation(), BillStatus.TAX);
        
        if(cashBalance != null)
        {
            cashBalance.setCashBalance(cashBalance.getCashBalance() - getCurrent().getAmount().doubleValue());
            getCashBookBalanceController().edit(cashBalance);
            cashBook.setBalance(cashBalance.getCashBalance());            
        }
        else
        {
            cashBalance = new CashBookBalance(getCurrent().getRelatedLocation(),getCurrent().getAmount().doubleValue(),0,BillStatus.TAX );
            getCashBookBalanceController().create(cashBalance);
            cashBook.setBalance(getCurrent().getAmount().doubleValue());
        }

        getCashbookController().create(cashBook);
        
        //Increment the the Document No 
        getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getIncrementedSystemNumber());
        getSystemNumbersController().edit(getCurrentSystemNumber());
        
        getBankDepositController().create(getCurrent());
        
        return "Home";
    }

}
