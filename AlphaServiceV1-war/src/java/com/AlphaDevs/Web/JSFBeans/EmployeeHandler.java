
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Employee;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.Helpers.MessageHelper;
import com.AlphaDevs.Web.SessionBean.EmployeeController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@ManagedBean
@RequestScoped
public class EmployeeHandler {
    @EJB
    private EmployeeController employeController;
    
    private Employee current;

    public EmployeeHandler() {
        if(current == null){
            current = new Employee();
        }
    }
    
    public List<Employee> getList(){
        return employeController.findAll();
    }

    public EmployeeController getEmployeController() {
        return employeController;
    }

    public void setEmployeController(EmployeeController employeController) {
        this.employeController = employeController;
    }

    public Employee getCurrent() {
        return current;
    }

    public void setCurrent(Employee current) {
        this.current = current;
    }
    
    public String createEmploye()
    {
        try
        {
            System.out.println("Creating Log");
            Logger Log = EntityHelper.createLogger("Create Employee ", "", TransactionTypes.EMPLOYEE);
            System.out.println("Creating Log....ok");
            if(Log != null)
            {
                System.out.println("Log Validate");
                current.setLogger(Log);
                employeController.create(current);
                MessageHelper.addSuccessMessage("Employe Added!");
                return "Home";
                
            }
            else
            {
                MessageHelper.addErrorMessage("Error - createEmploye", "Session Invalid");
                return "Login";
            }
        
            
            
        }
        catch(Exception e)
        {
            MessageHelper.addErrorMessage("Error - createEmploye", e.toString());
            return "Login";
        }
        
    }
}
