package pwr.edu.App;

import pwr.edu.Utility.CsvHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.ref.WeakReference;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

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
    Map<String, FilePath> paths = new WeakHashMap<String, FilePath>();

    FilePath currentFile;
    Path currentDir;

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

                    //set label to status
                    if(paths.containsValue(file)) status.setText("ALREADY REFERENCED");
                    else status.setText("RELOADED FROM DISK");

                    if (CsvHandler.isDir(file.getPath())) {
                        currentDir = file.getPath();
                        CsvHandler.openFile(file.getPath().toString());
                        if (!paths.containsValue(file)) paths = CsvHandler.getPaths();
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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDir = currentDir.getParent();
                CsvHandler.openFile(currentDir.toString());
                populateList(listModel);
            }
        });
    }

    private void populateList(DefaultListModel<FilePath> listModel){
        listModel.clear();
        for (Map.Entry<String, FilePath> entry : paths.entrySet()) {
            listModel.addElement(entry.getValue());
        }
    }

    public static void main(String[] args) {
        new AppWindow();
    }
}
