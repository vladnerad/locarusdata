package ru.dst.analyze.locarus.handlers;

import ru.dst.analyze.locarus.DataParser;

public class DataHandler {

    private DataHandler(){}

    public static int[] convertData(int[] rawData){
        //raw data
        //0-LJoy; 1-Press L/R/B; 2-RJoy; 3-PressAtt/Fdr; 4-tEnv/tTurbo; 5-errP3
        int[] result = new int[20];
        for (int i = 0; i < rawData.length; i++){
            if (i == 0){
                result[0] = rawData[i];
                continue;
            }
            if (i == 1){
                result[1] = DataParser.getNumberFromByte(rawData[i], LeftPressHandler.getInstance());
                result[2] = DataParser.getNumberFromByte(rawData[i], RightPressHandler.getInstance());
                result[3] = DataParser.getNumberFromByte(rawData[i], BrakePressHandler.getInstance());
                continue;
            }
            if (i == 2){
                result[4] = rawData[i];
                continue;
            }
            if (i == 3){
                result[5] = rawData[i];
                continue;
            }
            if (i == 4){
                result[6] = DataParser.getNumberFromByte(rawData[i], AttPressHandler.getInstance());
                result[7] = DataParser.getNumberFromByte(rawData[i], FanDrivePressHandler.getInstance());
                continue;
            }
            if (i == 5){
                result[8] = DataParser.getNumberFromByte(rawData[i], TempEnvHandler.getInstance());
                result[9] = DataParser.getNumberFromByte(rawData[i], TempTurboHandler.getInstance());
                continue;
            }
            if (i == 6){
                result[10] = rawData[i];
                continue;
            }
            if (i == 7){
                result[11] = DataParser.getNumberFromByte(rawData[i], TempHydOilHandler.getInstance());
                continue;
            }
            if (i == 8){
                result[12] = DataParser.getNumberFromByte(rawData[i], HMSpeedHandler.getInstance());
                continue;
            }
            if (i == 9){
                result[13] = DataParser.getNumberFromByte(rawData[i], HMSpeedHandler.getInstance());
                continue;
            }
            if (i == 10){
                result[14] = rawData[i];
                continue;
            }
            if (i == 11){
                result[15] = rawData[i];
                continue;
            }
            if (i == 12){
                result[16] = DataParser.getNumberFromByte(rawData[i], EngineSpeedHanler.getInstance());
                continue;
            }
            if (i == 13){
                result[17] = DataParser.getNumberFromByte(rawData[i], TempHydOilHandler.getInstance());
                continue;
            }
            if (i == 14){
                result[18] = DataParser.getNumberFromByte(rawData[i], EngineOilPressHandler.getInstance());
            }
            if (i == 15){
                //it is possible to show tenths, but we need double
                result[19] = DataParser.getNumberFromByte(rawData[i], MotoHoursHandler.getInstance());
            }
        }
        return result;
    }
}
