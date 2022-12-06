package ORM;

import java.util.List;

/**
 *
 * @author xorigin
 */
public class SelectBuilder extends DML {
    
    private final String table;
    private final List<Object> fields;
    private String where, orderBy, limit, like;
    private String freeStatement;
    
    public SelectBuilder(List<Object> fields, Enum table){
        
        this.table = table.name();
        this.fields = fields;
        this.where = "";
        this.like = "";
        this.orderBy = "";
        this.limit = "";
        this.freeStatement = "";
    }
    
    public SelectBuilder(){
        this.table = "";
        this.fields = null;
    }
    
    public SelectBuilder where(Enum field, String operator, Object value){
        
        this.where += super.Where(field, operator, value);
        return this;
    }

    public SelectBuilder like(String pattern){
        this.like = pattern;
        return this;
    }

    public SelectBuilder operator(String operator){
    
        this.where += super.Operator(operator);
        return this;
    }
    
    public SelectBuilder controlPrecedence(String character){
        
        this.where += super.ControlPrecedence(character);
        return this;
    }
    
    public SelectBuilder orderBy(Enum orderBy, String typeOfOrder){
        
        this.orderBy = orderBy.name() + " " + typeOfOrder.toUpperCase();
        return this;
    }
    
    public SelectBuilder limit(String limit){
        
        this.limit = limit;
        return this;
    }
    
    public SelectQuery build(){
    
        return new SelectQuery(this);
    }

    public SelectQuery freeSQLStatement(String freeStatement){
        this.freeStatement = freeStatement;

        return new SelectQuery(this);
    }
    
    public String getTableName(){
        
        return this.table;
    }
    
    public List<Object> getFields(){
        
        return this.fields;
    }
    
    
    public String getWhereCondition(){
        
        return this.where;
    }
    
    
    public String getOrderBy(){
        
        return this.orderBy;
    }
    
    
    public String getLimit(){
        
        return this.limit;
    }

    public String getLike(){

        return this.like;
    }

    public String getFreeStatement(){

        return this.freeStatement;
    }
    
}
