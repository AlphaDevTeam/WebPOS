
package com.AlphaDevs.Web.Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@Entity
public class BankAccounts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String accountCode;
    private String accountName;
    @OneToOne
    private BankBranch relatedBranch;

    public BankAccounts() {
    }

    public BankAccounts(String accountCode, String accountName, BankBranch relatedBranch) {
        this.accountCode = accountCode;
        this.accountName = accountName;
        this.relatedBranch = relatedBranch;
    }

    public BankAccounts(String accountCode, String accountName) {
        this.accountCode = accountCode;
        this.accountName = accountName;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BankBranch getRelatedBranch() {
        return relatedBranch;
    }

    public void setRelatedBranch(BankBranch relatedBranch) {
        this.relatedBranch = relatedBranch;
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
        if (!(object instanceof BankAccounts)) {
            return false;
        }
        BankAccounts other = (BankAccounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.AlphaDevs.Web.Entities.BankAccounts[ id=" + id + " ]";
    }
    
}
