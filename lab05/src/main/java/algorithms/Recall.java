package algorithms;

public class Recall {
    public static float getvalue(String[][] data){
        float value = 0;
        int tp = 0;
        int fn = 0;
        for (int i = 0; i < data.length - 1; i++){
            tp += Integer.valueOf(data[i][i]);
            for (int j = 0; j < data.length - 1; j++) {
                if (j == i) continue;
                fn += Integer.valueOf(data[i][j]);
            }
            value += (float)tp/(tp+fn);
            tp = 0;
            fn = 0;
        }
        return value/ (data.length - 1);
    }
}
