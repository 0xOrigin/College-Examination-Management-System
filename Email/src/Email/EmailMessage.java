package Email;

import java.io.UnsupportedEncodingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import AppDataReader.AppDataEnum;
import AppDataReader.EmailConfig;

/**
 *
 * @author xorigin
 */
class EmailMessage {
    
    private final EmailConfiguration emailConfiguration;
    private final String personalName;
    
    private EmailMessage(EmailConfig emailConfig) {
        
        this.personalName = emailConfig.get(AppDataEnum.PersonalName);
        this.emailConfiguration = EmailConfiguration.setDefaultConfig(emailConfig);
    }
    
    private EmailMessage(String personalName, EmailConfig emailConfig) {
        
        this.personalName = personalName;
        this.emailConfiguration = EmailConfiguration.setDefaultConfig(emailConfig);
    }
    
    private EmailMessage(String personalName, String senderMail, String senderPassword, String host, String port) {
        
        this.personalName = personalName;
        this.emailConfiguration = EmailConfiguration.setFullConfig(senderMail, senderPassword, host, port);
    }
    
    
    static EmailMessage setDefaultConfig(EmailConfig emailConfig){
    
        return new EmailMessage(emailConfig);
    }
    
    static EmailMessage setPersonalName(String personalName, EmailConfig emailConfig){
    
        return new EmailMessage(personalName, emailConfig);
    }
    
    static EmailMessage setFullConfig(String personalName, String senderMail, String senderPassword, String host, String port){
    
        return new EmailMessage(personalName, senderMail, senderPassword, host, port);
    }
    
    Message generateMessage(String recepientEmail, String messageSubject, String messageText) {
    
        Message message = new MimeMessage(this.emailConfiguration.getSession());
        
        try {
            
            message.setFrom(new InternetAddress(this.emailConfiguration.getSenderEmail(), this.personalName));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepientEmail));
            message.setSubject(messageSubject);
            message.setText(messageText); 
            
        } catch (MessagingException | UnsupportedEncodingException exception) {
            MessageExceptionHandler.print(exception.toString());
        }
    
        return message;
    }
    
}
