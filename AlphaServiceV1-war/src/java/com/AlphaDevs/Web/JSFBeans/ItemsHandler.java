

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Design;
import com.AlphaDevs.Web.Entities.Items;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Entities.Stock;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.SessionBean.DesignController;
import com.AlphaDevs.Web.SessionBean.ItemsController;
import com.AlphaDevs.Web.SessionBean.LoggerController;
import com.AlphaDevs.Web.SessionBean.StockController;
import java.util.ArrayList;
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
public class ItemsHandler 
{
    @EJB
    private LoggerController loggerController;
    @EJB
    private DesignController designController;
    @EJB
    private StockController stockController;
    
    @EJB
    private ItemsController itemsController;
    
    
    private Items current;

    public ItemsHandler() 
    {
        if(current==null)
        {
            current = new Items();
        }
    }

    public Items getCurrent() {
        return current;
    }

    public void setCurrent(Items current) {
        this.current = current;
    }
    
    public List<Items> getList()
    {
        return itemsController.findAll();
    }
    
    public String persistItem()
    {
        Logger Log = EntityHelper.createLogger("Create Item", current.getItemCode(), TransactionTypes.ITEM);
        loggerController.create(Log);
        current.setLogger(Log);
        itemsController.create(current);
        Stock stock = new Stock();
        stock.setSockItem(current);
        stock.setStockLocation(current.getItemLocation());
        stock.setStockQty(0);
        stockController.create(stock);
        return "Home";
    }
    
    public List<Design> getListSpec()
    {
        return designController.findSpecific(current.getItemProduct());
    }
    
    public List<String> complete(String q)
    {
        List<String> tmpList = new ArrayList<String>();
        
        for(Items it :itemsController.findLike(q) )
        {
            tmpList.add(it.getItemName() + " - " + it.getItemCode() + " - " + it.getItemDescription());
            System.out.println("Got it : " + it.getItemName() + it.getItemCode());
        }
        System.out.println("Hope" + q);
        return tmpList;
    }

    public List<Items> completetest(String q)
    {
        return itemsController.findLike(q);
        
    }
}
