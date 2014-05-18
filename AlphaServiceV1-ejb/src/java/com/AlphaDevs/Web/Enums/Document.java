
package com.AlphaDevs.Web.Enums;

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
public enum Document {
    
    INVOICE("Invoice"),
    GOOD_RECEIPT_NOTE("Grn"),
    METER_READING("Meter"),
    CASH_PAYMENT_CUST("CashPaymentCust"),
    CASH_PAYMENT_EXP("CashPaymentExpenses"),
    CASH_RECEIPT_CUST("CashRecieptCust"),
    CASH_RECEIPT_EXP("CashRecieptExpenses"),
    BANK_DEPOSIT("BankDeposit"),
    STOCK_ADJESTMENT("StockAdjestment");
    
    private String fieldName;
    private Document(String feild){
        fieldName = feild;
    }
    
    public String getDocumentName(){
        return fieldName;
    }
    
}
