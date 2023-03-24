package pwr.edu.App;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class AppWindow extends JFrame {
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final String TITLE = "Music Quiz";

    private static final String MUSICBRAINZ_API_URL = "https://musicbrainz.org/ws/2/";
    private static final String MUSICBRAINZ_USER_AGENT = "Quiz App";

    private static String date;
    private static int year;
    private static String name;
    private static String title;
    private static int proper = 0;
    private static QuestionTemplates type;
    private static ArrayList<Integer> used = new ArrayList<Integer>();

    private final int SCREEN_WIDTH = SCREEN.width;
    private final int SCREEN_HEIGHT = SCREEN.height;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private JPanel mainPanel;
    private JButton ans3;
    private JButton ans4;
    private JButton ans2;
    private JButton ans1;
    private JComboBox langCombo;
    private JLabel questionField;
    private ResourceBundle bundle;
    private static Random rand = new Random();


    public AppWindow(){
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setBounds((SCREEN_WIDTH - WINDOW_WIDTH)/2, (SCREEN_HEIGHT - WINDOW_HEIGHT)/2 , WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);

        ans1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                correctCheck(1);
               reset();
            }
        });

        ans2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                correctCheck(2);
                reset();
            }
        });

        ans3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                correctCheck(3);
                reset();
            }
        });

        ans4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                correctCheck(4);
                reset();
            }
        });

        langCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTranslations();
            }
        });
    }

    private String getRandomArtist(){
        int offset = rand.nextInt(100000);
        String url = String.format("http://musicbrainz.org/ws/2/release?query=date:[1950+TO+2023]&offset=%d&fmt=json", offset);

        String tempName = "";

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                JOptionPane.showMessageDialog(null, "Error!!!!!");
                return null;
            }

            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode artistsNode = rootNode.path("releases");
            for (JsonNode artistNode : artistsNode) {

                JsonNode artistArray = artistNode.path("artist-credit").get(0);
                tempName = artistArray.path("name").asText();
                break;
            }

        }
        catch (IOException e){

        }

        return tempName;
    }

    private void getRandomRelease(){
        int offset = rand.nextInt(100000);
        String url = String.format("http://musicbrainz.org/ws/2/release?query=date:[1950+TO+2023]&offset=%d&fmt=json", offset);


        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                JOptionPane.showMessageDialog(null, "Error!!!!!");
                return;
            }

            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode artistsNode = rootNode.path("releases");
            for (JsonNode artistNode : artistsNode) {
                title = artistNode.path("title").asText();

                JsonNode artistArray = artistNode.path("artist-credit").get(0);
                name = artistArray.path("name").asText();

                String fullDate = artistNode.path("date").asText();
                date = fullDate.substring(0,4);

                year = Integer.parseInt(date);
                break;
            }
        }
        catch (IOException e){

        }
    }

    private void loadCreatorQuestion(){
        int order = rand.nextInt(4);
        proper = order + 1;
        switch(order){
            case 0:
                ans1.setText(name);
                ans2.setText(getRandomArtist());
                ans3.setText(getRandomArtist());
                ans4.setText(getRandomArtist());
                break;
            case 1:
                ans1.setText(getRandomArtist());
                ans2.setText(name);
                ans3.setText(getRandomArtist());
                ans4.setText(getRandomArtist());
                break;
            case 2:
                ans1.setText(getRandomArtist());
                ans2.setText(getRandomArtist());
                ans3.setText(name);
                ans4.setText(getRandomArtist());
                break;
            case 3:
                ans1.setText(getRandomArtist());
                ans2.setText(getRandomArtist());
                ans3.setText(getRandomArtist());
                ans4.setText(name);
                break;
        }
    }

    private void loadDateQuestion(){
        int order = rand.nextInt(4);
        proper = order + 1;
        switch(order){
            case 0:
                ans1.setText(date);
                ans2.setText(getRandomYear());
                ans3.setText(getRandomYear());
                ans4.setText(getRandomYear());
                break;
            case 1:
                ans1.setText(getRandomYear());
                ans2.setText(date);
                ans3.setText(getRandomYear());
                ans4.setText(getRandomYear());
                break;
            case 2:
                ans1.setText(getRandomYear());
                ans2.setText(getRandomYear());
                ans3.setText(date);
                ans4.setText(getRandomYear());
                break;
            case 3:
                ans1.setText(getRandomYear());
                ans2.setText(getRandomYear());
                ans3.setText(getRandomYear());
                ans4.setText(date);
                break;
        }
    }

    private void loadQuestion (){
        int a = rand.nextInt(2);
        String question = "";

        if (a == 0) {
            type = QuestionTemplates.releaseCreator;
            question = bundle.getString("creatorQuestion") + title + bundle.getString("in") + date + "?";
            loadCreatorQuestion();
        }
        else{
            type = QuestionTemplates.releaseDate;
            question = bundle.getString("dateQuestion") + title + bundle.getString("by") + name + bundle.getString("released");
            loadDateQuestion();
        }
        questionField.setText(question);
    }

    private void correctCheck(int button){
        if (type == QuestionTemplates.releaseCreator){
            if (button == proper){
                JOptionPane.showMessageDialog(null, (bundle.getString("correctCreator") + title + bundle.getString("is") + name));
            } else {
                JOptionPane.showMessageDialog(null, (bundle.getString("falseCreator") + title + bundle.getString("is") + name));
            }
        }
        else {
            if (button == proper) {
                JOptionPane.showMessageDialog(null, (bundle.getString("correctDate") + title + bundle.getString("was") + date));
            }
            else {
                JOptionPane.showMessageDialog(null, (bundle.getString("falseDate") + title + bundle.getString("was") + date));
            }
        }
    }

    private String getRandomYear(){
        int newYear = Integer.MAX_VALUE;
        while (newYear > 2023 || newYear == year || used.contains(newYear)){
            newYear = rand.nextInt(20) + year - 10;
        }
        used.add(newYear);
        return Integer.toString(newYear);
    }

    private void reset(){
        used.clear();
        getRandomRelease();
        loadQuestion();
    }

    private void loadTranslations(){
        String lang = (String)langCombo.getSelectedItem();
        Locale locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("translation", locale);
        reset();
    }

    public static void main(String[] args) {
        AppWindow app = new AppWindow();
        app.loadTranslations();
        app.getRandomRelease();
        app.loadQuestion();
    }
}
