

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.ItemBincard;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Entities.MeterReading;
import com.AlphaDevs.Web.Entities.Stock;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.SessionBean.ItemBincardController;
import com.AlphaDevs.Web.SessionBean.LoggerController;
import com.AlphaDevs.Web.SessionBean.MeterReadingController;
import com.AlphaDevs.Web.SessionBean.StockController;
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
public class MeterReadingHandler {
   
    @EJB
    private ItemBincardController itemBincardController;
    @EJB
    private StockController stockController;
    
    @EJB
    private LoggerController loggerController;
    
    
    @EJB
    private MeterReadingController meterReadingController;

    private MeterReading current;
    
    public MeterReadingHandler() {
        current = new MeterReading();
    }

    public MeterReadingController getMeterReadingController() {
        return meterReadingController;
    }

    public void setMeterReadingController(MeterReadingController meterReadingController) {
        this.meterReadingController = meterReadingController;
    }

    public MeterReading getCurrent() {
        return current;
    }

    public void setCurrent(MeterReading current) {
        this.current = current;
    }

    public List<MeterReading> getList(){
        return getMeterReadingController().findAll();
    }
    
    public String saveMeterReading(){
        
        Logger Log = EntityHelper.createLogger("Meter Reading", current.getNote(), TransactionTypes.READINGS);
        loggerController.create(Log);
        current.setLogger(Log);
        Stock stock = stockController.findSpecific(current.getRelatedItem());
        stock.setStockQty((float) (stock.getStockQty() - current.getReading()));
        
        stockController.edit(stock);
        
        ItemBincard itemBin = new ItemBincard();
        itemBin.setDescription("Meter Reading - " + current.getNote());
        itemBin.setItem(current.getRelatedItem());
        itemBin.setTrnNumber(current.getReferenceNumber());
        itemBin.setQty((current.getReading() * -1));
        itemBin.setRelatedDate(current.getRelatedDate());
        itemBin.setLog(Log);
        itemBin.setBalance(stock.getStockQty());
        itemBincardController.create(itemBin);
        
        meterReadingController.create(current);
//        itemsController.create(current);
//         new Stock();
//        stock.setSockItem(current);
//        stock.setStockLocation(current.getItemLocation());
//        stock.setStockQty(0);
//        stockController.create(stock);
        return "Home";
        
    }
    
}
