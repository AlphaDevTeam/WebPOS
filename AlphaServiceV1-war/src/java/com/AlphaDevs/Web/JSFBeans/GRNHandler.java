
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.*;
import com.AlphaDevs.Web.Enums.BillStatus;
import com.AlphaDevs.Web.Enums.ChequeStatus;
import com.AlphaDevs.Web.Enums.CreditCardReceiptStatus;
import com.AlphaDevs.Web.Enums.Document;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.Helpers.MessageHelper;
import com.AlphaDevs.Web.Helpers.SessionDataHelper;
import com.AlphaDevs.Web.SessionBean.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

@ManagedBean
@SessionScoped
public class GRNHandler 
{
    @EJB
    private CreditCardReceiptsController creditCardReceiptsController;
    @EJB
    private ChequesController chequesController;
    @EJB
    private SystemNumbersController systemNumbersController;
    @EJB
    private PropertiesController propertiesController;
    
    @EJB
    private PropertyManagerController propertyManagerController;
    @EJB
    private GRNPaymentDetailsController gRNPaymentDetailsController;
    @EJB
    private CashBookBalanceController cashBookBalanceController;
    @EJB
    private CustomerBalanceController customerBalanceController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private CashbookController cashbookController;
    @EJB
    private ItemBincardController itemBincardController;
    @EJB
    private CustomerTransactionController customerTransactionController;
    @EJB
    private StockController stockController;
    @EJB
    private GrnDetailsController grnDetailsController;
    @EJB
    private SystemsController systemsController;
    @EJB
    private GRNController gRNController;
    
    private Document currentDocument;
    
    private double cashAmount;
    
    private GRN current;
    private GRNDetails currentDetails;
    private List<GRNDetails> VirtualList;
    private GRNDetails selectedGrnData;
    private GRNPaymentDetails paymentDetails;
    private Cheques receivedCheque; 
    private CreditCardReceipts receivedCreditCardReceipts;
    
    private SystemNumbers currentSystemNumber;
    
    public GRNHandler() {
        
        currentDocument = Document.GOOD_RECEIPT_NOTE;
                
        if (current == null){
            current = new GRN();
        }
        if(currentDetails == null)
        {
            currentDetails = new GRNDetails();
        }
        if(selectedGrnData == null)
        {
            selectedGrnData = new GRNDetails();
        }
        if(paymentDetails == null)
        {
            paymentDetails = new GRNPaymentDetails();
        }
        
        receivedCheque = new Cheques();
        receivedCreditCardReceipts = new CreditCardReceipts();
       
        VirtualList = new ArrayList<GRNDetails>();
        
        cashAmount = 0;
            
    }
    
