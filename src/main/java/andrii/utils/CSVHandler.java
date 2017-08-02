package andrii.utils;

import andrii.dto.ProductDTO;
import com.univocity.parsers.common.processor.BeanWriterProcessor;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVHandler {

    public static void writeProducts(List<ProductDTO> productList, Path path){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Writer outputWriter = new OutputStreamWriter(byteArrayOutputStream);
        CsvWriterSettings settings = new CsvWriterSettings();

        settings.setNullValue("null");
        settings.setRowWriterProcessor(new BeanWriterProcessor<>(ProductDTO.class));
        settings.setHeaders("name", "capacity", "price", "category", "description", "status");

        CsvWriter writer = new CsvWriter(outputWriter, settings);
        writer.writeHeaders();

        writer.processRecords(productList);
        writer.close();

        try(OutputStream outputStream = new FileOutputStream(path.toString())) {

            Files.createFile(path.getFileName());

            byteArrayOutputStream.writeTo(outputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }

}
