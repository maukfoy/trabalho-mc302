package fagocity.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import fagocity.model.interfaces.IActor;

 public class MouseController extends MouseAdapter implements MouseMotionListener {
	ArrayList<IActor> ActorsList;
	private static int mousePosX = 0;
	private static int mousePosY = 0;
	
	public void mouseMoved(MouseEvent e) {
		mousePosX = e.getX();
		mousePosY = e.getY();
	}
	
	public static int getMouseX() {
		return mousePosX;
	}
	
	public static int getMouseY() {
		return mousePosY;
	}
}