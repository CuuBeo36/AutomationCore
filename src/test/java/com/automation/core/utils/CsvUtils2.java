package com.automation.core.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;

public class CsvUtils2 {

    public void createCsvFile(String csvFileName) throws IOException {
        FileWriter out = new FileWriter(csvFileName);
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .build();
        CSVPrinter printer = csvFormat.print(out);
    }
}
