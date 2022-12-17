package AppDataReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.util.Map;

/**
 * The class Json reader.
 *
 * @author 0xOrigin
 */
class JSONReader {

    private Object parsedFile;
    private JSONObject json;


    /**
     * Instantiates a new Json reader.
     *
     * @param filePath the file path
     */
    JSONReader(String filePath){
        
        try {
        
            this.parsedFile = new JSONParser().parse(new FileReader(filePath));
            this.json = (JSONObject) this.parsedFile;
        
        } catch(IOException | ParseException exception){
            System.out.println(exception);
        }
        
    }


    /**
     * Get value as object.
     *
     * @param value the value
     * @return the object
     */
    Object getValue(Enum value){
        
        return this.json.get(value.name());
    }


    /**
     * Get map of a specific name.
     *
     * @param value the value
     * @return the map
     */
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
