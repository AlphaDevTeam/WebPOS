
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Engines.Validations.Standards.StandardValidationRule;
import com.AlphaDevs.Engines.Validations.ValidationRule;
import com.AlphaDevs.Helpers.Error.ValidationError;
import com.AlphaDevs.Web.Entities.CustomerCategory;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.Helpers.MessageHelper;
import com.AlphaDevs.Web.SessionBean.CustomerCategoryController;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@SessionScoped
public class CustomerCategoryHandler 
{
    @EJB
    private CustomerCategoryController customerCategoryController;
    
    
    private CustomerCategory current;

    public CustomerCategoryHandler() 
    {
        if(current == null)
        {
            current = new CustomerCategory();
        }
    }

    public CustomerCategory getCurrent() {
        return current;
    }

    public void setCurrent(CustomerCategory current) {
        this.current = current;
    }
    
    public String createCustomerCategory()
    {
        try
        {
            ValidationRule validation = new StandardValidationRule();
            validation.validatePage(current);
            List<ValidationError> errorList = new ArrayList<ValidationError>();
            errorList = validation.validateResults();
            System.out.println("Validating .... " + errorList);
            if(errorList.size() <= 0){
                System.out.println("Creating Log");
                Logger Log = EntityHelper.createLogger("Create Customer Category", "", TransactionTypes.CUSTOMERCAT);
                System.out.println("Creating Log....ok");
                if(Log != null)
                {
                    System.out.println("Log Validate");
                    current.setLogger(Log);
                    customerCategoryController.create(current);
                    MessageHelper.addSuccessMessage("Customer Category Added!");
                    return "Home";

                }
                else
                {
                    MessageHelper.addErrorMessage("Error - createCustomerCategory", "Session Invalid");
                    return "Login";
                }
            }else{
                for(ValidationError validationError : errorList){
                    MessageHelper.addErrorMessage("Validation Error " + validationError.getErrorTitle() ,validationError.getErrorCode() + " - " + validationError.getErrorDetails());
                }
                
                return "Login";
            }
            
        }
        catch(Exception e)
        {
            MessageHelper.addErrorMessage("Error - createCustomerCategory", e.toString());
            return "Login";
        }
        
    }
    
    public List<CustomerCategory> getList()
    {
        return customerCategoryController.findAll();
    }
    
    
    public CustomerCategoryController getCustomerCategoryController() {
        return customerCategoryController;
    }

    public void setCustomerCategoryController(CustomerCategoryController customerCategoryController) {
        this.customerCategoryController = customerCategoryController;
    }
    
    
        
}
