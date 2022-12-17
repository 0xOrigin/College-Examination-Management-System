package ORM;

import java.util.List;

/**
 * The interface Adapter.
 *
 * @author 0xOrigin
 */
public interface Adapter {

    /**
     * Insert.
     *
     * @param fields the fields
     * @param values the values
     */
    void insert(List<Enum> fields, List<Object> values);

    /**
     * Update.
     *
     * @param fields the fields
     * @param values the values
     * @param where  the where condition
     */
    void update(List<Enum> fields, List<Object> values, String where);

    /**
     * Delete.
     *
     * @param where the where condition
     */
    void delete(String where);

    /**
     * Where condition.
     *
     * @param field    the field
     * @param operator the operator
     * @param value    the value
     * @return the condition
     */
    String Where(Enum field, String operator, Object value);

    /**
     * Aggregate.
     *
     * @param functionName      the function name
     * @param optionalAttribute the optional attribute
     * @param field             the field
     * @return the aggregate string
     */
    String Aggregate(String functionName, String optionalAttribute, Enum field);

    /**
     * Control precedence.
     *
     * @param character the character
     * @return the string
     */
    String ControlPrecedence(String character);

    /**
     * Operator.
     *
     * @param operator the operator
     * @return the string
     */
    String Operator(String operator);

    /**
     * Gets table name.
     *
     * @return the table name
     */
    Enum getTableName();

    /**
     * Gets unique key column name.
     *
     * @return the unique key column name
     */
    Enum getUniqueKeyColumnName();

    /**
     * Gets unique key column name 2.
     *
     * @return the unique key column name 2
     */
    Enum getUniqueKeyColumnName2();

}
