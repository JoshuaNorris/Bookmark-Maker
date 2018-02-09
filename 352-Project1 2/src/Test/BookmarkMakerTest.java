package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import GUI.ArticleContainer;
import GUI.GameContainer;
import GUI.GameListContainer;

public class BookmarkMakerTest {

	@Test
	public void testGameListContainer() {
		GameListContainer gamelist = new GameListContainer();
		GameContainer game1 = new GameContainer("Pac-Man", "19");
		GameContainer game2 = new GameContainer("Galaga", "1");
		GameContainer game3 = new GameContainer("hello", "9");
		GameContainer game4 = new GameContainer("goodbye", "4");
		GameContainer game5 = new GameContainer("helloagain", "3");
		GameContainer game6 = new GameContainer("goodbyeagain", "10");
		gamelist.add(game1);
		gamelist.add(game2);
		gamelist.add(game3);
		assertTrue(gamelist.size() == 3);
		assertTrue(gamelist.get(1).equals(game2));
		assertTrue(gamelist.get("Pac-Man").equals(game1));
		gamelist.add(game4);
		gamelist.add(game5);
		gamelist.add(game6);
		assertTrue(gamelist.size() == 6);	
	}
	
	@Test
	public void testGameContainer() {
		GameContainer game1 = new GameContainer("Pac-Man", "19");
		GameContainer game2 = new GameContainer("Galaga", "1");
		assertTrue(game1.getRating() == 19);
		assertTrue(game2.getTitle().equals("Galaga"));
		game1.setRating("2");
		assertTrue(game1.getRating() == 2);
		game2.setTitle("Donkey Kong");
		assertTrue(game2.getTitle().equals("Donkey Kong"));
		ArticleContainer article1 = new ArticleContainer("I <3 Pac-Man", "7", "this is a url");
		ArticleContainer article2 = new ArticleContainer("I <3 Pac-Man", "7", "this is a url");
		ArticleContainer article3 = new ArticleContainer("I <3 Pac-Man", "7", "this is a url");
		game1.addArticle(article1);
		game1.addArticle(article2);
		game1.addArticle(article3);
		assertTrue(game1.getArticles().size() == 3);
		game1.addArticle("galaga sux", "12", "this is another url");
		assertTrue(game1.getArticles().size() == 3);
	}

	@Test
	public void testArticleContainer() {
		ArticleContainer article1 = new ArticleContainer("I <3 Pac-Man", "7", "this is a url");
		assertTrue(article1.getRating() == 7);
		assertTrue(article1.getTitle().equals("I <3 Pac-Man"));
		assertTrue(article1.getTitle().equals("this is a url"));
		article1.setRating("5");
		article1.setTitle("Pac-Man suxks");
		article1.setURL("yet another url");
		assertTrue(article1.getRating() == 5);
		assertTrue(article1.getTitle().equals("Pac-Man suxks"));
		assertTrue(article1.getTitle().equals("yet another url"));
	}
	
}
