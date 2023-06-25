package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class AutomatGUI extends JFrame {
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final String TITLE = "Cryptocurrency Miner";
    private final int SCREEN_WIDTH = SCREEN.width;
    private final int SCREEN_HEIGHT = SCREEN.height;
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 500;

    private Automat automaton;
    private int[][] generation;
    private JButton loadButton;

    public AutomatGUI(String scriptName) {
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds((SCREEN_WIDTH - WINDOW_WIDTH)/2 + 1920, (SCREEN_HEIGHT - WINDOW_HEIGHT)/2 , WINDOW_WIDTH, WINDOW_HEIGHT);

        automaton = new Automat(scriptName);
        generation = new int[31][31];
        generation[15][15] = 1;

        JPanel panel = new JPanel(new GridLayout(0, 1)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < generation.length - 1; i++) {
                    for (int j = 0; j < generation[0].length - 1; j++) {
                        if (generation[i][j] == 1) {
                            g.fillRect(i * 15 + 25, j*15- 14, 15, 15);
                        }
                    }
                }
            }
        };
        add(panel);

        new Timer(1000, e -> {
            generation = (int[][]) automaton.callFunction("nextGeneration", new Object[]{generation});
            panel.repaint();
        }).start();

        loadButton = new JButton("Load Script");
        loadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JavaScript Files", "js");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String scriptPath = fileChooser.getSelectedFile().getPath();
                automaton.loadScript(scriptPath);
            }
        });

        add(loadButton, BorderLayout.PAGE_END);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AutomatGUI("src/main/java/org/example/Automat1.js");
    }
}