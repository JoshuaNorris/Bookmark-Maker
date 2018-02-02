package GUI;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class GUIController {

  @FXML
  private Button gotoUrl;
  
  @FXML
  private TextField url;
  
  @FXML
  private WebView articleViewer = new WebView();
  
  @FXML
  public void initialize() throws IOException {
	  url.setEditable(true);
  }
  
  @SuppressWarnings("unused")
  @FXML
  private void loadArticle() throws IOException {
	  WebEngine webEngine = articleViewer.getEngine();
	  
	  Connection urlConnection = Jsoup.connect(url.getText());
	  Document urlDocument = urlConnection.get();
	  String urlhtml = urlDocument.html();
	  
      webEngine.loadContent(urlhtml);
      System.out.println(urlhtml);
  }
  

}