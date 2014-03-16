

package com.AlphaDevs.Web.Helpers;

import com.AlphaDevs.Web.Extra.DaySummaryReportHelper;
import com.AlphaDevs.Web.Entities.CreditCardReceipts;
import com.AlphaDevs.Web.Entities.CreditCardTeminals;
import com.AlphaDevs.Web.Entities.GRN;
import com.AlphaDevs.Web.Entities.Invoice;
import com.AlphaDevs.Web.Entities.InvoiceDetails;
import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.Entities.MeterReading;
import com.AlphaDevs.Web.Entities.Pump;
import com.AlphaDevs.Web.SessionBean.CreditCardReceiptsController;
import com.AlphaDevs.Web.SessionBean.CreditCardTeminalsController;
import com.AlphaDevs.Web.SessionBean.GRNController;
import com.AlphaDevs.Web.SessionBean.InvoiceController;
import com.AlphaDevs.Web.SessionBean.MeterReadingController;
import com.AlphaDevs.Web.SessionBean.PumpController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
public class DaySummaryHandler {
    @EJB
    private CreditCardReceiptsController creditCardReceiptsController;
    @EJB
    private CreditCardTeminalsController creditCardTeminalsController;
    @EJB
    private MeterReadingController meterReadingController;
    @EJB
    private PumpController pumpController;
    @EJB
    private InvoiceController invoiceController;
    @EJB
    private GRNController gRNController;
    
    private List<DataTableRow> current;
    
    private Date relatedDate;
    private Location relatedLocation;
    private DaySummaryReportHelper daySumaryReportHelper;
    
    public DaySummaryHandler() {
        current = new ArrayList<DataTableRow>();
        daySumaryReportHelper = new DaySummaryReportHelper();
        
    }

    public Date getRelatedDate() {
        return relatedDate;
    }

    public MeterReadingController getMeterReadingController() {
        return meterReadingController;
    }

    public void setMeterReadingController(MeterReadingController meterReadingController) {
        this.meterReadingController = meterReadingController;
    }
    
    public void setRelatedDate(Date relatedDate) {
        System.out.println("Related" + relatedDate.toString());
        this.relatedDate = relatedDate;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }

    public InvoiceController getInvoiceController() {
        return invoiceController;
    }

    public CreditCardReceiptsController getCreditCardReceiptsController() {
        return creditCardReceiptsController;
    }

    public void setCreditCardReceiptsController(CreditCardReceiptsController creditCardReceiptsController) {
        this.creditCardReceiptsController = creditCardReceiptsController;
    }

    public CreditCardTeminalsController getCreditCardTeminalsController() {
        return creditCardTeminalsController;
    }

    public void setCreditCardTeminalsController(CreditCardTeminalsController creditCardTeminalsController) {
        this.creditCardTeminalsController = creditCardTeminalsController;
    }
    
    public PumpController getPumpController() {
        return pumpController;
    }

    public void setPumpController(PumpController pumpController) {
        this.pumpController = pumpController;
    }
    
