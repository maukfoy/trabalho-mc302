package fagocity.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import fagocity.model.Actor;

 public class MouseController extends MouseAdapter implements MouseMotionListener {
	ArrayList<Actor> ActorsList;
	private static int mousePosX = 0;
	private static int mousePosY = 0;
	private static int mX = 0;
	private static int mY = 0;
	
	public void mouseMoved(MouseEvent e) {
		mousePosX = e.getX();
		mousePosY = e.getY();
	}
	
	public void mousePressed (MouseEvent e)
	{
		mX = e.getX ();
		mY = e.getY ();
	}
	
	public static int getMouseX() {
		return mousePosX;
	}
	
	public static int getMouseY() {
		return mousePosY;
	}
	
	public static int getClickX() {
		return mX;
	}
	
	public static int getClickY() {
		return mY;
	}
}
