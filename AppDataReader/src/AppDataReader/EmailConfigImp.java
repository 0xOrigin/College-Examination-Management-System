package AppDataReader;

import java.util.Map;

/**
 * The class Email config imp.
 *
 * @author 0xOrigin
 */
public class EmailConfigImp implements EmailConfig{
    
    private final JSONReader jsonReader;
    private final Map<Object, Object> emailMap;

    /**
     * Instantiates a new Email config imp.
     */
    public EmailConfigImp(){
    
        this.jsonReader = new JSONReader(new ConfigPath().get());
        
        this.emailMap = (Map) this.jsonReader.getMap(AppDataEnum.EmailConfigurations);
    }
    
    @Override
    public String get(Enum field){
    
        return (String) this.emailMap.get(field.name());
    }
    
}
