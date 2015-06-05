package fagocity.model;

public class GameStatus {
	
	public static enum STATUS {
		Fagocity(), Menu(), Help();
	}
	
	public static STATUS status = STATUS.Menu;
}
