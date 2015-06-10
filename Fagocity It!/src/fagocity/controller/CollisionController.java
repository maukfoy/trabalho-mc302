package fagocity.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import fagocity.controller.Interfaces.ControllerSingleton;
import fagocity.model.Actor;
import fagocity.model.Enemy;
import fagocity.model.GameModel;
import fagocity.model.HUDModel;
import fagocity.model.Player;
import fagocity.model.ColorBuff;
import fagocity.view.ColorBuffView;

public class CollisionController implements ControllerSingleton {
	
	private SpawnController spawn;
	private GameModel model;
	private Player player;
	private HUDController hudController;
	private HUDModel hudModel;
	private AudioPlayer audioPlayer;
	GameController controller;
	
	private static CollisionController collisionController = null;
	
	public static CollisionController getInstance() {
		if (collisionController == null)
			collisionController = new CollisionController();
		return collisionController;
	}
	
	private CollisionController ()
	{
		
		this.hudController = HUDController.getInstance();
		this.hudModel = HUDModel.getInstance();
		this.spawn = SpawnController.getInstance();
		this.model = GameModel.getInstance();
		this.audioPlayer = AudioPlayer.getInstance();
		
		
	}
	
	public void update()
	{
		actorsCollision ();
		buffsCollision ();
	}
	
	public class EnemiesColorChanger implements Runnable {
		
		public void run() {
			
			player = (Player) GameController.getInstance().getPlayer();

			
			/* Marca como ativado o Color Buff */
			ColorBuff.setCurrentColorBuff( player.getColor() );
			
			/* Percorre a lista de actors e troca suas cores */
			ArrayList<Actor> list = model.getActorsList();
			for(int i = list.size() -1; i >= 0; i--) {
				Actor actor = list.get(i);
				/* Se for um enemy diminui a velocidade deles */
				if( actor instanceof Enemy) {
					actor.setColor( player.getColor() );
				}	
			}
			
			long startedTime = System.currentTimeMillis();
			long timer;
			/* Deixa o efeito do buff acontecer por um tempo e vai atualizando o timer*/
			while(System.currentTimeMillis() - startedTime < ColorBuff.getDuration()) {
				timer  = System.currentTimeMillis() - startedTime;
				ColorBuffView.getInstance().setTimer(timer);
			}
			
			/* desativa o buff */
			ColorBuff.setCurrentColorBuff( null );
		}		
	}
	
	private void buffsCollision() {
		player = (Player) GameController.getInstance().getPlayer();
		ArrayList<ColorBuff> buffsList = ColorBuffController.getBuffsList();
		int buffX, buffY, buffRadius;
		int playerX, playerY, playerRadius;
		
		if( player != null) {
			playerX = player.getX();
			playerY = player.getY();
			playerRadius = player.getRadius();
			
			for(int i = 0; i < buffsList.size(); i++) {
				ColorBuff colorBuff = buffsList.get(i);
				buffX = colorBuff.getX();
				buffY = colorBuff.getY();
				buffRadius = colorBuff.getRadius();
				
				/* Se houver colisão */
				if( distanceBetweenPoints ( ( buffX + buffRadius), (playerX + playerRadius/2),
						(buffY + buffRadius), (playerY + playerRadius/2) ) < (buffRadius + playerRadius/2) )  {
					buffsList.remove(colorBuff);
					/* Toca o som de colisão */
					audioPlayer.playAudio("BuffSound");
					/* Se não tiver nenhum Color Buff ligado, chama o Color Buff */
					if( ColorBuff.getCurrentColorBuff() == null) {
						new Thread( new EnemiesColorChanger()).start();
					}
				}
			}
		}
		
	}

