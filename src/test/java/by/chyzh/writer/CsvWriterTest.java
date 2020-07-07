package by.chyzh.writer;


import com.opencsv.CSVReader;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CsvWriterTest {

    private List<String[]> list;
    private File file;

    @Before
    public void before() {
        list = new ArrayList<>();
        list.add(new String[]{"www.tut.by", "0", "1", "2"});
        file = new File("src/test/resources/csv/test.csv");
    }

    @After
    public void after() {
        file.delete();
        file.getParentFile().delete();
    }

    @Test
    @SneakyThrows
    public void checkCsvWriter() {

        CsvWriter.write(list, file);

        CSVReader csvReader = new CSVReader(new FileReader(file));

        List<String[]> strings = csvReader.readAll();

        csvReader.close();

        assertTrue(file.getParentFile().exists());
        assertTrue(file.exists());
        assertEquals(1, strings.size());
        assertEquals(list.get(0)[0], strings.get(0)[0]);
        assertEquals(list.get(0)[1], strings.get(0)[1]);
        assertEquals(list.get(0)[2], strings.get(0)[2]);
        assertEquals(list.get(0)[3], strings.get(0)[3]);
    }
}