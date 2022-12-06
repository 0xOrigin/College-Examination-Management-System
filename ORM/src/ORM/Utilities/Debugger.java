package ORM.Utilities;

/**
 *
 * @author xorigin
 */
public class Debugger {
    
    public static void printQuery(String query, boolean On){
        
//        On = false; // For self-disabling

        if(On)
            System.out.println(query);
    }
    
    public static void emptyResultSet(boolean On){
    
        On = false; // For self-disabling

        if(On)
            System.out.println("Empty ResultSet!");
        
    }
}
