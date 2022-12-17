package Email;

import javax.mail.MessagingException;
import javax.mail.Transport;
import AppDataReader.EmailConfig;

/**
 * The class Send email.
 *
 * @author 0xOrigin
 */
public class SendEmail {
    
    private final EmailMessage emailMessage;
    
    private SendEmail(EmailConfig emailConfig){
        
        this.emailMessage = EmailMessage.setDefaultConfig(emailConfig);
    }
    
    private SendEmail(String personalName, EmailConfig emailConfig){
        
        this.emailMessage = EmailMessage.setPersonalName(personalName, emailConfig);
    }
    
    private SendEmail(String personalName, String senderMail, String senderPassword, String host, String port){
        
        this.emailMessage = EmailMessage.setFullConfig(personalName, senderMail, senderPassword, host, port);
    }


    /**
     * Set default email config.
     *
     * @param emailConfig the email config
     * @return send email instance
     */
    public static SendEmail setDefaultConfig(EmailConfig emailConfig){
    
        return new SendEmail(emailConfig);
    }

    /**
     * Set personal name and email config.
     *
     * @param personalName the personal name
     * @param emailConfig  the email config
     * @return send email instance
     */
    public static SendEmail setPersonalName(String personalName, EmailConfig emailConfig){
    
        return new SendEmail(personalName, emailConfig);
    }

    /**
     * Set full email config.
     *
     * @param personalName   the personal name
     * @param senderMail     the sender mail
     * @param senderPassword the sender password
     * @param host           the host
     * @param port           the port
     * @return send email instance
     */
    public static SendEmail setFullConfig(String personalName, String senderMail, String senderPassword, String host, String port){
    
        return new SendEmail(personalName, senderMail, senderPassword, host, port);
    }


    /**
     * Send.
     *
     * @param recipientEmail the recipient email
     * @param messageSubject the message subject
     * @param messageText    the message text
     */
    public void send(String recipientEmail, String messageSubject, String messageText){
    
        try {

            Transport.send(this.emailMessage.generateMessage(recipientEmail, messageSubject, messageText));

        } catch (MessagingException exception) {
            
            MessageExceptionHandler.handle(exception.toString(), messageSubject, messageText);
        }
    
    }
    
}