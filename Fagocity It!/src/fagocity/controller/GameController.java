package fagocity.controller;

import java.awt.Color;
import java.util.ArrayList;

import fagocity.controller.Interfaces.ControllerSingleton;
import fagocity.model.Actor;
import fagocity.model.Enemy;
import fagocity.model.GameStatus;
import fagocity.model.GameStatus.STATUS;
import fagocity.model.GameModel;
import fagocity.model.Player;
import fagocity.view.GameView;

public class GameController implements ControllerSingleton {
	
	private GameModel model;
	private GameView view;
	private Player player = null;
	private ActorFactory actorFactory;
	private SpawnController spawn;
	private MenuController menu;
	private CollisionController collision;
	private BoundsController bounds;
	private CameraController camera;
	private HUDController hud;
	private ColorBuffController buff;
	private AudioPlayer audioPlayer;
	
	private static GameController gameController = null;
	
	public static GameController getInstance() {
		if (gameController == null)
			gameController = new GameController();
		return gameController;
	}
	
	private GameController() {
		this.model = GameModel.getInstance();
		this.view = GameView.getInstance();
		actorFactory = ActorFactory.getInstance();
		
		camera = CameraController.getInstance();
		bounds = BoundsController.getInstance();
		audioPlayer = AudioPlayer.getInstance();
		spawn = SpawnController.getInstance();
		hud = HUDController.getInstance();
		collision = CollisionController.getInstance();	
		menu = MenuController.getInstance();	
		buff = ColorBuffController.getInstance();
		
		/* Cria o gerador de buffs autonomo */
		new Thread(buff).start();

	}
	
	/* Reseta o jogo para o in�cio */
	public void resetGame() {
		
		/* Reseta o player */
		player.setX( (view.getMaxXBounds() - player.getRadius()) / 2 );
		player.setY( (view.getMaxYBounds() - player.getRadius()) / 2 );
		player.setRadius(110);
		player.setLifes(3);
		
		ArrayList<Actor> list = model.getActorsList();
		/* Deleta todos inimigos do jogo */
		for(int i = list.size() -1; i >= 0; i--) {
			if(list.get(i) instanceof Enemy)
				list.remove( i );
		}
		
		/* Reseta o score */
		hud.setScore(0);
		
		/* Limpa o fagocity streak */
		hud.cleanFagocityStreak();
		
	}
	
	/* Cria as condicoes iniciais do jogo */
	public void initialConditions() {
		/* Cria o player */
		int radius = 110;
		
		player = (Player) actorFactory.createActor((view.getMaxXBounds() - radius)/2, (view.getMaxYBounds() - radius)/2, 0, 0, radius,
				 Color.RED, "player");
	}
	
	/* Atualiza todos fatores do jogo */
	public void update() {
		
		/* Percorre a lista de actors e manda eles atualizarem suas informações */
		ArrayList<Actor> lista = model.getActorsList();
		Actor obj;
		
		if (GameStatus.status == STATUS.Fagocity)
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
		else if (GameStatus.status == STATUS.Menu || GameStatus.status == STATUS.Help){
			/*se nao estiver no modo jogavel, menu ou help fica ativo*/
			menu.update();
		}
		
	}
	
	public Actor getPlayer ()
	{
		ArrayList<Actor> list = model.getActorsList();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) instanceof Player)
				return list.get(i);
		}
		return null;
	}
	
	public GameView getView ()
	{
		return view;
	}
	
	public GameModel getModel ()
	{
		return model;
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
