package fagocity.controller;

import java.util.Random;

import fagocity.model.Player;
import fagocity.view.GameView;

public class BossSpawnController {
	
	enum SIDE {DOWN, UP, LEFT, RIGHT};
	
	private SIDE side;
	private int width = GameView.getScreenWidth();
	private int height = GameView.getScreenHeight();
	private  double defaultBossVelocity = 3;
	private  int defaultBossRadius = 3200;
	int[] coordinates = new int[2];
	private Player p;
	
	
	private static BossSpawnController bossController = null;
	
	public static BossSpawnController getInstance() {
		if (bossController == null)
			bossController = new BossSpawnController();
		return bossController;
	}
	
	private BossSpawnController( ){
		}
	
	public int[] generateBossSpawnCoordinates() {
		Random r = new Random();
		this.p = (Player) GameController.getInstance().getPlayer();

		
		switch(r.nextInt (4)) {
			/* Nascer do lado esquerdo da tela */
			case 0:		
				System.out.println("left");
				coordinates[0] = p.getX() - width/2 - defaultBossRadius;
				coordinates[1] = p.getY() - defaultBossRadius/2;
				System.out.println("esqeurda");		
				side = SIDE.LEFT;
				break;
			/* Nascer do lado direito da tela */
			case 1:
				System.out.println("right");
				coordinates[0] =  p.getX() + width/2;
				coordinates[1] = p.getY() - defaultBossRadius/2;
				System.out.println("direito");
				side = SIDE.RIGHT;
				break;
			/* Nascer em cima da tela */
			case 2:
				System.out.println("up");
					coordinates[0] = p.getX() - defaultBossRadius/2;
					coordinates[1] = p.getY() - height/2 - defaultBossRadius;			
					System.out.println("cima");
				side = SIDE.UP;
				break;
			/* Nascer em baixo da tela */
			case 3:
				System.out.println("down");
				coordinates[0] = p.getX() - defaultBossRadius/2;
				coordinates[1] = p.getY() + height/2;	
				System.out.println("baixo");
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
