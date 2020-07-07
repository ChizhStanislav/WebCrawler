package by.chyzh.writer;

import com.opencsv.CSVWriter;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static java.lang.String.format;


@Log4j
@UtilityClass
public class CsvWriter {

    public static void write(List<String[]> stringArray, File csvFile) {

        CSVWriter writer;

        try {
            if (!csvFile.getParentFile().exists()) {
                if (!csvFile.getParentFile().mkdir()) {
                    log.error("Directory is not created");
                }
            }
            writer = new CSVWriter(new FileWriter(csvFile));
            stringArray.forEach(writer::writeNext);
            writer.close();

            log.info(format("Recording data to %s is completed", csvFile.getName()));

        } catch (IOException e) {
            log.error(format("%s is not created", csvFile.getName()));
        }
    }
}
