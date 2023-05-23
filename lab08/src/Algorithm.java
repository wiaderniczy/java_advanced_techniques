import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    static {
        System.loadLibrary("native");
    }

    private static int[] core;
    private static int[] processing;

    private static void readCsv(int which, String path){;
        String line;

        List<Integer> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            while ((line = br.readLine()) != null){
                String[] values = line.split(";");
                for (String value:values){
                    list.add(Integer.parseInt(value));
                }
            }
        } catch(IOException e){}

        if (which == 1) {
            core = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                core[i] = list.get(i);
            }
        } else{
            processing = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                processing[i] = list.get(i);
            }
        }
    }

    private void solve(int[] core, int[] processed){
        System.out.println("xd");
    }

    private native void solveNative(int[] core, int[] processed);

    public static void main(String[] args) {
        readCsv(1, "D:/data/core.csv");
        readCsv(15, "D:/data/proc.csv");
        new Algorithm().solveNative(core, processing);
    }}