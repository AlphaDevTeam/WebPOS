

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.CustomerTransaction;
import com.AlphaDevs.Web.SessionBean.CustomerTransactionController;
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
public class CustomerTransactionHandler 
{
    @EJB
    private CustomerTransactionController customerTransactionController;
    
    private CustomerTransaction current;

    /** Creates a new instance of CustomerTransactionHandler */
    public CustomerTransactionHandler() 
    {
        if(current == null )
        {
            current = new CustomerTransaction();
        }
    }

    public CustomerTransaction getCurrent() {
        return current;
    }

    public void setCurrent(CustomerTransaction current) {
        this.current = current;
    }
    
    public List<CustomerTransaction> getList()
    {
        return customerTransactionController.findAll();
    }

}
