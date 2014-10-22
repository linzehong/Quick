package com.cvte.game.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.cvte.game.Assets;
import com.cvte.game.Data;

public class Score {

	private Label mLbScore;
	
	public Score() {
		BitmapFont bfont = Assets.getBFontScore();
		LabelStyle labelStyle = new LabelStyle(bfont, bfont.getColor());
		mLbScore = new Label("0", labelStyle);
	}
	
	public void dispose() {
		if (mLbScore != null) {
			mLbScore.remove();
			mLbScore.clear();
			mLbScore = null;
		}
	}
	
	public void displayScore(int score) {
		mLbScore.setText(score + "");
		
//		mLbScore.setX((Data.SCREEN_WIDTH - mLbScore.getTextBounds().width) / 2);
		mLbScore.setX(Data.SCREEN_WIDTH - mLbScore.getTextBounds().width);
		mLbScore.setY(Data.SCREEN_HEIGHT - 35 - mLbScore.getHeight());
	}
	
	public Label getLabelScore() {
		return mLbScore;
	}
	
}
