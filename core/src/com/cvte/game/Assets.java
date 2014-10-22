package com.cvte.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;



public class Assets {
	private static Assets instance;
	
	private static Texture mTextureBG;
	private static Texture mTextureCell;
	private static Texture mTextureHeart;
	
	private static BitmapFont mBFontScore;
	private static BitmapFont mBFontOT;
	
	public static Assets getInstance() {
		if (instance == null) {
			instance = new Assets();
		}
		return instance;
	}
	
	public static Assets getInstanceValue() {
		return instance;
	}
	
	private Assets() {

	}
	
	public void dispose() {
		mTextureBG = null;
		mTextureCell = null;
		mTextureHeart = null;
		
		if (mBFontScore != null) {
			mBFontScore.dispose();
			mBFontScore = null;
		}
		if (mBFontOT != null) {
			mBFontOT.dispose();
			mBFontOT = null;
		}
		
		instance = null;
	}
	
	public static Texture getTextureBG() {
		if (mTextureBG == null) {
			mTextureBG = new Texture(Gdx.files.internal("bg.png"));
		}
		return mTextureBG;
	}
	
	public static Texture getTextureCell() {
		if (mTextureCell == null) {
			mTextureCell = new Texture(Gdx.files.internal("cell.png"));
		}
		return mTextureCell;
	}
	
	public static Texture getTextureHeart() {
		if (mTextureHeart == null) {
			mTextureHeart = new Texture(Gdx.files.internal("heart.png"));
		}
		return mTextureHeart;
	}
	
	public static BitmapFont getBFontScore() {
		if (mBFontScore == null) {
			mBFontScore = new BitmapFont(Gdx.files.internal("num.fnt"));
		}
		return mBFontScore;
	}
	
	public static BitmapFont getBFontOT() {
		if (mBFontOT == null) {
			mBFontOT= new BitmapFont(Gdx.files.internal("chot.fnt"));
		}
		return mBFontOT;
	}
	
}
