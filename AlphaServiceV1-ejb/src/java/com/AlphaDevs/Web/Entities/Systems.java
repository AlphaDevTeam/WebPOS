/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.Web.Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Home
 */
@Entity
public class Systems implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String Header;
    private boolean isGRNnumberAutoGenarated;
    private boolean isINVNumberAutoGenarated;
    
    @OneToOne
    private Company relatedCompany;
    @ManyToOne
    private Location relatedLocation;

    public Company getRelatedCompany() {
        return relatedCompany;
    }

    public void setRelatedCompany(Company relatedCompany) {
        this.relatedCompany = relatedCompany;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }

    public boolean isIsGRNnumberAutoGenarated() {
        return isGRNnumberAutoGenarated;
    }

    public void setIsGRNnumberAutoGenarated(boolean isGRNnumberAutoGenarated) {
        this.isGRNnumberAutoGenarated = isGRNnumberAutoGenarated;
    }

    public boolean isIsINVNumberAutoGenarated() {
        return isINVNumberAutoGenarated;
    }

    public void setIsINVNumberAutoGenarated(boolean isINVNumberAutoGenarated) {
        this.isINVNumberAutoGenarated = isINVNumberAutoGenarated;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Systems() {
    }

    public Systems(String Header) {
        this.Header = Header;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String Header) {
        this.Header = Header;
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
        if (!(object instanceof Systems)) {
            return false;
        }
        Systems other = (Systems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.Web.Entities.Systems[ id=" + id + " ]";
    }
    
}
