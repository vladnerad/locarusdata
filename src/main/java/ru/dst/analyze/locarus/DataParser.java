package ru.dst.analyze.locarus;

import ru.dst.analyze.locarus.response.Message;

public class DataParser {

    public static int[] getIntegerArray(Message message, int analogInNumber) {
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
}
