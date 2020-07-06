package by.chyzh.util;

import com.opencsv.CSVWriter;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static by.chyzh.util.PathUtil.PATH_TO_CSV_DIRECTORY;


@Log4j
@UtilityClass
public class CsvUtil {

    public static void csvWriter(List<String[]> stringArray, String nameCsvFile) {

        CSVWriter writer;

        try {

            log.info("File creation: " + nameCsvFile);

            File file = new File(PATH_TO_CSV_DIRECTORY);

            if (!file.exists()) {
                file.mkdir();
            }

            writer = new CSVWriter(new FileWriter(PATH_TO_CSV_DIRECTORY + nameCsvFile));

            log.info("File created");

            log.info("Recording allData to " + nameCsvFile);

            stringArray.forEach(writer::writeNext);
            writer.close();

            log.info("The recording is completed");

        } catch (IOException e) {
            log.error(nameCsvFile + " is not created");
        }


    }
}
