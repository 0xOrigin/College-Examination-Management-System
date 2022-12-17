package ORM;

import ORM.Utilities.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Resource.
 *
 * @author 0xOrigin
 */
public class Resource {
    
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;


    /**
     * Instantiates a new Resource.
     *
     * @param resource the resource instance
     */
    public Resource(ResultSet resource){
    
        this.resultSet = resource;
        
        try {
            
            this.statement = resource.getStatement();
            this.connection = resource.getStatement().getConnection();
            
        } catch (SQLException ex) {
            ModelExceptionHandler.handle(ex, true);
        }
    }


    /**
     * Instantiates a new Resource.
     *
     * @param resource the resource instance
     */
    public Resource(Statement resource){
    
        this.resultSet = null;
        this.statement = resource;
        
        try {
            
            this.connection = resource.getConnection();
        
        } catch (SQLException ex) {
            ModelExceptionHandler.handle(ex, true);
        } 
    }


    /**
     * Is result set empty.
     *
     * @return the boolean
     */
    public boolean isResultSetEmpty(){
    
        try {
            
            if(this.resultSet == null || this.resultSet.isClosed())
                Debugger.emptyResultSet(true);
            
            return this.resultSet == null || this.resultSet.isClosed();
            
        } catch (SQLException ex) {
            ModelExceptionHandler.handle(ex, true);
        }
        
        return false;
    }


    /**
     * Close.
     */
    public void close(){
    
        try {
            
            this.statement.close();
            this.connection.close();
            
        } catch (SQLException ex) {
            ModelExceptionHandler.handle(ex, true);
        }
    }
    
}
