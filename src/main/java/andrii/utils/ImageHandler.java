package andrii.utils;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageHandler {

    public static BufferedImage decodeBASE64 (String sourceData) {
        String formattedData = sourceData.split(",")[1];
        Base64 decoder = new Base64();

        byte[] imageByte = decoder.decode(formattedData);
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(imageByte)){
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void save(BufferedImage bufferedImage, String fileName) {
        try {
            ImageIO.write(bufferedImage, "jpg", new File("D:\\" + fileName + ".jpg"));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
