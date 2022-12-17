package AppDataReader;

/**
 * The interface Email config.
 *
 * @author 0xOrigin
 */
public interface EmailConfig {

    /**
     * Get email config by field name.
     *
     * @param field the field
     * @return the email config
     */
    public String get(Enum field);
    
}
