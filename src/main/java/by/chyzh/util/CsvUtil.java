package by.chyzh.util;

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
public class CsvUtil {

    public static void csvWriter(List<String[]> stringArray, File csvFile) {

        CSVWriter writer;

        try {

            if (!csvFile.getParentFile().exists()) {
                if (!csvFile.getParentFile().mkdir()) {
                    log.error("Directory is not created");
                }
                log.info("Directory is created");
            }

            log.info(format("File creation: %s", csvFile.getName()));

            writer = new CSVWriter(new FileWriter(csvFile));

            log.info("File created");

            log.info(format("Recording data to %s", csvFile.getName()));

            stringArray.forEach(writer::writeNext);
            writer.close();

            log.info("The recording is completed");

        } catch (IOException e) {
            log.error(format("%s is not created", csvFile.getName()));
        }
    }
}
