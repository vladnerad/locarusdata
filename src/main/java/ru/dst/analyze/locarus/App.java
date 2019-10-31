package ru.dst.analyze.locarus;

import ru.dst.analyze.locarus.handlers.*;
import ru.dst.analyze.locarus.resources.ParamNames;
import ru.dst.analyze.locarus.response.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {

        String locarusNum = "";
        String from = "";
        String to = "";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter locarus number:");
            locarusNum = br.readLine();
            System.out.println("Enter date FROM (yyyy-mm-dd):");
            from = br.readLine();
            System.out.println("Enter date TO (yyyy-mm-dd):");
            to = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Message message = new JsonHelper(locarusNum, from, to).getMessage();
//        Message message = new JsonHelper("4NG024644", "2019-10-30", "2019-10-30").getMessage();

        if (message != null) {
            CSVWriter writer = new CSVWriter("C:\\Users\\vpriselkov\\Desktop\\test.csv");
            // creating CSV header
            String header = ParamNames.getNamesForCSV(ParamNames.ANALOG_IN_NAMES)
                    .concat(",")
                    .concat(ParamNames.getNamesForCSV(ParamNames.DIGITAL_IN_NAMES));
            //getting Time from JSON response
            String[] time = DataParser.getTimeArr(message);
            //getting analogIn from JSON response
            int[][] data = DataParser.getDataArray(message);
            //getting digitalIn from JSON response
            boolean[][] digInData = DataParser.getDigitalInData(message);
            //creating and writing .csv file
            try {
                writer.writeLine(header);
                for (int i = 0; i < data.length; i++) {
                    writer.writeTime(time[i]);
                    writer.writeJsonLineFromIntArray(DataHandler.convertData(data[i]));
                    writer.getFileWriter().write(DataHandler.getErrors(data[i]));
                    writer.getFileWriter().write(",");
                    writer.writeLine(DataHandler.getStingFromDigInData(digInData[i], true));
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("Message is null");
    }
}