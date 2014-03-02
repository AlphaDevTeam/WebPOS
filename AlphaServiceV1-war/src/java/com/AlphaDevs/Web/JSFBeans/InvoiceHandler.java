

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.CashBook;
import com.AlphaDevs.Web.Entities.CashBookBalance;
import com.AlphaDevs.Web.Entities.CustomerBalance;
import com.AlphaDevs.Web.Entities.CustomerTransaction;
import com.AlphaDevs.Web.Entities.Invoice;
import com.AlphaDevs.Web.Entities.InvoiceDetails;
import com.AlphaDevs.Web.Entities.ItemBincard;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Entities.PaymentDetails;
import com.AlphaDevs.Web.Entities.Properties;
import com.AlphaDevs.Web.Entities.PropertyManager;
import com.AlphaDevs.Web.Entities.Stock;
import com.AlphaDevs.Web.Entities.SystemNumbers;
import com.AlphaDevs.Web.Entities.UserX;
import com.AlphaDevs.Web.Enums.BillStatus;
import com.AlphaDevs.Web.Enums.Document;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.Helpers.MessageHelper;
import com.AlphaDevs.Web.Helpers.SessionDataHelper;
import com.AlphaDevs.Web.SessionBean.CashBookBalanceController;
import com.AlphaDevs.Web.SessionBean.CashbookController;
import com.AlphaDevs.Web.SessionBean.CustomerBalanceController;
import com.AlphaDevs.Web.SessionBean.CustomerTransactionController;
import com.AlphaDevs.Web.SessionBean.InvoiceController;
import com.AlphaDevs.Web.SessionBean.ItemBincardController;
import com.AlphaDevs.Web.SessionBean.LoggerController;
import com.AlphaDevs.Web.SessionBean.PaymentDetailsController;
import com.AlphaDevs.Web.SessionBean.PropertiesController;
import com.AlphaDevs.Web.SessionBean.PropertyManagerController;
import com.AlphaDevs.Web.SessionBean.StockController;
import com.AlphaDevs.Web.SessionBean.SystemNumbersController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
public class InvoiceHandler 
{ 
    @EJB
    private SystemNumbersController systemNumbersController;
    @EJB
    private PaymentDetailsController paymentDetailsController;
    @EJB
    private ItemBincardController itemBincardController;
    @EJB
    private CustomerTransactionController customerTransactionController;
    @EJB
    private CustomerBalanceController customerBalanceController;
    @EJB
    private StockController stockController;
    @EJB
    private LoggerController loggerController;
    @EJB
    private CashbookController cashbookController;
    @EJB
    private CashBookBalanceController cashBookBalanceController;
    @EJB
    private PropertiesController propertiesController;
    @EJB
    private PropertyManagerController propertyManagerController;
    @EJB
    private InvoiceController invoiceController;
    
    private SystemNumbers currentSystemNumber;
    
    private Invoice current;
    private InvoiceDetails currentDetails;
    private List<InvoiceDetails> virtualList;
    private InvoiceDetails selectedInvoiceDetails;
    private PaymentDetails paymentDetails;
    private boolean isBypassStock;
    private Document currentDocument;
    
    public InvoiceHandler() 
    {
        setCurrentDocument(Document.INVOICE);
        setIsBypassStock(false);
        if(current == null)
        {
            current = new Invoice();
        }
        if(currentDetails == null){
            currentDetails = new InvoiceDetails();
        }
        if(selectedInvoiceDetails == null){
            selectedInvoiceDetails = new InvoiceDetails();
        }
        virtualList = new ArrayList<InvoiceDetails>();
        paymentDetails = new PaymentDetails();
    }

    public boolean isIsBypassStock() {
        return isBypassStock;
    }

    public void setIsBypassStock(boolean isBypassStock) {
        this.isBypassStock = isBypassStock;
    }
    
    public Document getCurrentDocument() {
        return currentDocument;
    }

