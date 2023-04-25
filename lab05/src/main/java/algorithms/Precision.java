package algorithms;

public class Precision {
    public static float getvalue(String[][] data){
        float value = 0;
        int tp = 0;
        int fp = 0;
        for (int i = 0; i < data.length - 1; i++){
            tp += Integer.valueOf(data[i][i]);
            for (int j = 0; j < data.length - 1; j++) {
                if (j == i) continue;
                fp += Integer.valueOf(data[j][i]);
            }
            value += (float)tp/(tp+fp);
            tp = 0;
            fp = 0;
        }
        return value/ (data.length - 1);
    }
}
