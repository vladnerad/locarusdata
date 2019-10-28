package ru.dst.analyze.locarus.handlers;

import ru.dst.analyze.locarus.DataParser;

public class DataHandler {

    private DataHandler(){}

    public static int[] convertData(int[] rawData){
        //raw data
        //0-LJoy; 1-Press L/R/B; 2-RJoy; 3-PressAtt/Fdr; 4-tEnv/tTurbo; 5-errP3
        int[] result = new int[22];
        for (int i = 0; i < rawData.length; i++){
            if (i == 0){
                result[0] = JoyMoveHandler.getForwRev(rawData[i]);
                result[1] = JoyMoveHandler.getLeftRight(rawData[i]);
                continue;
            }
            if (i == 1){
                result[2] = DataParser.getNumberFromByte(rawData[i], LeftPressHandler.getInstance());
                result[3] = DataParser.getNumberFromByte(rawData[i], RightPressHandler.getInstance());
                result[4] = DataParser.getNumberFromByte(rawData[i], BrakePressHandler.getInstance());
                continue;
            }
            if (i == 2){
                result[5] = rawData[i];
                continue;
            }
            if (i == 3){
                result[6] = JoyAttachHandler.getForwRev(rawData[i]);
                result[7] = JoyAttachHandler.getLeftRight(rawData[i]);
                continue;
            }
            if (i == 4){
                result[8] = DataParser.getNumberFromByte(rawData[i], AttPressHandler.getInstance());
                result[9] = DataParser.getNumberFromByte(rawData[i], FanDrivePressHandler.getInstance());
                continue;
            }
            if (i == 5){
                result[10] = DataParser.getNumberFromByte(rawData[i], TempEnvHandler.getInstance());
                result[11] = DataParser.getNumberFromByte(rawData[i], TempTurboHandler.getInstance());
                continue;
            }
            if (i == 6){
                result[12] = rawData[i];
                continue;
            }
            if (i == 7){
                result[13] = DataParser.getNumberFromByte(rawData[i], TempHydOilHandler.getInstance());
                continue;
            }
            if (i == 8){
                result[14] = DataParser.getNumberFromByte(rawData[i], HMSpeedHandler.getInstance());
                continue;
            }
            if (i == 9){
                result[15] = DataParser.getNumberFromByte(rawData[i], HMSpeedHandler.getInstance());
                continue;
            }
            if (i == 10){
                result[16] = rawData[i];
                continue;
            }
            if (i == 11){
                result[17] = rawData[i];
                continue;
            }
            if (i == 12){
                result[18] = DataParser.getNumberFromByte(rawData[i], EngineSpeedHanler.getInstance());
                continue;
            }
            if (i == 13){
                result[19] = DataParser.getNumberFromByte(rawData[i], TempHydOilHandler.getInstance());
                continue;
            }
            if (i == 14){
                result[20] = DataParser.getNumberFromByte(rawData[i], EngineOilPressHandler.getInstance());
            }
            if (i == 15){
                //it is possible to show tenths, but we need double
                result[21] = DataParser.getNumberFromByte(rawData[i], MotoHoursHandler.getInstance());
            }
        }
        return result;
    }
}
