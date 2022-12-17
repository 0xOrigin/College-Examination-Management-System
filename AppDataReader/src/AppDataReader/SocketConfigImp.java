package AppDataReader;

import java.util.Map;

/**
 * The class Socket config imp.
 * @author 0xOrigin
 */
public class SocketConfigImp implements SocketConfig{

    private final Map<Object, Object> configMap;

    /**
     * Instantiates a new Socket config imp.
     */
    public SocketConfigImp(){
        JSONReader jsonReader = new JSONReader(new ConfigPath().get());

        this.configMap = (Map) jsonReader.getMap(AppDataEnum.SocketConfigurations);
    }

    @Override
    public String getHost() {
        return (String) this.configMap.get(AppDataEnum.Host.name());
    }

    @Override
    public int getPort() {
        return Integer.parseInt((String) this.configMap.get(AppDataEnum.Port.name()));
    }

    @Override
    public int getBacklog(){
        return Integer.parseInt((String) this.configMap.get(AppDataEnum.Backlog.name()));
    }

}
