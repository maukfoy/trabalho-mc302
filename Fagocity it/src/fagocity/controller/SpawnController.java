package fagocity.controller;

import java.awt.Color;
import java.util.Random;

import fagocity.model.Actor;
import fagocity.view.GameView;

public class SpawnController {
	
	enum SIDE {DOWN, UP, LEFT, RIGHT};
	
	private static long oldTime = 0;
	private static long spawnTime = 20;
	private static double minDefaultEnemyVelocity = 3;
	private static double maxDefaultEnemyVelocity = 6;
	private static int minDefaultRadius = 20;
	private static int maxDefaultRadius = 120;
	private static SIDE side;
	
	private static int width = GameView.getScreenWidth();
	private static int height = GameView.getScreenHeight();
	private static int minXBounds = GameView.getMinXBounds();
	private static int maxXBounds = GameView.getMaxXBounds();
	private static int minYBounds = GameView.getMinYBounds();
	private static int maxYBounds = GameView.getMaxYBounds();
	
	public static void update (){
		autoEnemyCreator();
	}
	
	public static void autoEnemyCreator() {
		
		if(System.currentTimeMillis() > oldTime + spawnTime) {
			int[] coordinates = generateSpawnCoordinates();
			double[] velocities = generateSpawnVelocities();
			ActorFactory.createActor(coordinates[0], coordinates[1],
					velocities[0], velocities[1], generateRandomRadius(), generateRandomColor(), "enemy");
			
			oldTime = System.currentTimeMillis();
		}
	}
	
	private static int[] generateSpawnCoordinates() {
		Random r = new Random();
		Actor p = GameController.getPlayer();
		int[] coordinates = new int[2];
		
		/*caso base para o nascimento dos inimigos*/
		if (p != null)
		{
			coordinates[0] = p.getX() + width/2;
			coordinates[1] = p.getY() + height/2;
		}	
		
		switch(r.nextInt(4)) {
			/* Nascer do lado esquerdo da tela */
			case 0:
				if (p == null)/*no menu*/ /*ninguem pode nascer na tela inicial (0.0)*/
				{
					coordinates[0] = r.nextInt(maxXBounds/2 - width/2);
					coordinates[1] = r.nextInt(maxYBounds - height) + height;
				}
				else/*no jogo, os inimigos devem nascer longe do personagem*/
				{
					if ((p.getX() - width/2) > 0)//evita que o limite de random se inverte e de crush
					{
					coordinates[0] = r.nextInt (p.getX() - width/2);
					coordinates[1] = r.nextInt (maxYBounds);
					}
				}
				side = SIDE.LEFT;
				break;
			/* Nascer do lado direito da tela */
			case 1:
				if (p == null)//no menu
				{
					coordinates[0] = r.nextInt(maxXBounds - maxXBounds/2 - width/2) + maxXBounds/2 + width/2;
					coordinates[1] = r.nextInt(maxYBounds);
				}
				else//no jogo
				{
					if ((maxXBounds - p.getX() - width/2)  > (p.getX() - width/2))
					{
					coordinates[0] = r.nextInt(maxXBounds - p.getX() - width/2) + p.getX() + width/2;
					coordinates[1] = r.nextInt(maxYBounds);
					}
				}
				side = SIDE.RIGHT;
				break;
			/* Nascer em cima da tela */
			case 2:
				if (p == null)//no menu
				{
					coordinates[0] = r.nextInt(maxXBounds - width) + width;
					coordinates[1] = r.nextInt(maxYBounds/2 - height/2);
				}
				else//no jogo
				{
					if ((p.getY() - height/2) > 0)
					{
						coordinates[0] = r.nextInt(maxXBounds);
						coordinates[1] = r.nextInt(p.getY() - height/2);
					}
				}
				side = SIDE.UP;
				break;
			/* Nascer em baixo da tela */
			case 3:
				if (p == null)//no menu
				{		
					coordinates[0] =r.nextInt(maxXBounds);
					coordinates[1] = r.nextInt(maxYBounds - maxYBounds/2 - height/2) + maxYBounds/2 + height/2;
				}
				else//no jogo
				{
					if ((maxYBounds - p.getY() - height/2)  > (p.getY() - height/2))
					{
					coordinates[0] = r.nextInt(maxXBounds);
					coordinates[1] = r.nextInt( maxYBounds - p.getY() - height/2) + p.getY() + height/2;
					}
				}
				side = SIDE.DOWN;
				break;
		}
		return coordinates;
	}

	private static double[] generateSpawnVelocities() {
		double velTotal, velX, velY;
		double[] velocities = new double[2];
		Random random = new Random();
		
		/* Escolhe aleatoriamente qualquer numero entre minDefaultEnemyVelocity
		 *  e maxDefaultEnemyVelocity, incluindo eles mesmos */
		velTotal = random.nextInt( (int)maxDefaultEnemyVelocity - (int)minDefaultEnemyVelocity + 1)
				+ (int)minDefaultEnemyVelocity;
		
		double angle = random.nextDouble() * Math.PI/2;
		
		/* Decompõe a velocidade */
		velX = velTotal * Math.cos(angle);
		velY = velTotal * Math.sin(angle);
		
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
		radius = (random.nextInt(maxDefaultRadius - minDefaultRadius) + minDefaultRadius);
		
		return radius;
	}
	
	public static Color generateRandomColor() {
		Random r = new Random();
		
		/* Vetor com todas cores de bolinha possíveis no jogo */
		Color[] colors = {Color.blue, Color.cyan, Color.green, 
				Color.yellow, Color.magenta, Color.orange, Color.pink, 
				Color.white, Color.yellow, Color.RED};
		
		int randomInt = r.nextInt( colors.length ) ;
		
		return colors[randomInt];		
		
	}
		
}
