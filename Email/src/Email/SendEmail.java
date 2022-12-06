package Email;

import javax.mail.MessagingException;
import javax.mail.Transport;
import AppDataReader.EmailConfig;

/**
 *
 * @author xorigin
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
    
    
    public static SendEmail setDefaultConfig(EmailConfig emailConfig){
    
        return new SendEmail(emailConfig);
    }
    
    public static SendEmail setPersonalName(String personalName, EmailConfig emailConfig){
    
        return new SendEmail(personalName, emailConfig);
    }
    
    public static SendEmail setFullConfig(String personalName, String senderMail, String senderPassword, String host, String port){
    
        return new SendEmail(personalName, senderMail, senderPassword, host, port);
    }
    
    
    public void send(String recepientEmail, String messageSubject, String messageText){
    
        try {

            Transport.send(this.emailMessage.generateMessage(recepientEmail, messageSubject, messageText));

        } catch (MessagingException exception) {
            
            MessageExceptionHandler.handle(exception.toString(), messageSubject, messageText);
        }
    
    }
    
}