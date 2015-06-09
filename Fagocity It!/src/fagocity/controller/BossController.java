package fagocity.controller;

import java.awt.Color;

import fagocity.model.GameModel;
import fagocity.model.HUDModel;
import fagocity.model.Player;
import fagocity.view.GameView;

public class BossController {
	
	private  int[] coordinates = new int[2];
	private  double[] velocities = new double[2];
	private  int defaultBossRadius;
	private HUDController hudController;
	private ActorFactory actorFactory;
	private GameView view;
	private GameModel model;
	private BossSpawnController bossSpawner;
	private Player player;
	private BoundsController bounds;
	private CameraController camera;
	private HUDModel hudModel;
	private GameController controller;
	
	public BossController (HUDController hud, ActorFactory actorFactory, 
		   GameView view, GameModel model, Player player, BoundsController bounds, CameraController camera,
		   GameController controller)
	{
		this.hudController = hud;
		this.controller = controller;
		this.actorFactory = actorFactory;
		this.view = view;
		this.model = model;
		this.player = player;
		this.bounds = bounds;
		this.camera = camera;
		
		this.hudModel = model.getHudModel();
		
		bossSpawner = new BossSpawnController (player, view);
		
		defaultBossRadius = bossSpawner.getDefaultBossRadius();
	}


		
	public void createBoss ()
	{
		generateBossCoordinates ();
		generateBossVelocities();
		actorFactory.createActor(coordinates[0], coordinates[1], velocities[0],
				velocities[1], defaultBossRadius, Color.WHITE, "enemy", view, model, controller);
	}
	
	private void generateBossCoordinates ()
	{
		coordinates = bossSpawner.generateBossSpawnCoordinates();
	}
	
	private void generateBossVelocities ()
	{
		velocities = bossSpawner.generateBossSpawnVelocities();
	}
	
	/*Ve se ja se passou score suficiente para invocar o boss*/
	public void checkScore ()
	{
		
		if (hudModel.getScore() - (hudModel.getDefaultPointsTillBoss() * 
			hudController.getBossScore()) >= 0)
		{
			hudController.incrementBossScore();
			createBoss();
		}
	}
	
	

}
