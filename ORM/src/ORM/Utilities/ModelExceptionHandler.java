package ORM.Utilities;

import java.sql.SQLException;

/**
 * The class Model exception handler.
 *
 * @author 0xOrigin
 */
public class ModelExceptionHandler {

    /**
     * Handle.
     *
     * @param exception the exception
     * @param On        the print determination
     */
    public static void handle(SQLException exception, boolean On){
    
//        On = false; // For self-disabling
        
        if(On)
            System.out.println(exception);
    }
    
}
