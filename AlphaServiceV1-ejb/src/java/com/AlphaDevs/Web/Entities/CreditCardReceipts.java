

package com.AlphaDevs.Web.Entities;

import com.AlphaDevs.Web.Enums.ChequeStatus;
import com.AlphaDevs.Web.Enums.CreditCardReceiptStatus;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@Entity
public class CreditCardReceipts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String cardNumber;
    private String note;
    private double amount;
    @Enumerated(EnumType.STRING)
    private CreditCardReceiptStatus receiptStatus;
    @OneToOne
    private CreditCardTeminals relatedTerminal;
    @OneToOne
    private Logger relatedLogger;
    @OneToOne
    private Location relatedLocation;
    
    @OneToOne
    private GRNPaymentDetails relatedGRNPaymentDetails;
    
    private PaymentDetails relatedPaymentDetails;

    public CreditCardReceipts() {
    }

    public CreditCardReceipts(String cardNumber, String note, double amount, CreditCardReceiptStatus receiptStatus, CreditCardTeminals relatedTerminal, Logger relatedLogger, Location relatedLocation, GRNPaymentDetails relatedGRNPaymentDetails, PaymentDetails relatedPaymentDetails) {
        this.cardNumber = cardNumber;
        this.note = note;
        this.amount = amount;
        this.receiptStatus = receiptStatus;
        this.relatedTerminal = relatedTerminal;
        this.relatedLogger = relatedLogger;
        this.relatedLocation = relatedLocation;
        this.relatedGRNPaymentDetails = relatedGRNPaymentDetails;
        this.relatedPaymentDetails = relatedPaymentDetails;
    }

    public PaymentDetails getRelatedPaymentDetails() {
        return relatedPaymentDetails;
    }

    public void setRelatedPaymentDetails(PaymentDetails relatedPaymentDetails) {
        this.relatedPaymentDetails = relatedPaymentDetails;
    }

    public GRNPaymentDetails getRelatedGRNPaymentDetails() {
        return relatedGRNPaymentDetails;
    }

    public void setRelatedGRNPaymentDetails(GRNPaymentDetails relatedGRNPaymentDetails) {
        this.relatedGRNPaymentDetails = relatedGRNPaymentDetails;
    }

    
    public Logger getRelatedLogger() {
        return relatedLogger;
    }

    public void setRelatedLogger(Logger relatedLogger) {
        this.relatedLogger = relatedLogger;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CreditCardReceiptStatus getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(CreditCardReceiptStatus receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public CreditCardTeminals getRelatedTerminal() {
        return relatedTerminal;
    }

    public void setRelatedTerminal(CreditCardTeminals relatedTerminal) {
        this.relatedTerminal = relatedTerminal;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditCardReceipts)) {
            return false;
        }
        CreditCardReceipts other = (CreditCardReceipts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getCardNumber() + "-" + getNote();
    }

}
