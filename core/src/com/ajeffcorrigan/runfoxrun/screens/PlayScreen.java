package com.ajeffcorrigan.runfoxrun.screens;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.ajeffcorrigan.runfoxrun.tools.Fox;
import com.ajeffcorrigan.runfoxrun.tools.jAssets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {

	private RunFoxRun game;
	
    //basic playscreen variables
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    
    //Fox player
    private Fox player;
		
	public PlayScreen(RunFoxRun game) {
		this.game = game;
		
		gamecam = new OrthographicCamera();
		
        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(RunFoxRun.gw, RunFoxRun.gh, gamecam);
        
        //initially set our gamcam to be centered correctly at the start of of map
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight(), 0);
        
        player = new Fox();
        
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
        update(delta);
        		
		//Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
        
        game.batch.begin();
        player.draw(game.batch);
        game.batch.draw(jAssets.getTexture("grassMid"), 0, 0);
        game.batch.end();
        
        
		
	}

	private void update(float delta) {
		handleInput(delta);
		
		player.update(delta);
		
        //update our gamecam with correct coordinates after changes
        gamecam.update();		
		
	}

	private void handleInput(float delta) {
		if(Gdx.input.justTouched()) {
			Gdx.app.log("Game cam position X", Float.toString(gamecam.position.x));
		}
		
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


	
	
