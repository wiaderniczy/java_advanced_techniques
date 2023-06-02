package example;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AppWindow extends JFrame {
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final String TITLE = "Cryptocurrency Miner";
    private final int SCREEN_WIDTH = SCREEN.width;
    private final int SCREEN_HEIGHT = SCREEN.height;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    private JPanel mainPanel;
    private JButton encryptButton;
    private JButton decryptButton;
    private JTextField inputField;
    private JTextField outputField;
    private JComboBox algorithmComboBox;
    private JTextField hashField;
    private JButton saveKeyButton;

    private SecretKey key;
    private final IvParameterSpec ivAES;
    private final IvParameterSpec ivDES;
    private IvParameterSpec iv;
    private String algorithm;

    public AppWindow(){
        setTitle(TITLE);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds((SCREEN_WIDTH - WINDOW_WIDTH)/2, (SCREEN_HEIGHT - WINDOW_HEIGHT)/2 , WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        byte[] hashA = {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1};
        byte[] hashD = {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1};
        ivAES = new IvParameterSpec(hashA);
        ivDES = new IvParameterSpec(hashD);

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cryptography crypto = new Cryptography(key, algorithm);
                try {
                    crypto.encrypt(inputField.getText(), outputField.getText(), iv);
                } catch (InvalidKeyException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalBlockSizeException ex) {
                    throw new RuntimeException(ex);
                } catch (BadPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidAlgorithmParameterException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cryptography crypto = new Cryptography(key, algorithm);
                try {
                    crypto.decrypt(inputField.getText(), outputField.getText(), iv);
                } catch (InvalidKeyException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalBlockSizeException ex) {
                    throw new RuntimeException(ex);
                } catch (BadPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidAlgorithmParameterException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        saveKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String combo = algorithmComboBox.getSelectedItem().toString();
                int length;
                if (combo.equals("DES")) {
                    algorithm = "DES/CBC/PKCS5Padding";
                    iv = ivDES;
                }
                else {
                    algorithm = "AES/CBC/PKCS5Padding";
                    iv = ivAES;
                }
                byte[] hash = hashField.getText().getBytes();
                key = new SecretKeySpec(hash, combo);
                Cryptography crypto = new Cryptography(key, algorithm);
            }
        });
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        new AppWindow();
    }
}
