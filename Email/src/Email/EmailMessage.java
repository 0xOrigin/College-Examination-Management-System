package Email;

import java.io.UnsupportedEncodingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import AppDataReader.AppDataEnum;
import AppDataReader.EmailConfig;

/**
 * The class Email message.
 *
 * @author 0xOrigin
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


    /**
     * Set default email config.
     *
     * @param emailConfig the email config
     * @return the email message instance
     */
    static EmailMessage setDefaultConfig(EmailConfig emailConfig){
    
        return new EmailMessage(emailConfig);
    }

    /**
     * Set personal name and email config.
     *
     * @param personalName the personal name
     * @param emailConfig  the email config
     * @return the email message instance
     */
    static EmailMessage setPersonalName(String personalName, EmailConfig emailConfig){
    
        return new EmailMessage(personalName, emailConfig);
    }

    /**
     * Set full email config.
     *
     * @param personalName   the personal name
     * @param senderMail     the sender mail
     * @param senderPassword the sender password
     * @param host           the host
     * @param port           the port
     * @return the email message instance
     */
    static EmailMessage setFullConfig(String personalName, String senderMail, String senderPassword, String host, String port){
    
        return new EmailMessage(personalName, senderMail, senderPassword, host, port);
    }

    /**
     * Generate email message.
     *
     * @param recipientEmail the recipient email
     * @param messageSubject the message subject
     * @param messageText    the message text
     * @return the message instance
     */
    Message generateMessage(String recipientEmail, String messageSubject, String messageText) {
    
        Message message = new MimeMessage(this.emailConfiguration.getSession());
        
        try {
            
            message.setFrom(new InternetAddress(this.emailConfiguration.getSenderEmail(), this.personalName));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(messageSubject);
            message.setText(messageText); 
            
        } catch (MessagingException | UnsupportedEncodingException exception) {
            MessageExceptionHandler.print(exception.toString());
        }
    
        return message;
    }
    
}
