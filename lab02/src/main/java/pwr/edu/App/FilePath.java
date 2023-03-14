package pwr.edu.App;

import java.nio.file.Path;

public class FilePath {
    public String name;
    public Path path;
    public String [] data;

    public FilePath(String name, Path path, String [] data){
        this.name = name;
        this.path = path;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public Path getPath() {
        return path;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return name;
    }
}
