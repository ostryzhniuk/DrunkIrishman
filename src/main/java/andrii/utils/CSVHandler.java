package andrii.utils;

import andrii.dto.ProductDTO;
import com.univocity.parsers.common.processor.BeanWriterProcessor;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.common.processor.RowProcessor;
import com.univocity.parsers.common.processor.RowWriterProcessor;
import com.univocity.parsers.fixed.FixedWidthFields;
import com.univocity.parsers.fixed.FixedWidthWriter;
import com.univocity.parsers.fixed.FixedWidthWriterSettings;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVHandler {

    public static void parse(ProductDTO productDTO){

        ByteArrayOutputStream fixedWidthResult = new ByteArrayOutputStream();
        Writer outputWriter = new OutputStreamWriter(fixedWidthResult);
        FixedWidthFields lengths = new FixedWidthFields(10, 10, 35, 10, 40);
        FixedWidthWriterSettings settings = new FixedWidthWriterSettings(lengths);

        settings.setNullValue("null");
        settings.setRowWriterProcessor(new BeanWriterProcessor<>(ProductDTO.class));
        settings.setHeaders("name", "capacity", "price", "category", "description", "status");

        FixedWidthWriter writer = new FixedWidthWriter(outputWriter, settings);
        writer.writeHeaders();

        writer.processRecord(productDTO);
        writer.close();

        Path path = Paths.get("D:\\test.csv");
        try {
            Files.createFile(path.getFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(OutputStream outputStream = new FileOutputStream(path.toString())) {
            fixedWidthResult.writeTo(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
