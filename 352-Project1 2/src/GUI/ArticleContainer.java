package GUI;

public class ArticleContainer {

	private int rating;
	private String title;
	private String url;

	public ArticleContainer(String title, String rating, String url) {
		try {
			this.rating = Integer.parseInt(rating);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		this.title = title;
		this.url = url;
	}

	public int getRating() {
		return rating;
	}

	public String getTitle() {
		return title;
	}

	public String getURL() {
		return url;
	}

	public void setRating(String rating) {
		this.rating = Integer.parseInt(rating);
		;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setURL(String url) {
		this.url = url;
	}

}
