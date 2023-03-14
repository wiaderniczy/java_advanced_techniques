package pwr.edu.App;

import pwr.edu.Utility.CsvHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AppWindow extends JFrame{
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final String TITLE = "CSV reader";

    private final int SCREEN_WIDTH = SCREEN.width;
    private final int SCREEN_HEIGHT = SCREEN.height;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    private JList catalogueList;
    private JPanel mainPanel;
    private JLabel status;
    private JTextField pathField;
    private JButton button1;
    private JButton backButton;
    private JButton nextButton;
    private JTextArea fileText;
    ArrayList<FilePath> paths = new ArrayList<FilePath>();
    FilePath currentFile;

    public AppWindow(){
        DefaultListModel<FilePath> listModel = new DefaultListModel<>();

        catalogueList.setModel(listModel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setTitle(TITLE);
        setBounds((SCREEN_WIDTH - WINDOW_WIDTH)/2, (SCREEN_HEIGHT - WINDOW_HEIGHT)/2 , WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CsvHandler.openFile(pathField.getText());
                paths = CsvHandler.getPaths();
                populateList(listModel);

            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (catalogueList.getSelectedIndex() > -1){
                    int index = catalogueList.getSelectedIndex();
                    FilePath file = (FilePath)catalogueList.getModel().getElementAt(index);
                    if (CsvHandler.isDir(file.getPath())) {
                        CsvHandler.openFile(file.getPath().toString());
                        paths = CsvHandler.getPaths();
                        populateList(listModel);
                    }
                    else {
                        fileText.setText(null);
                        file.setData(CsvHandler.readFile(file.getPath()));
                        currentFile = file;
                        for (int i = 0; i < currentFile.data.length; i ++){
                            fileText.append(currentFile.data[i] + "\n");
                        }

                    }
                }
            }
        });
    }

    private void populateList(DefaultListModel<FilePath> listModel){
        listModel.clear();
        for (FilePath path: paths) {
            listModel.addElement(path);
        }
    }

    public static void main(String[] args) {
        new AppWindow();
    }
}
