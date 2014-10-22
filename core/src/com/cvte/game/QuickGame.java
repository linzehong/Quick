package com.cvte.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.cvte.game.game.GameScreen;
import com.cvte.game.over.OverScreen;

public class QuickGame extends Game {
	private static QuickGame instance;
	
	public final static int SCREEN_GAME = 0;
	public final static int SCREEN_OVER = 1;
	
	private Screen mCurScreen;
	
	public static QuickGame getInstance() {
		if (instance == null) {
			instance = new QuickGame();
		}
		return instance;
	}
	
	public static QuickGame getInstanceValue() {
		return instance;
	}
	
	@Override
	public void create () {
		instance = this;
		
		jumpScreen(SCREEN_GAME);
		
//		SoundManager.getInstance().playBGMusic();
	}

	@Override
	public void dispose() {
		super.dispose();
		
		if (mCurScreen != null) {
			mCurScreen.dispose();
			mCurScreen = null;
		}
		
		SoundManager.getInstance().dispose();
	}
	
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	
	public void jumpScreen(int screenID) {
		if (mCurScreen != null) {
			mCurScreen.dispose();
			mCurScreen = null;
		}
		
		switch (screenID) {
		case SCREEN_GAME:
			mCurScreen = GameScreen.getInstance();
			break;
		case SCREEN_OVER:
			mCurScreen = OverScreen.getInstance();
			break;
		}
		setScreen(mCurScreen);
	}
	
	public void quit() {
		Gdx.app.exit();
	}
	
}
