package ORM;

/**
 *
 * @author xorigin
 */
abstract class DML {
    
    protected DML(){
    
    }
    
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
    
    public String Aggregate(String functionName, String optionalAttribute, Enum field){
    
        return functionName.toUpperCase() + "(" + optionalAttribute.toUpperCase() + " " + field.name() + ")";
    } 
    
    public String ControlPrecedence(String character){
    
        return character;
    }
    
    public String Operator(String operator){
    
        return " " + operator.toUpperCase() + " ";
    }

    public String binaryOperator(String value1, String operator, String value2){

        return value1 + " " + operator.toUpperCase() + " " + value2;
    }

}
