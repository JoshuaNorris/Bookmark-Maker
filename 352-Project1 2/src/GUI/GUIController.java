package GUI;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;

public class GUIController {

	public GameListContainer gameListContainer = new GameListContainer();

	@FXML
	public BorderPane wholeGUI;

	@FXML
	public Button addGame;

	@FXML
	public TextField url;

	@FXML
	public WebView articleViewer = new WebView();

	@FXML
	public SplitPane gameList = new SplitPane();

	@FXML
	public TabPane tabs = new TabPane();

	@FXML
	public Tab gamePageTab = new Tab();

	@FXML
	public VBox gamePage = new VBox();

	@FXML
	public Tab articleViewerTab = new Tab();

	@FXML
	public void addGame() {
		createGameView();
		int amountofgames = gameList.getItems().size();
		setDividers(amountofgames);
	}

	public HBox createGameSideBar(String title, String rating) {
		HBox gameView = new HBox();

		Label gameTitle = new Label("Game: " + title + "   \nRating: " + rating);

		Button goToGamePage = getGoToGamePageButton(title);

		gameView.getChildren().addAll(gameTitle, goToGamePage);
		return gameView;
	}

	public Button getGoToGamePageButton(String title) {
		Button goToGamePage = new Button();
		goToGamePage.setPrefWidth(70);
		goToGamePage.setText("GO!");
		goToGamePage.setOnAction((event) -> {
			try {
				getGamePage(title);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		return goToGamePage;
	}

	public void getGamePage(String title) throws IOException {
		selectTab(gamePageTab);
		GameContainer game = getGamePageGame(title);
		HBox heading = getGamePageHBox(game);
		ArrayList<VBox> articlesVBox = getArticlesVBox(game);
		addToGamePage(heading, articlesVBox);
	}

	public void addToGamePage(HBox heading, ArrayList<VBox> articlesVBox) {
		gamePage.getChildren().clear();
		gamePage.getChildren().add(heading);
		gamePage.getChildren().addAll(articlesVBox);
	}

	public ArrayList<VBox> getArticlesVBox(GameContainer game) throws IOException {
		ArrayList<ArticleContainer> articles = game.getArticles();
		ArrayList<VBox> articlesVBox = new ArrayList<VBox>();
		for (int x = 0; x < articles.size(); x++) {
			VBox thisarticle = getArticleVBox(articles.get(x), x);
			articlesVBox.add(thisarticle);
		}
		return articlesVBox;
	}

	public HBox getGamePageHBox(GameContainer game) {
		HBox heading = new HBox();
		Label label = new Label(game.getTitle() + "\nRating: " + game.getRating());
		Button addArticle = new Button();
		addArticle.setText("+");

		final GameContainer gameFinal = game;
		addArticle.setOnAction((event) -> {
			getNewArticle(gameFinal);
		});

		heading.getChildren().addAll(label, addArticle);
		return heading;
	}

	public GameContainer getGamePageGame(String title) {
		GameContainer game = gameListContainer.get(title);
		return game;
	}

	public void selectTab(Tab tab) {
		SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
		selectionModel.select(tab);
	}

	public void getNewArticle(GameContainer game) {
		Stage popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		VBox layout = new VBox();

		TextField enterTitle = new TextField();
		HBox titleContainer = getTitleContainer(enterTitle);

		TextField enterRating = new TextField();
		HBox ratingContainer = getRatingContainer(enterRating);

		TextField enterURL = new TextField();
		HBox urlContainer = getArticleURLContainer(enterURL);

		HBox popupButtonContainer = getArticlePopUpButtonContainer(game, enterTitle, enterRating, enterURL, popup);

		layout.getChildren().addAll(titleContainer, ratingContainer, urlContainer, popupButtonContainer);
		Scene popupscene = new Scene(layout, 300, 250);
		popup.setScene(popupscene);
		popup.show();
	}

	public HBox getArticlePopUpButtonContainer(GameContainer game, TextField enterTitle, TextField enterRating,
			TextField enterURL, Stage popup) {
		HBox popupButtonContainer = new HBox();
		Button closePopUp = new Button();
		closePopUp.setText("OK!");
		popupButtonContainer.getChildren().add(closePopUp);
		closePopUp.setOnAction((event) -> {
			game.addArticle(enterTitle.getText(), enterRating.getText(), enterURL.getText());
			try {
				getGamePage(game.getTitle());
			} catch (IOException e) {
				e.printStackTrace();
			}
			popup.close();
		});
		return popupButtonContainer;
	}

	public HBox getArticleURLContainer(TextField enterURL) {
		HBox urlContainer = new HBox();
		Label urlLabel = new Label("URL: ");
		enterURL.setPromptText("Enter the URL");
		enterURL.setEditable(true);
		urlContainer.getChildren().addAll(urlLabel, enterURL);
		return urlContainer;
	}

	public HBox getRatingContainer(TextField enterRating) {
		HBox ratingContainer = new HBox();
		Label ratingLabel = new Label("Rating: ");
		enterRating.setPromptText("Enter rating out of 10");
		enterRating.setEditable(true);
		ratingContainer.getChildren().addAll(ratingLabel, enterRating);
		return ratingContainer;
	}

	public HBox getTitleContainer(TextField enterTitle) {
		HBox titleContainer = new HBox();
		Label titleLabel = new Label("Title: ");
		enterTitle.setPromptText("Enter article title here.");
		enterTitle.setEditable(true);
		titleContainer.getChildren().addAll(titleLabel, enterTitle);
		return titleContainer;
	}

	public VBox getArticleVBox(ArticleContainer articleContainer, int num) throws IOException {
		VBox article = new VBox();
		if (num == 1) {
			Label articleLabel = new Label((num + 1) + ". " + articleContainer.getTitle()
					+ "\nRating: " + articleContainer.getRating());
		} else {
			Label articleLabel = new Label(
					(num + 1) + ". " + articleContainer.getTitle() + "\nRating: " + articleContainer.getRating());
		}

		Button goToArticle = new Button();
		goToArticle.setText("GO!");
		goToArticle.setOnAction((event) -> {
			loadArticle(articleContainer.getURL());
		});

		article.getChildren().addAll(articleLabel, goToArticle);
		return article;
	}

	public void createGameView() {
		GameContainer game = new GameContainer("Title", "1");
		gameListContainer.add(game);

		Stage popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		VBox layout = new VBox();

		TextField enterTitle = new TextField();
		HBox titleContainer = getTitleContainer(enterTitle);

		TextField enterRating = new TextField();
		HBox ratingContainer = getRatingContainer(enterRating);

		HBox popupButtonContainer = getGameViewPopUpButtonContainer(enterTitle, enterRating, popup);

		layout.getChildren().addAll(titleContainer, ratingContainer, popupButtonContainer);
		Scene popupscene = new Scene(layout, 300, 250);
		popup.setScene(popupscene);
		popup.show();
	}

	public HBox getGameViewPopUpButtonContainer(TextField enterTitle, TextField enterRating, Stage popup) {
		HBox popupButtonContainer = new HBox();
		Button closePopUp = new Button();
		closePopUp.setText("OK!");
		popupButtonContainer.getChildren().add(closePopUp);
		closePopUp.setOnAction((event) -> {
			gameListContainer.get(gameListContainer.size() - 1).setTitle(enterTitle.getText());
			gameListContainer.get(gameListContainer.size() - 1).setRating(enterRating.getText());
			HBox gameView = createGameSideBar(enterTitle.getText(), enterRating.getText());
			gameList.getItems().add(gameView);
			popup.close();
		});
		return popupButtonContainer;
	}

	public void setDividers(int amountofgames) {
		for (int x = 0; x <= amountofgames; x++) {
			gameList.setDividerPosition(x, ((x) + 0.0) / ((amountofgames - 1) + 0.0));
		}

	}

	public void loadArticle(String url) {
		SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
		selectionModel.select(articleViewerTab);

		WebEngine webEngine = articleViewer.getEngine();

		Connection urlConnection = Jsoup.connect(url);

		try {
			Document urlDocument = urlConnection.get();
			String urlhtml = urlDocument.html();
			webEngine.loadContent(urlhtml);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}