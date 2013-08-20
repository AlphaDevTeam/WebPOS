
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Enums.Document;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
@RequestScoped
public class DocumentEnumHandler {

    public DocumentEnumHandler() {
    }
    
    public Document[] getDocumentList(){
        return Document.values();
    }
}
