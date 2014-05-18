

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.CashBook;
import com.AlphaDevs.Web.Entities.Location;
import com.AlphaDevs.Web.Helpers.MessageHelper;
import com.AlphaDevs.Web.SessionBean.CashbookController;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */

@ManagedBean
@SessionScoped
public class CashbookHandler 
{
    @EJB
    private CashbookController cashbookController;
    
    private CashBook current;
    private Date relatedDate;
    private Location relatedLocation;
    Date inputDate;
    /** Creates a new instance of CashbookHandler */
    public CashbookHandler() 
    {
        if(current == null)
        {
            current = new CashBook();
        }
    }

    public Date getRelatedDate() {
        return relatedDate;
    }

    public void setRelatedDate(Date relatedDate) {
        this.relatedDate = relatedDate;
    }

    public Location getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(Location relatedLocation) {
        this.relatedLocation = relatedLocation;
    }
    
    public CashbookController getCashbookController() {
        return cashbookController;
    }

    public void setCashbookController(CashbookController cashbookController) {
        this.cashbookController = cashbookController;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }
    
    public CashBook getCurrent() {
        return current;
    }

    public void setCurrent(CashBook current) {
        this.current = current;
    }
    
    public List<CashBook> getList()
    {
        inputDate = null;
        try {
 
            if(getRelatedDate() == null){
                setRelatedDate(new Date());
            }
            
            String inputStr = new SimpleDateFormat("yyyy-MM-dd").format(getRelatedDate());
           
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            inputDate = dateFormat.parse(inputStr);
            setInputDate(inputDate);
        } catch (ParseException ex) {
            MessageHelper.addErrorMessage("Error While Converting Date", ex.getLocalizedMessage());
        }
        
        
        return cashbookController.findReadingByDate(getRelatedDate(), getRelatedLocation());
    }
     
    public void printReport() throws JRException, IOException
    {
        JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(getList());
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/CashBook.jasper");
        JasperPrint jasPrint =  JasperFillManager.fillReport(reportPath, new HashMap(), beanCollection);
        HttpServletResponse responce = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        //responce.addHeader("content-disposition" ,"filename=GrnReport.pdf");
        ServletOutputStream output = responce.getOutputStream();
        responce.addHeader("Content-Disposition", "iattachment; filename=CashBook.pdf"); 
        JasperExportManager.exportReportToPdfStream(jasPrint, output);
        FacesContext.getCurrentInstance().responseComplete();
        System.out.println("Report done Normal");
    }
    
    public void printReportDownload() throws JRException, IOException
    {
        JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(getList());
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/CashBookV2.jasper");
        System.out.println("Path : " + reportPath);
        
        //Set Map
        HashMap map = new HashMap();
        map.put("Header", getRelatedLocation().toString());
        
        
        JasperPrint jasPrint =  JasperFillManager.fillReport(reportPath, map, beanCollection);
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
    
    /*public void openPopupClicked(ActionEvent event) 
    {
        // View's id in the same form as used in the navigation rules in faces-config.xml
        // This value could be passed as parameter (using <f:param>)
        final String viewId = "/popup.faces"; 

        FacesContext facesContext = FacesContext.getCurrentInstance();

        // This is the proper way to get the view's url
        ViewHandler viewHandler = facesContext.getApplication().getViewHandler();
        String actionUrl = viewHandler.getActionURL(facesContext, viewId);

        String javaScriptText = "window.open('"+actionUrl+"', 'popupWindow', 'dependent=yes, menubar=no, toolbar=no');";

        // Add the Javascript to the rendered page's header for immediate execution
        AddResource addResource = AddResourceFactory.getInstance(facesContext);
        addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);         
    }*/

}
