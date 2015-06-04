package fagocity.controller;

import java.awt.Color;
import java.util.Random;
import fagocity.view.GameView;

public class SpawnController {
	
	enum SIDE {DOWN, UP, LEFT, RIGHT};
	
	static long oldTime = 0;
	static long spawnTime = 500;
	static int defaultEnemyRadius = 50;
	static double minDefaultEnemyVelocity = 3;
	static double maxDefaultEnemyVelocity = 6;
	static int minDefaultRadius = 20;
	static int maxDefaultRadius = 200;
	static SIDE side;
	
	public static void autoEnemyCreator() {
		
		if(System.currentTimeMillis() > oldTime + spawnTime) {
			int[] coordinates = generateSpawnCoordinates();
			double[] velocities = generateSpawnVelocities();
			EnemyController.createEnemy(coordinates[0], coordinates[1],
					velocities[0], velocities[1], generateRandomRadius(), generateRandomColor());
			
			oldTime = System.currentTimeMillis();
		}
	}
	
	private static int[] generateSpawnCoordinates() {
		Random random = new Random();
		int[] coordinates = new int[2];
		
		switch(random.nextInt(4)) {
			/* Nascer do lado esquerdo da tela */
			case 0:
				coordinates[0] = -defaultEnemyRadius;
				coordinates[1] = random.nextInt(GameView.getScreenHeight());
				side = SIDE.LEFT;
				break;
			/* Nascer do lado direito da tela */
			case 1:
				coordinates[0] = GameView.getScreenWidth();
				coordinates[1] = random.nextInt(GameView.getScreenHeight());
				side = SIDE.RIGHT;
				break;
			/* Nascer em cima da tela */
			case 2:
				coordinates[0] = random.nextInt(GameView.getScreenWidth());
				coordinates[1] = -defaultEnemyRadius;
				side = SIDE.UP;
				break;
			/* Nascer em baixo da tela */
			case 3:
				coordinates[0] = random.nextInt(GameView.getScreenWidth());
				coordinates[1] = GameView.getScreenHeight();
				side = SIDE.DOWN;
				break;
		}
		return coordinates;
	}

	private static double[] generateSpawnVelocities() {
		double velTotal, velX, velY;
		int width = GameView.getScreenWidth();
		int height = GameView.getScreenHeight();
		double[] velocities = new double[2];
		Random random = new Random();
		
		/* Escolhe aleatoriamente qualquer numetro entre minDefaultEnemyVelocity
		 *  e maxDefaultEnemyVelocity, incluindo eles mesmos */
		velTotal = random.nextInt( (int)maxDefaultEnemyVelocity - (int)minDefaultEnemyVelocity + 1)
				+ (int)minDefaultEnemyVelocity;
		
		/* Decompõe a velocidade */
		velX = velTotal * (width / Math.sqrt( (width*width) + (height*height) ));
		velY = velTotal * (height / Math.sqrt( (width*width) + (height*height) ));
		
		/* Faz com que a velocidade da bolinha esteja sempre direcionada para dentro da tela */
		switch(side) {
			case LEFT:
				velY = randomFlipPositiveNegative(velY);
				break;
			case RIGHT:
				velX = -1 * velX;
				velY = randomFlipPositiveNegative(velY);
				break;
			case UP:
				velX = randomFlipPositiveNegative(velX);
				break;
			case DOWN:
				velY = -1 * velY;
				velX = randomFlipPositiveNegative(velX);
				break;
			default:
				break;
		}
		
		velocities[0] = velX;
		velocities[1] = velY;
		
		return velocities;

	}
	
	private static double randomFlipPositiveNegative(double number) {
		Random random = new Random();
		
		switch(random.nextInt(2)) {
		case 1:
			return number;
		case 0:
			return -1 * number;
		default:
			return 0;
		}
	}

	private static int generateRandomRadius() {
		Random random = new Random();
		int radius;
		
		/* Numero random entre o radio maximo e o minimo */
		radius = (int) (random.nextInt(maxDefaultRadius - minDefaultRadius) + minDefaultEnemyVelocity);
		
		return radius;
	}
	
	private static Color generateRandomColor() {
		Random r = new Random();
		
		/* Vetor com todas cores de bolinha possíveis no jogo */
		Color[] colors = {Color.blue, Color.cyan, Color.green, 
				Color.yellow, Color.magenta, Color.orange, Color.pink, 
				Color.white, Color.yellow};
		
		int randomInt = r.nextInt( colors.length ) ;
		
		return colors[randomInt];		
		
	}
		
}
