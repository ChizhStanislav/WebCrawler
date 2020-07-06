package by.chyzh.util;

import com.opencsv.CSVWriter;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static by.chyzh.util.PathUtil.PATH_TO_CSV_DIRECTORY;
import static java.lang.String.format;


@Log4j
@UtilityClass
public class CsvUtil {

    public static void csvWriter(List<String[]> stringArray, String nameCsvFile) {

        CSVWriter writer;

        try {

            log.info(format("File creation: %s.csv", nameCsvFile));

            File file = new File(PATH_TO_CSV_DIRECTORY);

            if (!file.exists()) {
                if (!file.mkdir()) {
                    log.error("Directory is not created");
                }
                log.info("Directory is created");
            }

            writer = new CSVWriter(new FileWriter(format("%s%s.csv", PATH_TO_CSV_DIRECTORY, nameCsvFile)));

            log.info("File created");

            log.info(format("Recording allData to %s.csv", nameCsvFile));

            stringArray.forEach(writer::writeNext);
            writer.close();

            log.info("The recording is completed");

        } catch (IOException e) {
            log.error(format("%s is not created", nameCsvFile));
        }


    }
}
