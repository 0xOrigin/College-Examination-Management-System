package CEMS.Models.Database;

import CEMS.Models.Enum.Column;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import ORM.*;
import ORM.Utilities.*;


/**
 * The class Model utility.
 */
abstract class ModelUtility {
    
    private final Adapter modelInstance;
    private final Enum uniqueKey;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;


    /**
     * Instantiates a new Model utility.
     *
     * @param adapter the adapter
     */
    protected ModelUtility(Adapter adapter){
    
        this.modelInstance = adapter;
        this.uniqueKey = adapter.getUniqueKeyColumnName();
    }


    /**
     * Get info map.
     *
     * @param fields     the fields
     * @param identifier the identifier
     * @return the map
     */
    public Map<Enum, Object> getInfo(List<Enum> fields, String identifier){
        
        Map<Enum, Object> info = new HashMap<>();
        
        this.selectQuery = new SelectBuilder(Arrays.asList(fields),
                                        this.modelInstance.getTableName())
                                        .where(this.uniqueKey, "=", identifier).build();
        
        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try { 
        
            if(!this.resource.isResultSetEmpty())
                for(Enum field : fields)
                    info.put(field, this.resultSet.getObject(field.name()));
            
        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }
        
        return info;
    }


    /**
     * Is password match.
     *
     * @param identifier the identifier
     * @param password   the password
     * @return the boolean
     */
    public boolean isPasswordMatch(String identifier, String password){
    
        boolean isMatch = false;
        
        this.selectQuery = new SelectBuilder(Arrays.asList(Column.Password),
                                        this.modelInstance.getTableName())
                                        .where(this.uniqueKey, "=", identifier)
                                        .build();

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try { 
        
            if(!this.resource.isResultSetEmpty())
                isMatch = this.resultSet.getString(Column.Password.name()).equals(password);
            
        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }

        return isMatch;
    }

    /**
     * Get list.
     *
     * @param fields      the fields
     * @param selectQuery the select query
     * @return the list
     */
    public List<Map<Enum, Object>> getList(List<Enum> fields, SelectQuery selectQuery){

        Map<Enum, Object> info;
        List<Map<Enum, Object>> container = new ArrayList<>();

        this.resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        this.resource = new Resource(this.resultSet);

        try {

            if(!this.resource.isResultSetEmpty()){

                while(this.resultSet.next()){

                    info = new HashMap<>();

                    for(Enum field : fields)
                        info.put(field, this.resultSet.getObject(field.name()));

                    container.add(info);
                }
            }

        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }

        return container;
    }

}
