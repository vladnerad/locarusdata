package ru.dst.analyze.locarus;

import java.util.HashSet;
import java.util.Set;

public class DataAnalyzer {

    public static void analyzeErrors(int[] data, int num) {
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
