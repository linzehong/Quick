package com.cvte.game.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.cvte.game.Assets;
import com.cvte.game.Data;


public class Time {
	private static Time instance;
	
	private Label mLbTime;
	
	private Timer mTimer;
	private TimeTask mTask;

	public static Time getInstance() {
		if (instance == null) {
			instance = new Time();
		}
		return instance;
	}
	
	public static Time getInstanceValue() {
		return instance;
	}
	
	private Time() {
		BitmapFont bfont = Assets.getBFontScore();
		LabelStyle labelStyle = new LabelStyle(bfont, bfont.getColor());
		mLbTime = new Label("0", labelStyle);
		displayTime(TimeTask.MAX_SECOND);
		
		mTask = new TimeTask(this);
		
		mTimer = Timer.instance();
		mTimer.scheduleTask(mTask, 1, 1, 60);
	}
	
	public void dispose() {
		if (mLbTime != null) {
			mLbTime.remove();
			mLbTime.clear();
			mLbTime = null;
		}
		
		if (mTimer == null) {
			mTimer.clear();
			mTimer = null;
		}
		mTask = null;
	}

	public void displayTime(int second) {
		mLbTime.setText(second + "");
		
		mLbTime.setX(0);
		mLbTime.setY(Data.SCREEN_HEIGHT - 35 - mLbTime.getHeight());
	}
	
	public Label getLabelTime() {
		return mLbTime;
	}
	
	
	private class TimeTask extends Task {
		
		private Time mParent;
		
		private int mRemainSecond;
		
		public final static int MAX_SECOND = 60;
		
		public TimeTask(Time time) {
			mParent = time;
			
			mRemainSecond = MAX_SECOND;
		}

		@Override
		public void run() {
			--mRemainSecond;
			mParent.displayTime(mRemainSecond);
			if (mRemainSecond <= 0) {
				cancel();
			}
		}
		
	}

}
