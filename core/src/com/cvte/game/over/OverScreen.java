package com.cvte.game.over;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.cvte.game.Assets;
import com.cvte.game.Data;
import com.cvte.game.QuickGame;
import com.cvte.game.game.BG;

public class OverScreen implements Screen {
	private static OverScreen instance;
	
	private Stage mStage;
	private BG mBG;
	
	private Label mLbOT;
	private Label mLbRestart;
	private Label mLbQuit;
	
	public static OverScreen getInstance() {
		if (instance == null) {
			instance = new OverScreen();
		}
		return instance;
	}
	
	public static OverScreen getInstanceValue() {
		return instance;
	}
	
	private OverScreen() {
		
	}
	
	@Override
	public void dispose() {
		if (mStage != null) {
			mStage.dispose();
			mStage = null;
		}
		if (mBG != null) {
			mBG.dispose();
			mBG = null;
		}
		
		if (mLbOT != null) {
			mLbOT = null;
		}
		if (mLbRestart != null){
			mLbRestart = null;
		}
		if (mLbQuit != null){
			mLbQuit = null;
		}
		
		instance = null;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		mStage.act(delta);
		mStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		mStage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		mStage = new Stage(new ScalingViewport(Scaling.stretch, Data.SCREEN_WIDTH, Data.SCREEN_HEIGHT));
		
		mBG = new BG();
		
		BitmapFont bfont = Assets.getBFontOT();
		LabelStyle labelStyle = new LabelStyle(bfont, bfont.getColor());
		mLbOT = new Label("0", labelStyle);
		displayOT();
		
		mLbRestart = new Label("Restart", labelStyle);
		mLbRestart.setPosition(30, 50);
		mLbRestart.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				restart();
			}
		});
		
		mLbQuit = new Label("Quit", labelStyle);
		mLbQuit.setPosition(Data.SCREEN_WIDTH - mLbQuit.getTextBounds().width - 30, 50);
		mLbQuit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				quit();
			}
		});
		
		mStage.addActor(mBG);
		mStage.addActor(mLbOT);
		mStage.addActor(mLbRestart);
		mStage.addActor(mLbQuit);
		
		Gdx.input.setInputProcessor(mStage);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	private void displayOT() {
		mLbOT.setText("恭喜您通过了" + Data.mCurLevel + "层考验!恭喜您终于成为了" + Data.getUserName(Data.mCurLevel) + "!");

		mLbOT.setBounds(50, 400, Data.SCREEN_WIDTH - 100, 450);
		mLbOT.setWrap(true);
	}
	
	private void restart() {
		QuickGame.getInstance().jumpScreen(QuickGame.SCREEN_GAME);
	}
	
	private void quit() {
		QuickGame.getInstance().quit();
	}

}
