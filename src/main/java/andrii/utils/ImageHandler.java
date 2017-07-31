package andrii.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

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

    public static void save(BufferedImage bufferedImage, Path path) {
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path.getFileName());
            ImageIO.write(bufferedImage, "jpg", path.toFile());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static String loadEncodedImage(Path path) {

        try (InputStream inputStream = new FileInputStream(path.toString())){

            byte[] binaryData = IOUtils.toByteArray(inputStream);
            return new String(Base64.encodeBase64(binaryData));

        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

}
