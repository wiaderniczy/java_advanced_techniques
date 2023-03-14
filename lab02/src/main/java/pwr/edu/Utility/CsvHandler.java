package pwr.edu.Utility;

import pwr.edu.App.FilePath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvHandler {
    private static ArrayList<FilePath> filePaths = new ArrayList<FilePath>();

    public static ArrayList<FilePath> openFile(String filePath){
        filePaths.clear();
        Path path = Paths.get(filePath);
        ArrayList<FilePath> paths = new ArrayList<FilePath>();
        String data[] = null;


        try {
            Stream<Path> subPath = Files.walk(path,1);
            List<Path> temp = subPath.collect(Collectors.toList());
            int i = 0;
            for (Path tempPath:temp){
                filePaths.add(new FilePath(tempPath.getFileName().toString(), tempPath, data));
            }
            filePaths.remove(0);
        } catch (IOException e) {}

        return paths;
    }

    public static String[] readFile(Path path){
        List<String> lines = null;
        String [] data = new String [5];
        try {
            lines = Files.readAllLines(path);
            data = new String[lines.size()];

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < data.length;i++){
            data[i] = lines.get(i);
        }
        return data;
    }

    public static ArrayList<FilePath> getPaths(){
        return filePaths;
    }

    public static boolean isDir(Path path){
        return Files.isDirectory(path);
    }
}
