package fagocity.controller;

import java.awt.Color;
import java.util.Random;

import fagocity.model.ColorBuff;
import fagocity.model.GameModel;
import fagocity.model.Player;
import fagocity.view.GameView;

public class SpawnController {
	
	enum SIDE {DOWN, UP, LEFT, RIGHT};
	
	protected long oldTime = 0;
	protected long spawnTime = 14;
	protected double minDefaultEnemyVelocity = 4;
	protected double maxDefaultEnemyVelocity = 7;
	protected int minDefaultRadius = 20;
	protected int maxDefaultRadius = 120;
	protected SIDE side;
	
	protected int width;
	protected int height; 
	protected int maxXBounds;
	protected int maxYBounds; 
	
	private ActorFactory actorFactory;
	private Player p;
	private GameView view;
	private GameModel model;
	private GameController controller;
	
	private static SpawnController spawnController = null;
	
	public static SpawnController getInstance() {
		if (spawnController == null)
			spawnController = new SpawnController();
		return spawnController;
	}
	
	private SpawnController ()
	{
		this.actorFactory = ActorFactory.getInstance();
		this.view = GameView.getInstance();
		this.model = GameModel.getInstance();
		
		width = GameView.getScreenWidth();
		height = GameView.getScreenHeight();
		maxXBounds = view.getMaxXBounds();
		maxYBounds = view.getMaxYBounds();
		
	}
	
	public void update (){
		autoEnemyCreator();
	}
	
	public void autoEnemyCreator() {
		
		if(System.currentTimeMillis() > oldTime + spawnTime) {
			int[] coordinates = generateSpawnCoordinates();
			double[] velocities = generateSpawnVelocities();
			/* Se o buff de cor estiver desligado */
			if( ColorBuff.getCurrentColorBuff() == null) {
			actorFactory.createActor(coordinates[0], coordinates[1],
					velocities[0], velocities[1], generateRandomRadius(),
					generateRandomColor(), "enemy");
			}
			/* Se o buff de cor estiver ligado */
			else {
				actorFactory.createActor(coordinates[0], coordinates[1],
						velocities[0], velocities[1], generateRandomRadius(),
						ColorBuff.getCurrentColorBuff(), "enemy");
			}
			
			oldTime = System.currentTimeMillis();
		}
	}
	
	public int[] generateSpawnCoordinates() {
		Random r = new Random();
		int[] coordinates = new int[2];
		this.p = (Player) GameController.getInstance().getPlayer();

		
		/*caso base para o nascimento dos inimigos*/
		if (p != null)
		{
			coordinates[0] = p.getX() + width;
			coordinates[1] = p.getY() + height;
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
				if (p == null)//no menu , nasce ‡ direita do menu
				{
					coordinates[0] = r.nextInt(maxXBounds - maxXBounds/2 - width/2) + maxXBounds/2 + width/2;
					coordinates[1] = r.nextInt(maxYBounds);
				}
				else//no jogo, nasce ‡ direita do jogador e dento do mapa
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
				if (p == null)//no menu, nasce em cima dele
				{
					coordinates[0] = r.nextInt(maxXBounds - width) + width;
					coordinates[1] = r.nextInt(maxYBounds/2 - height/2);
				}
				else//no jogo, nasce ‡ cima do jogador e dento do mapa
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
				if (p == null)//no menu, nasce ‡ baixo dele
				{		
					coordinates[0] =r.nextInt(maxXBounds);
					coordinates[1] = r.nextInt(maxYBounds - maxYBounds/2 - height/2) + maxYBounds/2 + height/2;
				}
				else//no jogo, nasce ‡ baixo do jogado e dentro do mapa
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
	
	private double[] generateSpawnVelocities() {
		double velTotal, velX, velY;
		double[] velocities = new double[2];
		Random random = new Random();
		
		/* Escolhe aleatoriamente qualquer numero entre minDefaultEnemyVelocity
		 *  e maxDefaultEnemyVelocity, incluindo eles mesmos */
		velTotal = random.nextInt( (int)maxDefaultEnemyVelocity - (int)minDefaultEnemyVelocity + 1)
				+ (int)minDefaultEnemyVelocity;
		
		double angle = random.nextDouble() * Math.PI/2;
		
		/* Decomp√µe a velocidade */
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
	
	private double randomFlipPositiveNegative(double number) {
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

	private int generateRandomRadius() {
		Random random = new Random();
		int radius;
		
		/* Numero random entre o radio maximo e o minimo */
		radius = (random.nextInt(maxDefaultRadius - minDefaultRadius) + minDefaultRadius);
		
		return radius;
	}
	
	public Color generateRandomColor() {
		Random r = new Random();
		
		Color yellow = new Color(255, 255, 24);
		Color pink = new Color(255, 0, 127);
		Color orange = new Color(255, 128, 0);
		Color brown = new Color(102, 51, 0);
		Color red = new Color(255, 0, 0);
		Color gray = new Color(128, 128, 128);
		Color lightBlue = new Color(0, 204, 204);
		
		/* Vetor com todas cores de bolinha poss√≠veis no jogo */
		Color[] colors = { yellow, pink, orange, brown, red, gray, lightBlue};
		
		int randomInt = r.nextInt( colors.length ) ;
		
		return colors[randomInt];		
		
	}
	
	public double getMinDefaultEnemyVelocity() {
		return minDefaultEnemyVelocity;
	}
	
	public void setMinDefaultEnemyVelocity(double newVelocity) {
		minDefaultEnemyVelocity = newVelocity;
	}
	
	public double getMaxDefaultEnemyVelocity() {
		return maxDefaultEnemyVelocity;
	}
	
	public void setMaxDefaultEnemyVelocity(double newVelocity) {
		maxDefaultEnemyVelocity = newVelocity;
	}
		
}
