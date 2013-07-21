

package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Product;
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
        productController.create(current);
        return "#";
    }

}
