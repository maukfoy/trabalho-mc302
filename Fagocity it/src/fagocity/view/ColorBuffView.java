package fagocity.view;

import fagocity.model.ColorBuff;

public class ColorBuffView {
	static long timer;
	
	public static void setTimer(long timer) {
		ColorBuffView.timer = timer;
	}
	
	public static double getTimerPercentage() {
		double percentage = (double)(1- (double)(timer)/(double)ColorBuff.getDuration() );
		System.out.println(percentage);
		return percentage;
	}

}
