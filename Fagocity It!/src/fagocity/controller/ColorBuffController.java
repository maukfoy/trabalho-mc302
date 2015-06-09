package fagocity.controller;

import java.util.ArrayList;

import fagocity.model.ColorBuff;

public class ColorBuffController implements Runnable {
	private static long timeToNextBuff = 30000; // em milissegundos
	private static int maximumBuffs = 2;
	
	private static ArrayList<ColorBuff> buffsList = new ArrayList<ColorBuff>();
	
	private SpawnController spawn;
	
	public ColorBuffController (SpawnController spawn){
		this.spawn = spawn;
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
