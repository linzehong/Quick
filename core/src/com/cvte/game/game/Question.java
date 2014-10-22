package com.cvte.game.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.cvte.game.Data;
import com.cvte.game.game.cell.Cell;
import com.cvte.game.game.cell.ColorGenerate;

public class Question extends Group {
	private static Question instance;
	
	private Cell mCell;
	
	private final int QUES_WIDTH = 80;
	
	private final float QUES_X = 100;
	private final float QUES_Y = 400;
	
	public static Question getInstance() {
		if (instance == null) {
			instance = new Question();
		}
		return instance;
	}
	
	public static Question getInstanceValue() {
		return instance;
	}
	
	private Question(){
		
	}
	
	public void dispose() {
		if (mCell != null) {
			mCell.dispose();
			mCell = null;
		}
		
		instance = null;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public void init() {
		if (mCell == null) {
			mCell = new Cell();
		}
		mCell.initDefault(ColorGenerate.getInstanceValue().getColorDiffirent(), QUES_WIDTH);
		mCell.setPosition((Data.SCREEN_WIDTH>>1) - (QUES_WIDTH>>1), Data.SCREEN_HEIGHT - 120);
		
		addActor(mCell);
		
		setBounds(0, 0, mCell.getWidth(), mCell.getHeight());
	}
	
	public void reset() {
		if (mCell != null) {
			mCell.dispose();
			mCell = null;
		}
	}
	
}
