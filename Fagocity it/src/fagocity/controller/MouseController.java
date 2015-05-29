package fagocity.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import fagocity.model.GameModel;
import fagocity.model.Player;
import fagocity.model.interfaces.IActor;

public class MouseController extends MouseAdapter implements MouseMotionListener {
	ArrayList<IActor> ActorsList;
	
	public void mouseMoved(MouseEvent e) {
		ActorsList = GameModel.getActorsList();
		/* Procura o player na lista, quando o encontra atualiza suas coordenadas */
		for(int i = 0; i < ActorsList.size(); i++) {
			IActor obj = ActorsList.get(i);
			if(obj  instanceof Player) {
				((Player) obj).setX(e.getX());
				((Player) obj).setY(e.getY());
			}
		}
	}
}
