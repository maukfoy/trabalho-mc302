package fagocity.model;

public class GameStatus {
	
	public static enum STATUS {
		Fagocity(),
		Menu(),
		Help(),
		Pause(),
		End();
	}
	
	public static STATUS status = STATUS.Menu;
	
	public static void update(){
		
	}
	
}
