package ru.dst.analyze.locarus;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.dst.analyze.locarus.response.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

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

        ObjectMapper mapper = new ObjectMapper();
        String response = new JsonHelper(locarusNum, from, to).getJson(); /*getJson(locarusNum, from, to);*/
//        System.out.println(response);

        if (response != null && !response.equals("")) {
            try {
                Message message = mapper.readValue(response, Message.class);
                if (message != null) {
                    int[] arr = getIntegerArray(message, 11);
                    int[] arr2 = getIntegerArray(message, 12);
                    int[] arr3 = getIntegerArray(message, 7);
                    if (arr != null && arr2 != null && arr3 != null) {
                        System.out.println("Array length is " + arr.length);
                        analyzeErrors(arr, 0);
                        analyzeErrors(arr2, 1);
                        analyzeErrors(arr3, 2);
                    }
                } else System.out.println("Message is null");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Empty response");
        }
    }

//    private static String getJson(String locarusNum, String from, String to) {
//        try {
//            String url = String.format("http://lserver3.ru:8091/do.locator?q=track&imei=%s&mode=full&filter=false&from=%sT00:00:00Z&to=%sT00:00:00Z", locarusNum, from, to);
////            String url = "http://lserver3.ru:8091/do.locator?q=track&imei=4NG024644&mode=full&filter=false&from=2019-10-15T00:00:00Z&to=2019-10-17T00:00:00Z";
//            String userCredentials = "dst_ural:dst_ural";
//            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
//
//            URL obj = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
//
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Authorization", basicAuth);
//
//            System.out.print("Getting connection... ");
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            System.out.println(" done.");
//
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//
//            System.out.print("Collecting data... ");
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//            System.out.println(" done.");
////            System.out.println(response.toString());
//
//            return response.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    private static int[] getIntegerArray(Message message, int analogInNumber) {
        if (message.getDescription() == null) {
            Object[] pressL = message.getResult().getData().stream().map(data -> data.getAnalogIn().get(String.valueOf(analogInNumber))).toArray();
            int[] arr = new int[pressL.length];
            for (int i = 0; i < pressL.length; i++) {
//                arr[i] = Integer.parseInt(String.valueOf(pressL[i]));
                if (pressL[i] != null) {
                    arr[i] = (int) ((double) pressL[i] * 1/*0.05*/);
                }
            }
            return arr;
        } else {
            System.out.println(message.getDescription());
            return null;
        }
    }

    private static void analyzeErrors(int[] data, int num) {
        Set<Integer> allErrors = new HashSet<>();
        for (int n : data) {
            boolean[] bits = new boolean[32];
            for (int i = 31; i >= 0; i--) {
                bits[i] = (n & (1 << i)) != 0;
            }
            for (int i = 0; i < bits.length; i++) {
                if (bits[i]) {
                    if (num==0) allErrors.add(i + 1);
                    else if(num==1) allErrors.add(i + 33);
                    else if(num==2) allErrors.add(i + 65);
                    else System.out.println("analyzeErrors - wrong argument");
                }
            }
        }
        System.out.println(allErrors);
    }
}
