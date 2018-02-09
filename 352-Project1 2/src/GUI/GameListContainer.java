package GUI;

import java.util.ArrayList;

public class GameListContainer {

	public ArrayList<GameContainer> listOfGames = new ArrayList<GameContainer>();
	
	public void add(GameContainer game) {
		listOfGames.add(game);
	}
	
	public GameContainer get(String title) {
		GameContainer game = null;
		for (int x = 0; x < listOfGames.size(); x++) {
			if (listOfGames.get(x).getTitle().equals(title)) {
				game = listOfGames.get(x);
			}
		}
		return game;
	}
	
	public GameContainer get(int index) {
		return listOfGames.get(index);
	}
	
	public int size() {
		return listOfGames.size();
	}
		
}
