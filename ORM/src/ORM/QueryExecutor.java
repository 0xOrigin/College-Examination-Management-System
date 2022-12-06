package ORM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import ORM.Utilities.*;
import AppDataReader.*;

/**
 *
 * @author xorigin
 */
public class QueryExecutor {
    
    static void execute(String query, Queue<String> imagesPaths){
        
        if(query.isBlank())
            throw new UnsupportedOperationException("Query is Empty!");
            
        PreparedStatement preparedStatement = prepareQuery(query, imagesPaths);

        try {
            
            // Determine the type of Query to execute
            switch(query.split(" ")[0].toLowerCase()){

                case "insert":
                case "delete":
                    Debugger.printQuery(query, true);
                    preparedStatement.execute();
                    break;
                case "update":
                    Debugger.printQuery(query, true);
                    preparedStatement.executeUpdate();
                    break;
                default:
                    throw new UnsupportedOperationException("Invalid Query Syntax!");
            }
            
        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        }
        
        new Resource(preparedStatement).close();       
    }
    
    
    public static ResultSet executeSelectQuery(SelectQuery selectQuery){
    
        String query = selectQuery.toString();
        Debugger.printQuery(query, true);
        
        if(query.isBlank() || !query.toLowerCase().contains("select"))
            throw new UnsupportedOperationException("Invalid Query!");
        
        PreparedStatement preparedStatement = prepareQuery(query, new LinkedList<>());
        ResultSet resultSet = null;
        
        try {

            resultSet = preparedStatement.executeQuery();
            
        } catch (SQLException ex) {
            ModelExceptionHandler.handle(ex, true);
        }
        
        return resultSet;
    }
    
    
    private static PreparedStatement prepareQuery(String query, Queue<String> imagesPaths){
    
        // Lazy connection to SQLite
        Connection connection = DatabaseConnection.getInstance(new ConnectionStringImp());
        PreparedStatement preparedStatement = null;

        try {
        
            preparedStatement = connection.prepareStatement(query);

            if(query.contains(" ? ")){

                // Replaces ? that written by SQLiteAdapter.processValues in the Query.
                
                int iterator = 1;

                while(!imagesPaths.isEmpty()){

                    preparedStatement.setBytes(iterator++, ImageConverter.readImage(imagesPaths.remove()));
                }
            }
            
        } catch (SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        }
           
        return preparedStatement;
    }
    
}
