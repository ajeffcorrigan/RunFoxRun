package com.ajeffcorrigan.runfoxrun;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.tools.GameAssetManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RunFoxRun extends Game {

	public static final int G_WIDTH = 400;
	public static final int G_HEIGHT = 208;
	public static final float PPM = 100;
	public static final boolean DEBUGON = true;			//Is debug enabled.
	
	public SpriteBatch batch;
	public GameAssetManager gam;
	
	@Override
	public void create () {
	
		batch = new SpriteBatch();
		gam = new GameAssetManager();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
		
}
	
