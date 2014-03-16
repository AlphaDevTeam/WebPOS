

package com.AlphaDevs.Web.Entities;

import com.AlphaDevs.Web.Enums.Document;
import com.AlphaDevs.Web.Enums.TransactionTypes;
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
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@Entity
public class PaymentDetails implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionTypes TrnType;
    @OneToOne
    private Logger loger;
    @Enumerated(EnumType.STRING)
    private Document documentType;
    private double cashAmount;
    private double creditCardAmount;
    private double chequeAmount;
    private double creditAmount;
    private double totalAmount;
    
    @OneToOne
    private Cheques relatedCheque;
    
    @OneToOne
    private CreditCardReceipts relatedCreditCardReceipts;

    public PaymentDetails() {
    }

    public PaymentDetails(TransactionTypes TrnType, Logger loger, Document documentType, double cashAmount, double creditCardAmount, double chequeAmount, double creditAmount, double totalAmount, Cheques relatedCheque, CreditCardReceipts relatedCreditCardReceipts) {
        this.TrnType = TrnType;
        this.loger = loger;
        this.documentType = documentType;
        this.cashAmount = cashAmount;
        this.creditCardAmount = creditCardAmount;
        this.chequeAmount = chequeAmount;
        this.creditAmount = creditAmount;
        this.totalAmount = totalAmount;
        this.relatedCheque = relatedCheque;
        this.relatedCreditCardReceipts = relatedCreditCardReceipts;
    }

    public PaymentDetails(TransactionTypes TrnType, Logger loger, Document documentType, double cashAmount, double creditCardAmount, double chequeAmount, double creditAmount, Cheques relatedCheque, CreditCardReceipts relatedCreditCardReceipts) {
        this.TrnType = TrnType;
        this.loger = loger;
        this.documentType = documentType;
        this.cashAmount = cashAmount;
        this.creditCardAmount = creditCardAmount;
        this.chequeAmount = chequeAmount;
        this.creditAmount = creditAmount;
        this.relatedCheque = relatedCheque;
        this.relatedCreditCardReceipts = relatedCreditCardReceipts;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Cheques getRelatedCheque() {
        return relatedCheque;
    }

    public void setRelatedCheque(Cheques relatedCheque) {
        this.relatedCheque = relatedCheque;
    }

    public CreditCardReceipts getRelatedCreditCardReceipts() {
        return relatedCreditCardReceipts;
    }

    public void setRelatedCreditCardReceipts(CreditCardReceipts relatedCreditCardReceipts) {
        this.relatedCreditCardReceipts = relatedCreditCardReceipts;
    }
    
    public TransactionTypes getTrnType() {
        return TrnType;
    }

    public void setTrnType(TransactionTypes TrnType) {
        this.TrnType = TrnType;
    }

    public Logger getLoger() {
        return loger;
    }

    public void setLoger(Logger loger) {
        this.loger = loger;
    }

    public Document getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Document documentType) {
        this.documentType = documentType;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public double getCreditCardAmount() {
        return creditCardAmount;
    }

    public void setCreditCardAmount(double creditCardAmount) {
        this.creditCardAmount = creditCardAmount;
    }

    public double getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(double chequeAmount) {
        this.chequeAmount = chequeAmount;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
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
        if (!(object instanceof PaymentDetails)) {
            return false;
        }
        PaymentDetails other = (PaymentDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.Web.Entities.PaymentDetails[ id=" + id + " ]";
    }

}
