package com.cvte.game.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cvte.game.Assets;

public class BG extends Actor {
	
	private TextureRegion mTRBG;
	
	public BG() {
		mTRBG = new TextureRegion(Assets.getTextureBG());
		
		setBounds(0, 0, mTRBG.getRegionWidth(), mTRBG.getRegionHeight());
	}

	public void dispose() {
		mTRBG = null;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		batch.draw(mTRBG, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
}
