package ru.dst.analyze.locarus;

import ru.dst.analyze.locarus.response.Message;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class DataAnalyzer {

    public static Set<Integer> analyzeErrors(int[] data, int num) {
        Set<Integer> errFromOneAnalogIn = new HashSet<>();
        for (int n : data) {
            boolean[] bits = new boolean[32];
            for (int i = 31; i >= 0; i--) {
                bits[i] = (n & (1 << i)) != 0;
            }
            for (int i = 0; i < bits.length; i++) {
                if (bits[i]) {
                    if (num == 0) errFromOneAnalogIn.add(i + 1);
                    else if (num == 1) errFromOneAnalogIn.add(i + 33);
                    else if (num == 2) errFromOneAnalogIn.add(i + 65);
                    else System.out.println("analyzeErrors - wrong argument");
                }
            }
        }
        return errFromOneAnalogIn;
    }

    public static Set<Integer> getAllErrors(Message message) {
        Set<Integer> result = new TreeSet<>();
        if (message != null) {
            int[] arr = DataParser.getIntegerArray(message, 11);
            int[] arr2 = DataParser.getIntegerArray(message, 12);
            int[] arr3 = DataParser.getIntegerArray(message, 7);
            if (arr != null && arr2 != null && arr3 != null) {
//                System.out.println("Array length is " + arr.length);
                result.addAll(analyzeErrors(arr, 0));
                result.addAll(analyzeErrors(arr2, 1));
                result.addAll(analyzeErrors(arr3, 2));
            }
        }
        return result;
    }
}
