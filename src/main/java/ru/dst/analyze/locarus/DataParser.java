package ru.dst.analyze.locarus;

import ru.dst.analyze.locarus.handlers.Handler;
import ru.dst.analyze.locarus.response.Data;
import ru.dst.analyze.locarus.response.Message;
import ru.dst.analyze.locarus.response.Time;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

    public static int getNumberFromByte(int number, int startBit, int length) {
        boolean[] bits = new boolean[32];
        for (int i = 0; i < 32; i++) {
            bits[31 - i] = (number & (1 << i)) != 0;
        }
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

    public static String[] getTimeArr(Message message) {
        if (message.getDescription() == null) {
            return message.getResult().getData().stream().map(Data::getTime).map(Time::toString).map(s -> s.replaceAll("T", " ")).map(s -> s.replaceAll("\\.000Z", "")).toArray(String[]::new);
        } else {
            System.out.println(message.getDescription());
            return null;
        }
    }

    public static Set<Integer> getErrors(int errPack1, int errPack2, int errPack3) {
        Set<Integer> result = new TreeSet<>();
        result.addAll(getErrorsFromNumber(errPack1, 1));
        result.addAll(getErrorsFromNumber(errPack2, 2));
        result.addAll(getErrorsFromNumber(errPack3, 3));
        return result;
    }

    public static Set<Integer> getErrorsFromNumber(int n, int errPackNum) {
        Set<Integer> result = new HashSet<>();
        boolean[] bits = new boolean[32];
        for (int i = 31; i >= 0; i--) {
            bits[i] = (n & (1 << i)) != 0;
        }
        for (int i = 0; i < bits.length; i++) {
            if (bits[i]) {
                if (errPackNum == 1) result.add(i + 1);
                else if (errPackNum == 2) result.add(i + 33);
                else if (errPackNum == 3) result.add(i + 65);
                else System.out.println("getErrorsFromNumber error");
            }
        }
        return result;
    }

    public static String errorsToString(Set<Integer> errors) {
        StringBuilder sb = new StringBuilder("");
        for (Integer i : errors) {
            sb.append(i).append(":");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        } else {
            sb.append("no errors");
        }
        return sb.toString();
    }
}
