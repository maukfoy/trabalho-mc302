package tutorial.realtutsgml.wave;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Objeto {
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	Handler handler;
	
	public abstract Rectangle getBounds();//retorna todos os pontos ocupados pelo objeto
	public abstract void tick();
	public abstract void render(Graphics g);
	
	
	/**************/
	/* CONSTRUTOR */
	/**************/
	public Objeto(float x, float y, ID id, Handler handler)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}
	
	/*********************/
	/* GETTERS E SETTERS */
	/*********************/
	public void setID(ID id)
	{
		this.id = id;
	}
	
	public ID getID() 
	{
		return this.id;
	}
	
	public void setX(int x) 
	{
		this.x = x;
	}
	
	public float getX() 
	{
		return this.x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public float getY()
	{
		return this.y;
	}
	
	public void setVelX(int velX)
	{
		this.velX = velX;
	}
	
	public float getVelX()
	{
		return this.velX;
	}
	
	public void setVelY(int velY) 
	{
		this.velY = velY;
	}
	
	public float getVelY() 
	{
		return this.velY;
	}
	
}

