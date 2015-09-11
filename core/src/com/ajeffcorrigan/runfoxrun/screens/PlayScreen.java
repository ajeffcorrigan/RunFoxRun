package com.ajeffcorrigan.runfoxrun.screens;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.ajeffcorrigan.runfoxrun.sprites.GroundTile;
import com.ajeffcorrigan.runfoxrun.tools.ScreenGrid;
import com.ajeffcorrigan.runfoxrun.tools.jActor;
import com.ajeffcorrigan.runfoxrun.tools.jActor.State;
import com.ajeffcorrigan.runfoxrun.tools.jAssets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState;

public class PlayScreen implements Screen {

	public static final float VELOCITY = 78;
	
	private RunFoxRun game;
	
    //basic play screen variables
    public OrthographicCamera gamecam;
    private Viewport gamePort;
    private ShapeRenderer shaperenderer;
    
    private ScreenGrid grid;
    
    //Fox player
    private jActor fox;
		
	public PlayScreen(RunFoxRun game) {
		this.game = game;
		
		gamecam = new OrthographicCamera();
		gamecam.setToOrtho(false);
		
        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(RunFoxRun.gw, RunFoxRun.gh, gamecam);
        
        //initially set our game camera to be centered correctly at the start of of map
        gamecam.position.set(gamePort.getWorldWidth() / 2 , gamePort.getWorldHeight() / 2, 0);
        
        fox = new jActor(jAssets.getTexture("foxstill"));
              
        grid = new ScreenGrid(5,10,new Vector2(0,0), 70);
        
        shaperenderer = new ShapeRenderer();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
        update(delta);
        		
		//Clear the game screen with Black
        Gdx.gl.glClearColor(.5f, .8f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
        
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        fox.draw(game.batch);
        game.batch.end();
        
        shaperenderer.setProjectionMatrix(gamecam.combined);
        shaperenderer.begin(ShapeType.Line);
        shaperenderer.setColor(Color.BLACK);
        grid.drawBounds(shaperenderer);
        //shaperenderer.circle(player.headcircle.x, player.headcircle.y, player.headcircle.radius);
        //shaperenderer.rect(fox.textureBound);
        shaperenderer.end();
	}

	private void update(float delta) {
		handleInput(delta);
		
		gamecam.translate(new Vector2(delta * VELOCITY,0));
		
		//grid.update(delta, this);
		
		fox.update(delta);
		
		//for(GroundTile gt : grid.screengrid) {
		//	if (fox.actorBound.overlaps(gt.getBoundingRectangle()) && gt.isRigid) {
		//		fox.actorPosition.y = gt.getY() + gt.getHeight();
		//		fox.setState(State.RUNNING);
		//	}
		//}
		
        //update our game camera with correct coordinates after changes
        gamecam.update();		
		
	}

	private void handleInput(float delta) {
		if(Gdx.input.justTouched() && fox.currentState == State.RUNNING) {
			Gdx.app.log("Game cam position X", Float.toString(gamecam.position.x));
			Gdx.app.log("Number of tiles",Integer.toString(RunFoxRun.gw / 70));
			Gdx.app.log("PlayScreen", Float.toString(grid.rows.items[3].tiles.items[2].tilePosition.x));
			
			fox.setState(State.JUMPINGUP);
			fox.actorVelocity.y = fox.ACTOR_JUMP_IMPULSE;
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


	
	
