
package com.AlphaDevs.Engines.Validations.Member;

import com.AlphaDevs.Engines.Validations.Standards.StandardValidationRule;
import com.AlphaDevs.Engines.Validations.ValidationRule;
import com.AlphaDevs.Helpers.Error.ValidationError;
import com.AlphaDevs.Web.Entities.CustomerCategory;
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
public class MihiValidationRule extends StandardValidationRule{

       
    @Override
    public void validatePage(Object validatingObject) {    
        
        CustomerCategory custCat =  (CustomerCategory) validatingObject;
        
        if(custCat.getDescription().length() >5 ){
            addValidation("CUST VLD 0.1","This is Custopm Validation");
        }
        
    }

}
