package GUI;

import java.util.ArrayList;

public class GameContainer {

	private int rating;
	private String title;
	private ArrayList<ArticleContainer> articles = new ArrayList<ArticleContainer>();
	
	public GameContainer(String title, String rating) {
		this.rating = Integer.parseInt(rating);
		this.title = title;	
	}
	
	public int getRating() {
		return rating;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setRating(String rating) {
		this.rating = Integer.parseInt(rating);;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public ArrayList<ArticleContainer> getArticles() {
		return articles;
	}
	
	public void addArticle(String title, String rating, String url) {
		ArticleContainer article = new ArticleContainer(title, rating, url);
		articles.add(article);
	}
	
	public void addArticle(ArticleContainer article) {
		articles.add(article);
	}

}
