
package com.AlphaDevs.Web.Entities;

import com.AlphaDevs.Web.Enums.DocType;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class AdditionalFields implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fieldName;
    private String fieldValue;
    private String displayName;
    @Enumerated(EnumType.STRING)
    private DocType displayOn;
    private boolean isHidden;
    private boolean isRequired;
    @ManyToOne
    private Company relatedCompany;
    @ManyToOne
    private GRN gRN;
    
    public AdditionalFields() {
    }

    public AdditionalFields(String fieldName, String fieldValue, String displayName, DocType displayOn, boolean isHidden, boolean isRequired, Company relatedCompany) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.displayName = displayName;
        this.displayOn = displayOn;
        this.isHidden = isHidden;
        this.isRequired = isRequired;
        this.relatedCompany = relatedCompany;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public boolean isIsRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Company getRelatedCompany() {
        return relatedCompany;
    }

    public void setRelatedCompany(Company relatedCompany) {
        this.relatedCompany = relatedCompany;
    }
    
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public DocType getDisplayOn() {
        return displayOn;
    }

    public void setDisplayOn(DocType displayOn) {
        this.displayOn = displayOn;
    }

    public boolean isIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
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
        if (!(object instanceof AdditionalFields)) {
            return false;
        }
        AdditionalFields other = (AdditionalFields) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.Web.Entities.AdditionalFields[ id=" + id + " ]";
    }
    
}
