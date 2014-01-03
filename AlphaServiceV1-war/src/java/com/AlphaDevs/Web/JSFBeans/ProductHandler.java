

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Entities.Product;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.Helpers.MessageHelper;
import com.AlphaDevs.Web.SessionBean.LoggerController;
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
public class ProductHandler 
{
    @EJB
    private LoggerController loggerController;
    
    @EJB
    private ProductController productController;
    
    private Product current;

    /** Creates a new instance of ProductHandler */
    public ProductHandler() 
    {
        if(current==null)
        {
            current = new Product();
        }
            
    }

    public Product getCurrent() {
        return current;
    }

    public void setCurrent(Product current) {
        this.current = current;
    }
    
    public List<Product> getList()
    {
        return productController.findAll();
    }
    
    public String persistProduct()
    {
        Logger Log = EntityHelper.createLogger("Product Design", current.getProdCode(), TransactionTypes.PRODUCT);
        loggerController.create(Log);
        current.setLogger(Log);
        MessageHelper.addSuccessMessage("Product Added!");
        productController.create(current);
        return "Home";
    }

}
