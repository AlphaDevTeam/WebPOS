

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.*;
import com.AlphaDevs.Web.Enums.BillStatus;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.Helpers.MessageHelper;
import com.AlphaDevs.Web.SessionBean.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@SessionScoped
public class PurchaseReturnHandler 
{
    @EJB
    private CustomerTransactionController customerTransactionController;
    @EJB
    private CustomerBalanceController customerBalanceController;
    @EJB
    private CashbookController cashbookController;
    @EJB
    private CashBookBalanceController cashBookBalanceController;
    @EJB
    private ItemBincardController itemBincardController;
    @EJB
    private StockController stockController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private PurchaseReturnController purchaseReturnController;
    
    private PurchaseReturn current;
    private PurchaseReturnDetails currentDetails;
    private List<PurchaseReturnDetails> VirtualList;
    private PurchaseReturnDetails selectedGrnData;
    
    public PurchaseReturnHandler() 
    {
        if(current == null)
        {
            current = new PurchaseReturn();
        }
        if(currentDetails == null)
        {
            currentDetails = new PurchaseReturnDetails();
        }
        if(selectedGrnData == null)
        {
            selectedGrnData = new PurchaseReturnDetails();
        }
        
        VirtualList = new ArrayList<PurchaseReturnDetails>();
    }

    public List<PurchaseReturnDetails> getVirtualList() {
        return VirtualList;
    }

    public void setVirtualList(List<PurchaseReturnDetails> VirtualList) {
        this.VirtualList = VirtualList;
    }

    public PurchaseReturnDetails getCurrentDetails() {
        return currentDetails;
    }

    public void setCurrentDetails(PurchaseReturnDetails currentDetails) {
        this.currentDetails = currentDetails;
    }

    public PurchaseReturnController getPurchaseReturnController() {
        return purchaseReturnController;
    }

    public void setPurchaseReturnController(PurchaseReturnController purchaseReturnController) {
        this.purchaseReturnController = purchaseReturnController;
    }

    public PurchaseReturnDetails getSelectedGrnData() {
        return selectedGrnData;
    }

    public void setSelectedGrnData(PurchaseReturnDetails selectedGrnData) {
        this.selectedGrnData = selectedGrnData;
    }
    
    public List<PurchaseReturn> getList()
    {
        return purchaseReturnController.findAll();
    }

    public PurchaseReturn getCurrent() {
        return current;
    }

    public void setCurrent(PurchaseReturn current) {
        this.current = current;
    }
    
    public void handleSelect(SelectEvent event) 
    {   
        currentDetails.setGrnItemCost(currentDetails.getGrnItem().getItemCost());
    }  
  
    public void onEdit(RowEditEvent event) 
    {   
        
        MessageHelper.addSuccessMessage(((PurchaseReturnDetails) event.getObject()).getGrnItem().getItemCode() + " Updated!");
        for(PurchaseReturnDetails details : getVirtualList())
        {
            PurchaseReturnDetails purchRetDetails  = new PurchaseReturnDetails();
            purchRetDetails = (PurchaseReturnDetails)event.getObject();
            if(details.getGrnItem().getId() == purchRetDetails.getGrnItem().getId() )
            {
                
                details.setGrnQty(purchRetDetails.getGrnQty());
                details.setGrnItemCost(purchRetDetails.getGrnItemCost());
                details.setGrnItemDiscountPerc(purchRetDetails.getGrnItemDiscountPerc());
            }
        }
        
        current.setTotalAmount(calculateTotal());
    }  
    
    public void onCancel(RowEditEvent event) 
    {  
        MessageHelper.addSuccessMessage(((PurchaseReturnDetails) event.getObject()).getGrnItem().getItemCode() + "Not Updated!");
        
    }
    
    public Double calculateTotal()
    {
        double TotalValue=0;
        for(PurchaseReturnDetails details : getVirtualList())
        {
            TotalValue = TotalValue +  (details.getGrnItemCost() * details.getGrnQty());
        }
        return TotalValue;
    }
    
    public void addItem()
    {
        boolean isFound =false;
        
        if(currentDetails.getGrnItem() == null || currentDetails.getGrnItemCost() ==0 || currentDetails.getGrnQty() == 0)
        {
            MessageHelper.addErrorMessage("Error", "Please Fill All Details");
            return;
        }
        
        currentDetails.setRelatedPurchaseRet(current);
        
        for(PurchaseReturnDetails Retdetails : getVirtualList())
        {
            if(Retdetails.getGrnItem().getId() == currentDetails.getGrnItem().getId())
            {
                Retdetails.setGrnQty(Retdetails.getGrnQty() + currentDetails.getGrnQty());
                isFound = true;
                break;
            }
            
        }
        if (!isFound)
        {
            getVirtualList().add(currentDetails);
        }
        
        current.setTotalAmount(calculateTotal());
        currentDetails = new PurchaseReturnDetails();
    }
    
