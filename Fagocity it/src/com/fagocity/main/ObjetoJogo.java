package com.fagocity.main;

import java.awt.Graphics;

public abstract class ObjetoJogo {
	protected ID id;
	protected int velX, velY;
	
	/**************/
	/* CONSTRUTOR */
	/**************/
	public ObjetoJogo(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	/*********************/
	/* GETTERS E SETTERS */
	/*********************/
	public void setID(ID id) {
		this.id = id;
	}
	
	public ID getID() {
		return this.id;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public int getVelX() {
		return this.velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getVelY() {
		return this.velY;
	}
	
}
