package fagocity.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class MouseController extends MouseAdapter {
	
	public void mouseClicked(MouseEvent e) {
		// escrever aqui o que deve acontecer quando o mouse for clicado.
	}
}



/* FAZ O JOGADOR SEGUIR O MOUSE. IMPLEMENTAR NO JOGO CASO NOSSO JOGO VÁ SEGUIR ESSE SISTEMA.
 * NO MOMENTO EU DEIXEI DESATIVADO - VITAO
 */
/* public class MouseController extends MouseAdapter implements MouseMotionListener {
	ArrayList<IActor> ActorsList;
	
	public void mouseMoved(MouseEvent e) {
		ActorsList = GameModel.getActorsList();
		// Procura o player na lista, quando o encontra atualiza suas coordenadas
		for(int i = 0; i < ActorsList.size(); i++) {
			IActor obj = ActorsList.get(i);
			if(obj  instanceof Player) {
				((Player) obj).setX(e.getX());
				((Player) obj).setY(e.getY());
			}
		}
	}
} */