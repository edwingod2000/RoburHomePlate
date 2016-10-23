/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.web.email;

import com.robur58.web.common.ServerPropertiesHelper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import nl.profict.platform.web.util.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author godefrooije
 */
public class EmailManager {

    public static final String REGISTREER_TEMPLATE = "/WEB-INF/content/email/registratieEmail.html";
    public static final String WACHTWOORD_TEMPLATE = "/WEB-INF/content/email/wachtwoordEmail.html";
//    private static final String SMTP_SERVER = "smtp.robur58.com";
//    private static final int SMTP_PORT = 25;
//    private static final String SMTP_USER = "registratie@robur58.com";
//    private static final String SMTP_PASSWORD = "homerun58";
//    private static final String EMAIL_FROM = "registratie@robur58.com";
//    private static final String EMAIL_FROMNAME = "Robur '58 Registratie";

    private String toEmailAddress;
    private String toName;
    private String subject;
    private String template;
    private Map<String,String> parameters;
    private String emailHeader = "RoburHeader1.gif";
    
    public EmailManager(String subject, String toEmailAddress, String toName, String template, Map<String,String> parameters) {
        this.toEmailAddress = toEmailAddress;
        this.toName = toName;
        this.subject = subject;
        this.template = template;
        this.parameters = parameters;
    }
    
    /**
     * Geeft terug middels true of false als het gelukt is of niet.
     * @return
     * @throws IOException 
     */
    public boolean sendHTMLEmail() throws IOException {
        
        // Create the email message
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(ServerPropertiesHelper.getInstance().getSmtpServer());
            email.setFrom(ServerPropertiesHelper.getInstance().getSmtpEmailFrom(), ServerPropertiesHelper.getInstance().getSmtpEmailFromName());
            email.setAuthentication(ServerPropertiesHelper.getInstance().getSmtpUser(), ServerPropertiesHelper.getInstance().getSmtpPassword());
            email.setSmtpPort(Integer.parseInt(ServerPropertiesHelper.getInstance().getSmtpPort()));
            email.addTo(toEmailAddress, toName);
            email.setSubject(subject);
            
            // embed the image and get the content id
//            try {
//                URL url;
//                url = new URL( IMAGE );
//                Logger.getLogger(EmailManager.class.getName()).log(Level.ALL.INFO, "url: " + ServerPropertiesHelper.getInstance().getImageprefix() + emailHeader);
//                String cidImage1 = email.embed(url, "Robur Header");
//                Logger.getLogger(EmailManager.class.getName()).log(Level.ALL.INFO, "Na email.embed");

//                parameters.put("cidImage1", cidImage1); 
//                Logger.getLogger(EmailManager.class.getName()).log(Level.ALL.INFO, "Na parameters.put");
//            } catch(Exception e) {
//                Logger.getLogger(EmailManager.class.getName()).log(Level.INFO, null, e);
//            }
            // load your HTML email template
            String htmlEmailTemplate = ophalenTemplate();
            Logger.getLogger(EmailManager.class.getName()).log(Level.ALL.INFO, "Na ophalenTemplate");

            // set the html message
            email.setHtmlMsg(htmlEmailTemplate); //, new File("").toURI().toURL(), false);
            Logger.getLogger(EmailManager.class.getName()).log(Level.ALL.INFO, "Na setHtmlMsg");

            // set the alternative message
            email.setTextMsg("Your email client does not support HTML messages");
            Logger.getLogger(EmailManager.class.getName()).log(Level.ALL.INFO, "Na setTextMsg");

            // send the email
            email.send();
            Logger.getLogger(EmailManager.class.getName()).log(Level.ALL.INFO, "Na send()");
            return true;
        } catch (EmailException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    private EmailAttachment createAttachment(String description, String name, String path) {
        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(path);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(description);
        attachment.setName(name);
        
        return attachment;
    }
    
    private String ophalenTemplate() throws IOException {
        String templateTekst = null;
        try {
            InputStream resourceAsStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(template);
            if (resourceAsStream != null) { 
                templateTekst = read(resourceAsStream);
                if (parameters != null && parameters.size() > 0) {
                    Set<Entry<String, String>> entrySet = parameters.entrySet();
                    for (Entry<String, String> parameter: entrySet) {
                        templateTekst = StringUtils.replace(templateTekst, ":" + parameter.getKey(), parameter.getValue());
                    }
                }
            } else {
                MessageUtils.addErrorMessage("Fout opgetreden bij samenstellen email. Probeer het later nog eens.");
            }
        } catch (Exception e) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return templateTekst;
    }

    private String read(InputStream resourceAsStream) throws IOException {
        StringBuilder text = new StringBuilder();
        String NL = System.getProperty("line.separator");
        Scanner scanner = new Scanner(resourceAsStream);
        try {
          while (scanner.hasNextLine()){
            text.append(scanner.nextLine() + NL);
          }
        }
        finally{
          scanner.close();
        }
        return text.toString();
    }    
}
