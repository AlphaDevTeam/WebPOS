/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.Engines.Validations.Standards;

import com.AlphaDevs.Engines.Validations.ValidationRule;
import com.AlphaDevs.Web.Entities.Company;
import com.AlphaDevs.Web.Entities.UserX;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author Home
 */
public class StandardValidationRule implements ValidationRule{
        
    @Override
    public String isRequred() {
        String tmpValue="This is Standard";
        try { 
            FacesContext context = FacesContext.getCurrentInstance();
            UserX logedUser = (UserX) context.getExternalContext().getSessionMap().get("User");
            Company company = logedUser.getAssociatedCompany();
            Class validationClass = Class.forName(company.getCompanyDescription());
            ValidationRule rule = (ValidationRule) validationClass.newInstance();
            tmpValue = rule.isRequred();
        } catch (Exception ex) {
            Logger.getLogger(StandardValidationRule.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       return tmpValue;
    }
    
}
