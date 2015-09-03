package com.ajeffcorrigan.runfoxrun.screens;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.ajeffcorrigan.runfoxrun.scenes.Hud;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {
	
	private RunFoxRun game;	
	
	//play screen variables
	private OrthographicCamera gamecam;
	private Viewport gameport;
	private Hud hud;
	
	
	public PlayScreen(RunFoxRun game) {
		this.game = game;
		
		gamecam = new OrthographicCamera();
		
		gameport = new FitViewport(RunFoxRun.G_WIDTH / RunFoxRun.PPM, RunFoxRun.G_HEIGHT / RunFoxRun.PPM, gamecam);
		
		hud = new Hud(game.batch);
		
		
	}
	
	public void update(float dt) {
        //handle user input first
        handleInput(dt);
        
        //update our game cam with correct coordinates after changes
        gamecam.update();
        
	}

	private void handleInput(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
