package ORM;

/**
 * The class Dml.
 *
 * @author 0xOrigin
 */
abstract class DML {

    /**
     * Instantiates a new Dml.
     */
    protected DML(){
    
    }

    /**
     * Where condition.
     *
     * @param field    the field
     * @param operator the operator
     * @param value    the value
     * @return the condition
     */
    public String Where(Enum field, String operator, Object value){
    
        String statement = " ( " + field.name() + " " + operator + " ";

        boolean select = String.valueOf(value).toLowerCase().contains("select");
        if(value instanceof String && !select)
            statement += "\'" + value + "\'";
        else if(select) // To support nested Select queries.
            statement += "( " + value + " )";
        else
            statement += (value != null ? value : "");
        
        return statement + " )";
    }

    /**
     * Aggregate.
     *
     * @param functionName      the function name
     * @param optionalAttribute the optional attribute
     * @param field             the field
     * @return the aggregate string
     */
    public String Aggregate(String functionName, String optionalAttribute, Enum field){
    
        return functionName.toUpperCase() + "(" + optionalAttribute.toUpperCase() + " " + field.name() + ")";
    }

    /**
     * Control precedence.
     *
     * @param character the character
     * @return the string
     */
    public String ControlPrecedence(String character){
    
        return character;
    }

    /**
     * Operator.
     *
     * @param operator the operator
     * @return the string
     */
    public String Operator(String operator){
    
        return " " + operator.toUpperCase() + " ";
    }

    /**
     * Binary operator.
     *
     * @param value1   the value 1
     * @param operator the operator
     * @param value2   the value 2
     * @return the string
     */
    public String binaryOperator(String value1, String operator, String value2){

        return value1 + " " + operator.toUpperCase() + " " + value2;
    }

}
