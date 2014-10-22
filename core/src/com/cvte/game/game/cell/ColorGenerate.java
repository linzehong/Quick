package com.cvte.game.game.cell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public class ColorGenerate {
	private static ColorGenerate instance;
	
	private Color mColorDefault;
	private Color mColorDiffirent;
	
	//设定好的颜色
	private final Color[][] DEMO_COLORS = {
		{new Color(0xFFC800FF), new Color(0x0016FFFF)},
		{new Color(0xFF9800FF), new Color(0x0095FFFF)},
		{new Color(0xFF6F00FF), new Color(0x00E1FFFF)},
		{new Color(0xFF4200FF), new Color(0x00FFC7FF)},
		{new Color(0xFF0017FF), new Color(0x00FF3BFF)},
		{new Color(0xFF009BFF), new Color(0x13FF00FF)},
		{new Color(0xB700FFFF), new Color(0xB5FF00FF)},
		{new Color(0x5F00FFFF), new Color(0xFFF400FF)},
		{new Color(0x0004FFFF), new Color(0xFFCF00FF)},
		{new Color(0xFF9E42FF), new Color(0x42D7FFFF)},
		{new Color(0xFF8059FF), new Color(0x59FFD3FF)},
		{new Color(0xC04FFFFF), new Color(0xDCFF4FFF)},
		{new Color(0x5850FFFF), new Color(0xFFE250FF)},
		{new Color(0x42FFB5FF), new Color(0xFF6142FF)},
		{new Color(0x03FF2CFF), new Color(0xFF0337FF)},
		{new Color(0x99FF4DFF), new Color(0xF44DFFFF)},
		{new Color(0xFFF41AFF), new Color(0x6B1AFFFF)},
		{new Color(0xFF3000FF), new Color(0x00FFA5FF)},
		{new Color(0xFF0073FF), new Color(0x00FF07FF)},
		{new Color(0x7F00FFFF), new Color(0xFDFF00FF)},
	};
	
	public static ColorGenerate getInstance() {
		if (instance == null) {
			instance = new ColorGenerate();
		}
		return instance;
	}
	
	public static ColorGenerate getInstanceValue() {
		return instance;
	}
	
	private ColorGenerate() {
		initColors();
	}
	
	public void dispose() {
		instance = null;
	}
	
	public void initColors() {
		int index = MathUtils.random(DEMO_COLORS.length - 1);
		mColorDefault = DEMO_COLORS[index][0];
		mColorDiffirent = DEMO_COLORS[index][1];
	}
	
	public Color getColorDefault() {
		return mColorDefault;
	}
	
	public Color getColorDiffirent() {
		return mColorDiffirent;
	}
	
	/**
	 * 是否与异类颜色相同，不包括透明度
	 * @param color
	 * @return
	 */
	public boolean equalDiffirentColor(Color color) {
		boolean isDiffirent = false;
		if ((color.r == mColorDiffirent.r) && (color.g == mColorDiffirent.g) && (color.b == mColorDiffirent.b)) {
			isDiffirent = true;
		}
		return isDiffirent;
	}
	
}
