package com.cvte.game.game.cell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cvte.game.Assets;
import com.cvte.game.game.GameScreen;

public class Cell extends Actor {
	
	private Sprite mSpCell;

	private int mCellWidth;
	
	private int mState;
	private final int STATE_NONE = 0;
	private final int STATE_ALIVE = 1;//正常显示
	private final int STATE_BOMB_TO_DEAD = 2;//死掉前的爆炸
	private final int STATE_DEAD = 3;//被点掉了的
	
	public Cell() {
		mState = STATE_NONE;
	}
	
	public void dispose() {
		mSpCell = null;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		if (mSpCell != null) {
			mSpCell.setColor(getColor());
			mSpCell.draw(batch);
		}
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);

		if (mSpCell != null) {
			mSpCell.setPosition(x, y);
		}
	}
	
	@Override
	public void setScale(float scaleX, float scaleY) {
		super.setScale(scaleX, scaleY);

		if (mSpCell != null) {
			mSpCell.setScale(scaleX, scaleY);
		}
	}

	@Override
	public void setColor(Color color) {
		super.setColor(color);
		
		if (mSpCell != null) {
			mSpCell.setColor(color);
		}
	}

	public void initDefault(Color color,int cellWidth) {
		mCellWidth = cellWidth;
		
		mSpCell = new Sprite(Assets.getTextureCell(), mCellWidth, mCellWidth);
		
		setColor(color);
		getColor().a = 0;
		mSpCell.setColor(getColor());
		setScale(0, 0);
		
		actionBorn();
		
		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				clickeCell(event, x, y);
			}
		});
				
		setBounds(0, 0, mCellWidth, mCellWidth);
		
		mState = STATE_ALIVE;
	}
	
	private void clickeCell(InputEvent event, float x, float y) {
		GameScreen.getInstanceValue().touchCell(this);
	}
	
	/**
	 * 正常被点掉的爆炸
	 */
	public void setBombToDead() {
		mState = STATE_BOMB_TO_DEAD;
		
		actionDead(false);
	}
	
	/**
	 * 所有要求的都点掉后，强制剩下的都爆炸死掉，这里消失前有添加延时
	 */
	public void forceBombToDead() {
		mState = STATE_BOMB_TO_DEAD;
		
		actionDead(true);
	}
	
	private void setToDead() {
		mState = STATE_DEAD;
		
		setVisible(false);
		clearListeners();
	}
	
	public boolean isDead() {
		return (mState == STATE_DEAD);
	}
	
	public boolean isAlive() {
		return (mState == STATE_ALIVE);
	}
	
	/**
	 * 出来时动作效果
	 */
	private void actionBorn() {
		AlphaAction alphaAction1 = Actions.alpha(0.1f, 0.3f);
		AlphaAction alphaAction2 = Actions.alpha(0.7f, 0.3f);
		SequenceAction sequenceAction1 = Actions.sequence(alphaAction1, alphaAction2);
		ScaleToAction scaleToAction2 = Actions.scaleTo(1.1f, 1.1f, 0.3f);
		ScaleToAction scaleToAction3 = Actions.scaleTo(1f, 1f, 0.3f);
		SequenceAction sequenceAction2 = Actions.sequence(scaleToAction2, scaleToAction3);
		DelayAction delayAction = Actions.delay(MathUtils.random(0.5f));
		ParallelAction parallelAction = Actions.parallel(sequenceAction1, sequenceAction2);
		SequenceAction sequenceAction = Actions.sequence(delayAction, parallelAction);
		addAction(sequenceAction);
	}
	
	/**
	 * 死亡时动作效果
	 */
	private void actionDead(boolean isNeedDelay) {
		AlphaAction alphaAction1 = Actions.alpha(0.1f, 0.1f);
		AlphaAction alphaAction2 = Actions.alpha(0.0f, 0.1f);
		SequenceAction sequenceAction1 = Actions.sequence(alphaAction1, alphaAction2);
		ScaleToAction scaleToAction2 = Actions.scaleTo(4f, 4f, 0.1f);
		ScaleToAction scaleToAction3 = Actions.scaleTo(8f, 8f, 0.1f);
		SequenceAction sequenceAction2 = Actions.sequence(scaleToAction2, scaleToAction3);
		ParallelAction parallelAction = Actions.parallel(sequenceAction1, sequenceAction2);
		RunnableAction runnableAction = Actions.run(new Runnable() {
			@Override
			public void run() {
				setToDead();
				GameScreen.getInstanceValue().cellDeadEnd();
			}
		});
		
		SequenceAction sequenceAction3;
		if (isNeedDelay) {
			DelayAction delayAction = Actions.delay(MathUtils.random(0.5f));
			sequenceAction3 = Actions.sequence(delayAction, parallelAction, runnableAction);
		}
		else {
			sequenceAction3 = Actions.sequence(parallelAction, runnableAction);
		}
		addAction(sequenceAction3);
	}
	
}
