package andrii.utils;

import andrii.dto.ProductDTO;
import com.univocity.parsers.common.processor.BeanWriterProcessor;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.common.processor.RowProcessor;
import com.univocity.parsers.common.processor.RowWriterProcessor;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import com.univocity.parsers.fixed.FixedWidthFields;
import com.univocity.parsers.fixed.FixedWidthWriter;
import com.univocity.parsers.fixed.FixedWidthWriterSettings;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVHandler {

    public static void write(ProductDTO productDTO){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Writer outputWriter = new OutputStreamWriter(byteArrayOutputStream);
        CsvWriterSettings settings = new CsvWriterSettings();

        settings.setNullValue("null");
        settings.setRowWriterProcessor(new BeanWriterProcessor<>(ProductDTO.class));
        settings.setHeaders("name", "capacity", "price", "category", "description", "status");

        CsvWriter writer = new CsvWriter(outputWriter, settings);
        writer.writeHeaders();

        writer.processRecord(productDTO);
        writer.close();

        Path path = Paths.get("D:\\test.csv");

        try(OutputStream outputStream = new FileOutputStream(path.toString())) {

            if (Files.exists(path)) {
                Files.delete(path);
            } else {
                Files.createDirectories(path.getParent());
                Files.createFile(path.getFileName());
            }

            byteArrayOutputStream.writeTo(outputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }

}
