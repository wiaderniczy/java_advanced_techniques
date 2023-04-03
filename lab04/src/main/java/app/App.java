package app;

import classloader.ClassHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App extends JFrame{
    private JPanel mainPanel;
    private JTextField classField;
    private JButton loadButton;
    private JTextField taskField;
    private JLabel label1;
    private JButton executeButton;
    private JList classList;

    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final String TITLE = "Instruction executor with custom class loader";

    private final int SCREEN_WIDTH = SCREEN.width;
    private final int SCREEN_HEIGHT = SCREEN.height;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    ArrayList<Object> list = new ArrayList<Object>();

    public App(){
        DefaultListModel<Object> model = new DefaultListModel<>();
        classList.setModel(model);

        setTitle(TITLE);
        setBounds((SCREEN_WIDTH - WINDOW_WIDTH)/2, (SCREEN_HEIGHT - WINDOW_HEIGHT)/2 , WINDOW_WIDTH, WINDOW_HEIGHT);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ClassHandler.load(classField.getText(), taskField.getText());
               list = ClassHandler.getList();
               populateList(model);
            }
        });

        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Class<?> tempC = list.get(classList.getSelectedIndex()).getClass();
                Object tempO = list.get(classList.getSelectedIndex());
                ClassHandler.execute( tempC, tempO, taskField.getText());
            }
        });
    }

    private void populateList(DefaultListModel<Object> listModel){
        listModel.clear();
        for (Object entry : list) {
            listModel.addElement(entry);
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
