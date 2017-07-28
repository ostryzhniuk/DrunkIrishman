package andrii.core;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageHandler.class);

    public static BufferedImage decodeBASE64 (String sourceData) {
        BASE64Decoder decoder = new BASE64Decoder();
        BufferedImage bufferedImage = null;
        ByteArrayInputStream inputStream = null;
        try {
            byte[] imageByte = decoder.decodeBuffer(sourceData);
            inputStream = new ByteArrayInputStream(imageByte);
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            LOGGER.warn("Decode image BASE64 has failed:", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bufferedImage;
        }
    }

    public static void save(BufferedImage bufferedImage, String fileName) {
        try {
            ImageIO.write(bufferedImage, "jpg", new File("D:\\" + fileName + ".jpg"));
        } catch (IOException e) {
            LOGGER.warn("Save image has failed:", e);
        }
    }

    public static void save(String sourceData, String fileName) {
//        BASE64Decoder decoder = new BASE64Decoder();
        Base64 decoder = new Base64();
        FileOutputStream outputStream = null;
        try {
            byte[] imageByte = decoder.decode(sourceData);
            outputStream = new FileOutputStream(new File("D:\\" + fileName + ".txt"));
            outputStream.write(imageByte);
//            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
