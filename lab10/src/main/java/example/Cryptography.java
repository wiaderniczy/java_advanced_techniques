package example;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Cryptography {
    private SecretKey key;
    private Cipher cipher;

    public Cryptography(SecretKey key, String transformation){
        this.key = key;
        try {
            this.cipher = Cipher.getInstance(transformation);
        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchPaddingException e) {
        }
    }

    public void encrypt(String inputFile, String outputFile, IvParameterSpec iv) throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        cipher.init(Cipher.ENCRYPT_MODE, this.key, iv);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] buffer = new byte[64];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) {
                outputStream.write(output);
            }
        }
        byte[] outputBytes = cipher.doFinal();
        if (outputBytes != null) {
            outputStream.write(outputBytes);
        }
        inputStream.close();
        outputStream.close();
    }

    public void decrypt(String inputFile, String outputFile, IvParameterSpec iv) throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        cipher.init(Cipher.DECRYPT_MODE, this.key, iv);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] buffer = new byte[64];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] output = cipher.update(buffer, 0, bytesRead);
            if (output != null) {
                outputStream.write(output);
            }
        }
        byte[] outputBytes = cipher.doFinal();
        if (outputBytes != null) {
            outputStream.write(outputBytes);
        }
        inputStream.close();
        outputStream.close();
    }

    public byte[] generateIV(int length){
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[length];
        do {
            random.nextBytes(iv);
        } while (iv[0] == 0);
        return iv;
    }
}