    public String getGrnNumber() {
        currentSystemNumber = null;
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get("User");
        if (loggedUser != null && getCurrent().getLocation() != null) {
            List<SystemNumbers> systemNumbers = systemNumbersController.findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getLocation(), currentDocument);
            if (systemNumbers != null && !systemNumbers.isEmpty()) {
                currentSystemNumber = systemNumbers.get(0);
                getCurrent().setGrnNo(currentSystemNumber.getDocumentSystemNo());
            }
        }
        return currentSystemNumber != null ? currentSystemNumber.getDocumentSystemNo() : "";
    }

    public CreditCardReceiptsController getCreditCardReceiptsController() {
        return creditCardReceiptsController;
    }

    public void setCreditCardReceiptsController(CreditCardReceiptsController creditCardReceiptsController) {
        this.creditCardReceiptsController = creditCardReceiptsController;
    }

    public CreditCardReceipts getReceivedCreditCardReceipts() {
        return receivedCreditCardReceipts;
    }

    public void setReceivedCreditCardReceipts(CreditCardReceipts receivedCreditCardReceipts) {
        this.receivedCreditCardReceipts = receivedCreditCardReceipts;
    }
    
    public ChequesController getChequesController() {
        return chequesController;
    }

    public Cheques getReceivedCheque() {
        return receivedCheque;
    }

    public void setReceivedCheque(Cheques receivedCheque) {
        this.receivedCheque = receivedCheque;
    }

    public void setChequesController(ChequesController chequesController) {
        this.chequesController = chequesController;
    }

    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public PropertiesController getPropertiesController() {
        return propertiesController;
    }

    public void setPropertiesController(PropertiesController propertiesController) {
        this.propertiesController = propertiesController;
    }

    public PropertyManagerController getPropertyManagerController() {
        return propertyManagerController;
    }

    public void setPropertyManagerController(PropertyManagerController propertyManagerController) {
        this.propertyManagerController = propertyManagerController;
    }

    public GRNPaymentDetailsController getgRNPaymentDetailsController() {
        return gRNPaymentDetailsController;
    }

    public void setgRNPaymentDetailsController(GRNPaymentDetailsController gRNPaymentDetailsController) {
        this.gRNPaymentDetailsController = gRNPaymentDetailsController;
    }

    public CashBookBalanceController getCashBookBalanceController() {
        return cashBookBalanceController;
    }

    public void setCashBookBalanceController(CashBookBalanceController cashBookBalanceController) {
        this.cashBookBalanceController = cashBookBalanceController;
    }

    public CustomerBalanceController getCustomerBalanceController() {
        return customerBalanceController;
    }

    public void setCustomerBalanceController(CustomerBalanceController customerBalanceController) {
        this.customerBalanceController = customerBalanceController;
    }

    public LoggerController getLoggerController() {
        return loggerController;
    }

    public void setLoggerController(LoggerController loggerController) {
        this.loggerController = loggerController;
    }

    public CashbookController getCashbookController() {
        return cashbookController;
    }

    public void setCashbookController(CashbookController cashbookController) {
        this.cashbookController = cashbookController;
    }

    public ItemBincardController getItemBincardController() {
        return itemBincardController;
    }

    public void setItemBincardController(ItemBincardController itemBincardController) {
        this.itemBincardController = itemBincardController;
    }

    public CustomerTransactionController getCustomerTransactionController() {
        return customerTransactionController;
    }

    public void setCustomerTransactionController(CustomerTransactionController customerTransactionController) {
        this.customerTransactionController = customerTransactionController;
    }

    public StockController getStockController() {
        return stockController;
    }

    public void setStockController(StockController stockController) {
        this.stockController = stockController;
    }

    public GrnDetailsController getGrnDetailsController() {
        return grnDetailsController;
    }

    public void setGrnDetailsController(GrnDetailsController grnDetailsController) {
        this.grnDetailsController = grnDetailsController;
    }

    public SystemsController getSystemsController() {
        return systemsController;
    }

    public void setSystemsController(SystemsController systemsController) {
        this.systemsController = systemsController;
    }

    public GRNController getgRNController() {
        return gRNController;
    }

    public void setgRNController(GRNController gRNController) {
        this.gRNController = gRNController;
    }

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public void setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
    }

    public SystemNumbers getCurrentSystemNumber() {
        return currentSystemNumber;
    }

    public void setCurrentSystemNumber(SystemNumbers currentSystemNumber) {
        this.currentSystemNumber = currentSystemNumber;
    }
    
    public void setGrnNumber(String grnNumber){
        getCurrent().setGrnNo(grnNumber);
    }

    public GRNPaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(GRNPaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
    

    public GRNDetails getSelectedGrnData() {
        return selectedGrnData;
    }

    public void setSelectedGrnData(GRNDetails selectedGrnData) {
        this.selectedGrnData = selectedGrnData;
    }
    
    

    public GRN getCurrent() {
        return current;
    }

    public void setCurrent(GRN current) {
        this.current = current;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }
    
    public void handleSelect(SelectEvent event) 
    {   
        currentDetails.setGrnItemCost(currentDetails.getGrnItem().getItemCost());
    }  
    
    public String addTEst(){
        
        
        if (getVirtualList().isEmpty()) {
            MessageHelper.addErrorMessage("Error", "No Items Found!");
            return "";
        }

        Logger log = EntityHelper.createLogger("Good Received Note", getCurrent().getGrnNo(), TransactionTypes.GRN);
        loggerController.create(log);

        getCurrent().setBillStatus(BillStatus.TAX);
        getCurrent().setgRNDetailss(VirtualList);
        getCurrent().setLogger(log);
        
        getCurrent().setgRNPaymentDetails(getPaymentDetails());
        getgRNController().create(getCurrent());
        
        for (Properties properties : getCurrent().getExtraz()) {
            System.out.println("Trying Saving ... " + properties.getPropertyName() + " : " + properties.getPropertyValue());
            properties.setRelatedGRNs(getCurrent());
            propertiesController.create(properties);
            System.out.println("Done");
        }

        for (GRNDetails grnDet : getVirtualList()) {
            grnDet.setRelatedGRN(getCurrent());
            //grnDetailsController.create(grnDet); 

            Stock stock = stockController.findSpecific(grnDet.getGrnItem());
            stock.setStockQty(stock.getStockQty() + grnDet.getGrnQty());
            stockController.edit(stock);

            ItemBincard itemBin = new ItemBincard();
            itemBin.setDescription("Good Received Note - " + getCurrent().getGrnNo());
            itemBin.setItem(grnDet.getGrnItem());
            itemBin.setRelatedDate(getCurrent().getGrnDate());
            itemBin.setTrnNumber(getCurrent().getInvNo());
            itemBin.setQty(grnDet.getGrnQty());
            itemBin.setLog(log);
            itemBin.setBalance(stock.getStockQty());
            itemBincardController.create(itemBin);

        }

        //Customer Transaction
        CustomerTransaction custTran = new CustomerTransaction();
        custTran.setDescription("GRN - " + getCurrent().getGrnNo());
        custTran.setSupplier(getCurrent().getSupplier());
        custTran.setDR(getCurrent().getTotalAmount());
        custTran.setCR(0);

        //Getting Cust Balance
        CustomerBalance Balance = customerBalanceController.getCustomerBalanceObject(getCurrent().getSupplier());
        if (Balance != null) {
            Balance.setBalance(Balance.getBalance() + getCurrent().getTotalAmount());
            customerBalanceController.edit(Balance);
            custTran.setBalance(Balance.getBalance());

        } else {
            Balance= new CustomerBalance(getCurrent().getSupplier(), getCurrent().getTotalAmount());
            getCustomerBalanceController().create(Balance);
            
            custTran.setBalance(paymentDetails.getCashAmount());
        }

        custTran.setLogger(log);
        customerTransactionController.create(custTran);


        if (getPaymentDetails() != null) {
            
            getPaymentDetails().setTotalAmount(getCurrent().getTotalAmount());
            getPaymentDetails().setLogger(log);
            getPaymentDetails().setRelatedGrn(getCurrent());

            if(getReceivedCheque() != null && getReceivedCheque().getChequeAmount() != null ){
                Cheques relatedCheque = getReceivedCheque();
                if (relatedCheque.getChequeAmount() > 0) {
                    relatedCheque.setStatus(ChequeStatus.PENDING);
                    relatedCheque.setRelatedLocation(getCurrent().getLocation());
                    relatedCheque.setgRNPaymentDetails(getPaymentDetails());
                    getPaymentDetails().setChequeAmount(getReceivedCheque().getChequeAmount());
                    getPaymentDetails().setRelatedCheque(relatedCheque);
                    getChequesController().create(relatedCheque);
                }
            }
            
            if(getReceivedCreditCardReceipts() != null){
                CreditCardReceipts relatedCreditCardReceipt = getReceivedCreditCardReceipts();
                if (relatedCreditCardReceipt.getAmount()> 0) {
                    relatedCreditCardReceipt.setReceiptStatus(CreditCardReceiptStatus.RECEIVED);
                    relatedCreditCardReceipt.setRelatedLocation(getCurrent().getLocation());
                    relatedCreditCardReceipt.setReceiptDate(getCurrent().getGrnDate());
                    relatedCreditCardReceipt.setRelatedGRNPaymentDetails(getPaymentDetails());
                    relatedCreditCardReceipt.setRelatedLogger(log);
                    getPaymentDetails().setCreditCardAmount(relatedCreditCardReceipt.getAmount());
                    getPaymentDetails().setRelatedCreditCardReceipts(relatedCreditCardReceipt);
                    getCreditCardReceiptsController().create(relatedCreditCardReceipt);
                }
            }
            
            if (getPaymentDetails().getCashAmount() > 0) {
                CustomerTransaction custTranPaid = new CustomerTransaction();
                custTranPaid.setDescription("PAID - " + getCurrent().getGrnNo());
                custTranPaid.setSupplier(getCurrent().getSupplier());
                custTranPaid.setDR(0);
                custTranPaid.setCR(getPaymentDetails().getCashAmount());

                Balance.setBalance(Balance.getBalance() - getPaymentDetails().getCashAmount());
                getCustomerBalanceController().edit(Balance);
                custTranPaid.setBalance(Balance.getBalance());
                custTranPaid.setLogger(log);
                getCustomerTransactionController().create(custTranPaid);

                //Cashbook
                CashBook cashBook = new CashBook();
                cashBook.setDescription("Purchase - " + getCurrent().getGrnNo());
                cashBook.setCR(getPaymentDetails().getCashAmount());
                cashBook.setDR(0);
                cashBook.setLocation(getCurrent().getLocation());
                cashBook.setLogger(log);

                CashBookBalance cashBalance = getCashBookBalanceController().getCashBookBalanceObject(getCurrent().getLocation(), getCurrent().getBillStatus());
                if (cashBalance != null) {
                    cashBalance.setCashBalance(cashBalance.getCashBalance() - getPaymentDetails().getCashAmount());
                    getCashBookBalanceController().edit(cashBalance);
                    cashBook.setBalance(cashBalance.getCashBalance());
                } else {
                    cashBook.setBalance(getPaymentDetails().getCashAmount());
                }

                getCashbookController().create(cashBook);


            }

        }
        
        //getgRNPaymentDetailsController().create(getPaymentDetails());
       
        
        //Increment the the Document No 
        if(getCurrentSystemNumber() != null){
            getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getSystemNumber() + 1);
            getSystemNumbersController().edit(getCurrentSystemNumber());
        }
        
        getgRNController().edit(getCurrent());
        printReportDownload();
        setCurrent(new GRN());
        currentDetails = new GRNDetails();
        VirtualList = new ArrayList<GRNDetails>();
        setCashAmount(0);
        return "Home";
    }
    
    
    
    
    public List<GRN> getGrnList()
    {
        return gRNController.findAll();
    }
    
    public int getListCount(){
        return getCurrent().getExtraz().size();
    }
    
    public String rePrintGRN(){
        System.out.println("Reprinting GRN " + getCurrent().getId() + ":" + getCurrent().getGrnNo() );
        printReportSpecificGrn(getCurrent());
        return "Home";
    }
    
    public void printReportSpecificGrn(GRN printGrn) 
    {
        try
        {
            List<GRN> GrnList = new ArrayList<GRN>();
            GRN printableGrn = (GRN) gRNController.find(printGrn.getId());
            System.out.println("Grn : Added for Reprint " + printableGrn.getGrnNo() + " With :" + printableGrn.getId());
            GrnList.add(printableGrn);
            JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(GrnList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/GrnReport.jasper");
            System.out.println("Path : " + reportPath);
            JasperPrint jasPrint =  JasperFillManager.fillReport(reportPath, new HashMap(), beanCollection);
            HttpServletResponse responce = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            responce.setContentType("application/pdf");
            String filename = new StringBuffer(reportPath).append(".pdf").toString();  
            ServletOutputStream output = responce.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasPrint, output);
            responce.addHeader("Content-Disposition", "inline; filename="+ filename);  
            //responce.addHeader("Content-disposition", "attachment; filename=CashBook.pdf");
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("Report Done");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public void printReportDownload() 
    {
        try
        {
            List<GRN> GrnList = new ArrayList<GRN>();
            GRN nn = (GRN) gRNController.find(getCurrent().getId());
            System.out.println("Grn : re d " + nn.getGrnNo() + " With :" + nn.getId());
            GrnList.add(nn);
            JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(GrnList);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/GrnReport.jasper");
            System.out.println("Path : " + reportPath);
            JasperPrint jasPrint =  JasperFillManager.fillReport(reportPath, new HashMap(), beanCollection);
            HttpServletResponse responce = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            responce.setContentType("application/pdf");
            String filename = new StringBuffer(reportPath).append(".pdf").toString();  
            ServletOutputStream output = responce.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasPrint, output);
            responce.addHeader("Content-Disposition", "inline; filename="+ filename);  
            //responce.addHeader("Content-disposition", "attachment; filename=CashBook.pdf");
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("Report Done");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public String removeItem(GRNDetails details)
    {
        
        for(int a=0;a<getVirtualList().size();a++)
        {
        
            if(getVirtualList().get(a) == details )
            {
                getVirtualList().remove(a);

                break;
            }
        }
        getCurrent().setTotalAmount(getTotal());
        
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
            getCurrent().setTotalAmount(getTotal());
        }
        return "#";
    }
    
    public String loadRTE()
    {
        Systems sys = new Systems("Header");
        sys.setId(1L);
        systemsController.create(sys);
        System.out.println("Done");
        return "#";
    }

    public GRNDetails getCurrentDetails() {
        return currentDetails;
    }

    public void setCurrentDetails(GRNDetails currentDetails) {
        this.currentDetails = currentDetails;
    }

    public List<GRNDetails> getVirtualList() {
        return VirtualList;
    }

    public void setVirtualList(List<GRNDetails> VirtualList) {
        this.VirtualList = VirtualList;
    }
    
    public void addItem()
    {
        boolean isFound =false;
        
        if(currentDetails.getGrnItem() == null || currentDetails.getGrnItemCost() ==0 || currentDetails.getGrnQty() == 0)
        {
            MessageHelper.addErrorMessage("Error", "Please Fill All Details");
            return;
        }
        
        currentDetails.setRelatedGRN(getCurrent());
        
        for(GRNDetails det : getVirtualList())
        {
            if(det.getGrnItem().getId() == currentDetails.getGrnItem().getId())
            {
                det.setGrnQty(det.getGrnQty() + currentDetails.getGrnQty());
                isFound = true;
                break;
            }
            
        }
        if (!isFound)
        {
            getVirtualList().add(currentDetails);
        }
        
        currentDetails = new GRNDetails();
        
        System.out.println("Total : " + getTotal());
        getCurrent().setTotalAmount(getTotal());
        currentDetails = new GRNDetails();
    }
   
    
    public Double getTotal()
    {
        double TotalValue=0;
        for(GRNDetails details : getVirtualList())
        {
            //System.out.println("Det :  " + details.getGrnItem().getItemDescription() + " : " + details.getGrnItem().getItemCost());
            TotalValue = TotalValue +  (details.getGrnItemCost() * details.getGrnQty());
        }
        return TotalValue;
    }
    
    public void onEdit(RowEditEvent event) 
    {   
        
        MessageHelper.addSuccessMessage(((GRNDetails) event.getObject()).getGrnItem().getItemCode() + " Updated!");
        for(GRNDetails det: getVirtualList())
        {
            if(det.getGrnItem().getId() == ((GRNDetails)event.getObject()).getGrnItem().getId() )
            {
                
                det.setGrnQty(((GRNDetails)event.getObject()).getGrnQty());
                det.setGrnItemCost(((GRNDetails)event.getObject()).getGrnItemCost());
                det.setGrnItemDiscountPerc(((GRNDetails)event.getObject()).getGrnItemDiscountPerc());
            }
        }
        
        getCurrent().setTotalAmount(getTotal());
    }  
    public void onCancel(RowEditEvent event) 
    {  
        MessageHelper.addSuccessMessage(((GRNDetails) event.getObject()).getGrnItem().getItemCode() + "Not Updated!");
        
    }
     
    public void validateItems(FacesContext context, UIComponent component, Object value) 
    {
        
         
        if(getVirtualList().isEmpty()) 
        {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Item Empty");
            message.setDetail("Item is Empty");
            context.addMessage(null, message);
            throw new ValidatorException(message);
        }
    } 
     
    public String preapareList(GRN grn)
    {
        setCurrent(grn);
        System.out.println("Grn Set " + grn.getGrnNo());
        return "#";      
    }
    
    public List<GRNDetails> getSpecificGrnList()
    {
        System.out.println("Got List");
        return gRNController.findSpecific(getCurrent());
        
    }
    
    public List<Properties> getPropListTest(){
        
        if(getCurrent().getExtraz() == null && propertyManagerController != null && propertyManagerController.findAll() != null){
            List<Properties> propertyList = new ArrayList<Properties>();
            for(PropertyManager propertyMng : propertyManagerController.findAll()){
                Properties property = new Properties(propertyMng.getFieldName(), null);
                propertyList.add(property);
                System.out.println("Property added : " + property.getPropertyName());
            }
            getCurrent().setExtraz(propertyList);
            System.out.println("Properties set");
        }
        
        return getCurrent().getExtraz();
    }
    
   
}
