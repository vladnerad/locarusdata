package ru.dst.analyze.locarus;

import ru.dst.analyze.locarus.handlers.*;
import ru.dst.analyze.locarus.resources.ParamNames;
import ru.dst.analyze.locarus.response.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//need to work with date as ID of string data;
//need to process digital inputs;
//need to process all errors;
public class App {
    public static void main(String[] args) {

//        String digIn = "-4294950528";
//        System.out.println(Arrays.toString(DataParser.getDigitalInputs(digIn)));
//        int num = 416284672;
//        System.out.println("Left: " + DataParser.getNumberFromByte(num, LeftPressHandler.getInstance()));
//        System.out.println("Right: " + DataParser.getNumberFromByte(num, RightPressHandler.getInstance()));
//        System.out.println("Brake: " + DataParser.getNumberFromByte(num, BrakePressHandler.getInstance()));

//        System.out.println("Front(+)/Back(-): " + JoyMoveHandler.getForwRev(num));
//        System.out.println("Right(+)/Left(-): " + JoyMoveHandler.getLeftRight(num));

//        System.out.println("Attachments joystick (right hand joy)");
//        System.out.println("Front(+)/Back(-): " + JoyAttachHandler.getForwRev(num));
//        System.out.println("Right(+)/Left(-): " + JoyAttachHandler.getLeftRight(num));

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
//            DataAnalyzer.analyzeDigitalIn(message);
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
            String analogIn = "Time,JoyMoveF(+)/B(-),JoyMoveR(+)/L(-),PressLPump,PressRPump,PressBrake,FuelLevel,JoyAttachF(+)/B(-),JoyAttachR(+)/L(-),PressAttach,PressFanDrive,EnvTemp,TurboTemp,HydOilTemp,HMSpeedL,HMSpeedR,EngineSpeed,CoolantTemp,EngineOilPress,MotoHours,Errors";
            String header = analogIn.concat(",").concat(ParamNames.getNamesForCSV(ParamNames.DIGITAL_IN_NAMES));
            int[][] data = DataParser.getDataArray(message);
            boolean[][] digInData = DataParser.getDigitalInData(message);
            String[] time = DataParser.getTimeArr(message);
            try {
                writer.writeLine(header);
                for (int i = 0; i < data.length; i++) {
                    writer.writeTime(time[i]);
                    writer.writeJsonLineFromIntArray(DataHandler.convertData(data[i]));
                    writer.getFileWriter().write(DataHandler.getErrors(data[i]));
                    writer.getFileWriter().write(",");
                    writer.writeLine(DataHandler.getStingFromDigInData(digInData[i]));
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("Message is null");
    }
}