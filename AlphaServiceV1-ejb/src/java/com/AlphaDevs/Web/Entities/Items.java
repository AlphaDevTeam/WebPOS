
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
public class Items implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String ItemCode;
    private String ItemName;
    private String ItemBarcode;
    private String ItemDescription;
    
    @OneToOne
    private Product ItemProduct;
    
    @OneToOne
    private Design ItemDesign;
    
    @OneToOne
    private Supplier ItemSupplier;
    
    private double ItemCost;
    private double UnitPrice;
    private double ReorderLevel;
    @OneToOne
    private Location ItemLocation;
    
    @OneToOne
    private Logger Logger;

    public Items() {
    }

    public Items(String ItemCode, String ItemName, String ItemBarcode, String ItemDescription, Product ItemProduct, Design ItemDesign, Supplier ItemSupplier, double ItemCost, double UnitPrice, double ReorderLevel, Location ItemLocation, com.AlphaDevs.Web.Entities.Logger Logger) {
        this.ItemCode = ItemCode;
        this.ItemName = ItemName;
        this.ItemBarcode = ItemBarcode;
        this.ItemDescription = ItemDescription;
        this.ItemProduct = ItemProduct;
        this.ItemDesign = ItemDesign;
        this.ItemSupplier = ItemSupplier;
        this.ItemCost = ItemCost;
        this.UnitPrice = UnitPrice;
        this.ReorderLevel = ReorderLevel;
        this.ItemLocation = ItemLocation;
        this.Logger = Logger;
    }

    
    public String getItemBarcode() {
        return ItemBarcode;
    }

    public com.AlphaDevs.Web.Entities.Logger getLogger() {
        return Logger;
    }

    public void setLogger(com.AlphaDevs.Web.Entities.Logger Logger) {
        this.Logger = Logger;
    }

    public void setItemBarcode(String ItemBarcode) {
        this.ItemBarcode = ItemBarcode;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String ItemCode) {
        this.ItemCode = ItemCode;
    }

    public double getItemCost() {
        return ItemCost;
    }

    public void setItemCost(double ItemCost) {
        this.ItemCost = ItemCost;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public void setItemDescription(String ItemDescription) {
        this.ItemDescription = ItemDescription;
    }

    public Design getItemDesign() {
        return ItemDesign;
    }

    public void setItemDesign(Design ItemDesign) {
        this.ItemDesign = ItemDesign;
    }

    public Location getItemLocation() {
        return ItemLocation;
    }

    public void setItemLocation(Location ItemLocation) {
        this.ItemLocation = ItemLocation;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public Product getItemProduct() {
        return ItemProduct;
    }

    public void setItemProduct(Product ItemProduct) {
        System.out.println("Item Prod Set");
        this.ItemProduct = ItemProduct;
    }

    public Supplier getItemSupplier() {
        return ItemSupplier;
    }

    public void setItemSupplier(Supplier ItemSupplier) {
        this.ItemSupplier = ItemSupplier;
    }

    public double getReorderLevel() {
        return ReorderLevel;
    }

    public void setReorderLevel(double ReorderLevel) {
        this.ReorderLevel = ReorderLevel;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }
    
    
    public Long getId() 
    {
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
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ItemDescription;
    }

}
