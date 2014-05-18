

package com.AlphaDevs.Web.Extra;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

public class MailUtil {
    
    private String toEmailAddress;
    private String fromEmailAddress;
    private String messageBody;
    private String emailSubject;
    private String smtpServer; 
    private DataSource datasource;

    /**
     * @return the toEmailAddress
     */
    public String getToEmailAddress() {
        return toEmailAddress;
    }

    /**
     * @param toEmailAddress the toEmailAddress to set
     */
    public void setToEmailAddress(String toEmailAddress) {
        this.toEmailAddress = toEmailAddress;
    }

    /**
     * @return the fromEmailAddress
     */
    public String getFromEmailAddress() {
        return fromEmailAddress;
    }

    /**
     * @param fromEmailAddress the fromEmailAddress to set
     */
    public void setFromEmailAddress(String fromEmailAddress) {
        this.fromEmailAddress = fromEmailAddress;
    }

    /**
     * @return the messageBody
     */
    public String getMessageBody() {
        return messageBody;
    }

    /**
     * @param messageBody the messageBody to set
     */
    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    /**
     * @return the emailSubject
     */
    public String getEmailSubject() {
        return emailSubject;
    }

    /**
     * @param emailSubject the emailSubject to set
     */
    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    /**
     * @return the smtpServer
     */
    public String getSmtpServer() {
        return smtpServer;
    }

    /**
     * @param smtpServer the smtpServer to set
     */
    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }
    
    
    public int sendMail() {
        try {

            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", getSmtpServer());
            props.put("mail.smtp.auth", "true");
            
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            // -- Create a new message --
            Message msg = new MimeMessage(session);
            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(getFromEmailAddress()));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getToEmailAddress(), false));
            msg.setSubject(getEmailSubject());
            msg.setText(getMessageBody());
            // -- Set some other header information --
            msg.setHeader("MyMail", "Mr. XYZ");
            msg.setSentDate(new Date());
            
            // create the message part 
            MimeBodyPart messageBodyPart = 
              new MimeBodyPart();

            //fill message
            messageBodyPart.setText("Hi");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            
            if(getDatasource() != null){
                messageBodyPart = new MimeBodyPart();
                messageBodyPart.setDataHandler(new DataHandler(getDatasource()));
                //messageBodyPart.setFileName(fileAttachment);
                multipart.addBodyPart(messageBodyPart);
            }
            

            // Put parts in message
            msg.setContent(multipart);
            
            // -- Send the message --
            Transport.send(msg);
            System.out.println("Message sent to " + getToEmailAddress() + " OK. With Attachment");
            return 0;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception " + ex);
            return -1;
        }
    }

    /**
     * @return the datasource
     */
    public DataSource getDatasource() {
        return datasource;
    }

    /**
     * @param datasource the datasource to set
     */
    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username =  "alphadevs.mail@gmail.com";           
            String password = "KnightRider";                          
            return new PasswordAuthentication(username, password);
        }
    }
}
