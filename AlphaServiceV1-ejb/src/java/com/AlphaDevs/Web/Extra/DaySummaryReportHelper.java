

package com.AlphaDevs.Web.Extra;

import com.AlphaDevs.Web.Entities.BankDeposit;
import com.AlphaDevs.Web.Entities.CashPaymentVoucher;
import com.AlphaDevs.Web.Entities.CashPaymentVoucherExpenses;
import com.AlphaDevs.Web.Entities.CashReceivedVoucher;
import com.AlphaDevs.Web.Entities.CashReceivedVoucherExpenses;
import com.AlphaDevs.Web.Entities.CreditCardReceipts;
import com.AlphaDevs.Web.Entities.CustomerBalance;
import com.AlphaDevs.Web.Entities.InvoiceDetails;
import com.AlphaDevs.Web.Entities.MeterReading;
import com.AlphaDevs.Web.Entities.StockAdjestments;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

public class DaySummaryReportHelper {
    
    private List<MeterReading> meterReadings;
    private List<InvoiceDetails> invoiceDetails;
    private List<StockAdjestments> stockAdjestments;
    private List<CustomerBalance> customerBalances;
    private List<CreditCardReceipts> creditCardReceipts;
    private List<CashPaymentVoucher> cashPaymentVouchers;
    private List<CashReceivedVoucher> cashReceivedVouchers;
    private List<CashPaymentVoucherExpenses> cashPaymentVouchersExpenses;
    private List<CashReceivedVoucherExpenses> cashReceivedVouchersExpenses;
    private List<BankDeposit> bankDeposits;

    public DaySummaryReportHelper(List<MeterReading> meterReadings, List<InvoiceDetails> invoiceDetails, List<StockAdjestments> stockAdjestments, List<CustomerBalance> customerBalances, List<CreditCardReceipts> creditCardReceipts, List<CashPaymentVoucher> cashPaymentVouchers, List<CashReceivedVoucher> cashReceivedVouchers, List<CashPaymentVoucherExpenses> cashPaymentVouchersExpenses, List<CashReceivedVoucherExpenses> cashReceivedVouchersExpenses, List<BankDeposit> bankDeposits) {
        this.meterReadings = meterReadings;
        this.invoiceDetails = invoiceDetails;
        this.stockAdjestments = stockAdjestments;
        this.customerBalances = customerBalances;
        this.creditCardReceipts = creditCardReceipts;
        this.cashPaymentVouchers = cashPaymentVouchers;
        this.cashReceivedVouchers = cashReceivedVouchers;
        this.cashPaymentVouchersExpenses = cashPaymentVouchersExpenses;
        this.cashReceivedVouchersExpenses = cashReceivedVouchersExpenses;
        this.bankDeposits = bankDeposits;
    }

    public DaySummaryReportHelper() {
    }
    
    

    public List<BankDeposit> getBankDeposits() {
        return bankDeposits;
    }

    public void setBankDeposits(List<BankDeposit> bankDeposits) {
        this.bankDeposits = bankDeposits;
    }

    public List<CreditCardReceipts> getCreditCardReceipts() {
        return creditCardReceipts;
    }

    public void setCreditCardReceipts(List<CreditCardReceipts> creditCardReceipts) {
        this.creditCardReceipts = creditCardReceipts;
    }

    public List<CashPaymentVoucher> getCashPaymentVouchers() {
        return cashPaymentVouchers;
    }

    public void setCashPaymentVouchers(List<CashPaymentVoucher> cashPaymentVouchers) {
        this.cashPaymentVouchers = cashPaymentVouchers;
    }

    public List<CashReceivedVoucher> getCashReceivedVouchers() {
        return cashReceivedVouchers;
    }

    public void setCashReceivedVouchers(List<CashReceivedVoucher> cashReceivedVouchers) {
        this.cashReceivedVouchers = cashReceivedVouchers;
    }

    public List<CashPaymentVoucherExpenses> getCashPaymentVouchersExpenses() {
        return cashPaymentVouchersExpenses;
    }

    public void setCashPaymentVouchersExpenses(List<CashPaymentVoucherExpenses> cashPaymentVouchersExpenses) {
        this.cashPaymentVouchersExpenses = cashPaymentVouchersExpenses;
    }

    public List<CashReceivedVoucherExpenses> getCashReceivedVouchersExpenses() {
        return cashReceivedVouchersExpenses;
    }

    public void setCashReceivedVouchersExpenses(List<CashReceivedVoucherExpenses> cashReceivedVouchersExpenses) {
        this.cashReceivedVouchersExpenses = cashReceivedVouchersExpenses;
    }

    public List<CustomerBalance> getCustomerBalances() {
        return customerBalances;
    }

    public void setCustomerBalances(List<CustomerBalance> customerBalances) {
        this.customerBalances = customerBalances;
    }
    
    public List<StockAdjestments> getStockAdjestments() {
        return stockAdjestments;
    }

    public void setStockAdjestments(List<StockAdjestments> stockAdjestments) {
        this.stockAdjestments = stockAdjestments;
    }

    public List<MeterReading> getMeterReadings() {
        return meterReadings;
    }

    public void setMeterReadings(List<MeterReading> meterReadings) {
        this.meterReadings = meterReadings;
    }

    public List<InvoiceDetails> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetails> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
    
    public void clean(){
        setMeterReadings(new ArrayList<MeterReading>());
        setInvoiceDetails(new ArrayList<InvoiceDetails>());
        setStockAdjestments(new ArrayList<StockAdjestments>());
        setCustomerBalances(new ArrayList<CustomerBalance>());
        setCreditCardReceipts(new ArrayList<CreditCardReceipts>());
        setCashPaymentVouchers(new ArrayList<CashPaymentVoucher>());
        setCashReceivedVouchers(new ArrayList<CashReceivedVoucher>());
        setCashPaymentVouchersExpenses(new ArrayList<CashPaymentVoucherExpenses>());
        setCashReceivedVouchersExpenses(new ArrayList<CashReceivedVoucherExpenses>());
    }
    
    
    
}
