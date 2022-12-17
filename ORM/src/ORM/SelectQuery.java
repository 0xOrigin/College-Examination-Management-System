package ORM;

import java.util.List;

/**
 * The class Select query.
 *
 * @author 0xOrigin
 */

public class SelectQuery {
    
    private final String table;
    private final List<Object> fields;
    private final String where, orderBy, limit, like;
    private final String freeStatement;

    /**
     * Instantiates a new Select query.
     *
     * @param builder the builder
     */
    public SelectQuery(SelectBuilder builder){
        
        this.table = builder.getTableName();
        this.fields = builder.getFields();
        this.where = builder.getWhereCondition();
        this.like = builder.getLike();
        this.orderBy = builder.getOrderBy();
        this.limit = builder.getLimit();
        this.freeStatement = builder.getFreeStatement();
    }

    
    @Override
    public String toString(){

        if(!this.freeStatement.isBlank())
            return this.freeStatement;

        return "SELECT " + this.fields.toString().replaceAll("[\\[\\]]", "") + " FROM " + this.table +
                (!this.where.isBlank() ? " WHERE ( " + this.where + " ) " : "") +
                (!this.like.isBlank() ? " LIKE '" + this.like + "'" : "") +
                (!this.orderBy.isBlank() ? " ORDER BY " + this.orderBy : "") +
                (!this.limit.isBlank() ? " LIMIT " + this.limit : "") +
                ";";
    }
    
}


