import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AppWindow extends JFrame {
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final String TITLE = "Change detector";

    private final int SCREEN_WIDTH = SCREEN.width;
    private final int SCREEN_HEIGHT = SCREEN.height;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 300;

    private JPanel mainPanel;
    private JPanel panel1;
    private JButton button1;
    private JTextField pathBox;
    private JLabel statusLabel;

    private MD5Calculator md5;

    public AppWindow() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Snapshot snapshot = new Snapshot();
                char status = snapshot.compareSnapshots(pathBox.getText());
                switch (status){
                    case 0:
                        statusLabel.setText("NEW FILE");
                        break;
                    case 1:
                        statusLabel.setText("CHANGED");
                        break;
                    case 2:
                        statusLabel.setText("UNCHANGED");
                        break;
                }
            }
        });
    }

    public void createWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setBounds((SCREEN_WIDTH - WINDOW_WIDTH)/2, (SCREEN_HEIGHT - WINDOW_HEIGHT)/2 , WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    }
}