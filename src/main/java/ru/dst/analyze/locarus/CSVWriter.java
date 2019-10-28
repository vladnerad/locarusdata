package ru.dst.analyze.locarus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    private File csvFile;
    private FileWriter fileWriter;

    public CSVWriter(String fileName) {
        try {
            csvFile = new File(fileName);
            fileWriter = new FileWriter(csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLine(String line) {
        try {
            fileWriter.write(line + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String arrToString(int[] numbers) {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < numbers.length; i++) {
            result.append(numbers[i]);
            if (i != numbers.length - 1) {
                result.append(",");
            }
        }
        return result.toString();
    }

    public void writeJsonLine(int[] numbers) {
        writeLine(arrToString(numbers));
    }

    public void close() throws IOException {
        fileWriter.write("end_file");
        fileWriter.flush();
        fileWriter.close();
    }

}