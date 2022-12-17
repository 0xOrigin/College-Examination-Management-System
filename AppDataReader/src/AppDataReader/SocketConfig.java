package AppDataReader;

/**
 * The interface Socket config.
 * @author 0xOrigin
 */
public interface SocketConfig {

    /**
     * Gets host.
     *
     * @return the host
     */
    String getHost();

    /**
     * Gets port.
     *
     * @return the port
     */
    int getPort();

    /**
     * Gets backlog.
     *
     * @return the backlog
     */
    int getBacklog();

}
