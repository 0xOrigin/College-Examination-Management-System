package ORM.Utilities;

import java.sql.SQLException;

/**
 *
 * @author xorigin
 */
public class ModelExceptionHandler {
    
    /**
     *
     * @param exception
     * @param On
     */
    public static void handle(SQLException exception, boolean On){
    
//        On = false; // For self-disabling
        
        if(On)
            System.out.println(exception);
    }
    
}
