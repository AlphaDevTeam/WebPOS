

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Design;
import com.AlphaDevs.Web.Entities.Product;
import com.AlphaDevs.Web.SessionBean.DesignController;
import com.AlphaDevs.Web.SessionBean.ProductController;
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
public class DesignHandler 
{
    @EJB
    private ProductController productController;
    @EJB
    private DesignController designController;
     
    private Design current;

    /** Creates a new instance of DesignHandler */
    public DesignHandler() 
    {
        if(current == null)
        {
            current = new Design();
        }
    }

    public Design getCurrent() {
        return current;
    }

    public void setCurrent(Design current) {
        this.current = current;
    }
    
    public List<Design> getList()
    {
        return designController.findAll();
    }
    
    public void changeProd()
    {
        System.out.println("Changed Prod : ");
        current.setProduct(productController.find(Long.valueOf(1)));
    }
    
    
    
    public String persistDesign()
    {
        designController.create(current);
        return "#";
    }

}
