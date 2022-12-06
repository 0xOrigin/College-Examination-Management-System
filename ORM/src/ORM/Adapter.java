package ORM;

import java.util.List;

/**
 *
 * @author xorigin
 */
public interface Adapter {
    
    void insert(List<Enum> fields, List<Object> values);
    
    void update(List<Enum> fields, List<Object> values, String where);
    
    void delete(String where);
    
    String Where(Enum field, String operator, Object value);
    
    String Aggregate(String functionName, String optionalAttribute, Enum field);
    
    String ControlPrecedence(String character);
    
    String Operator(String operator);
    
    Enum getTableName();
    
    Enum getUniqueKeyColumnName();

    Enum getUniqueKeyColumnName2();

}
