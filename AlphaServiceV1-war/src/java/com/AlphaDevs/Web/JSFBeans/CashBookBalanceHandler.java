

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.CashBookBalance;
import com.AlphaDevs.Web.SessionBean.CashBookBalanceController;
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
public class CashBookBalanceHandler 
{
    @EJB
    private CashBookBalanceController cashBookBalanceController;
    
    private CashBookBalance current;

    /** Creates a new instance of CashBookBalanceHandler */
    public CashBookBalanceHandler() 
    {
        if(current == null)
        {
            current = new CashBookBalance();
        }
    }

    public CashBookBalance getCurrent() {
        return current;
    }

    public void setCurrent(CashBookBalance current) {
        this.current = current;
    }
    
    

}
