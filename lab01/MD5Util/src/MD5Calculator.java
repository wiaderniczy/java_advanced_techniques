import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.*;

public class MD5Calculator {

    public static String calculateHash(String filePath){

        byte[] data = new byte[0];
        MessageDigest md5 = null;

        try {
            data = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = md5.digest(data);
        String checksum = new BigInteger(1, hash).toString(16);

        return checksum;
    }
}