    public final void setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
    }
    
    public SystemNumbersController getSystemNumbersController() {
        return systemNumbersController;
    }

    public void setSystemNumbersController(SystemNumbersController systemNumbersController) {
        this.systemNumbersController = systemNumbersController;
    }

    public SystemNumbers getCurrentSystemNumber() {
        return currentSystemNumber;
    }

    public void setCurrentSystemNumber(SystemNumbers currentSystemNumber) {
        this.currentSystemNumber = currentSystemNumber;
    }
    
    public PaymentDetailsController getPaymentDetailsController() {
        return paymentDetailsController;
    }

    public void setPaymentDetailsController(PaymentDetailsController paymentDetailsController) {
        this.paymentDetailsController = paymentDetailsController;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
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

    public CashBookBalanceController getCashBookBalanceController() {
        return cashBookBalanceController;
    }

    public void setCashBookBalanceController(CashBookBalanceController cashBookBalanceController) {
        this.cashBookBalanceController = cashBookBalanceController;
    }

    public PropertiesController getPropertiesController() {
        return propertiesController;
    }

    public void setPropertiesController(PropertiesController propertiesController) {
        this.propertiesController = propertiesController;
    }

    public Invoice getCurrent() {
        return current;
    }

    public void setCurrent(Invoice current) {
        this.current = current;
    }

    public InvoiceController getInvoiceController() {
        return invoiceController;
    }

    public void setInvoiceController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    public InvoiceDetails getCurrentDetails() {
        return currentDetails;
    }

    public void setCurrentDetails(InvoiceDetails currentDetails) {
        this.currentDetails = currentDetails;
    }

    public PropertyManagerController getPropertyManagerController() {
        return propertyManagerController;
    }

    public void setPropertyManagerController(PropertyManagerController propertyManagerController) {
        this.propertyManagerController = propertyManagerController;
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

    public CustomerBalanceController getCustomerBalanceController() {
        return customerBalanceController;
    }

    public void setCustomerBalanceController(CustomerBalanceController customerBalanceController) {
        this.customerBalanceController = customerBalanceController;
    }

    public StockController getStockController() {
        return stockController;
    }

    public void setStockController(StockController stockController) {
        this.stockController = stockController;
    }

    public List<InvoiceDetails> getVirtualList() {
        return virtualList;
    }

    public void setVirtualList(List<InvoiceDetails> virtualList) {
        this.virtualList = virtualList;
    }

    public InvoiceDetails getSelectedInvoiceDetails() {
        return selectedInvoiceDetails;
    }

    public void setSelectedInvoiceDetails(InvoiceDetails selectedInvoiceDetails) {
        this.selectedInvoiceDetails = selectedInvoiceDetails;
    }
    
    public List<Invoice> getList()
    {
        return invoiceController.findAll();
    }
    
    public void handleSelect(SelectEvent event) 
    {   
        currentDetails.setPrice(currentDetails.getItem().getUnitPrice());
    }  
    
    public String getBillNumber(){
        setCurrentSystemNumber(null);
        Map<String, Object> sessionMap = SessionDataHelper.getSessionMap();
        UserX loggedUser = (UserX) sessionMap.get("User");
        if(loggedUser != null && getCurrent().getLocation() != null){
            List<SystemNumbers> systemNumbers = getSystemNumbersController().findSpecific(loggedUser.getAssociatedCompany(), getCurrent().getLocation(), getCurrentDocument());
            if(systemNumbers != null && !systemNumbers.isEmpty()){
                setCurrentSystemNumber(systemNumbers.get(0));
                getCurrent().setBillNo(getCurrentSystemNumber().getDocumentSystemNo());
            }
            
        }
        return  getCurrentSystemNumber() != null ? getCurrentSystemNumber().getDocumentSystemNo() : "";
        
    }
    
    public void setBillNumber(String billNumbere){
        getCurrent().setBillNo(billNumbere);
    }
    
    public List<Properties> getPropList(){
        
        if(current.getExtraz() == null && propertyManagerController != null && propertyManagerController.findAll() != null){
            List<Properties> propertyList = new ArrayList<Properties>();
            for(PropertyManager propertyMng : propertyManagerController.findAll()){
                Properties property = new Properties(propertyMng.getFieldName(), null);
                propertyList.add(property);
                System.out.println("Property Added ( INV ) : " + property.getPropertyName());
            }
            current.setExtraz(propertyList);
            System.out.println("Properties Set - INV");
        }
        
        return current.getExtraz();
    }
    
    public void addItem()
    {
        boolean isFound =false;
        
        if(currentDetails.getItem() == null || currentDetails.getPrice()==0 || currentDetails.getItemQty() == 0)
        {
            MessageHelper.addErrorMessage("Error", "Please Fill All Details");
            return;
        }
        
        currentDetails.setInvoice(current);
        
        for(InvoiceDetails invDetails : getVirtualList())
        {
            if(invDetails.getItem().getId() == currentDetails.getItem().getId())
            {
                invDetails.setItemQty(invDetails.getItemQty() + currentDetails.getItemQty());
                isFound = true;
                break;
            }
            
        }
        if (!isFound)
        {
            getVirtualList().add(currentDetails);
        }

        System.out.println("Total : " + getTotal());
        current.setTotalAmount(getTotal());
        currentDetails = new InvoiceDetails();
    }
   
    
    public Double getTotal()
    {
        double TotalValue=0;
        for(InvoiceDetails invDetails : getVirtualList())
        {
            //System.out.println("Det :  " + details.getGrnItem().getItemDescription() + " : " + details.getGrnItem().getItemCost());
            TotalValue = TotalValue +  (invDetails.getPrice()* invDetails.getItemQty());
        }
        return TotalValue;
    }
    
    public void onEdit(RowEditEvent event) 
    {   
        
        MessageHelper.addSuccessMessage(((InvoiceDetails) event.getObject()).getItem().getItemCode() + " Updated!");
        for(InvoiceDetails invDetails : getVirtualList())
        {
            if(invDetails.getItem().getId() == ((InvoiceDetails)event.getObject()).getItem().getId() )
            {
                
                invDetails.setItemQty(((InvoiceDetails)event.getObject()).getItemQty());
                invDetails.setPrice(((InvoiceDetails)event.getObject()).getPrice());
                
            }
        }
        
        current.setTotalAmount(getTotal());
    }  
    public void onCancel(RowEditEvent event) 
    {  
        MessageHelper.addSuccessMessage(((InvoiceDetails) event.getObject()).getItem().getItemCode() + "Not Updated!");
        
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
    
    public String removeSelectedItem()
    {
        if ( getSelectedInvoiceDetails() == null)
        {
            MessageHelper.addErrorMessage("Selection ","No item Selected ");
            return "";
        }
        else
        {
            for(int a=0;a<getVirtualList().size();a++)
            {

                if(getVirtualList().get(a) == getSelectedInvoiceDetails())
                {
                    getVirtualList().remove(a);
                    break;
                }
            }
            current.setTotalAmount(getTotal());
        }
        return "#";
    }
    
    public String saveInvoice()
    {
        if(getVirtualList().isEmpty())
        {
            MessageHelper.addErrorMessage("Error", "No Items Found!");
            return "";
        }
        
        Logger log = EntityHelper.createLogger("Invoice" , current.getBillNo(), TransactionTypes.SALE);
        getLoggerController().create(log);
        
        
        current.setBillStat(BillStatus.TAX);
        
        current.setInvDetails(getVirtualList());
        current.setLogger(log);
        getInvoiceController().create(current);
        
//        for(Properties properties : current.getExtraz()){
//            System.out.println("Trying Saving ... " + properties.getPropertyName() + " : " + properties.getPropertyValue());
//            properties.se(current);
//            propertiesController.create(properties);
//            System.out.println("Done");
//        }
        
        for(InvoiceDetails invDetails : getVirtualList())
        {
           invDetails.setInvoice(current);
           if(!isIsBypassStock()){
               Stock  stock = stockController.findSpecific(invDetails.getItem());
               stock.setStockQty((float) (stock.getStockQty() - invDetails.getItemQty()));
               getStockController().edit(stock);

               ItemBincard itemBin = new ItemBincard();
               itemBin.setDescription("Invoice - " + current.getBillNo());
               itemBin.setItem(invDetails.getItem());
               itemBin.setRelatedDate(current.getTrnDate());
               itemBin.setTrnNumber(current.getRefNo());
               itemBin.setQty(invDetails.getItemQty()* -1);
               itemBin.setLog(log);
               itemBin.setBalance(stock.getStockQty());
               getItemBincardController().create(itemBin);
           }
           
           
        }
        
        //Customer Transaction
        CustomerTransaction custTran = new CustomerTransaction();
        custTran.setDescription("Invoice - " + current.getBillNo() + " - " + current.getRefNo());
        custTran.setSupplier(current.getCustomer());
        custTran.setDR(0);
        custTran.setCR(current.getTotalAmount());
        
        //Getting Cust Balance
        CustomerBalance Balance = customerBalanceController.getCustomerBalanceObject(current.getCustomer());
        if(Balance != null)
        {
            Balance.setBalance(Balance.getBalance() + current.getTotalAmount() );
            getCustomerBalanceController().edit(Balance);
            custTran.setBalance(Balance.getBalance());
            
        }
        else
        {
            Balance = new CustomerBalance(getCurrent().getCustomer(), 0);
            //custTran.setBalance(paymentDetails.getCashAmount());
        }
        
        custTran.setLogger(log);
        getCustomerTransactionController().create(custTran);
        
//        paymentDetails.setTotalAmount(current.getTotalAmount());
//        paymentDetails.setLogger(log);
//        paymentDetails.setRelatedGrn(current);
//        gRNPaymentDetailsController.create(paymentDetails);
        
        paymentDetails.setLoger(log);
        paymentDetails.setDocumentType(Document.INVOICE);
        getPaymentDetailsController().create(paymentDetails);
        
        if ( paymentDetails.getCashAmount() > 0 )
        {
            CustomerTransaction custTranPaid = new CustomerTransaction();
            custTranPaid.setDescription("PAID - " + current.getBillNo());
            custTranPaid.setSupplier(current.getCustomer());
            custTranPaid.setDR(paymentDetails.getCashAmount());
            custTranPaid.setCR(0);

            Balance.setBalance(Balance.getBalance() - paymentDetails.getCashAmount());
            customerBalanceController.edit(Balance);
            custTranPaid.setBalance(Balance.getBalance());
            custTranPaid.setLogger(log);
            customerTransactionController.create(custTranPaid);
            
            //Cashbook
            CashBook cashBook = new CashBook();
            cashBook.setDescription("Invoice - " + current.getBillNo());
            cashBook.setCR(0);
            cashBook.setDR(paymentDetails.getCashAmount());
            cashBook.setLocation(current.getLocation());
            cashBook.setLogger(log);

            CashBookBalance cashBalance = cashBookBalanceController.getCashBookBalanceObject(current.getLocation(), current.getBillStat());
            if(cashBalance != null)
            {
                cashBalance.setCashBalance(cashBalance.getCashBalance() + paymentDetails.getCashAmount() );
                cashBookBalanceController.edit(cashBalance);
                cashBook.setBalance(cashBalance.getCashBalance());            
            }
            else
            {
                cashBook.setBalance(paymentDetails.getCashAmount());
            }

            cashbookController.create(cashBook);
            
            
        }
        
        
        
        //Increment the the Document No 
        getCurrentSystemNumber().setSystemNumber(getCurrentSystemNumber().getSystemNumber() + 1);
        getSystemNumbersController().edit(getCurrentSystemNumber());
       
        
        //printReportDownload();
        
        current = new Invoice();
        currentDetails = new InvoiceDetails();
        virtualList = new ArrayList<InvoiceDetails>();
        paymentDetails = new PaymentDetails();
        return "Home";
    }

}
