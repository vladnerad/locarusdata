package ru.dst.analyze.locarus;

import ru.dst.analyze.locarus.handlers.Handler;
import ru.dst.analyze.locarus.response.Message;

import java.util.Map;

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

    public static int getNumberFromByte(int number, int startBit, int length){
        boolean[] bits = new boolean[32];
        for (int i = 0; i < 32; i++) {
            bits[31 - i] = (number & (1 << i)) != 0;
        }
//        System.out.println(Arrays.toString(bits));
        int result = bits[bits.length - startBit - 1] ? 1 : 0;
        for (int z = 1; z < length; z++) {
            int o = (bits[bits.length - startBit - 1 - z] ? 1 : 0) << z;
            result = result | o;
        }
        return result;
    }

    public static int getNumberFromByte(int number, Handler handler) {
        boolean[] bits = new boolean[32];
        for (int i = 0; i < 32; i++) {
            bits[31 - i] = (number & (1 << i)) != 0;
        }
        ;
        int result = bits[bits.length - handler.getStartBit() - 1] ? 1 : 0;
        for (int z = 1; z < handler.getLength(); z++) {
            int o = (bits[bits.length - handler.getStartBit() - 1 - z] ? 1 : 0) << z;
            result = result | o;
        }
        return (int) (result * handler.getMultiply()) + handler.getShift();
    }

    public static int[][] getDataArray(Message message) {
        int[][] result = new int[message.getResult().getData().size()][message.getResult().getData().get(0).getAnalogIn().size()];
        if (message.getDescription() == null) {
            for (int i = 0; i < result.length; i++) {
                Map<String, Double> map = message.getResult().getData().get(i).getAnalogIn();
                for (int j = 0; j < result[0].length; j++) {
                    result[i][j] = (int) (double) map.get(String.valueOf(j + 1));
                }
            }
            return result;
        } else {
            System.out.println(message.getDescription());
            return null;
        }
    }
}
