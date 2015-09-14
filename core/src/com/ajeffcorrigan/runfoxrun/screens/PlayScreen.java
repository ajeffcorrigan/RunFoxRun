package com.ajeffcorrigan.runfoxrun.screens;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.ajeffcorrigan.runfoxrun.sprites.ScreenTile;
import com.ajeffcorrigan.runfoxrun.sprites.foxActor;
import com.ajeffcorrigan.runfoxrun.sprites.foxActor.State;
import com.ajeffcorrigan.runfoxrun.tools.GridRow;
import com.ajeffcorrigan.runfoxrun.tools.ScreenGrid;
import com.ajeffcorrigan.runfoxrun.tools.WorldPhysicsContainer;
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

public class PlayScreen implements Screen {

	public static final float VELOCITY = 100;					//Game speed.
	public static final float JUMP_IMPULSE = 360;			//Jump impulse.
	public static final float GRAVITY = -10;					//Gravity force.
	
	private RunFoxRun game;
	
    //basic play screen variables
    public OrthographicCamera gamecam;
    private Viewport gamePort;
    private ShapeRenderer shaperenderer;
    private ScreenGrid grid;
    private WorldPhysicsContainer wpc;
    
    //Fox player
    private foxActor fox;
		
	public PlayScreen(RunFoxRun game) {
		this.game = game;
		
		gamecam = new OrthographicCamera();
		gamecam.setToOrtho(false);
		
        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(RunFoxRun.gw, RunFoxRun.gh, gamecam);
        
        //initially set our game camera to be centered correctly at the start of of map
        gamecam.position.set(gamePort.getWorldWidth() / 2 , gamePort.getWorldHeight() / 2, 0);
        
        fox = new foxActor(this);
        
        grid = new ScreenGrid(5,15,new Vector2(0,0), 70);
        
        for(ScreenTile st : grid.rows.first().tiles) {
        	st.setRigidSprite(new Sprite(jAssets.getTexture("grassMid")));
        }
        ScreenTile st = grid.rows.get(1).tiles.get(7);
        st.setRigidSprite(new Sprite(jAssets.getTexture("grassMid")));
        
        wpc = new WorldPhysicsContainer();
        
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
        grid.draw(game.batch);
        fox.draw(game.batch);
        game.batch.end();
        
        //shaperenderer.setProjectionMatrix(gamecam.combined);
        //shaperenderer.begin(ShapeType.Line);
        //shaperenderer.setColor(Color.BLACK);
        ////grid.drawBounds(shaperenderer);
        //shaperenderer.circle(fox.headcircle.x, fox.headcircle.y, fox.headcircle.radius);
        //shaperenderer.rect(fox.textureBound);
        //shaperenderer.end();
	}

	private void update(float delta) {
		
		boolean onGround = false;
		
		handleInput(delta);
		
		gamecam.translate(new Vector2(delta * VELOCITY,0));
		
		grid.update(delta, this);
		
		fox.update(delta);
		
		for(GridRow gr : grid.rows) {
			for(ScreenTile st : gr.tiles) {
				if(fox.getBoundingRectangle().overlaps(st.tileBounds) && st.isRigid) {
					fox.setPosition(fox.getX(), st.tilePosition.y + st.tileSize);
					fox.setState(State.RUNNING);
					onGround = true;
				}
			}
		}
		if(!onGround && fox.currentState == State.RUNNING) { fox.setState(State.FALLING); }
		
		
        //update our game camera with correct coordinates after changes
        gamecam.update();		
		
	}

	private void handleInput(float delta) {
		if(Gdx.input.justTouched() && fox.currentState == State.RUNNING) {	
			fox.setState(State.JUMPINGUP);
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


	
	
