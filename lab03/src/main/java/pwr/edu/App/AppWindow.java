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

public class AppWindow extends JFrame {
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final String TITLE = "Music Quiz";

    private static final String MUSICBRAINZ_API_URL = "https://musicbrainz.org/ws/2/";
    private static final String MUSICBRAINZ_USER_AGENT = "Quiz App";

    private final int SCREEN_WIDTH = SCREEN.width;
    private final int SCREEN_HEIGHT = SCREEN.height;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private JPanel mainPanel;
    private JButton ans3;
    private JButton ans4;
    private JButton ans2;
    private JButton ans1;
    private JComboBox comboBox1;


    public AppWindow(){
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setBounds((SCREEN_WIDTH - WINDOW_WIDTH)/2, (SCREEN_HEIGHT - WINDOW_HEIGHT)/2 , WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);


        ans1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchArtist();
            }
        });

        ans2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRelease();
            }
        });

        ans3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        ans4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void searchArtist(){
        String ufquery = "eminem";
        String query = ufquery.replace(" ", "%20");
        String url = String.format("http://musicbrainz.org/ws/2/artist/?query=%s&fmt=json", query);

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
            JsonNode artistsNode = rootNode.path("artists");
            for (JsonNode artistNode : artistsNode) {
                String name = artistNode.path("name").asText();
                String id = artistNode.path("id").asText();
                String country = artistNode.path("country").asText();
                String type = artistNode.path("type").asText();
                String score = artistNode.path("score").asText();
                String result = String.format("%s (%s) [%s] [%s] (%s)\n", name, id, type, country, score);
                System.out.println("xd");
                JOptionPane.showMessageDialog(null, result);
                break;
            }
        }
        catch (IOException e){

        }

    }

    private void searchRelease(){
        String ufquery = "the marshall mathers AND artist:eminem";
        String query = ufquery.replace(" ", "%20");
        String url = String.format("http://musicbrainz.org/ws/2/release/?query=%s&fmt=json", query);

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
                String title = artistNode.path("title").asText();
                String id = artistNode.path("id").asText();
                JsonNode artistArray = artistNode.path("artist-credit").get(0);
                String name = artistArray.path("name").asText();
                String date = artistNode.path("date").asText();
                String tracks = artistNode.path("track-count").asText();

                String result = String.format("%s (%s) [%s] [%s] (%s)\n", title, id, name, date, tracks);

                JOptionPane.showMessageDialog(null, result);
                break;
            }
        }
        catch (IOException e){

        }

    }

    public static void main(String[] args) {
        AppWindow app = new AppWindow();
    }
}
