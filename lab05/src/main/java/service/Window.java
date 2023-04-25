package service;

import ex.api.AnalysisException;
import ex.api.DataSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Window extends JFrame {
    private JTable dataTable;
    private JButton kappaButton;
    private JButton recallButton;
    private JButton precisionButton;
    private JButton f1Button;
    private JPanel mainPanel;
    private JLabel algorithmLabel;
    private JLabel scoreLabel;
    private JButton loadButton;
    private JScrollPane scroll;
    private DefaultTableModel tableModel;

    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final int SCREEN_WIDTH = SCREEN.width;
    private final int SCREEN_HEIGHT = SCREEN.height;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    private DataSet ds;
    private static String path;
    private static Service service;

    public Window(){
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("App");
        this.setBounds((SCREEN_WIDTH - WINDOW_WIDTH)/2, (SCREEN_HEIGHT - WINDOW_HEIGHT)/2 , WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

        kappaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitTask("Kappa");
            }
        });
        precisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitTask("Precision");
            }
        });
        recallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitTask("Recall");
            }
        });
        f1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitTask("F1Score");
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooserActionPerformed(e);
            }
        });
    }

    private void submitTask(String algorithm){
        String [] temp = {algorithm};
        try {
            service.setOptions(temp);
            service.submit(ds);
        } catch (AnalysisException ex) {
            ex.printStackTrace();
        }
        algorithmLabel.setText(service.getName());
        scoreLabel.setText(Float.toString(service.getScore()));
    }

    private void fileChooserActionPerformed(ActionEvent e){
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("F:\\STUDIA\\PROGRAMOWANIE\\INTELIJ\\mactom101_javata_2023\\lab05\\data"));
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = chooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            loadCSV();
            populateTable();
        }
    }

    private void loadCSV(){
        this.service = new Service(path);
        try {
            this.ds = service.retrieve(false);
        } catch (AnalysisException e) {
            e.printStackTrace();
        }
    }

    private void populateTable(){
        dataTable.setModel(new DefaultTableModel(
                ds.getData(),
                ds.getHeader()
        ));
        tableModel = (DefaultTableModel) dataTable.getModel();
        dataTable.getTableHeader().setReorderingAllowed(false);
        tableModel.removeRow(tableModel.getRowCount() - 1);
    }

    public static void main(String[] args) {
        Window window = new Window();

    }
}
