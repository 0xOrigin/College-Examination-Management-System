package ORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.File;
import AppDataReader.*;

/**
 * The class Database connection.
 *
 * @author 0xOrigin
 */
class DatabaseConnection {
    
    // Singleton design pattern
    private static Connection uniqueInstance;
    
    private DatabaseConnection(){

    }

    /**
     * Get connection instance.
     *
     * @param connStrings the connection string
     * @return the connection string
     */
    static Connection getInstance(ConnectionString connStrings){
    
        try {
            
            if(uniqueInstance == null || uniqueInstance.isClosed())
                uniqueInstance = new DatabaseConnection().getConnection(connStrings);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return uniqueInstance;
    }
    
    private Connection getConnection(ConnectionString connStrings){
        
        Connection connection = null; 
        
        try {
            
            String connectionPath = connStrings.getConnectionPath();
            
            if(!this.isValidPath(connectionPath))
                this.endOfExecution(connectionPath);
            
            Class.forName(connStrings.getClassName());
            connection = DriverManager.getConnection(connectionPath);
            
            try(PreparedStatement preparedStatement = connection.prepareStatement("PRAGMA foreign_keys = ON;");){
                
                preparedStatement.execute();
            
            } catch (SQLException exception){
                
                System.out.println(exception.toString());
            
            }
            
        } catch (ClassNotFoundException | SQLException exception) {
            
            System.out.println(exception.toString());
            
        }

        return connection;
    }
    
    private String getPath(String connectionPath){
    
        int start = connectionPath.lastIndexOf(":") + 1;
        
        return connectionPath.substring(start);
    }
    
    private boolean isValidPath(String connectionPath){
    
        return new File(this.getPath(connectionPath)).exists();
    }
       
    private void endOfExecution(String connectionPath){
    
        System.out.println("The database file not found at: " + this.getPath(connectionPath));
        System.exit(0);
    }
    
}