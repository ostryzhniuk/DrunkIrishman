package andrii.utils;

import andrii.dto.ProductDTO;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.common.processor.BeanWriterProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVHandler extends Base64Handler {

    public static void writeProducts(List<ProductDTO> productList, Path path){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Writer outputWriter = new OutputStreamWriter(byteArrayOutputStream);
        CsvWriterSettings settings = new CsvWriterSettings();

        settings.setRowWriterProcessor(new BeanWriterProcessor<>(ProductDTO.class));
        settings.setHeaders("id", "price", "status");

        CsvWriter writer = new CsvWriter(outputWriter, settings);
        writer.writeHeaders();

        writer.processRecords(productList);
        writer.close();

        try(OutputStream outputStream = new FileOutputStream(path.toString())) {

            Files.createFile(path.getFileName());

            byteArrayOutputStream.writeTo(outputStream);
            byteArrayOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }

    public static List<ProductDTO> parseProducts(String base64SourceData){
        BeanListProcessor<ProductDTO> beanListProcessor = new BeanListProcessor<>(ProductDTO.class);

        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.setProcessor(beanListProcessor);
        parserSettings.setHeaderExtractionEnabled(true);

        CsvParser parser = new CsvParser(parserSettings);

        try (InputStream inputStream = new ByteArrayInputStream(decodeBASE64(base64SourceData))) {
            parser.parse(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return beanListProcessor.getBeans();
    }

}
