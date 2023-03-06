import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Snapshot {
    private static final String DIR = System.getProperty("user.home");

    public Snapshot() {
        Path snapshotDir = Paths.get(DIR + "/.snapshot");
        try {

            Files.createDirectories(snapshotDir);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public void createDirectory(String filePath) {
        String newPath = filePath.replace(":", "");
        Path path = Paths.get(DIR + "/.snapshot/" + newPath);
        try {

            Files.createDirectories(path);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public char compareSnapshots(String filePath) {
        //0 for NEW files, 1 for CHANGED files, 2 for UNCHANGED files
        String newPath = filePath.replace(":", "");
        String inputData = MD5Calculator.calculateHash(filePath);
        Path path = Paths.get(newPath);

        if (!Files.exists(path)){
            createDirectory(filePath);
            Path createdPath = Paths.get(DIR + "/.snapshot/" + newPath + ".md5");
            //System.out.println(createdPath.toString());
            try {
                Files.writeString(createdPath, inputData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }

        char status = 0;



        Stream<String> snapshotData = null;

        try {

            snapshotData = Files.lines(Paths.get(DIR + "/" + newPath + ".md5"));

        } catch (IOException e) {

            e.printStackTrace();

        }

        snapshotData.forEach(System.out::println);

        return status;
    }

}
