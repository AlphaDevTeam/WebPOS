

package com.AlphaDevs.Web.Entities;

import java.io.Serializable;
import javax.persistence.*;

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
public class PurchaseReturnDetails implements Serializable {
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @OneToOne
    private Items GrnItem;
    
    private float GrnQty;
    private double GrnItemCost;
    private float GrnItemDiscountPerc;
    
  
    @ManyToOne
    private PurchaseReturn relatedPurchaseRet;

    public PurchaseReturnDetails() {
    }

    public PurchaseReturnDetails(Items GrnItem, float GrnQty, double GrnItemCost, float GrnItemDiscountPerc, PurchaseReturn relatedPurchaseRet) {
        this.GrnItem = GrnItem;
        this.GrnQty = GrnQty;
        this.GrnItemCost = GrnItemCost;
        this.GrnItemDiscountPerc = GrnItemDiscountPerc;
        this.relatedPurchaseRet = relatedPurchaseRet;
    }

    public Items getGrnItem() {
        return GrnItem;
    }

    public void setGrnItem(Items GrnItem) {
        this.GrnItem = GrnItem;
    }

    public double getGrnItemCost() {
        return GrnItemCost;
    }

    public void setGrnItemCost(double GrnItemCost) {
        this.GrnItemCost = GrnItemCost;
    }

    public float getGrnItemDiscountPerc() {
        return GrnItemDiscountPerc;
    }

    public void setGrnItemDiscountPerc(float GrnItemDiscountPerc) {
        this.GrnItemDiscountPerc = GrnItemDiscountPerc;
    }

    public float getGrnQty() {
        return GrnQty;
    }

    public void setGrnQty(float GrnQty) {
        this.GrnQty = GrnQty;
    }

    
    public PurchaseReturn getRelatedPurchaseRet() {
        return relatedPurchaseRet;
    }

    public void setRelatedPurchaseRet(PurchaseReturn relatedPurchaseRet) {
        this.relatedPurchaseRet = relatedPurchaseRet;
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
        if (!(object instanceof PurchaseReturnDetails)) {
            return false;
        }
        PurchaseReturnDetails other = (PurchaseReturnDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.Web.Entities.PurchaseReturnDetails[ id=" + id + " ]";
    }

}
