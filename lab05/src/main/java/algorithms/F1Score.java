package algorithms;

public class F1Score {
    public static float getvalue(float p, float r){
        return 2*(p*r/(p+r));
    }
}
