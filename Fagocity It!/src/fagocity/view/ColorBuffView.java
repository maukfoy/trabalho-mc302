package fagocity.view;

import fagocity.model.ColorBuff;

public class ColorBuffView {
	static long timer;
	
	private static ColorBuffView colorBuff = null;
	
	public static ColorBuffView getInstance() {
		if (colorBuff == null)
			colorBuff = new ColorBuffView();
		return colorBuff;
	}
	private ColorBuffView (){
		
	}
	
	public void setTimer(long timer) {
		ColorBuffView.timer = timer;
	}
	
	public double getTimerPercentage() {
		double percentage = (double)(1- (double)(timer)/(double)ColorBuff.getDuration() );
		System.out.println(percentage);
		return percentage;
	}

}
