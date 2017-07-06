package com.ajeffcorrigan.runfoxrun;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.tools.GameAssetManager;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RunFoxRun extends Game {

	// Screen width and height
	public static int gw;
	public static int gh;

	public static final boolean DEBUGON = true;			//Is debug enabled.

	public static final float PTM = 100f;

	public SpriteBatch batch;
	public GameAssetManager gam;
	
	@Override
	public void create () {

		// Set the log level for the application
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		// Set the current screen size.
		gw = Gdx.graphics.getWidth();
		gh = Gdx.graphics.getHeight();
		Gdx.app.debug(this.getClass().getSimpleName(),"Screen dimensions, width: "+gw+", height: "+gh+".");

		// Initialize the sprite batch.
		batch = new SpriteBatch();

		// Initialize the game asset manager.
		gam = new GameAssetManager();

		// Set the PlayScreen screen as starting screen.
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
	