    public void setInvoiceController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }
    
    public GRNController getgRNController() {
        return gRNController;
    }

    public void setgRNController(GRNController gRNController) {
        this.gRNController = gRNController;
    }
    
    public List<DataTableRow> getCurrent() {
        return current;
    }

    public void setCurrent(List<DataTableRow> current) {
        this.current = current;
    }
    
    public List<DataTableRow> getData(){
        getCurrent().clear();
        Double amount = 0.0;
        Date inputDate = null;
        try {
 
            if(getRelatedDate() == null){
                setRelatedDate(new Date());
            }
            
            String inputStr = new SimpleDateFormat("yyyy-MM-dd").format(getRelatedDate());
           
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            inputDate = dateFormat.parse(inputStr);
        } catch (ParseException ex) {
            MessageHelper.addErrorMessage("Error While Converting Date", ex.getLocalizedMessage());
        }
        
        //Meter Readings 
        List<MeterReading> meterReading = getMeterReadingController().findReadingByDate(inputDate, getRelatedLocation());
        if(meterReading != null && !meterReading.isEmpty()){
             daySumaryReportHelper.setMeterReadings(meterReading);
             for (MeterReading reading : meterReading) {
                 if(reading != null && reading.getRelatedPump() !=null ){
                     Pump relatedPump = reading.getRelatedPump();
                     getCurrent().add(new DataTableRow("Pump Readings Opening - " + relatedPump.getPumpCode() + "-" + relatedPump.getRelatedItem() , String.valueOf(reading.getLastReading()) , "0"));
                     getCurrent().add(new DataTableRow("Pump Readings Closing - " + relatedPump.getPumpCode() + "-" + relatedPump.getRelatedItem() ,"0",String.valueOf( (reading.getLastReading()+ reading.getReading()))));
                     getCurrent().add(new DataTableRow("Pump Issues - " + relatedPump.getPumpCode() + "-" + relatedPump.getRelatedItem() ,"0",String.valueOf( reading.getReading())));
                 }
             }
        }
        
        //Invoices 
        List<Invoice> invoices = getInvoiceController().findInvoicesByDate(inputDate, getRelatedLocation());
        boolean itemExsist = false;
        if(invoices !=null && !invoices.isEmpty()){
            List<InvoiceDetails> invoiceDetailsList = new ArrayList<InvoiceDetails>();
            for (Invoice invoice : invoices) {
                if (invoice != null && invoice.getInvDetails() != null && !invoice.getInvDetails().isEmpty()) {
                    List<InvoiceDetails> invoiceDetails = invoice.getInvDetails();
                    for (InvoiceDetails invoiceDetail : invoiceDetails) {
                        if (invoiceDetail != null) {
                            itemExsist = false;
                            for (InvoiceDetails invoiceDetailIndividual : invoiceDetailsList) {
                                if (invoiceDetailIndividual.getItem().equals(invoiceDetail.getItem())) {
                                    invoiceDetailIndividual.setItemQty(invoiceDetailIndividual.getItemQty() + invoiceDetail.getItemQty());
                                    itemExsist = true;
                                    System.out.println("Exsist" + invoiceDetailIndividual.getItem());
                                }
                            }
                            if(!itemExsist){
                                invoiceDetailsList.add(invoiceDetail);
                            }
                            getCurrent().add(new DataTableRow("Invoice - " + invoice.getBillNo() + "-" + invoiceDetail.getItem(), String.valueOf(invoiceDetail.getItemQty()), "0"));
                        }
                    }
                    
                }
            }
            daySumaryReportHelper.setInvoiceDetails(invoiceDetailsList);
        }
        List<CreditCardTeminals> relatedTerminals = getCreditCardTeminalsController().findTerminalByLocation(getRelatedLocation());
        if(relatedTerminals != null && ! relatedTerminals.isEmpty()){
            for (CreditCardTeminals creditCardTeminal : relatedTerminals) {
                if(creditCardTeminal != null){
                    List<CreditCardReceipts> creditCardReceipts = getCreditCardReceiptsController().findTerminalByLocation(creditCardTeminal, getRelatedLocation());
                     if(creditCardReceipts != null && ! creditCardReceipts.isEmpty()){
                         for (CreditCardReceipts creditCardReceipt : creditCardReceipts) {
                             if(creditCardReceipt !=null ){
                                 
                             }
                         }
                     }
                }
            }
        }
        
        
        for (GRN grn : getgRNController().findByDateRange(inputDate,getRelatedLocation())) {
            amount = amount + grn.getTotalAmount();
            getCurrent().add(new DataTableRow("Good Receipts - " + grn + "-" + grn.getSupplier().getFirstName() , String.valueOf(grn.getTotalAmount()) , "0"));
        }
        
        for (Invoice inv : getInvoiceController().findAll()) {
            amount = amount - inv.getTotalAmount();
            getCurrent().add(new DataTableRow("Invoice - " + inv + "-" +  inv.getCustomer().getFirstName()  , "0" , String.valueOf(inv.getTotalAmount())));
        }
       
        getCurrent().add(new DataTableRow("TOTAL", amount.toString(), null));
        
        
        
        return getCurrent();
        
    }
    
    public void genarateReport(){
        List<DaySummaryReportHelper> listSummary = new ArrayList<DaySummaryReportHelper>();
        listSummary.add(daySumaryReportHelper);
        printReportSpecificGrn(listSummary);
        System.out.println("mETER" + daySumaryReportHelper.toString());
    }
    public void printReportSpecificGrn(List<DaySummaryReportHelper> daySummary) 
    {
        try
        {
            JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(daySummary);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/TestReport.jasper");
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

}
