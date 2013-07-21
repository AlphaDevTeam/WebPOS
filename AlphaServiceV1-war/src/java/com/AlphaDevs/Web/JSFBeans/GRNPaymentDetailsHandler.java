

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.GRNPaymentDetails;
import com.AlphaDevs.Web.SessionBean.GRNPaymentDetailsController;
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
public class GRNPaymentDetailsHandler 
{
    @EJB
    private GRNPaymentDetailsController gRNPaymentDetailsController;
    
    private GRNPaymentDetails current;

    /** Creates a new instance of GRNPaymentDetailsHandler */
    public GRNPaymentDetailsHandler() 
    {
        if(current == null)
        {
            current = new GRNPaymentDetails();
        }
    }

    public GRNPaymentDetails getCurrent() {
        return current;
    }

    public void setCurrent(GRNPaymentDetails current) {
        this.current = current;
    }
    
    public String persistGRNPaymentDetails()
    {
        gRNPaymentDetailsController.create(current);
        return "CreateGRN";
    }

}
