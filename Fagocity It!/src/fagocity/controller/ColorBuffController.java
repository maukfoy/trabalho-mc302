package fagocity.controller;

import java.util.ArrayList;

import fagocity.controller.Interfaces.ControllerSingleton;
import fagocity.model.ColorBuff;

public class ColorBuffController implements Runnable, ControllerSingleton{
	private static long timeToNextBuff = 30000; // em milissegundos
	private static int maximumBuffs = 2;
	
	private static ArrayList<ColorBuff> buffsList = new ArrayList<ColorBuff>();
	
	private SpawnController spawn;
	
	private static ColorBuffController colorBuff = null;
	
	public static ColorBuffController getInstance() {
		if (colorBuff == null)
			colorBuff = new ColorBuffController();
		return colorBuff;
	}
	
	private ColorBuffController ( ){
		this.spawn = SpawnController.getInstance();
	}
	
	/* Gera um buff */
	public void generateBuff() {
		if( buffsList.size() < maximumBuffs ) {
			int[] coordinates = spawn.generateSpawnCoordinates();
			
			ColorBuff colorBuff = new ColorBuff(coordinates[0], coordinates[1]);
			buffsList.add(colorBuff);
		}
	}
	
	/* Retorna a lista de buffs */
	public static ArrayList<ColorBuff> getBuffsList() {
		return buffsList;
	}

	@Override
	public void run() {
		
		
		while(true) {
			/* Gera o buff */
			generateBuff();
			
			/* Faz os intervalos entre os buffs */
			try {
				Thread.sleep(timeToNextBuff);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
