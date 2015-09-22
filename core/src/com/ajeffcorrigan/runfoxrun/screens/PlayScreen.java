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
import com.badlogic.gdx.Input;
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

	public static final float VELOCITY = 2.5f;				//Game speed.
	public static final float JUMP_IMPULSE = 400;			//Jump impulse.
	public static final float GRAVITY = -10;				//Gravity force.
	
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

		grid = new ScreenGrid(5,12,new Vector2(0,0), 70, this);
		
		for(ScreenTile st : grid.rows.first().tiles) {
			st.setRigidSprite(new Sprite(jAssets.getTexture("grassMid")), this);
		}
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
        fox.draw(game.batch);
        game.batch.end();
        
	}

	private void update(float delta) {
		//Handle any input from user.	
		handleInput(delta);
		
		//Step the world physics simulation.
		world.step(1 / 60f, 6, 2);
		
		//Maintain running speed.
		fox.b2body.setLinearVelocity(VELOCITY,fox.b2body.getLinearVelocity().y);
		
		//gamecam.position.y = fox.b2body.getPosition().y;
		fox.update(delta);
		
		//set game camera to follow fox on x axis
		gamecam.position.x = fox.b2body.getPosition().x + (gamePort.getWorldWidth() * .3f);
		
		//update grid
		grid.update(delta, this);
		
		//Update the game camera.
		gamecam.update();

	}

	private void handleInput(float delta) {
		if(Gdx.input.justTouched()) {
			if(fox.b2body.getLinearVelocity().y == 0f) {
				fox.b2body.applyLinearImpulse(new Vector2(0, 5.55f), fox.b2body.getWorldCenter(), true);
			}
			Gdx.app.log("PlayScreen", "fox box2d body x:"+fox.b2body.getPosition().x);
			//Gdx.app.log("PlayScreen", "fox box2d body y:"+fox.b2body.getPosition().y);
			Gdx.app.log("PlayScreen", "world bodies: "+world.getBodyCount());
			//Gdx.app.log("PlayScreen", "world x pos:" + Gdx.input.getX() / RunFoxRun.PTM);
			//Gdx.app.log("PlayScreen", "world y pos:" + Gdx.input.getY() / RunFoxRun.PTM);
			Gdx.app.log("PlayScreen", "sprite x pos:" + fox.getX());	
			//Gdx.app.log("PlayScreen", "sprite y pos:" + fox.getY());
			Gdx.app.log("PlayScreen", "linearimpluse y:"+fox.b2body.getLinearVelocity().y+" linearimpluse x:"+fox.b2body.getLinearVelocity().x);
			
			
		}
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            fox.b2body.applyLinearImpulse(new Vector2(0, 1f), fox.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && fox.b2body.getLinearVelocity().x <= 2)
            fox.b2body.applyLinearImpulse(new Vector2(0.1f, 0), fox.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && fox.b2body.getLinearVelocity().x >= -2)
            fox.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), fox.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            fox.b2body.applyLinearImpulse(new Vector2(0, -.05f), fox.b2body.getWorldCenter(), true);
		
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


	
	
