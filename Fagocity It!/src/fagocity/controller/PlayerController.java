package fagocity.controller;

import java.util.ArrayList;

import fagocity.controller.Interfaces.IActorController;
import fagocity.model.Actor;
import fagocity.model.GameModel;
import fagocity.model.Player;
import fagocity.view.GameView;

public class PlayerController implements IActorController {
	private GameView view;
	private GameModel model;
	private Player p;
	private CameraController camera;
	private BoundsController bounds;
	private MouseController mouse;
	
	public PlayerController (Player player, GameView view, GameModel model,
			GameController controller){
		this.p = player;
		this.view = view;
		this.model = model;
		this.bounds = controller.getBounds();
		this.camera = controller.getCameraController();
		
		this.mouse = controller.getMouseController();
	}
	
	public void update() {
		atualizaVelocidade();
		atualizarPosicao();
	}
	
	private void atualizarPosicao() {
		p.setX(p.getX() + (int)p.getVelX());
		p.setY(p.getY() + (int)p.getVelY());
		
		/*reestabelece x e y se eles passarem dos limites do mapa*/
		p.setX(bounds.playerRepositioning (p.getX(),view.getMinXBounds(),view.getMaxXBounds() - p.getRadius()));
		p.setY(bounds.playerRepositioning (p.getY(),view.getMinYBounds(),view.getMaxYBounds() - p.getRadius()));
	}
	
	private void atualizaVelocidade() {
		double velTotal = p.getDefaultSpeed();
		 
		/*pega as posicoes de input do mouse e soma-as aos parametros de translacao*/
		int mouseX = (int) (mouse.getMouseX() - camera.getTX());
		int mouseY = (int) (mouse.getMouseY() - camera.getTY());
		
		/* Coordenadas do centro do player */
		int xCenter = p.getX() + p.getRadius()/2;
		int yCenter = p.getY() + p.getRadius()/2;
		
		/* Distance between mouse and player */
		double distance = Math.sqrt( (mouseY - yCenter)*(mouseY - yCenter) +  (mouseX - xCenter)*(mouseX - xCenter) );
		
		/* Componentes da velocidade */
		int velX = (int) ((velTotal/distance) * (mouseX - xCenter));
		int velY = (int) ((velTotal/distance) * (mouseY - yCenter));
		
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
	
	
	public Player getPlayer() {
		ArrayList<Actor> list = model.getActorsList();
		Player player = null;
		
		for(int i = 0; i < list.size(); i++) {
			Actor actor = list.get(i);
			if( actor instanceof Player )
				player = (Player) actor;
		}
		return player;
	}

}
