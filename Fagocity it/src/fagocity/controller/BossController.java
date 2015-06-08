package fagocity.controller;

import java.awt.Color;

import fagocity.model.HUDModel;

public class BossController {
	
	private static int[] coordinates = new int[2];
	private static double[] velocities = new double[2];
	private static int defaultBossRadius = BossSpawnController.getDefaultBossRadius();


		
	public static void createBoss ()
	{
		generateBossCoordinates ();
		generateBossVelocities();
		ActorFactory.createActor(coordinates[0], coordinates[1], velocities[0], velocities[1], defaultBossRadius, Color.WHITE, "enemy");
	}
	
	private static void generateBossCoordinates ()
	{
		coordinates = BossSpawnController.generateBossSpawnCoordinates();
	}
	
	private static void generateBossVelocities ()
	{
		velocities = BossSpawnController.generateBossSpawnVelocities();
	}
	
	/*Ve se ja se passou score suficiente para invocar o boss*/
	public static void checkScore ()
	{
		
		if (HUDModel.getScore() - (HUDModel.getDefaultPointsTillBoss() * 
			HUDController.getBossScore()) >= 0)
		{
			HUDController.incrementBossScore();
			createBoss();
		}
	}
	
	

}
