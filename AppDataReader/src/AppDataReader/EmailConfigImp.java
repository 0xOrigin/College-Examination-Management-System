package AppDataReader;

import java.util.Map;

/**
 *
 * @author xorigin
 */
public class EmailConfigImp implements EmailConfig{
    
    private final JSONReader jsonReader;
    private final Map<Object, Object> emailMap;
    
    public EmailConfigImp(){
    
        this.jsonReader = new JSONReader(new ConfigPath().get());
        
        this.emailMap = (Map) this.jsonReader.getMap(AppDataEnum.EmailConfigurations);
    }
    
    @Override
    public String get(Enum field){
    
        return (String) this.emailMap.get(field.name());
    }
    
}
