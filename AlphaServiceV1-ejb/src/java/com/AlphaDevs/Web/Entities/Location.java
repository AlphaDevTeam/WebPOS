
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
public class Location implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String Description;
    private String Code;
    
    @OneToOne
    private Logger Logger;
    @OneToOne(mappedBy = "location")
    private CashBookBalance cashBookBalance;

    public Location() {
    }

    public com.AlphaDevs.Web.Entities.Logger getLogger() {
        return Logger;
    }

    public void setLogger(com.AlphaDevs.Web.Entities.Logger Logger) {
        this.Logger = Logger;
    }

    public Location(String Description, String Code, com.AlphaDevs.Web.Entities.Logger Logger) {
        this.Description = Description;
        this.Code = Code;
        this.Logger = Logger;
    }

   

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
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
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Description;
    }
    
}
