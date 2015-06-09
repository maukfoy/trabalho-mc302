package fagocity.controller;

import java.util.Random;

import fagocity.controller.SpawnController.SIDE;
import fagocity.model.Actor;
import fagocity.model.Player;
import fagocity.view.GameView;

public class BossSpawnController {
	
	enum SIDE {DOWN, UP, LEFT, RIGHT};
	
	private SIDE side;
	private int width, height;
	private  double defaultBossVelocity = 3;
	private  int defaultBossRadius = 3200;
	int[] coordinates = new int[2];
	private GameView view;
	private Player p;
	
	
	public BossSpawnController(Player player, GameView view){
		this.p = player;
		this.view = view;
		
		width = view.getScreenWidth();
		height = view.getScreenHeight();
		}
	
	public int[] generateBossSpawnCoordinates() {
		Random r = new Random();
		
		
		switch(r.nextInt (4)) {
			/* Nascer do lado esquerdo da tela */
			case 0:		
				System.out.println("left");
				coordinates[0] = p.getX() - width/2 - defaultBossRadius;
				coordinates[1] = p.getY() - defaultBossRadius/2;
						
				side = SIDE.LEFT;
				break;
			/* Nascer do lado direito da tela */
			case 1:
				System.out.println("right");
				coordinates[0] =  p.getX() + width/2;
				coordinates[1] = p.getY() - defaultBossRadius/2;
					
				side = SIDE.RIGHT;
				break;
			/* Nascer em cima da tela */
			case 2:
				System.out.println("up");
					coordinates[0] = p.getX() - defaultBossRadius/2;
					coordinates[1] = p.getY() - height/2  - defaultBossRadius;			
			
				side = SIDE.UP;
				break;
			/* Nascer em baixo da tela */
			case 3:
				System.out.println("down");
				coordinates[0] = p.getX() - defaultBossRadius/2;
				coordinates[1] = p.getY() + height/2;	
				
				side = SIDE.DOWN;
				break;
		}
		return coordinates;
	}
	
	public double[] generateBossSpawnVelocities()
	{
		double[] velocities = new double[2];

		switch (side)
		{
			case LEFT:
			{
				velocities[0] = defaultBossVelocity;
				velocities[1] = 0;
				break;
			}
			case RIGHT:
			{
				velocities[0] = -defaultBossVelocity;
				velocities[1] = 0;
				break;
			}
			case UP:
			{
				velocities[0] = 0;
				velocities[1] = defaultBossVelocity;
				break;
			}
			case DOWN:
			{
				velocities[0] = 0;
				velocities[1] = -defaultBossVelocity;
				break;
			}
		}
		
		return velocities;

	}

	public int getDefaultBossRadius(){
		return defaultBossRadius;
	}
}
