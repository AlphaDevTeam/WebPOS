

package com.AlphaDevs.Web.Extra;

import com.AlphaDevs.Web.Entities.InvoiceDetails;
import com.AlphaDevs.Web.Entities.MeterReading;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

public class DaySummaryReportHelper {
    
    private List<MeterReading> meterReadings;
    private List<InvoiceDetails> invoiceDetails;

    public DaySummaryReportHelper() {
        meterReadings = new ArrayList<MeterReading>();
        invoiceDetails = new ArrayList<InvoiceDetails>();
    }

    public List<MeterReading> getMeterReadings() {
        return meterReadings;
    }

    public void setMeterReadings(List<MeterReading> meterReadings) {
        this.meterReadings = meterReadings;
    }

    public List<InvoiceDetails> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetails> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
    
    
    
}
