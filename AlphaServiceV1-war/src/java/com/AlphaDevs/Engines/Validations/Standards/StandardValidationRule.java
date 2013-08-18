
package com.AlphaDevs.Engines.Validations.Standards;

import com.AlphaDevs.Engines.Validations.ValidationRule;
import static com.AlphaDevs.Engines.Validations.ValidationRule.validationErrors;
import com.AlphaDevs.Helpers.Error.ValidationError;
import com.AlphaDevs.Web.Entities.Company;
import com.AlphaDevs.Web.Entities.CustomerCategory;
import com.AlphaDevs.Web.Entities.UserX;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2013/07/23
 * @see Alpha Development Team ( www.AlphaDevs.com )
 * 
 */
public class StandardValidationRule implements ValidationRule{
    
    @Override
    public List<ValidationError> isRequred(Object ObjectLite) {
        
        List<ValidationError> errorList  = new ArrayList<ValidationError>();
        
       return errorList;
    }

    @Override
    public void addValidation(ValidationError vaidationError) {
        if(vaidationError != null){
            validationErrors.add(vaidationError);
        }
    }

    @Override
    public void addValidation(String errorTitle, String errorDetails) {
        if( ! errorTitle.isEmpty() && ! errorDetails.isEmpty()){
            ValidationError validationError = new ValidationError("CUST101", errorTitle, errorDetails);
            addValidation(validationError);
        }
        
    }
    
    public void resetValidation(){
        validationErrors.clear();
    }
    
    public void standardValidations(Object validatingObject){
        //Strandard VLD 1
        //Validate the Description Length
        CustomerCategory custCat =  (CustomerCategory) validatingObject;
        if(custCat.getDescription().length() == 0){
            addValidation("STANDARD VLD 1","You must Specify a Value to Discription");
        }
    }

    @Override
    public void validatePage(Object validatingObject) {
        resetValidation();
        
        try {
            
            FacesContext context = FacesContext.getCurrentInstance();
            UserX logedUser = (UserX) context.getExternalContext().getSessionMap().get("User");
            Company company = logedUser.getAssociatedCompany();
            Class validationClass = Class.forName(company.getCompanyDescription());
            ValidationRule rule = (ValidationRule) validationClass.newInstance();
            rule.validatePage(validatingObject);
           
        } catch (Exception ex) {
            Logger.getLogger(StandardValidationRule.class.getName()).log(Level.SEVERE, null, ex);

        }
       
    }

    @Override
    public List<ValidationError> validateResults() {
        return validationErrors;
    }

}
