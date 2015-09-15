package com.ajeffcorrigan.runfoxrun;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.tools.GameAssetManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RunFoxRun extends Game {

	public static int gw;
	public static int gh;
	public static final boolean DEBUGON = true;			//Is debug enabled.
	public static final float PPM = 100;

	public SpriteBatch batch;
	public GameAssetManager gam;
	
	@Override
	public void create () {
		
		gw = Gdx.graphics.getWidth();
		gh = Gdx.graphics.getHeight();
		
		batch = new SpriteBatch();
		gam = new GameAssetManager();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		batch.dispose();
	}
		
}
	
