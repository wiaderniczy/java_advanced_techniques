package example;

import example.entries.Currency;
import example.entries.CurrencyList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AppWindow extends JFrame {
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final String TITLE = "XML parser";
    private final int SCREEN_WIDTH = SCREEN.width;
    private final int SCREEN_HEIGHT = SCREEN.height;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    private static CurrencyList list;

    private JPanel mainPanel;
    private JButton load;
    private JTextArea fileContents;
    private JButton loadJaxb;
    private JButton loadJaxp;
    private JButton transformButton;

    public AppWindow(){
        setTitle(TITLE);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds((SCREEN_WIDTH - WINDOW_WIDTH)/2, (SCREEN_HEIGHT - WINDOW_HEIGHT)/2 , WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        loadJaxb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jfc = new JFileChooser();
                jfc.setCurrentDirectory(new File("D:/"));
                String file = null;
                int result = jfc.showOpenDialog(mainPanel);
                if (result == JFileChooser.APPROVE_OPTION){
                    file = jfc.getSelectedFile().getAbsolutePath();
                }
                try {
                    list = unmarshallingJAXB(file);
                } catch (JAXBException ex) {
                } catch (IOException ex) {
                }
                fileContents.setText("Rates as of " + list.getItems().get(0).getPubDate() + "\n");
                for (Currency currency : list.getItems()){
                    fileContents.append("1 " + currency.getBaseCurrency() + " --> " + currency.getTargetCurrency() + ": " + currency.getExchangeRate() + "\n      "
                            + "1 " + currency.getTargetCurrency() + "-->" + currency.getBaseCurrency() + ": " + currency.getInverseRate() + "\n");
                }
            }
        });
        loadJaxp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setCurrentDirectory(new File("D:/"));
                String file = null;
                List<Currency> curList = null;
                int result = jfc.showOpenDialog(mainPanel);
                if (result == JFileChooser.APPROVE_OPTION){
                    file = jfc.getSelectedFile().getAbsolutePath();
                }
                try {
                    curList = parseJAXPDom(file);
                } catch (ParserConfigurationException ex) {
                } catch (IOException ex) {
                } catch (SAXException ex) {
                }
                fileContents.setText("Rates as of " + curList.get(0).getPubDate() + "\n");
                for (Currency currency : curList){
                    fileContents.append("1 " + currency.getBaseCurrency() + " --> " + currency.getTargetCurrency() + ": " + currency.getExchangeRate() + "\n      "
                            + "1 " + currency.getTargetCurrency() + "-->" + currency.getBaseCurrency() + ": " + currency.getInverseRate() + "\n");
                }
            }
        });
    }

    public static void main(String[] args) throws JAXBException, IOException {
        new AppWindow();
    }

    private static void transform(){

    }

    private static CurrencyList unmarshallingJAXB(String file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(CurrencyList.class);
        Unmarshaller um = context.createUnmarshaller();
        CurrencyList list = (CurrencyList) um.unmarshal(new FileReader(file));

        return list;
    }

    private static List<Currency> parseJAXPDom(String file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(file));
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("item");
        List<Currency> curlist = new ArrayList<Currency>();

        for (int i = 0; i < nodeList.getLength(); i++){
            Currency curr = new Currency();

            Element element = (Element) nodeList.item(i);

            curr.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
            curr.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
            curr.setPubDate(element.getElementsByTagName("pubDate").item(0).getTextContent());
            curr.setBaseCurrency(element.getElementsByTagName("baseCurrency").item(0).getTextContent());
            curr.setTargetCurrency(element.getElementsByTagName("targetCurrency").item(0).getTextContent());
            curr.setExchangeRate(Double.parseDouble(element.getElementsByTagName("exchangeRate").item(0).getTextContent()));
            curr.setInverseRate(Double.parseDouble(element.getElementsByTagName("inverseRate").item(0).getTextContent()));

            curlist.add(curr);
        }
        return curlist;
    }
}
