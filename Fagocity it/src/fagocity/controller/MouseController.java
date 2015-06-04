package fagocity.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import fagocity.model.Actor;

 public class MouseController extends MouseAdapter implements MouseMotionListener {
	ArrayList<Actor> ActorsList;
	private static double mousePosX = 0;
	private static double mousePosY = 0;
	
	public void mouseMoved(MouseEvent e) {
		mousePosX = e.getX();
		mousePosY = e.getY();
	}
	
	public static double getMouseX() {
		return mousePosX;
	}
	
	public static double getMouseY() {
		return mousePosY;
	}
}
