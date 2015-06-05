package fagocity.model;

public class GameMode {
	
	public static enum STATUS {
		Fagocity(), Menu(), Help();
	}
	
	public static STATUS status = STATUS.Menu;
}
