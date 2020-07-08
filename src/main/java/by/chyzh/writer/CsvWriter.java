package by.chyzh.writer;

import com.opencsv.CSVWriter;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static java.lang.String.format;


@Log4j
@UtilityClass
public class CsvWriter {

    public static void write(List<String[]> stringArray, String csvName) {

        CSVWriter writer;

        try {

            writer = new CSVWriter(new FileWriter(csvName));
            stringArray.forEach(writer::writeNext);
            writer.close();

            log.info(format("Recording data to %s is completed", csvName));

        } catch (IOException e) {
            log.error(format("%s is not created", csvName));
        }
    }
}