	public void actorsCollision()
	{
		player = (Player) GameController.getInstance().getPlayer();
		ArrayList<Actor> list = model.getActorsList();
		ArrayList<Actor> toBeDeleted = new ArrayList<Actor>();
		ArrayList<Actor> toBeColored = new ArrayList<Actor>();
		Actor obj1, obj2;
		
		/*compara todos os Actors do jogo*/
		for(int i = 0; i < list.size(); i++)
		{
			for(int j = i; j < list.size(); j++)
			{
				obj1 = list.get(i);
				obj2 = list.get(j);
				
				/*se distintos objetos, com distintos tamanhos, tiverem condicao de intersecao
				 *propicia a fagocitacao, guarda-se o menor (p/ remove-lo posteriormente) e 
				 *aumenta-se o maior*/
				if ( (j != i) && (obj1.getRadius() != obj2.getRadius()) && (intersection(obj1, obj2)))
				{	
					Actor greatest = greatestObject(obj1, obj2);
					Actor smallest = smallestObject(obj1, obj2);
				
					/*aumenta o Growing do objeto maior, usando-se o calculo do newRadius*/
					greatest.setGrowingRadius(greatest.getGrowingRadius() + newRadius(obj1, obj2)/2);
					
					/* Se o player consegue fagocitar */
					if( greatest instanceof Player  ) {
						/* Tira o menor, no caso o inimigo, do jogo */
						toBeDeleted.add(smallest);
												
						/* Se as cores não forem iguais, perde vida */
						if( equalColors(obj1.getColor(), obj2.getColor()) == false ) {
							reducePlayerLife((Player)greatest, toBeDeleted);
							hudController.resetFagocityStreak();
						}
						else {
							/* O Player consegue fagocitar, então toca o som de fagocitose */
							audioPlayer.playAudio("FagocitySound");
							/* Atualiza o streak do player */
							hudController.updateFagocityStreak();
							/* Atualiza o raio total fagocitado, importante para o score */
							hudController.setTotalFagocitedRadius(smallest.getRadius());
						}
						
						/* Muda a cor do player */
						if(ColorBuff.getCurrentColorBuff() == null) //se o color buff estiver desligado
							toBeColored.add(greatest);
						
 					}
					
					/* Se não consegue fagocitar, tira o Actor menor do jogo */
					else {
						toBeDeleted.add(smallest);
						/* Salva o highscore */
						try {
							hudModel.saveHighscore();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		/* Colore todos objetos que fagocitaram */
		for(int i = 0; i < toBeColored.size(); i++) {
			toBeColored.get(i).setColor( spawn.generateRandomColor() );
		}
		
		/*remove todos os objetos que foram fagocitados*/
		for (int i = 0; i < toBeDeleted.size(); i++) {
			Actor dead = toBeDeleted.get(i);
			if(dead instanceof Player) {
				audioPlayer.playAudio("DeathSound");
			}
			list.remove(dead);
		}
		
		sortPerRadius (list);	
		growBalls (list);
		
	}
	
	/*calcula a dinstancia entre dois pontos das coordenadas cartesianas*/
	public int distanceBetweenPoints (double x1, double x2, double y1, double y2)
	{
		int d;
		
		d = (int)(Math.sqrt ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
		
		return d;
	}
	
	/*retorna o objeto com raio menor*/
	public Actor smallestObject (Actor obj1, Actor obj2)
	{
		if (obj1.getRadius() > obj2.getRadius())
			return obj2;
		else
			return obj1;
	}
	
	/*retorna o objeto com raio maior*/
	public Actor greatestObject (Actor obj1, Actor obj2)
	{
		if (obj1.getRadius() > obj2.getRadius())
			return obj1;
		else
			return obj2;
	}
	
	/*retorna true se a intersecao entre dois objetos for suficiente para ocorrer a fagocitacao*/
	public boolean intersection (Actor obj1, Actor obj2)
	{
		
		double x1center = obj1.getX() + obj1.getRadius()/2;
		double x2center = obj2.getX()+ obj2.getRadius()/2;
		double y1center = obj1.getY()+ obj1.getRadius()/2;
		double y2center = obj2.getY()+ obj2.getRadius()/2;
		/*se a distancia entre os objetos for menor ou igual ao raio do maior objeto*/
		if (distanceBetweenPoints (x1center, x2center , y1center , y2center)
				<= greatestObject(obj1, obj2).getRadius()/2)
			return true;
		else
			return false;
	}
	
	/*retorna o raio a ser acrescido no objeto apos a fagocitacao*/
	public int newRadius (Actor obj1, Actor obj2)
	{
		double r;
		
		r = Math.sqrt(obj1.getRadius() * obj1.getRadius() + obj2.getRadius() * obj2.getRadius());
		
		r -= greatestObject (obj1, obj2).getRadius();
				
		return (int)r;
	}
	
	/*reorganiza o arraylist em ordem crescente de raio, por fins de renderizacao*/
	public void sortPerRadius (ArrayList <Actor> list)
	{
		int i, j , posAntiga = 0, maior;
		
		for (j = 0; j < list.size(); j++)
		{
			maior = 0;
			for (i = j; i < list.size(); i++)
			{
				if (list.get(i).getRadius() >= maior)
				{
					maior = list.get(i).getRadius();
					posAntiga = i;
				}
			}
			Collections.swap(list, j, posAntiga);
		}
	}
	
	/*aumenta o raio dos que fagocitaram, de update em update (implica animacao)*/
	public void growBalls (ArrayList <Actor> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).getGrowingRadius() > 0)
			{
				list.get(i).setRadius(list.get(i).getRadius() + 1);
				list.get(i).setGrowingRadius(list.get(i).getGrowingRadius() - 1);
			}
			else if (list.get(i).getGrowingRadius() < 0)
			{
				list.get(i).setRadius(list.get(i).getRadius() - 1);
				list.get(i).setGrowingRadius(list.get(i).getGrowingRadius() + 1);
			}
		}
	}
	
	private boolean equalColors(Color color1, Color color2) {
		if(color1.equals(color2))
			return true;
		else
			return false;
	}
	
	public void reducePlayerLife(Player player, ArrayList<Actor> toBeDeleted) {
		
		long currentTime = System.currentTimeMillis();
		/* Faz com que o deathTimeDelay seja respeitado, ou seja
		 * só deixa o player morrer se ja tiver se passado 
		 * o tempo de DeathTimeDaley desde sua ultima morte */
		if( player.getLastDeathTime() == 0 || 
			(currentTime - player.getLastDeathTime() ) > player.getDeathTimeDelay() ) {
			player.setLifes(player.getLifes() - 1);
			player.setLastDeathTime( System.currentTimeMillis() );
			audioPlayer.playAudio("LostLifeSound");
		}
		/* Se o player não tiver mais vidas, ele morre pra sempre */
		if(player.getLifes() < 0) {
			toBeDeleted.add(player);
			audioPlayer.playAudio("DeathSound");
		}
	}

}
