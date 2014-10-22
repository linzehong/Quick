package com.cvte.game.game.cell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.cvte.game.ComRandom;
import com.cvte.game.Data;
import com.cvte.game.game.GameScreen;

public class CellManager extends Group {
	private static CellManager instance;

	private Cell[][] mCells;
	
	private int mCellNumInLine;//一行由几个格子组成
	
	private int mCellWidth;//格子的宽
	private int mCellInterval;//格子间的空隙
	
	//格子显示区域
	private final int BOUNDS_WIDTH = 600;
	private final int BOUNDS_HEIGHT = 600;
	private final int BOUNDS_X = (Data.SCREEN_WIDTH - BOUNDS_WIDTH) / 2;
	private final int BOUNDS_Y = 200;
	
	public static CellManager getInstance() {
		if (instance == null) {
			instance = new CellManager();
		}
		return instance;
	}
	
	public static CellManager getInstanceValue() {
		return instance;
	}
	
	private CellManager() {
	
	}
	
	public void dispose() {
		disposeCells();
		
		instance = null;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public void reset() {
		disposeCells();
	}
	
	private void disposeCells() {
		if (mCells != null) {
			int length = mCells.length;
			for (int i = 0; i < length; ++i) {
				for (int j = 0; j < length; ++j) {
					if (mCells[i][j] != null) {
						mCells[i][j].dispose();
						mCells[i][j] = null;
					}
				}
			}
			mCells = null;
		}
	}
	
	public void init() {
		mCellNumInLine = 6;//CellData.getCellNumInLine(Data.mCurLevel);
		calcCellWidthAndInterval();
		
		mCells = new Cell[mCellNumInLine][mCellNumInLine];
		
		Color color0 = ColorGenerate.getInstanceValue().getColorDefault();
		Color color1 = ColorGenerate.getInstanceValue().getColorDiffirent();
		
		int cellX;
		int cellY;
		for (int i = 0; i < mCellNumInLine; ++i) {
			for (int j = 0; j < mCellNumInLine; ++j) {
				cellX = mCellWidth * j + mCellInterval * (j + 1);
				cellY = mCellWidth * (mCellNumInLine - i - 1) + mCellInterval * (mCellNumInLine - i);
				
				mCells[i][j] = new Cell();
				mCells[i][j].initDefault(color0, mCellWidth);
				mCells[i][j].setPosition(cellX, cellY);
				
				addActor(mCells[i][j]);
			}
		}
		
		int targetNum = GameScreen.getInstanceValue().getTargetNum();
		int[] rdmArray = new int[mCellNumInLine * mCellNumInLine];
		ComRandom.getRandomArrayWithNoRepeat(rdmArray, 0, rdmArray.length -1);
		int row;
		int column;
		for (int i = 0; i < targetNum; ++i) {
			row = rdmArray[i] / mCellNumInLine;
			column = rdmArray[i] % mCellNumInLine;
			mCells[row][column].setColor(color1);
		}
		
		setBounds(BOUNDS_X, BOUNDS_Y, BOUNDS_WIDTH, BOUNDS_HEIGHT);
	}
	
	/**
	 * 计算获得格子的宽和格子间隔
	 * 每个格子边上都有一个间隔
	 * (格子宽  * 一行格子数) + (间隔宽 * (一行格子数 + 1)) = 显示区宽
	 * 间隔宽 = 格子宽 * 10%
	 */
	private void calcCellWidthAndInterval() {
		mCellWidth = (int)(BOUNDS_WIDTH / (mCellNumInLine * 1.1 + 0.1));
		mCellInterval = (int)(mCellWidth * 0.1);
		
		System.out.println("mCellWidth = " + mCellWidth + "  " + "mCellInterval = " + mCellInterval);
	}
	
	public int getCellNumInLine() {
		return mCellNumInLine;
	}
	
	public int getCellWidth() {
		return mCellWidth;
	}
	
	/**
	 * 所有异类已经消除掉了
	 * @return
	 */
	public boolean isAllDifferentDead() {
		boolean isAllRight = true;
		for (int i = 0; i < mCellNumInLine; ++i) {
			for (int j = 0; j < mCellNumInLine; ++j) {
				if (ColorGenerate.getInstanceValue().equalDiffirentColor(mCells[i][j].getColor())) {
					if (!mCells[i][j].isDead()) {
						isAllRight = false;
						break;
					}
				}
			}
		}
		return isAllRight;
	}
	
	public boolean isAllDead() {
		boolean isAllDead = true;
		for (int i = 0; i < mCellNumInLine; ++i) {
			for (int j = 0; j < mCellNumInLine; ++j) {
				if (!mCells[i][j].isDead()) {
					isAllDead = false;
					break;
				}
			}
		}
		return isAllDead;
	}
	
	public void bombToNextLevel() {
		for (int i = 0; i < mCellNumInLine; ++i) {
			for (int j = 0; j < mCellNumInLine; ++j) {
				if (!mCells[i][j].isDead()) {
					mCells[i][j].forceBombToDead();
				}
			}
		}
	}
	
}
