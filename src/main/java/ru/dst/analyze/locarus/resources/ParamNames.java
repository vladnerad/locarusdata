package ru.dst.analyze.locarus.resources;

public class ParamNames {
    public static final String[] DIGITAL_IN_NAMES =
            {"FDrAuto", "SafeBtn", "TranspMode", "HydOilLowLev",
                    "RipMode", "SwimMode", "ParkSwt", "TowSwt"};

    public static String getNamesForCSV(String[] names) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < names.length; i++) {
            sb.append(names[i]);
            if (i != names.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
