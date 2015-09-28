package com.ajeffcorrigan.runfoxrun.screens;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.ajeffcorrigan.runfoxrun.sprites.ScreenTile;
import com.ajeffcorrigan.runfoxrun.sprites.foxActor;
import com.ajeffcorrigan.runfoxrun.sprites.foxActor.State;
import com.ajeffcorrigan.runfoxrun.tools.ScreenGrid;
import com.ajeffcorrigan.runfoxrun.tools.WorldContactListener;
import com.ajeffcorrigan.runfoxrun.tools.jAssets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {

	
	public static final float VELOCITY = 2.8f;				//Game speed.
	public static final float JUMP_IMPULSE = 5.5f;			//Jump impulse.
	public static final float GRAVITY = -10;				//Gravity force.
	public final float BLOCKWIDTH = 70;
	
	public enum GameState {PLAYING, STOPPED};
	
	//Main game controller.
	private RunFoxRun game;
	
    //basic play screen variables
    public OrthographicCamera gamecam;
    private Viewport gamePort;
    
    //Screen grid manager.
    private ScreenGrid grid;
    
    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    
    //Fox player
    private foxActor fox;
    
    private GameState currentGameState;
    
    
		
	public PlayScreen(RunFoxRun game) {
		this.game = game;
		
		//Game camera and viewport setup.
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(RunFoxRun.gw / RunFoxRun.PTM, RunFoxRun.gh / RunFoxRun.PTM, gamecam);
		gamecam.position.set(gamePort.getWorldWidth() / 2 , gamePort.getWorldHeight() / 2, 0);
		
		//Box2D world and debugger setup.
		world = new World(new Vector2(0, GRAVITY), true);
		b2dr = new Box2DDebugRenderer();
		
		//World objects, actors, tiles, etc.
		fox = new foxActor(this);
		
		int widthoftiles = (int)Math.ceil(gamePort.getWorldWidth() / (BLOCKWIDTH / RunFoxRun.PTM) + 2);
		Gdx.app.log("PlayScreen","number of tiles: "+ widthoftiles);
		
		//Initialize the screen grid for blocks.
		grid = new ScreenGrid(5,widthoftiles,new Vector2(0,0), BLOCKWIDTH, this);
		
		//Set initial blocks to flat ground.
		for(ScreenTile st : grid.rows.first().tiles) {
			st.setRigidSprite(new Sprite(jAssets.getTextureRegion("grassMid")), this);
		}
		
		world.setContactListener(new WorldContactListener());
		currentGameState = GameState.STOPPED;
		
	}

	@Override
	public void render(float delta) {
        update(delta);
        		
		//Clear the game screen 
        Gdx.gl.glClearColor(.5f, .8f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
        
        //renderer our Box2DDebugLines
        b2dr.render(world, gamecam.combined);
        
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        grid.draw(game.batch);
        //fox.draw(game.batch);
        game.batch.end();
        
	}

	private void update(float delta) {
		//Handle any input from user.	
		handleInput(delta);
		
		if(currentGameState == GameState.PLAYING) {
			//Step the world physics simulation.
			world.step(1 / 60f, 6, 2);
		
			//Maintain running speed.
			fox.b2body.setLinearVelocity(VELOCITY,fox.b2body.getLinearVelocity().y);
		
			fox.update(delta);

			//set game camera to follow fox on x axis
			gamecam.position.x = fox.b2body.getPosition().x + (gamePort.getWorldWidth() * .3f);
			
			//update grid
			grid.update(delta, this);
		}
		
		if(currentGameState == GameState.STOPPED) {
			
			fox.setPosition(fox.b2body.getPosition().x - fox.getWidth() / 11.5f, fox.b2body.getPosition().y - fox.getHeight() / 2.5f);
			
			//set game camera to follow fox on x axis
			gamecam.position.x = fox.b2body.getPosition().x + (gamePort.getWorldWidth() * .4f);
		}
		
		//Update the game camera.
		gamecam.update();
		
	}

	private void handleInput(float delta) {
		if(Gdx.input.justTouched()) {
			if(currentGameState == GameState.PLAYING) {
				if(fox.b2body.getLinearVelocity().y == 0f) {
					fox.b2body.applyLinearImpulse(new Vector2(0, JUMP_IMPULSE), fox.b2body.getWorldCenter(), true);
				}
			}

			if(currentGameState == GameState.STOPPED) {
				currentGameState = GameState.PLAYING;
			}
		}
	}

	@Override
	public void resize(int width, int height) {
        //updated our game viewport
        gamePort.update(width,height);
		
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
		world.dispose();
		b2dr.dispose();
		
	}
	
    public World getWorld(){
        return world;
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
}


	
	
