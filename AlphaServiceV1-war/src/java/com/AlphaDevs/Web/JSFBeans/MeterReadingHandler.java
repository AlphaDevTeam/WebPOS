

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.ItemBincard;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Entities.MeterReading;
import com.AlphaDevs.Web.Entities.Pump;
import com.AlphaDevs.Web.Entities.Stock;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.SessionBean.ItemBincardController;
import com.AlphaDevs.Web.SessionBean.LoggerController;
import com.AlphaDevs.Web.SessionBean.MeterReadingController;
import com.AlphaDevs.Web.SessionBean.PumpController;
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
public class MeterReadingHandler {
    @EJB
    private PumpController pumpController;
   
    @EJB
    private ItemBincardController itemBincardController;
    @EJB
    private StockController stockController;
    
    @EJB
    private LoggerController loggerController;
    
    
    @EJB
    private MeterReadingController meterReadingController;
    

    private MeterReading current;
    private double currentReading;
    
    public MeterReadingHandler() {
        current = new MeterReading();
    }

    public MeterReadingController getMeterReadingController() {
        return meterReadingController;
    }

    public PumpController getPumpController() {
        return pumpController;
    }

    public void setPumpController(PumpController pumpController) {
        this.pumpController = pumpController;
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
    
    public double getLastReading(){
        //System.out.println("S" + getCurrent().getRelatedItem() + " - " + getCurrent().getRelatedLocation());
        //return getMeterReadingController().findReadingByItem(getCurrent().getRelatedPump().getRelatedItem(),getCurrent().getRelatedLocation());
        //return getPumpController().findReadingByPump(getCurrent().getRelatedPump(), getCurrent().getRelatedLocation());
        if(getCurrent() != null && getCurrent().getRelatedPump()!= null && getCurrent().getRelatedPump().getLastReading() != null ){
            return getCurrent().getRelatedPump().getLastReading();
        }else{
            return 0;
        }
        
    }
    
    public List<Pump> getPumpListAccordingToLocation(){
        if(getCurrent() != null && getCurrent().getRelatedLocation() != null ){
            return getPumpController().findReadingByPump(getCurrent().getRelatedLocation());
        }else{
            return new ArrayList<Pump>();
        }
        
    }
    

    public ItemBincardController getItemBincardController() {
        return itemBincardController;
    }

    public void setItemBincardController(ItemBincardController itemBincardController) {
        this.itemBincardController = itemBincardController;
    }

    public StockController getStockController() {
        return stockController;
    }

    public void setStockController(StockController stockController) {
        this.stockController = stockController;
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    public double getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(double currentReading) {
        this.currentReading = currentReading;
    }
    public void calculateReading(){
        //System.out.println("awas"  + getLastReading() + " -- " + getCurrentReading());
        getCurrent().setReading(getCurrentReading() - getLastReading());
    }
    
    public String saveMeterReading(){
        
        Logger Log = EntityHelper.createLogger("Meter Reading", current.getNote(), TransactionTypes.READINGS);
        loggerController.create(Log);
        current.setLogger(Log);
        Stock stock = stockController.findSpecific(current.getRelatedPump().getRelatedItem());
        stock.setStockQty((float) (stock.getStockQty() - current.getReading()));
        
        stockController.edit(stock);
        
        ItemBincard itemBin = new ItemBincard();
        itemBin.setDescription("Meter Reading - " + getCurrent().getRelatedPump() + " - " + current.getNote());
        itemBin.setItem(current.getRelatedPump().getRelatedItem());
        itemBin.setTrnNumber(current.getReferenceNumber());
        itemBin.setQty((current.getReading() * -1));
        itemBin.setRelatedDate(current.getRelatedDate());
        itemBin.setLog(Log);
        itemBin.setBalance(stock.getStockQty());
        itemBincardController.create(itemBin);
        
        meterReadingController.create(current);
        return "Home";
        
    }
    
     
    
}
