
package com.AlphaDevs.Engines.Validations.Member;

import com.AlphaDevs.Engines.Validations.Standards.StandardValidationRule;
import com.AlphaDevs.Engines.Validations.ValidationRule;
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
public class CompanyValidationRule extends StandardValidationRule{

    @Override
    public List<ValidationError> isRequred(Object ObjectLite) {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        return errors;
    }
    
}
