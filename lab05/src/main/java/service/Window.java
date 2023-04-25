package service;

import ex.api.AnalysisException;
import ex.api.DataSet;
import spi.ServiceProvider;

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
    private String path;
    private ServiceProvider service;

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
        service.providers(false).forEachRemaining(service -> {
            try {
                service.setOptions(temp);
                service.submit(ds);
            } catch (AnalysisException e) {
                e.printStackTrace();
            }
        });
        service.providers(false).forEachRemaining(service -> algorithmLabel.setText(service.getName()));
        service.providers(false).forEachRemaining(service -> scoreLabel.setText(Float.toString(service.getScore())));
    }

    private void fileChooserActionPerformed(ActionEvent e){
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("F:\\STUDIA\\PROGRAMOWANIE\\INTELIJ\\mactom101_javata_2023\\lab05\\data"));
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = chooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            loadService();
            loadCSV();
            populateTable();
        }
    }

    private void loadService(){
        service = new ServiceProvider();

    }

    private void loadCSV(){
        this.ds = new DataSet();

        String [][] matrix = Reader.readFile(path);
        String[] header = matrix[0];
        String[][] data = new String[matrix.length][matrix[1].length];

        for (int i = 1; i < matrix.length; i++){
            data[i-1] = matrix[i];
        }

        this.ds.setData(data);
        this.ds.setHeader(header);
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
