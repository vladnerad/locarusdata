package ru.dst.analyze.locarus;

import ru.dst.analyze.locarus.handlers.*;

public class App {
    public static void main(String[] args) {

//        System.out.println(DataParser.getNumberFromByte(62916096, AttPressHandler.getInstance()));
//        System.out.println(DataParser.getNumberFromByte(62916096, FanDrivePressHandler.getInstance()));

        System.out.println(DataParser.getNumberFromByte(469966896, LeftPressHandler.getInstance()));
        System.out.println(DataParser.getNumberFromByte(469966896, RightPressHandler.getInstance()));
        System.out.println(DataParser.getNumberFromByte(469966896, BrakePressHandler.getInstance()));

//        System.out.println(DataParser.getNumberFromByte(21050, TempEnvHandler.getInstance()));
//        System.out.println(DataParser.getNumberFromByte(21050, TempTurboHandler.getInstance()));

//        String locarusNum = "";
//        String from = "";
//        String to = "";
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            System.out.println("Enter locarus number:");
//            locarusNum = br.readLine();
//            System.out.println("Enter date FROM (yyyy-mm-dd):");
//            from = br.readLine();
//            System.out.println("Enter date TO (yyyy-mm-dd):");
//            to = br.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Message message = new JsonHelper(locarusNum, from, to).getMessage();
//
//        if (message != null) {
//            int[] arr = DataParser.getIntegerArray(message, 11);
//            int[] arr2 = DataParser.getIntegerArray(message, 12);
//            int[] arr3 = DataParser.getIntegerArray(message, 7);
//            if (arr != null && arr2 != null && arr3 != null) {
//                System.out.println("Array length is " + arr.length);
//                DataAnalyzer.analyzeErrors(arr, 0);
//                DataAnalyzer.analyzeErrors(arr2, 1);
//                DataAnalyzer.analyzeErrors(arr3, 2);
//            }
//        } else System.out.println("Message is null");
    }
}
