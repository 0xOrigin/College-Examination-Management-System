package Email;

/**
 * The class Message exception handler.
 *
 * @author 0xOrigin
 */
class MessageExceptionHandler {

    /**
     * Handle.
     *
     * @param exception      the exception
     * @param messageSubject the message subject
     * @param messageText    the message text
     */
    static void handle(String exception, String messageSubject, String messageText){
    
        print(exception);
        System.out.println("----------------------------------------------------------");
        System.out.println("Mail subject: \n\t" + messageSubject);
        System.out.println("Mail text: \n" + messageText);
        System.out.println("----------------------------------------------------------");
    }

    /**
     * Print.
     *
     * @param exception the exception
     */
    static void print(String exception){

        System.out.println("## Email services: Couldn't connect to host or port.");
        System.out.println("## Email services: Please contact customer service to get your login information.");
        System.out.println(exception);
    }
}
