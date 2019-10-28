package ru.dst.analyze.locarus.handlers;

public class JoyAttachHandler {

    public static int getForwRev(int n){
        boolean[] bits = new boolean[32];
        boolean isBackward = false;
        boolean isNeutral = false;
        for (int i = 31; i >= 0; i--) {
            bits[i] = (n & (1 << i)) != 0;
        }
        if (validate(bits)) {
            //Defining the direction FNR
            if (bits[20] && !bits[18]) isBackward = true;
            else isNeutral = true;
            //Defining percentage of tilt
            int sideTilt = bits[22] ? 1 : 0;
            for (int z = 1; z < 10; z++) {
                int o = (bits[22 + z] ? 1 : 0) << z;
                sideTilt = sideTilt | o;
            }

            if (isNeutral) return 0;
            else if (isBackward) return sideTilt - (sideTilt * 2);
            else return sideTilt;
        }
        else return 111;
    }

    public static int getLeftRight(int n){
        boolean[] bits = new boolean[32];
        boolean isRight = false;
        boolean isNeutral = false;
        for (int i = 31; i >= 0; i--) {
            bits[i] = (n & (1 << i)) != 0;
        }
        if (validate(bits)) {
            //Defining the direction left/right
            if (bits[4] && !bits[2]) isRight = true;
            else isNeutral = true;
            //Defining percentage of tilt
            int driveTilt = bits[6] ? 1 : 0;
            for (int z = 1; z < 10; z++) {
                int o = (bits[6 + z] ? 1 : 0) << z;
                driveTilt = driveTilt | o;
            }
            if (isNeutral) return 0;
            else if (isRight) return driveTilt - (driveTilt * 2);
            else return driveTilt;
        } else return 111;
    }

    private static boolean validate(boolean[] bits) {
        if (bits[0] || bits[1] || bits[3] || bits[5] || bits[16] || bits[17] || bits[19] || bits[21]) {
            System.out.println("Error #1");
            return false;
        }
        if (bits[2] && bits[4]) {
            System.out.println("Error #2");
            return false;
        }
        if (bits[18] && bits[20]) {
            System.out.println("Error #3");
            return false;
        }
        return true;
    }
}
