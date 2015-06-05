package fagocity.controller;

import java.awt.Color;
import java.util.ArrayList;

import fagocity.model.Actor;
import fagocity.model.GameMode;
import fagocity.model.GameMode.STATUS;
import fagocity.model.GameModel;
import fagocity.view.GameView;

public class GameController {
	
	private static GameModel model;
	private GameView view;
	
	public GameController(GameModel model, GameView view) {
		GameController.setModel(model);
		this.setView(view);
	}
	
	/* Cria as condições iniciais do jogo */
	private static void initialConditions() {
		/* Cria o player */
		int radius = 35;
		ActorFactory.createActor((GameView.getScreenWidth() - radius)/2, (GameView.getScreenHeight() - radius)/2, 0, 0, radius, Color.red, "player");
	}
	
	/* Atualiza todos fatores do jogo */
	public void update() {
		
		/* Percorre a lista de actors e manda eles atualizarem suas informações */
		ArrayList<Actor> lista = GameModel.getActorsList();
		Actor obj;
		
		for(int i = 0; i < lista.size(); i++) {
			obj = lista.get(i);
			obj.update();
		}
		SpawnController.update();
		CollisionController.update();
		
		/*se nao estiver no modo jogavel, menu ou help fica ativo*/
		if (GameMode.status != STATUS.Fagocity)
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
}
