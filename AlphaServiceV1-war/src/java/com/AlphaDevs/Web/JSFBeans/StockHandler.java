
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Items;
import com.AlphaDevs.Web.Entities.Stock;
import com.AlphaDevs.Web.SessionBean.StockController;
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
public class StockHandler 
{
    @EJB
    private StockController stockController;
    
    private Stock current;

    public StockHandler() 
    {
        if(current == null)
        {
            current = new Stock();
        }
    }
    
    public List<Stock> getList()
    {
        return stockController.findAll();
    }

    public Stock getCurrent() {
        return current;
    }

    public void setCurrent(Stock current) {
        this.current = current;
    }

    public Stock getSpecificStock(Items item)
    {
        return stockController.findSpecific(item);
    }
    

}
