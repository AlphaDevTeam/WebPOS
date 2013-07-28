
package com.AlphaDevs.Engines.Validations;

import com.AlphaDevs.Helpers.Error.ValidationError;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * @version 1.0.0
 * @since 2013/07/23
 * @see Alpha Development Team ( www.AlphaDevs.com )
 * 
 */
public interface ValidationRule {
    
    public List<ValidationError> validationErrors = new ArrayList<ValidationError>();
    public List<ValidationError> isRequred(Object ObjectLite);
    public void addValidation(ValidationError vaidationError);
    public void addValidation(String errorTitle, String errorDetails);
    public void validatePage(Object validatingObject);
    public List<ValidationError> validateResults();
}
