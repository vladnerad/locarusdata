package ru.dst.analyze.locarus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.dst.analyze.locarus.response.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class JsonHelper {

    private static final String USER_CREDENTIALS = "dst_ural:dst_ural";
    //    private String locarusNum;
//    private String fromDate;
//    private String toDate;
    private String url;
    private ObjectMapper mapper;

    public JsonHelper(String locarusNum, String fromDate, String toDate) {
//        this.locarusNum = locarusNum;
//        this.fromDate = fromDate;
//        this.toDate = toDate;

        this.url = String.format(
                "http://lserver3.ru:8091/do.locator?q=track&imei=%s&mode=full&filter=false&from=%sT00:00:00Z&to=%sT23:59:59Z",
                locarusNum, fromDate, toDate);

        mapper = new ObjectMapper();
    }

    private String basicAuth = "Basic " + new String(Base64.getEncoder().encode(USER_CREDENTIALS.getBytes()));


    public String getJson() {
        try {
            java.net.URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", basicAuth);

            System.out.print("Getting connection... ");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println(" done.");

            String inputLine;
            StringBuilder response = new StringBuilder();

            System.out.print("Collecting data... ");
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(" done.");
//            System.out.println(response.toString());

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Message getMessage() {
        String response = getJson();
        if (response != null && !response.equals("")) {
            try {
                return mapper.readValue(response, Message.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}