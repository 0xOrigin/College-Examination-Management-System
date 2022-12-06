package ORM.Utilities;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author xorigin
 */
public class ImageConverter {
    
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
    
    public static Image getImage(byte[] imageArray){
    
        return Toolkit.getDefaultToolkit().createImage(imageArray);
    }
    
}
