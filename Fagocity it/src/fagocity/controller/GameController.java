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
		initialConditions();
	}
	
	/* Cria as condições iniciais do jogo */
	private void initialConditions() {
		/* Cria o player */
		int radius = 20;
		ActorFactory.createActor((view.getWidth() - radius)/2, (view.getHeight() - radius)/2, 0, 0, radius, Color.red, "player");
	}
	
	/* Atualiza todos fatores do jogo */
	public void update() {
		
		/*so vai dar os updates caso esteja no modo jogavel*/
		if (GameMode.status == STATUS.Fagocity){
			/* Percorre a lista de actors e manda eles atualizarem suas informações */
			ArrayList<Actor> lista = GameModel.getActorsList();
			Actor obj;
		
			for(int i = 0; i < lista.size(); i++) {
				obj = lista.get(i);
				obj.update();
			}
		
			SpawnController.update();
			CollisionController.update();
		}
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
