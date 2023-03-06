import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.*;

public class MD5Calculator {

    public static String calculateHash(String filePath) throws NoSuchAlgorithmException, IOException{

        byte[] data = new byte[0];
        MessageDigest md5 = null;

        data = Files.readAllBytes(Paths.get(filePath));
        md5 = MessageDigest.getInstance("MD5");
        byte[] hash = md5.digest(data);
        String checksum = new BigInteger(1, hash).toString(16);

        return checksum;
    }
}
