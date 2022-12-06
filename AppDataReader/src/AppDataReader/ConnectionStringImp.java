package AppDataReader;

import java.util.Map;

/**
 *
 * @author xorigin
 */
public class ConnectionStringImp implements ConnectionString {
    
    private final JSONReader jsonReader;
    private final Map<Object, Object> connMap;
    
    public ConnectionStringImp(){
    
        this.jsonReader = new JSONReader(new ConfigPath().get());
        
        this.connMap = (Map) this.jsonReader.getMap(AppDataEnum.ConnectionStrings);
    }
    
    @Override
    public String getClassName(){
    
        return (String) this.connMap.get(AppDataEnum.ClassName.name());
    }
    
    @Override
    public String getConnectionPath(){
    
        return (String) this.connMap.get(AppDataEnum.ClassPath.name()) + ":" + (String) this.connMap.get(AppDataEnum.DatabasePath.name());
    }
}
