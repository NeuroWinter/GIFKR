package filter.filters;

import java.awt.Color;

import filter.base.PixelImageFilter;
import kussmaulUtils.ImageTools;

enum HSB {HUE, SATURATION, BRIGHTNESS; 
	
	public int getIdx() {
		switch(this) {
		case HUE: return 0;
		case SATURATION: return 1;
		default: return 2;
		}
	}
}

public class HSBFilter extends PixelImageFilter {

	public HSB mode = HSB.HUE;
	public float level;
	public float value;
	
	@Override
	protected boolean randomControls() {
		return false;
	}
	
	@Override
	public int apply(int color) {
		float[] hsb = Color.RGBtoHSB((color >> 16) & 255, (color >> 8) & 255, color & 255, null);
		
		int idx = mode.getIdx();
		
		hsb[idx] = level*value + (1-level)*hsb[idx];
		
		return Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
	}
}
