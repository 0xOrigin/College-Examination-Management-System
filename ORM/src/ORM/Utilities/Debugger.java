package ORM.Utilities;

/**
 * The class Debugger.
 *
 * @author 0xOrigin
 */
public class Debugger {

    /**
     * Print query.
     *
     * @param query the query
     * @param On    the print determination
     */
    public static void printQuery(String query, boolean On){
        
//        On = false; // For self-disabling

        if(On)
            System.out.println(query);
    }

    /**
     * Print Empty result set.
     *
     * @param On the print determination
     */
    public static void emptyResultSet(boolean On){
    
        On = false; // For self-disabling

        if(On)
            System.out.println("Empty ResultSet!");
        
    }
}
