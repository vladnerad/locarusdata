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

    public void writeLine(String line) throws IOException {
        fileWriter.write(line + "\n");
    }

    public String arrToString(int[] numbers) {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < numbers.length; i++) {
            result.append(numbers[i]);
//            if (i != numbers.length - 1) {
            result.append(",");
//            }
        }
        return result.toString();
    }

    public void writeJsonLineFromIntArray(int[] numbers) throws IOException {
//        writeLine(arrToString(numbers));
        fileWriter.write(arrToString(numbers));
    }

    public void close() throws IOException {
        fileWriter.write("end_file");
        fileWriter.flush();
        fileWriter.close();
    }

    public void writeTime(String time) throws IOException {
        fileWriter.write(time);
        fileWriter.write(",");
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }
}