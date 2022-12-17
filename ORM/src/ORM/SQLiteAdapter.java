package ORM;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The class SQL lite adapter.
 *
 * @author 0xOrigin
 */
public class SQLiteAdapter extends DML implements Adapter {
    
    private final Enum table;
    private final Enum uniqueKey1;
    private Enum uniqueKey2;

    /**
     * Instantiates a new SQL lite adapter.
     *
     * @param table                the table
     * @param uniqueKeyOfthisTable the unique key of this table
     */
    public SQLiteAdapter(Enum table, Enum uniqueKeyOfthisTable){
    
        this.table = table;
        this.uniqueKey1 = uniqueKeyOfthisTable;
    }

    /**
     * Instantiates a new SQL lite adapter.
     *
     * @param table                 the table
     * @param uniqueKeyOfthisTable1 the unique key of this table 1
     * @param uniqueKeyOfthisTable2 the unique key of this table 2
     */
    public SQLiteAdapter(Enum table, Enum uniqueKeyOfthisTable1, Enum uniqueKeyOfthisTable2){

        this.table = table;
        this.uniqueKey1 = uniqueKeyOfthisTable1;
        this.uniqueKey2 = uniqueKeyOfthisTable2;
    }
    
    @Override
    public void insert(List<Enum> fields, List<Object> values){
    
        if(checkEquality(fields.size(), values.size())){
        
            String query = "INSERT INTO " + this.table.name() + " (" + fields.toString().replaceAll("[\\[\\]]", "") + ")" +
                           " VALUES "  + "(" + processValues(values) + ");";
            
            QueryExecutor.execute(query, getImagesPaths(values));
        }
    }
    
    
    @Override
    public void update(List<Enum> fields, List<Object> values, String where){
        
        if(checkEquality(fields.size(), values.size())){
            
            String query = "UPDATE " + this.table.name() + " SET";
            
            for(int index = 0; index < fields.size(); index++)
                query = query.concat(" " + fields.get(index).name() + "=" + processValues(Arrays.asList(values.get(index))) + ",");

            query = query.substring(0, query.length()-1).concat(" WHERE " + where).concat(";");

            QueryExecutor.execute(query, getImagesPaths(values));
        }
    }
    
    
    @Override
    public void delete(String where){
        
        String query = "DELETE FROM " + this.table.name() + " WHERE " + where + ";";
        
        QueryExecutor.execute(query, new LinkedList<>());
    }
    
    @Override
    public Enum getTableName(){
    
        return this.table;
    }
    
    
    @Override
    public Enum getUniqueKeyColumnName(){
    
        return this.uniqueKey1;
    }


    @Override
    public Enum getUniqueKeyColumnName2(){

        return this.uniqueKey2;
    }
    
    private static String processValues(List<Object> values){
        
        int size = values.size();
        String valuesString = "";
        
        for(Object value : values){
        
            if(isFile(value))
                valuesString = valuesString.concat(" ? ");
            
            else if(value instanceof String && !((String) value).matches(".*[+*-][=].*")) // To support +=, -=, *=
                valuesString = valuesString.concat("\'" + value + "\'");
            
            else
                valuesString = valuesString.concat(value.toString().replaceAll("=", ""));
         
            valuesString = valuesString.concat((size-- == 1 ? "" : ", ")); // For String ending.
        }
            
        return valuesString;
    }
    
    
    private static boolean isFile(Object path){
    
        return new File(path.toString()).isFile();
    }
    
    private static boolean checkEquality(int fieldsSize, int valuesSize){
        
        if(fieldsSize != valuesSize)
            throw new UnsupportedOperationException("Check number of passed fields or values");

        return true;
    }
    
    
    private static Queue<String> getImagesPaths(List<Object> values){
        
        Queue<String> imagesPaths = new LinkedList<>();
        
        for(Object value : values)
            if(isFile(value))
                imagesPaths.add(String.valueOf(value));
        
        return imagesPaths;
    }
}