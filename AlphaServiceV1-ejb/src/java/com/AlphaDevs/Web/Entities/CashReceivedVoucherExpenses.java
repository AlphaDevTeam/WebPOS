

package com.AlphaDevs.Web.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

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
public class CashReceivedVoucherExpenses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String receiptNumber;
    private String receiptRefNumber;
    private String receiptDescription;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date receiptDate;
    private double receiptAmount;
    @OneToOne
    private Location receiptLocation;
    @OneToOne
    private Company relatedCompany;
    @OneToOne
    private Expenses relatedExpenses;
    
    @OneToOne
    private Logger relatedLogger;
    
    public CashReceivedVoucherExpenses() {
    }

    public CashReceivedVoucherExpenses(String receiptNumber, String receiptRefNumber, String receiptDescription, Date receiptDate, double receiptAmount, Location receiptLocation, Company relatedCompany, Expenses relatedExpenses, Logger relatedLogger) {
        this.receiptNumber = receiptNumber;
        this.receiptRefNumber = receiptRefNumber;
        this.receiptDescription = receiptDescription;
        this.receiptDate = receiptDate;
        this.receiptAmount = receiptAmount;
        this.receiptLocation = receiptLocation;
        this.relatedCompany = relatedCompany;
        this.relatedExpenses = relatedExpenses;
        this.relatedLogger = relatedLogger;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Logger getRelatedLogger() {
        return relatedLogger;
    }

    public void setRelatedLogger(Logger relatedLogger) {
        this.relatedLogger = relatedLogger;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getReceiptRefNumber() {
        return receiptRefNumber;
    }

    public void setReceiptRefNumber(String receiptRefNumber) {
        this.receiptRefNumber = receiptRefNumber;
    }

    public String getReceiptDescription() {
        return receiptDescription;
    }

    public void setReceiptDescription(String receiptDescription) {
        this.receiptDescription = receiptDescription;
    }

    public double getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Location getReceiptLocation() {
        return receiptLocation;
    }

    public void setReceiptLocation(Location receiptLocation) {
        this.receiptLocation = receiptLocation;
    }

    public Company getRelatedCompany() {
        return relatedCompany;
    }

    public void setRelatedCompany(Company relatedCompany) {
        this.relatedCompany = relatedCompany;
    }

    public Expenses getRelatedExpenses() {
        return relatedExpenses;
    }

    public void setRelatedExpenses(Expenses relatedExpenses) {
        this.relatedExpenses = relatedExpenses;
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
        if (!(object instanceof CashReceivedVoucherExpenses)) {
            return false;
        }
        CashReceivedVoucherExpenses other = (CashReceivedVoucherExpenses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getReceiptNumber();
    }

}
