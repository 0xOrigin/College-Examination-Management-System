package Email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import AppDataReader.AppDataEnum;
import AppDataReader.EmailConfig;

/**
 * The class Email configuration.
 *
 * @author 0xOrigin
 */
class EmailConfiguration {
    
    private final String senderMail;
    private final String senderPassword;
    private final String host;
    private final String port;
    private Session session;

    private EmailConfiguration(EmailConfig emailConfig) {
        
        this.senderMail = emailConfig.get(AppDataEnum.SenderEmail);
        this.senderPassword = emailConfig.get(AppDataEnum.SenderPassword);
        this.host = emailConfig.get(AppDataEnum.Host);
        this.port = emailConfig.get(AppDataEnum.Port);
        
        createSession();
    }
    
    
    private EmailConfiguration(String senderMail, String senderPassword, String host, String port) {
        
        this.senderMail = senderMail;
        this.senderPassword = senderPassword;
        this.host = host;
        this.port = port;
        
        createSession();
    }

    /**
     * Set default email config.
     *
     * @param emailConfig the email config
     * @return the email configuration instance
     */
    static EmailConfiguration setDefaultConfig(EmailConfig emailConfig){
    
        return new EmailConfiguration(emailConfig);
    }

    /**
     * Set full email config.
     *
     * @param senderMail     the sender mail
     * @param senderPassword the sender password
     * @param host           the host
     * @param port           the port
     * @return the email configuration instance
     */
    static EmailConfiguration setFullConfig(String senderMail, String senderPassword, String host, String port){
    
        return new EmailConfiguration(senderMail, senderPassword, host, port);
    }
    
    private void createSession(){
       
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        properties.put("mail.smtp.host", this.host);
        properties.put("mail.smtp.port", this.port);

        this.session = Session.getInstance(properties, new Authenticator(){

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderMail, senderPassword);
            }
            
        }); 
    
    }

    /**
     * Get session.
     *
     * @return the session
     */
    Session getSession(){
    
        return this.session;
    }

    /**
     * Get sender email.
     *
     * @return the sender email
     */
    String getSenderEmail(){
    
        return this.senderMail;
    }
     
}