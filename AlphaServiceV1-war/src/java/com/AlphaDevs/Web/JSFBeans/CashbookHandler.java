

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.CashBook;
import com.AlphaDevs.Web.SessionBean.CashbookController;
import java.awt.event.ActionEvent;
import java.io.IOException;
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

    /** Creates a new instance of CashbookHandler */
    public CashbookHandler() 
    {
        if(current == null)
        {
            current = new CashBook();
        }
    }

    public CashbookController getCashbookController() {
        return cashbookController;
    }

    public void setCashbookController(CashbookController cashbookController) {
        this.cashbookController = cashbookController;
    }

    public CashBook getCurrent() {
        return current;
    }

    public void setCurrent(CashBook current) {
        this.current = current;
    }
    
    public List<CashBook> getList()
    {
        return cashbookController.findAll();
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
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reports/CashBook.jasper");
        System.out.println("Path : " + reportPath);
        
        //Set Map
        HashMap map = new HashMap();
        map.put("Header", "This is Heasder");
        
        
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
