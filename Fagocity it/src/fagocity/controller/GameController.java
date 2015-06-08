package fagocity.controller;

import java.util.ArrayList;
import fagocity.model.Actor;
import fagocity.model.GameStatus;
import fagocity.model.GameStatus.STATUS;
import fagocity.model.GameModel;
import fagocity.model.Player;
import fagocity.view.GameView;

public class GameController {
	
	private static GameModel model;
	private GameView view;
	private static Actor player = null;
	
	public GameController(GameModel model, GameView view) {
		GameController.setModel(model);
		this.setView(view);
	}
	
	/* Cria as condições iniciais do jogo */
	public static void initialConditions() {
		/* Cria o player */
		int radius = Player.defaultRadius;
		player = ActorFactory.createActor((GameView.getScreenWidth() - radius)/2, (GameView.getScreenHeight() - radius)/2, 0, 0, radius, SpawnController.generateRandomColor(), "player");
	
		/* Cria o gerador de buffs aut�nomo */
		new Thread( new ColorBuffController() ).start();
	}
	
	/* Atualiza todos fatores do jogo */
	public void update() {
		
		/* Percorre a lista de actors e manda eles atualizarem suas informações */
		ArrayList<Actor> lista = GameModel.getActorsList();
		Actor obj;
		
		SpawnController.update();
		
		/*deve vir antes dos objetos para que se possa ter os parametros de translacao*/
		if (GameStatus.status == STATUS.Fagocity)
			CameraController.update(player);
			
		/*atualiza os Actors*/
		for(int i = 0; i < lista.size(); i++) {
			obj = lista.get(i);
			obj.update();
		}
		
		CollisionController.update();
		BoundsController.update();
		
		if (GameStatus.status == STATUS.Fagocity){
			HUDController.update();
		}
		else
			/*se nao estiver no modo jogavel, menu ou help fica ativo*/
			MenuController.update();
	}
	
	/* Adiciona actors à lista de actors que fica em GameModel */
	public static void addToActorsList(Actor actor) {
		getModel().addToActorsList(actor);
	}

	/* Getters e setters */
	public static GameModel getModel() {
		return model;
	}
	public static void setModel(GameModel model) {
		GameController.model = model;
	}
	public GameView getView() {
		return view;
	}
	public void setView(GameView view) {
		this.view = view;
	}
	public static Actor getPlayer ()
	{
		return player;
	}
}
