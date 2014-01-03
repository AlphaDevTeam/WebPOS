
package com.AlphaDevs.Web.Entities;

import com.AlphaDevs.Web.Enums.AdjestmentTypes;
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
public class StockAdjestments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String AdjestmentDescription;
    private Double AdjestmentQty;
    @OneToOne
    private Logger logger;
    
    @OneToOne
    private Location AdjestmentLocation;
    
    @OneToOne
    private Items AdjestmentItems;
    
    @Enumerated(EnumType.STRING)
    private AdjestmentTypes adjestmentType;

    public StockAdjestments() {
    }

    public StockAdjestments(String AdjestmentDescription, Double AdjestmentQty, Logger logger, Location AdjestmentLocation, Items AdjestmentItems, AdjestmentTypes adjestmentType) {
        this.AdjestmentDescription = AdjestmentDescription;
        this.AdjestmentQty = AdjestmentQty;
        this.logger = logger;
        this.AdjestmentLocation = AdjestmentLocation;
        this.AdjestmentItems = AdjestmentItems;
        this.adjestmentType = adjestmentType;
    }

    public String getAdjestmentDescription() {
        return AdjestmentDescription;
    }

    public void setAdjestmentDescription(String AdjestmentDescription) {
        this.AdjestmentDescription = AdjestmentDescription;
    }

    public Double getAdjestmentQty() {
        return AdjestmentQty;
    }

    public void setAdjestmentQty(Double AdjestmentQty) {
        this.AdjestmentQty = AdjestmentQty;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Location getAdjestmentLocation() {
        return AdjestmentLocation;
    }

    public void setAdjestmentLocation(Location AdjestmentLocation) {
        this.AdjestmentLocation = AdjestmentLocation;
    }

    public Items getAdjestmentItems() {
        return AdjestmentItems;
    }

    public void setAdjestmentItems(Items AdjestmentItems) {
        this.AdjestmentItems = AdjestmentItems;
    }

    public AdjestmentTypes getAdjestmentType() {
        return adjestmentType;
    }

    public void setAdjestmentType(AdjestmentTypes adjestmentType) {
        this.adjestmentType = adjestmentType;
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
        if (!(object instanceof StockAdjestments)) {
            return false;
        }
        StockAdjestments other = (StockAdjestments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.Web.Entities.StockAdjestments[ id=" + id + " ]";
    }
    
}
