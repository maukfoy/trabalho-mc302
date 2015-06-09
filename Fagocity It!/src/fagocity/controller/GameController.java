package fagocity.controller;

import java.awt.Color;
import java.util.ArrayList;

import fagocity.model.Actor;
import fagocity.model.GameStatus;
import fagocity.model.GameStatus.STATUS;
import fagocity.model.GameModel;
import fagocity.model.Player;
import fagocity.view.GameView;

public class GameController {
	
	private GameModel model;
	private GameView view;
	private Actor player = null;
	private ActorFactory actorFactory;
	private SpawnController spawn;
	private MenuController menu;
	private CollisionController collision;
	private BoundsController bounds;
	private CameraController camera;
	private HUDController hud;
	private ColorBuffController buff;
	private MouseController mouseController;
	private AudioPlayer audioPlayer;
	
	
	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
		view.setController(this);

		
		audioPlayer = new AudioPlayer();
		mouseController = new MouseController();
		camera = new CameraController (view);
		view.setDisplay();
		view.setCameraView();

		
		actorFactory = new ActorFactory(model);
		
		bounds = new BoundsController (view, model);
		
		initialConditions();

		spawn = new SpawnController ((Player)player, actorFactory, view, model,bounds,camera, this);

		view.setHUDView();
		
		hud = new HUDController ((Player) player, actorFactory, view, model, bounds, camera, this);
	
		collision = new CollisionController ((Player) player, this, model);
			
		view.setMenuView();
	
		menu = new MenuController (this, hud, mouseController);
		
		buff = new ColorBuffController (spawn);
		/* Cria o gerador de buffs aut�nomo */
		new Thread( buff ).start();

	}
	
	/* Cria as condições iniciais do jogo */
	public void initialConditions() {
		/* Cria o player */
		int radius = 110;
		player = actorFactory.createActor((view.getScreenWidth() - radius)/2, (view.getScreenHeight() - radius)/2, 0, 0, radius,
				 Color.RED, "player", view, model, this);
	}
	
	/* Atualiza todos fatores do jogo */
	public void update() {
		
		/* Percorre a lista de actors e manda eles atualizarem suas informações */
		ArrayList<Actor> lista = model.getActorsList();
		Actor obj;
		
		spawn.update();
		
		/*deve vir antes dos objetos para que se possa ter os parametros de translacao*/
		if (GameStatus.status == STATUS.Fagocity)
			camera.update(player);
			
		/*chama os EnemmyController e PlayerController*/
		for(int i = 0; i < lista.size(); i++) {
			obj = lista.get(i);
			obj.update();
		}
		
		collision.update();
		bounds.update();
		
		if (GameStatus.status == STATUS.Fagocity){
			hud.update();
		}
		else
			/*se nao estiver no modo jogavel, menu ou help fica ativo*/
			menu.update();
	}
	
	public Actor getPlayer ()
	{
		return player;
	}
	
	public GameView getView ()
	{
		return view;
	}
	
	public GameModel getModel ()
	{
		return model;
	}

	public CameraController getCameraController (){
		return camera;
	}

	public MouseController getMouseController() {
		return mouseController;
	}
	public SpawnController getSpawnController() {
		return spawn;
	}
	public HUDController getHudController() {
		return hud;
	}
	public BoundsController getBounds() {
		return bounds;
	}
	public AudioPlayer getAudioPlayer() {
		return audioPlayer;
	}
}
