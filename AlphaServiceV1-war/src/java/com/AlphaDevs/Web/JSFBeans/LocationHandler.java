

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.CashBookBalance;
import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Enums.BillStatus;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.SessionBean.CashBookBalanceController;
import com.AlphaDevs.Web.SessionBean.LocationController;
import com.AlphaDevs.Web.SessionBean.LoggerController;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
@SessionScoped
public class LocationHandler 
{
    @EJB
    private CashBookBalanceController cashBookBalanceController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private LocationController locationController;
    
    
    
    private Location current;
    
    public LocationHandler() 
    {
        if(current == null)
        {
            current = new Location();
        }
    }
    
    public List<Location> getList()
    {
        return locationController.findAll();
    }

    public Location getCurrent() {
        return current;
    }

    public void setCurrent(Location current) {
        this.current = current;
    }
    
    public String persistLocation()
    {
        Logger Log = EntityHelper.createLogger("Create Location", current.getCode(), TransactionTypes.LOCATION);
        loggerController.create(Log);
        current.setLogger(Log);
        locationController.create(current);
        
        CashBookBalance BalanceTax = new CashBookBalance(current, 0, 0, BillStatus.TAX);
        CashBookBalance BalanceNonTax = new CashBookBalance(current, 0, 0, BillStatus.NON_TAX);
        
        cashBookBalanceController.create(BalanceTax);
        cashBookBalanceController.create(BalanceNonTax);
        
        
        return "#";
    }
    
}
