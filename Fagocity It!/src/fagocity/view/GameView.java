package fagocity.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fagocity.controller.ColorBuffController;
import fagocity.controller.GameController;
import fagocity.controller.HUDController;
import fagocity.model.Actor;
import fagocity.model.GameStatus;
import fagocity.model.GameModel;
import fagocity.model.GameStatus.STATUS;
import fagocity.model.ColorBuff;

public class GameView extends JPanel {
	private static final long serialVersionUID = 3856930242116209479L;
	private final int WIDTH = getScreenWidth();
	private final int HEIGHT = getScreenHeight();
	private Display display;
	private BufferStrategy bs;
	private Graphics2D g2d;
	private Graphics g;
	private int minXBounds = 0;
	private int maxXBounds = 5000;
	private int minYBounds = 0;
	private int maxYBounds = 4000;
	private BufferedImage background;
	private GameModel model;
	private MenuView menu;
	private CameraView cameraView;
	private GameController controller;
	private HUDView hudView;
	private String title;
	
	
	public GameView(String title, GameModel model) {
		this.model = model;
		this.title = title;
		
		/* Carrega a imagem de fundo */
		try {
			background = ImageIO.read(new File("src/fagocity/model/assets/sprites/Background.jpg"));
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	
	/* Metodo que renderiza */
	public void render() {
		/* Cria o Buffer Strategy, caso n√£o exista - Triple Buffering */
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g2d = (Graphics2D) g;
		
		/* Limpa a tela */
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		cameraView.CameraBeginning (g2d);
		
		/* Desenha o background */
		int imgWidth = background.getWidth();
		int imgHeight = background.getHeight();
		for(int i = -WIDTH ; i < maxXBounds+WIDTH; i += imgWidth ) {
			for(int j = -HEIGHT; j < maxYBounds+HEIGHT; j += imgHeight) {
				g2d.drawImage(background, i, j, null);
			}
		}
		
		/* Desenha todos os buffs */
		renderBuffs(g);
		
		/* Desenha todos actors */
		renderActors();
		
		/*desenha os limites do mapa*/
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, maxXBounds, maxYBounds);
		
		g.setColor(Color.DARK_GRAY);
		g2d.setStroke(new BasicStroke(3));
		g.drawRect(0, 0, maxXBounds, maxYBounds);

		cameraView.CameraEnding (g2d);
		
		/* Desenha o HUD se estiver no modo jog·vel*/
		if (GameStatus.status == STATUS.Fagocity)
			hudView.render(g);
		else
			menu.render(g);

		
		/* Desenha a barra do Color Buff se ele estiver ativado */
		if ( ColorBuff.getCurrentColorBuff() != null ) {
			g.setColor(Color.green);
			g.fillRect(WIDTH/2 -150, (int)(HEIGHT/1.2), (int)(ColorBuffView.getTimerPercentage() *300), 30);
		}
		
		/* Finaliza os desenhos */
		bs.show();
		g.dispose();
	}
	
	private void renderActors() {
		/* Desenha todos actors */	
		ArrayList <Actor> ActorsList = model.getActorsList();
		for(int i = model.getActorsList().size() - 1; i >= 0 ; i-- ) {
			Actor actor = ActorsList.get(i);
			g.setColor(actor.getColor());
			g.fillOval((int)actor.getX(),(int) actor.getY(),(int) actor.getRadius(),(int) actor.getRadius());
		}
	}
	
	private void renderBuffs(Graphics g) {
		ArrayList<ColorBuff> buffsList = ColorBuffController.getBuffsList();
		int x, y, height, width;
		BufferedImage sprite;
		for(int i = 0; i < buffsList.size(); i++) {
			x = buffsList.get(i).getX();
			y = buffsList.get(i).getY();
			width = buffsList.get(i).getWidth();
			height = buffsList.get(i).getHeight();
			sprite = buffsList.get(i).getSprite();
			g.drawImage(sprite, x , y, (int)(width), (int)(height), null);
		}
	}
	
	public void setController (GameController controller)
	{
		this.controller = controller;
	}
	public void setHUDView(){
		hudView = new HUDView (this, model, controller);
	}
	public void setDisplay(){
		this.display = new Display(title, WIDTH, HEIGHT, controller.getMouseController());
	}
	public void setMenuView(){
		menu = new MenuView (this);
	}
	public void setCameraView(){
		cameraView = new CameraView(controller.getCameraController());
	}
	
	/* Getters e setters */
	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}
	
	public int getScreenWidth() {
	    return Toolkit.getDefaultToolkit().getScreenSize().width;
	}

	public int getScreenHeight() {
	    return Toolkit.getDefaultToolkit().getScreenSize().height;
	}
	
	public HUDView getHUD() {
		return hudView;
	}
	public int getMinXBounds(){
		return minXBounds;
	}
	public int getMaxXBounds(){
		return maxXBounds;
	}
	public int getMinYBounds(){
		return minYBounds;
	}
	public int getMaxYBounds(){
		return maxYBounds;
	}
}


