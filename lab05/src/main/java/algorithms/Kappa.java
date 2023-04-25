package algorithms;

public class Kappa {
    /*
    Kappa Cohen'a:
    observed_accuracy = (TP + TN) / (TP + TN + FP + FN)
    chance_accuracy = ((TP + FP) * (TP + FN) + (FP + TN) * (FN + TN)) / (TP + TN + FP + FN)^2
    kappa = (observed_accuracy - chance_accuracy) / (1 - chance_accuracy)
    */

    public static float getvalue(String[][] data){
        float kappa = 0;
        float observed = 0;
        float chance = 0;
        int tp = 0;
        int tn = 0;
        int fp = 0;
        int fn = 0;
        int row;
        for (int counter = 0; counter < data.length - 1; counter++) {
            row = counter;
            tp += Integer.valueOf(data[row][row]);
            for (int i = 0; i < data.length - 1; i++) {
                if (i == row) continue;
                fn += Integer.valueOf(data[row][i]);
                fp += Integer.valueOf(data[i][row]);
                for (int j = 0; j < data.length - 1; j++){
                    if (j == row) continue;
                    tn += Integer.valueOf(data[i][j]);
                }
            }
            observed = (float)(tp + tn)/(tp + tn + fp + fn);
            float ch1 = (float)Math.pow( (float)(tp + fp)/(tp + tn + fp + fn), 2);
            float ch2 = (float)Math.pow( (float)(tn + fn)/(tp + tn + fp + fn), 2);
            chance = ch1 + ch2;
            kappa += (observed - chance)/(1 - chance);
            tp = 0;
            tn = 0;
            fp = 0;
            fn = 0;
        }

        return kappa/ (data.length - 1);
    }
}
