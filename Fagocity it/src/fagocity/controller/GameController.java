package fagocity.controller;

import fagocity.model.GameModel;
import fagocity.model.interfaces.IActor;
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
		int radius = 50;
		PlayerController.createPlayer((view.getWidth() - radius)/2, (view.getHeight() - radius)/2, radius, 0);
	}
	
	/* Adiciona actors à lista de actors que fica em GameModel */
	public static void addToActorsList(IActor actor) {
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