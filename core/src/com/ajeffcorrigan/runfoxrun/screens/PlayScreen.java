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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {

	public static final float VELOCITY = 140;					//Game speed.
	public static final float JUMP_IMPULSE = 400;			//Jump impulse.
	public static final float GRAVITY = -10;					//Gravity force.
	
	private RunFoxRun game;
	
    //basic play screen variables
    public OrthographicCamera gamecam;
    private Viewport gamePort;
    private ShapeRenderer shaperenderer;
    private ScreenGrid grid;
    
    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    
    //Fox player
    private foxActor fox;
		
	public PlayScreen(RunFoxRun game) {
		this.game = game;
		
		gamecam = new OrthographicCamera();
		gamecam.setToOrtho(false);
		
        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(RunFoxRun.gw / RunFoxRun.PPM, RunFoxRun.gh / RunFoxRun.PPM, gamecam);
        
        //initially set our game camera to be centered correctly at the start of of map
        gamecam.position.set(gamePort.getWorldWidth() / 2 , gamePort.getWorldHeight() / 2, 0);
        
        fox = new foxActor();
        
        grid = new ScreenGrid(5,15,new Vector2(0,0), 70, this);
        
        //create our Box2D world, setting no gravity in X, -10 gravity in Y, and allow bodies to sleep
        world = new World(new Vector2(0, -10), true);
        //allows for debug lines of our box2d world.
        b2dr = new Box2DDebugRenderer();
        
        shaperenderer = new ShapeRenderer();
	}

	@Override
	public void render(float delta) {
        update(delta);
        		
		//Clear the game screen with Black
        Gdx.gl.glClearColor(.5f, .8f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
        
        //renderer our Box2DDebugLines
        b2dr.render(world, gamecam.combined);
        
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        //grid.draw(game.batch);
        fox.draw(game.batch);
        game.batch.end();
        
        //shaperenderer.setProjectionMatrix(gamecam.combined);
        //shaperenderer.begin(ShapeType.Line);
        //shaperenderer.setColor(Color.BLACK);
        //grid.drawBounds(shaperenderer);
        //fox.drawBounds(shaperenderer);
        //shaperenderer.end();
	}

	private void update(float delta) {
		
		boolean onGround = false;
		
		handleInput(delta);
		
		world.step(1 / 60f, 6, 2);
		
		gamecam.translate(new Vector2(delta * VELOCITY,0));
		
		grid.update(delta, this);
		
		fox.update(delta);
		
		/*
		for(GridRow gr : grid.rows) {
			for(ScreenTile st : gr.tiles) {
				if(fox.foxBounds.overlaps(st.tileBounds) && st.isRigid) {
					fox.setPosition(fox.getX(), st.tilePosition.y + st.tileSize);
					fox.setState(State.RUNNING);
					onGround = true;
				}
			}
		}
		if(!onGround && fox.currentState == State.RUNNING) { fox.setState(State.FALLING); }
		*/
		
        //update our game camera with correct coordinates after changes
        gamecam.update();		
		
	}

	private void handleInput(float delta) {
		if(Gdx.input.justTouched() && fox.currentState == State.RUNNING) {	
			fox.setState(State.JUMPINGUP);
		}
		if(Gdx.input.justTouched()) {
			Gdx.app.log("PlayScreen", Float.toString(gamecam.position.y));
			Gdx.app.log("PlayScreen", Float.toString(world.getBodyCount()));
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
	
    public World getWorld(){
        return world;
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
}


	
	
