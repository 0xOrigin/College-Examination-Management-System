package AppDataReader;

import java.io.File;

/**
 * The class Config path.
 *
 * @author 0xOrigin
 */
class ConfigPath {
    
    private final String configFilePath;

    /**
     * Instantiates a new Config path.
     */
    ConfigPath() {
        
        this.configFilePath = "App_Data/App.config";
    }

    /**
     * Instantiates a new Config path.
     *
     * @param path the path
     */
    ConfigPath(String path){
    
        this.configFilePath = path;
    }

    /**
     * Get Config file path.
     *
     * @return the config file path
     */
    String get(){
    
        if(!this.isValidPath())
            this.endOfExecution();
        
        return this.configFilePath;
    }
    
    private boolean isValidPath(){
    
        return new File(this.configFilePath).exists();
    }
    
    private void endOfExecution() {
    
        System.out.println("The App config file not found at: " + this.configFilePath);
        System.exit(0);
    }
    
}