    public String removeItem(PurchaseReturnDetails RetDetails)
    {
        
        for(int a=0;a<getVirtualList().size();a++)
        {
        
            if(getVirtualList().get(a) == RetDetails )
            {
                getVirtualList().remove(a);

                break;
            }
        }
        current.setTotalAmount(calculateTotal());
        
        return "#";
    }
    
    public String removeSelectedItem()
    {
        if ( getSelectedGrnData() ==null)
        {
            MessageHelper.addErrorMessage("Selection ","No item Selected ");
            return "";
        }
        else
        {
            for(int a=0;a<getVirtualList().size();a++)
            {

                if(getVirtualList().get(a) == getSelectedGrnData() )
                {
                    getVirtualList().remove(a);

                    break;
                }
            }
            current.setTotalAmount(calculateTotal());
        }
        return "#";
    }
    
    public void printReport() 
    {
        try
        {
            List<PurchaseReturn> ReturnList = new ArrayList<PurchaseReturn>();
            PurchaseReturn PurRet = (PurchaseReturn) purchaseReturnController.find(current.getId());
            ReturnList.add(PurRet);
            JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(ReturnList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/PurchaseRetReport.jasper");
            JasperPrint jasPrint =  JasperFillManager.fillReport(reportPath, new HashMap(), beanCollection);
            HttpServletResponse responce = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            responce.setContentType("application/pdf");
            String filename = new StringBuffer(reportPath).append(".pdf").toString();  
            ServletOutputStream output = responce.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasPrint, output);
            responce.addHeader("Content-Disposition", "inline; filename="+ filename);  
            //responce.addHeader("Content-disposition", "attachment; filename=CashBook.pdf");
            FacesContext.getCurrentInstance().responseComplete();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public String grnReturn()
    {
        if(getVirtualList().isEmpty())
        {
            MessageHelper.addErrorMessage("Error", "No Items Found!");
            return "";
        }
        
        Logger log = EntityHelper.createLogger("Purchase Return " , current.getGrnRetNo(), TransactionTypes.GRNRETURN);
        loggerController.create(log);
        
        
        current.setBillStatus(BillStatus.TAX);
        
        
        current.setPurchaceRtnDetails(VirtualList);
        current.setLogger(log);
        purchaseReturnController.create(current);
        
        
        for(PurchaseReturnDetails PurRet : getVirtualList())
        {
           PurRet.setRelatedPurchaseRet(current);
           
           Stock  stock = stockController.findSpecific(PurRet.getGrnItem());
           stock.setStockQty(stock.getStockQty() - PurRet.getGrnQty());
           stockController.edit(stock);
           
           ItemBincard itemBin = new ItemBincard();
           itemBin.setDescription("Purchase Return - " + current.getGrnRetNo());
           itemBin.setItem(PurRet.getGrnItem());
           itemBin.setTrnNumber(current.getInvNo());
           itemBin.setQty(PurRet.getGrnQty() * -1);
           itemBin.setLog(log);
           itemBin.setBalance(stock.getStockQty());
           itemBincardController.create(itemBin);
           
        }
        
        //Customer Transaction
        CustomerTransaction custTran = new CustomerTransaction();
        custTran.setDescription("Purchase Return - " + current.getGrnRetNo());
        custTran.setSupplier(current.getSupplier());
        custTran.setDR(0);
        custTran.setCR(current.getTotalAmount());
        
        //Getting Cust Balance
        CustomerBalance Balance = customerBalanceController.getCustomerBalanceObject(current.getSupplier());
        if(Balance != null)
        {
            Balance.setBalance(Balance.getBalance() - current.getTotalAmount() );
            customerBalanceController.edit(Balance);
            custTran.setBalance(Balance.getBalance());
            
        }
        else
        {
            custTran.setBalance(current.getTotalAmount());
        }
        
        custTran.setLogger(log);
        customerTransactionController.create(custTran);
        
        /*    
        //Cashbook
        CashBook cashBook = new CashBook();
        cashBook.setDescription("Purchase Return - " + current.getGrnRetNo());
        cashBook.setCR(0);
        cashBook.setDR(current.getTotalAmount());
        cashBook.setLocation(current.getLocation());
        cashBook.setLogger(log);

        CashBookBalance cashBalance = cashBookBalanceController.getCashBookBalanceObject(current.getLocation(), current.getBillStatus());
        if(cashBalance != null)
        {
            
            cashBalance.setCashBalance(cashBalance.getCashBalance() - current.getTotalAmount());
            cashBookBalanceController.edit(cashBalance);
            cashBook.setBalance(cashBalance.getCashBalance());            
        }
        else
        {
            cashBook.setBalance(current.getTotalAmount());
        }
         
        cashbookController.create(cashBook);
        * 
        * 
        * 
        */
        
        printReport();
        current = new PurchaseReturn();
        currentDetails = new PurchaseReturnDetails();
        VirtualList = new ArrayList<PurchaseReturnDetails>();
        return "Home";
    }
    

}
