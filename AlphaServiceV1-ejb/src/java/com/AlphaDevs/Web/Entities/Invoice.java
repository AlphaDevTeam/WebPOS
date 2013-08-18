

package com.AlphaDevs.Web.Entities;

import com.AlphaDevs.Web.Enums.BillStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@Entity
public class Invoice implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String BillNo;
    private String RefNo;
    private double TotalAmount;
    private BillStatus BillStat;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date TrnDate;
    
    @OneToOne
    private Supplier Customer;
    
    @OneToOne
    private Location Location;
    
    @OneToOne
    private Logger Logger;
        
    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetails> InvDetails;
    
    @OneToMany
    private List<AdditionalFields> additionalFieldList;
    
    public Invoice() {
        
    }

    public Invoice(String BillNo, String RefNo, double TotalAmount, BillStatus BillStat, Date TrnDate, Supplier Customer, Location Location, Logger Logger, List<InvoiceDetails> InvDetails, List<AdditionalFields> additionalFieldList) {
        this.BillNo = BillNo;
        this.RefNo = RefNo;
        this.TotalAmount = TotalAmount;
        this.BillStat = BillStat;
        this.TrnDate = TrnDate;
        this.Customer = Customer;
        this.Location = Location;
        this.Logger = Logger;
        this.InvDetails = InvDetails;
        this.additionalFieldList = additionalFieldList;
    }

   
    public List<InvoiceDetails> getInvDetails() {
        return InvDetails;
    }

    public void setInvDetails(List<InvoiceDetails> InvDetails) {
        this.InvDetails = InvDetails;
    }

    public List<AdditionalFields> getAdditionalFieldList() {
        return additionalFieldList;
    }

    public void setAdditionalFieldList(List<AdditionalFields> additionalFieldList) {
        this.additionalFieldList = additionalFieldList;
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String BillNo) {
        this.BillNo = BillNo;
    }

    public BillStatus getBillStat() {
        return BillStat;
    }

    public void setBillStat(BillStatus BillStat) {
        this.BillStat = BillStat;
    }

    public Supplier getCustomer() {
        return Customer;
    }

    public void setCustomer(Supplier Customer) {
        this.Customer = Customer;
    }

    public com.AlphaDevs.Web.Entities.Location getLocation() {
        return Location;
    }

    public void setLocation(com.AlphaDevs.Web.Entities.Location Location) {
        this.Location = Location;
    }

    public com.AlphaDevs.Web.Entities.Logger getLogger() {
        return Logger;
    }

    public void setLogger(com.AlphaDevs.Web.Entities.Logger Logger) {
        this.Logger = Logger;
    }

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String RefNo) {
        this.RefNo = RefNo;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public Date getTrnDate() {
        return TrnDate;
    }

    public void setTrnDate(Date TrnDate) {
        this.TrnDate = TrnDate;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() 
    {
        if(getRefNo().equalsIgnoreCase("") || getRefNo() == null)
        {
            return getBillNo();
        }
        else
        {
            return getBillNo() + "(" + getRefNo() + ")";            
        }
        
    }

}
