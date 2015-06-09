package fagocity.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import fagocity.model.Actor;

 public class MouseController extends MouseAdapter implements MouseMotionListener {
	ArrayList<Actor> ActorsList;
	private  int mousePosX = 0;
	private  int mousePosY = 0;
	private  int mX = 0;
	private  int mY = 0;
	
	public void mouseMoved(MouseEvent e) {
		mousePosX = e.getX();
		mousePosY = e.getY();
	}
	
	public void mousePressed (MouseEvent e)
	{
		mX = e.getX ();
		mY = e.getY ();
	}
	
	public int getMouseX() {
		return mousePosX;
	}
	
	public int getMouseY() {
		return mousePosY;
	}
	
	public int getClickX() {
		return mX;
	}
	
	public int getClickY() {
		return mY;
	}
}
