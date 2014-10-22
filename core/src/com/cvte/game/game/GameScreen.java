package com.cvte.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cvte.game.Data;
import com.cvte.game.game.cell.Cell;
import com.cvte.game.game.cell.CellManager;
import com.cvte.game.game.cell.ColorGenerate;

public class GameScreen implements Screen {
	private static GameScreen instance;
	
	private Stage mStage;
	private BG mBG;
	private ColorGenerate mColorGenerate;
	private CellManager mCellManager;
	private Question mQuestion;
	private Score mScore;
	private Time mTime;
	
	private int mTargetNum;//目标数量
	
	private int mState;
	public final static int STATE_PLAYING = 0;
	public final static int STATE_BOMB_TO_NEXT_LEVEL = 1;
	
	public static GameScreen getInstance() {
		if (instance == null) {
			instance = new GameScreen();
		}
		return instance;
	}
	
	public static GameScreen getInstanceValue() {
		return instance;
	}
	
	private GameScreen() {
		
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
		if (mColorGenerate != null) {
			mColorGenerate.dispose();
			mColorGenerate = null;
		}
		if (mCellManager != null) {
			mCellManager.dispose();
			mCellManager = null;
		}
		if (mQuestion != null) {
			mQuestion.dispose();
			mQuestion = null;
		}
		if (mScore != null) {
			mScore.dispose();
			mScore = null;
		}
		if (mTime != null) {
			mTime.dispose();
			mTime = null;
		}
		
		instance = null;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 1, 1, 1);
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
		Data.mCurLevel = 0;
		Data.mCurScore = 0;
		
		initTargetNum();
		mColorGenerate = ColorGenerate.getInstance();
		
		mStage = new Stage(new FitViewport(Data.SCREEN_WIDTH, Data.SCREEN_HEIGHT));
		
		mBG = new BG();
		
		mCellManager = CellManager.getInstance();
		mCellManager.init();
		
		mQuestion = Question.getInstance();
		mQuestion.init();
		
		mScore = new Score();
		mScore.displayScore(Data.mCurScore);
		
		mTime = Time.getInstance();
		
		mStage.addActor(mBG);
		mStage.addActor(mCellManager);
		mStage.addActor(mQuestion);
		mStage.addActor(mScore.getLabelScore());
		mStage.addActor(mTime.getLabelTime());
		
		Gdx.input.setInputProcessor(mStage);
		
		mState = STATE_PLAYING;
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
	
	private void initTargetNum() {
		mTargetNum = MathUtils.random(1, 8);
	}
	
	public int getTargetNum() {
		return mTargetNum;
	}
	
	public void touchCell(Cell cell) {
		if (mColorGenerate.equalDiffirentColor(cell.getColor())) {
			cell.setBombToDead();
			
			++Data.mCurScore;
			mScore.displayScore(Data.mCurScore);
		}
	}
	
	public void cellDeadEnd() {
		if (mState == STATE_PLAYING) {
			if (mCellManager.isAllDifferentDead()) {
				mState = STATE_BOMB_TO_NEXT_LEVEL;
				mCellManager.bombToNextLevel();
			}
		}
		else if (mState == STATE_BOMB_TO_NEXT_LEVEL) {
			if (mCellManager.isAllDead()) {
				setToNextLevel();
			}
		}
	}
	
	private void setToNextLevel() {
		++Data.mCurLevel;

		mColorGenerate.initColors();
		initTargetNum();

		mCellManager.reset();
		mCellManager.init();

		mQuestion.reset();
		mQuestion.init();
		
		mState = STATE_PLAYING;
	}
	
	private void restart() {
		Data.mCurLevel = 0;
		Data.mCurScore = 0;
		
		mCellManager.reset();
		mCellManager.init();
		
		mScore.displayScore(Data.mCurScore);
	}

}
