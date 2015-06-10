package fagocity.controller;

import java.awt.Color;

import fagocity.model.HUDModel;
import fagocity.model.Player;

public class BossController {
	
	private  int[] coordinates = new int[2];
	private  double[] velocities = new double[2];
	private  int defaultBossRadius;
	private HUDController hudController;
	private ActorFactory actorFactory;
	private BossSpawnController bossSpawner;
	private HUDModel hudModel;
	
	public BossController ( )
	{

		
		this.actorFactory = ActorFactory.getInstance();
		
		this.hudModel = HUDModel.getInstance();
		
		bossSpawner = new BossSpawnController ();
		
		defaultBossRadius = bossSpawner.getDefaultBossRadius();
	}


		
	public void createBoss ()
	{
		generateBossCoordinates ();
		generateBossVelocities();
		actorFactory.createActor(coordinates[0], coordinates[1], velocities[0],
				velocities[1], defaultBossRadius, Color.WHITE, "enemy");
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
			HUDController.getInstance().getBossScore()) >= 0)
		{
			HUDController.getInstance().incrementBossScore();
			createBoss();
		}
	}
	
	

}
