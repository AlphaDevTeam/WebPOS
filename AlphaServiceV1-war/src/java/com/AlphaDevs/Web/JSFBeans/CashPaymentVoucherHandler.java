
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.CashBook;
import com.AlphaDevs.Web.Entities.CashBookBalance;
import com.AlphaDevs.Web.Entities.CashPaymentVoucher;
import com.AlphaDevs.Web.Entities.CustomerBalance;
import com.AlphaDevs.Web.Entities.CustomerTransaction;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Enums.BillStatus;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.SessionBean.CashBookBalanceController;
import com.AlphaDevs.Web.SessionBean.CashPaymentVoucherController;
import com.AlphaDevs.Web.SessionBean.CashbookController;
import com.AlphaDevs.Web.SessionBean.CustomerBalanceController;
import com.AlphaDevs.Web.SessionBean.CustomerTransactionController;
import com.AlphaDevs.Web.SessionBean.LoggerController;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


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
@RequestScoped
public class CashPaymentVoucherHandler {
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
    @EJB
    private CashPaymentVoucherController cashPaymentVoucherController;

    private CashPaymentVoucher current;
    
    public CashPaymentVoucherHandler() {
        if(current == null){
            current = new CashPaymentVoucher();
        }
    }

    public CashPaymentVoucherController getCashPaymentVoucherController() {
        return cashPaymentVoucherController;
    }

    public void setCashPaymentVoucherController(CashPaymentVoucherController cashPaymentVoucherController) {
        this.cashPaymentVoucherController = cashPaymentVoucherController;
    }

    public CashPaymentVoucher getCurrent() {
        return current;
    }

    public void setCurrent(CashPaymentVoucher current) {
        this.current = current;
    }
    
    public List<CashPaymentVoucher> getList(){
        return getCashPaymentVoucherController().findAll();
    }
    public String createPayment(){
        
        //Creating Logger
        Logger log = EntityHelper.createLogger("Cash Payament Voucher - " + getCurrent().getPaymentDescription() , getCurrent().getPaymentNumber(), TransactionTypes.CASHPAY);
        loggerController.create(log);
        getCurrent().setRelatedLogger(log);
        
        getCurrent().setRelatedCompany(EntityHelper.getLoggedCompany());
        
        //Customer Transaction
        CustomerTransaction custTran = new CustomerTransaction();
        custTran.setDescription("Cash Payament Voucher - " + current.getPaymentNumber() + "-" + getCurrent().getPaymentRefNumber() + " - "+ getCurrent().getPaymentDescription());
        custTran.setSupplier(current.getRelatedSupplier());
        custTran.setDR(getCurrent().getPaymentAmount());
        custTran.setCR(0);
        
        //Getting Cust Balance
        CustomerBalance Balance = customerBalanceController.getCustomerBalanceObject(getCurrent().getRelatedSupplier());
        if(Balance != null)
        {
            Balance.setBalance(Balance.getBalance() + getCurrent().getPaymentAmount() );
            customerBalanceController.edit(Balance);
            custTran.setBalance(Balance.getBalance());
            
        }
        else
        {
            custTran.setBalance(getCurrent().getPaymentAmount());
        }
        
        custTran.setLogger(log);
        customerTransactionController.create(custTran);
        
        //Cashbook
        CashBook cashBook = new CashBook();
        cashBook.setDescription("Cash Payament Voucher - " + current.getPaymentNumber() + "-" + getCurrent().getPaymentRefNumber() + " - "+ getCurrent().getPaymentDescription());
        cashBook.setCR(getCurrent().getPaymentAmount());
        cashBook.setDR(0);
        cashBook.setLocation(getCurrent().getPaymentLocation());
        cashBook.setLogger(log);

        CashBookBalance cashBalance = cashBookBalanceController.getCashBookBalanceObject(getCurrent().getPaymentLocation(), BillStatus.TAX);
        
        if(cashBalance != null)
        {
            cashBalance.setCashBalance(cashBalance.getCashBalance() - getCurrent().getPaymentAmount());
            cashBookBalanceController.edit(cashBalance);
            cashBook.setBalance(cashBalance.getCashBalance());            
        }
        else
        {
            cashBook.setBalance(getCurrent().getPaymentAmount());
        }

        cashbookController.create(cashBook);
        
        getCashPaymentVoucherController().create(current);
        
        return "Home";
    }
}
