package fagocity.controller;

import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import fagocity.controller.Interfaces.IActorController;
import fagocity.model.Actor;
import fagocity.model.GameModel;
import fagocity.model.Player;
import fagocity.view.GameView;

public class PlayerController implements IActorController {
	
	private Player p;
	
	public PlayerController (Player player){
		this.p = player;
	}
	
	public void update() {
		atualizaVelocidade();
		atualizarPosicao();
	}
	
	private void atualizarPosicao() {
		p.setX(p.getX() + (int)p.getVelX());
		p.setY(p.getY() + (int)p.getVelY());
		
		/*reestabelece x e y se eles passarem dos limites do mapa*/
		p.setX(BoundsController.playerRepositioning (p.getX(),GameView.getMinXBounds(),GameView.getMaxXBounds() - p.getRadius()));
		p.setY(BoundsController.playerRepositioning (p.getY(),GameView.getMinYBounds(),GameView.getMaxYBounds() - p.getRadius()));
	}
	
	private void atualizaVelocidade() {
		double velTotal = Player.defaultSpeed;
		 
		/*pega as posicoes de input do mouse e soma-as aos parametros de translacao*/
		int mouseX = (int) (MouseController.getMouseX() - CameraController.getTX());
		int mouseY = (int) (MouseController.getMouseY() - CameraController.getTY());
		
		/* Distance between mouse and player */
		double distance = Math.sqrt( (mouseY - p.getY())*(mouseY - p.getY()) +  (mouseX - p.getX())*(mouseX - p.getX()) );
		
		/* Componentes da velocidade */
		int velX = (int) ((velTotal/distance) * (mouseX - p.getX()));
		int velY = (int) ((velTotal/distance) * (mouseY - p.getY()));
		
		/* Tolerância de erro. Ele é necessário pois evita uma divisão por 0, evita o flickering do player
		 * e faz permite que a velocidade do player diminui conforme o mouse se aproxima dele */
		int tolerance = 100;
		if(distance > tolerance) {
			p.setVelX ( velX );
			p.setVelY ( velY );
		}
		else {
			p.setVelX ((int)(velX*distance/tolerance));
			p.setVelY ((int)(velY*distance/100));
		}
	}
	
	public static void reducePlayerLife(Actor player, ArrayList<Actor> toBeDeleted) {
		long currentTime = System.currentTimeMillis();
		/* Faz com que o deathTimeDelay seja respeitado, ou seja
		 * só deixa o player morrer se ja tiver se passado 
		 * o tempo de DeathTimeDaley desde sua ultima morte */
		if( Player.getLastDeathTime() == 0 || 
				(currentTime - Player.getLastDeathTime() ) > Player.getDeathTimeDelay() ) {
				Player.lives--;
				Player.setLastDeathTime( System.currentTimeMillis() );
				PlayerController.playPlayerSound("LostLifeSound");
		}
		/* Se o player não tiver mais vidas, ele morre pra sempre */
		if(Player.lives < 0) {
			toBeDeleted.add(player);
			PlayerController.playPlayerSound("DeathSound");
		}
	}
	
	public static Player getPlayer() {
		ArrayList<Actor> list = GameModel.getActorsList();
		Player player = null;
		
		for(int i = 0; i < list.size(); i++) {
			Actor actor = list.get(i);
			if( actor instanceof Player )
				player = (Player) actor;
		}
		return player;
	}
	
	public static void playPlayerSound(String name) {
		String path = "src/fagocity/model/assets/sounds/";
	    try {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File(path + name + ".wav")));
	        clip.start();
	    }
	    catch (Exception e) {
	        e.printStackTrace(System.out);
	    }
	}
}
