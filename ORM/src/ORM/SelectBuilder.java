package ORM;

import java.util.List;

/**
 * The class Select builder.
 *
 * @author 0xOrigin
 */
public class SelectBuilder extends DML {
    
    private final String table;
    private final List<Object> fields;
    private String where, orderBy, limit, like;
    private String freeStatement;

    // Builder design pattern

    /**
     * Instantiates a new Select builder.
     *
     * @param fields the fields
     * @param table  the table
     */
    public SelectBuilder(List<Object> fields, Enum table){
        
        this.table = table.name();
        this.fields = fields;
        this.where = "";
        this.like = "";
        this.orderBy = "";
        this.limit = "";
        this.freeStatement = "";
    }

    /**
     * Instantiates a new Select builder with no parameters.
     */
    public SelectBuilder(){
        this.table = "";
        this.fields = null;
    }

    /**
     * Where condition.
     *
     * @param field    the field
     * @param operator the operator
     * @param value    the value
     * @return the select builder
     */
    public SelectBuilder where(Enum field, String operator, Object value){
        
        this.where += super.Where(field, operator, value);
        return this;
    }

    /**
     * Like.
     *
     * @param pattern the pattern
     * @return the select builder
     */
    public SelectBuilder like(String pattern){
        this.like = pattern;
        return this;
    }

    /**
     * Operator.
     *
     * @param operator the operator
     * @return the select builder
     */
    public SelectBuilder operator(String operator){
    
        this.where += super.Operator(operator);
        return this;
    }

    /**
     * Control precedence.
     *
     * @param character the character
     * @return the select builder
     */
    public SelectBuilder controlPrecedence(String character){
        
        this.where += super.ControlPrecedence(character);
        return this;
    }

    /**
     * Order by.
     *
     * @param orderBy     the order by
     * @param typeOfOrder the type of order
     * @return the select builder
     */
    public SelectBuilder orderBy(Enum orderBy, String typeOfOrder){
        
        this.orderBy = orderBy.name() + " " + typeOfOrder.toUpperCase();
        return this;
    }

    /**
     * Limit.
     *
     * @param limit the limit
     * @return the select builder
     */
    public SelectBuilder limit(String limit){
        
        this.limit = limit;
        return this;
    }

    /**
     * Build select query.
     *
     * @return the select query
     */
    public SelectQuery build(){
    
        return new SelectQuery(this);
    }

    /**
     * Free sql statement select query.
     *
     * @param freeStatement the free statement
     * @return the select query
     */
    public SelectQuery freeSQLStatement(String freeStatement){
        this.freeStatement = freeStatement;

        return new SelectQuery(this);
    }

    /**
     * Get table name.
     *
     * @return the table name
     */
    public String getTableName(){
        
        return this.table;
    }

    /**
     * Get fields list.
     *
     * @return the list
     */
    public List<Object> getFields(){
        
        return this.fields;
    }


    /**
     * Get where condition string.
     *
     * @return the string
     */
    public String getWhereCondition(){
        
        return this.where;
    }


    /**
     * Get order by string.
     *
     * @return the string
     */
    public String getOrderBy(){
        
        return this.orderBy;
    }


    /**
     * Get limit string.
     *
     * @return the string
     */
    public String getLimit(){
        
        return this.limit;
    }

    /**
     * Get like string.
     *
     * @return the string
     */
    public String getLike(){

        return this.like;
    }

    /**
     * Get free statement string.
     *
     * @return the string
     */
    public String getFreeStatement(){

        return this.freeStatement;
    }
    
}
