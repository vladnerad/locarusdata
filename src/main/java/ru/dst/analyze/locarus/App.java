package ru.dst.analyze.locarus;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.dst.analyze.locarus.response.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {

//        System.out.println(DataParser.getNumberFromByte(15673, 0, 8) - 40);
//        System.out.println(DataParser.getNumberFromByte(15673, 8, 8) - 40);

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

        if (message != null) {
            int[] arr = DataParser.getIntegerArray(message, 11);
            int[] arr2 = DataParser.getIntegerArray(message, 12);
            int[] arr3 = DataParser.getIntegerArray(message, 7);
            if (arr != null && arr2 != null && arr3 != null) {
                System.out.println("Array length is " + arr.length);
                DataAnalyzer.analyzeErrors(arr, 0);
                DataAnalyzer.analyzeErrors(arr2, 1);
                DataAnalyzer.analyzeErrors(arr3, 2);
            }
        } else System.out.println("Message is null");
    }
}
