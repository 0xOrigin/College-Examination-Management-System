package AppDataReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.util.Map;

/**
 *
 * @author xorigin
 */
class JSONReader {

    private Object parsedFile;
    private JSONObject json;
    
    
    JSONReader(String filePath){
        
        try {
        
            this.parsedFile = new JSONParser().parse(new FileReader(filePath));
            this.json = (JSONObject) this.parsedFile;
        
        } catch(IOException | ParseException exception){
            System.out.println(exception);
        }
        
    }
    
    
    Object getValue(Enum value){
        
        return this.json.get(value.name());
    }
    
    
    Map<Object, Object> getMap(Enum value){
        
        Map map = (Map) this.json.get(value.name());
        Map<Object, Object> data = new HashMap<>();
        
        Iterator<Map.Entry> iterator = map.entrySet().iterator();
        
        while(iterator.hasNext()){
        
            Map.Entry pair = iterator.next();

            data.put(pair.getKey(), pair.getValue());
        }
        
        return data;
    }
}
