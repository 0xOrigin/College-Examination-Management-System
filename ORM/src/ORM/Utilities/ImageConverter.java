package ORM.Utilities;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The class Image converter.
 *
 * @author 0xOrigin
 */
public class ImageConverter {

    /**
     * Read image.
     *
     * @param filePath the file path
     * @return the byte array
     */
    public static byte[] readImage(String filePath) {
        
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            byteArrayOutputStream = new ByteArrayOutputStream();
            
            for (int length; (length = fileInputStream.read(buffer)) != -1; )
                byteArrayOutputStream.write(buffer, 0, length);
            
        } catch (FileNotFoundException exception) {
            System.err.println("Image not Found!");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
        
        return byteArrayOutputStream != null ? byteArrayOutputStream.toByteArray() : null;
    }

    /**
     * Get image.
     *
     * @param imageArray the image byte array
     * @return the image instance
     */
    public static Image getImage(byte[] imageArray){
    
        return Toolkit.getDefaultToolkit().createImage(imageArray);
    }
    
}
