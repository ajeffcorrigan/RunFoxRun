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
	
	//Main game controller.
	private RunFoxRun game;
	
    //basic play screen variables
    public OrthographicCamera gamecam;
    private Viewport gamePort;
    //private ShapeRenderer shaperenderer;
    
    //Screen grid manager.
    private ScreenGrid grid;
    
    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    
    //Fox player
    private foxActor fox;
		
	public PlayScreen(RunFoxRun game) {
		this.game = game;
		
		//Game camera and viewport setup.
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(RunFoxRun.gw / RunFoxRun.PTM, RunFoxRun.gh / RunFoxRun.PTM, gamecam);
		gamecam.position.set(gamePort.getWorldWidth() / 2 , gamePort.getWorldHeight() / 2, 0);
		
		//Box2D world and debugger setup.
		world = new World(new Vector2(0, -5f), true);
		b2dr = new Box2DDebugRenderer();
		
		//World objects, actors, tiles, etc.
		fox = new foxActor(this);
		
		//gamecam.setToOrtho(false);

        //grid = new ScreenGrid(5,15,new Vector2(0,0), 70, this);
               
        //shaperenderer = new ShapeRenderer();
		Gdx.app.log("PlayScreen", "start fox box2d body x:"+fox.b2body.getPosition().x);
		Gdx.app.log("PlayScreen", "start fox box2d body y:"+fox.b2body.getPosition().y);
	}

	@Override
	public void render(float delta) {
        update(delta);
        		
		//Clear the game screen 
        Gdx.gl.glClearColor(.5f, .8f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
        
        //renderer our Box2DDebugLines
        b2dr.render(world, gamecam.combined);
        
        //game.batch.setProjectionMatrix(gamecam.combined);
        //game.batch.begin();
        //grid.draw(game.batch);
        //fox.draw(game.batch);

        //game.batch.end();
        
        //shaperenderer.setProjectionMatrix(gamecam.combined);
        //shaperenderer.begin(ShapeType.Line);
        //shaperenderer.setColor(Color.BLACK);
        //grid.drawBounds(shaperenderer);
        //fox.drawBounds(shaperenderer);
        //shaperenderer.end();
	}

	private void update(float delta) {
		//Handle any input from user.	
		handleInput(delta);
		
		//Step the world physics simulation.
		world.step(1 / 60f, 6, 2);
		
		//Update the game camera.
		gamecam.update();
		
		//gamecam.translate(new Vector2(delta * VELOCITY,0));
		
		//grid.update(delta, this);
		
		//fox.update(delta);
		
		/*
		for(GridRow gr : grid.rows) {
			for(ScreenTile st : gr.tiles) {
				if(fox.foxBounds.overlaps(st.tileBounds) && st.isRigid) {
					fox.setPosition(fox.getX(), st.tilePosition.y + st.tileSize);
					fox.setState(State.RUNNING);
				}
			}
		}
		if(!onGround && fox.currentState == State.RUNNING) { fox.setState(State.FALLING); }
		*/
		
        	
		
	}

	private void handleInput(float delta) {
		if(Gdx.input.justTouched() && fox.currentState == State.RUNNING) {	
			fox.setState(State.JUMPINGUP);
		}
		if(Gdx.input.justTouched()) {
			Gdx.app.log("PlayScreen", "gamecam position y:"+Float.toString(gamecam.position.y));
			Gdx.app.log("PlayScreen", "gamecam position x:"+Float.toString(gamecam.position.x));
			Gdx.app.log("PlayScreen", Float.toString(gamePort.getWorldWidth()));
			Gdx.app.log("PlayScreen", "fox position:"+fox.getX());
			Gdx.app.log("PlayScreen", "fox box2d body x:"+fox.b2body.getPosition().x);
			Gdx.app.log("PlayScreen", "fox box2d body y:"+fox.b2body.getPosition().y);
			
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


	
	
