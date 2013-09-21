
package com.AlphaDevs.Web.JSFBeans;

import com.AlphaDevs.Web.Entities.Job;
import com.AlphaDevs.Web.Entities.Logger;
import com.AlphaDevs.Web.Entities.Stock;
import com.AlphaDevs.Web.Enums.TransactionTypes;
import com.AlphaDevs.Web.Helpers.EntityHelper;
import com.AlphaDevs.Web.SessionBean.JobController;
import java.util.List;
import javax.ejb.EJB;
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
public class JobHandler extends SuperHandler{
    @EJB
    private JobController jobController;
    
    
    private Job current;
    
    public JobHandler() {
        if(current == null){
            current = new Job();
        }
    }

    public JobController getJobController() {
        return jobController;
    }

    public void setJobController(JobController jobController) {
        this.jobController = jobController;
    }

    public Job getCurrent() {
        return current;
    }

    public void setCurrent(Job current) {
        this.current = current;
    }
    
    public List<Job> getList(){
        return jobController.findAll();
    }
    
    public String createJob(){
        
        Logger Log = EntityHelper.createLogger("Create Job", current.getJobNumber(), TransactionTypes.JOB);
        loggerController.create(Log);
        current.setLogger(Log);
        jobController.create(current);
        return "#";
    }
}
