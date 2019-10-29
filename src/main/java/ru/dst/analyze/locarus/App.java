package ru.dst.analyze.locarus;

import ru.dst.analyze.locarus.handlers.*;
import ru.dst.analyze.locarus.response.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//need to work with date as ID of string data;
//need to process digital inputs;
//need to process all errors;
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
//        Message message = new JsonHelper("4NG024644", "2019-10-26", "2019-10-29").getMessage();

        if (message != null) {
//            int[] arr = DataParser.getIntegerArray(message, 11);
//            int[] arr2 = DataParser.getIntegerArray(message, 12);
//            int[] arr3 = DataParser.getIntegerArray(message, 7);
//            if (arr != null && arr2 != null && arr3 != null) {
//                System.out.println("Array length is " + arr.length);
//                DataAnalyzer.analyzeErrors(arr, 0);
//                DataAnalyzer.analyzeErrors(arr2, 1);
//                DataAnalyzer.analyzeErrors(arr3, 2);
//            }

            CSVWriter writer = new CSVWriter("C:\\Users\\vpriselkov\\Desktop\\test.csv");
//            writer.writeLine("JoyMoveF/B,JoyMoveL/R,PressLPump,PressRPump,PressBrake,FuelLevel,JoyAttachF/B,JoyAttachL/R,PressAttach,PressFanDrive,EnvTemp,TurboTemp,err3,HydOilTemp,HMSpeedL,HMSpeedR,err1,err2,EngineSpeed,CoolantTemp,EngineOilPress,MotoHours");
            writer.writeLine("Time,JoyMoveF(+)/B(-),JoyMoveR(+)/L(-),PressLPump,PressRPump,PressBrake,FuelLevel,JoyAttachF(+)/B(-),JoyAttachR(+)/L(-),PressAttach,PressFanDrive,EnvTemp,TurboTemp,HydOilTemp,HMSpeedL,HMSpeedR,EngineSpeed,CoolantTemp,EngineOilPress,MotoHours,Errors");
            int[][] data = DataParser.getDataArray(message);
            String[] time = DataParser.getTimeArr(message);
            for (int i = 0; i < data.length; i++) {
                writer.writeTime(time[i]);
                writer.writeJsonLine(DataHandler.convertData(data[i]));
                writer.writeLine(DataHandler.getErrors(data[i]));
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("Message is null");
    }
}