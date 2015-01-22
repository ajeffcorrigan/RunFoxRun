package com.ajeffcorrigan.runfoxrun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class RunFoxRun extends ApplicationAdapter {

	//Game constants
	private static final float FOX_JUMP_IMPULSE = 360;
	private static final float GRAVITY = -10;
	private static final float FOX_VELOCITY_X = 200;
	private static final float FOX_START_Y = 50;
	private static final float FOX_START_X = 75;
	
	//Checks if assets have loaded.
	private boolean assetsInit = true;
	
	float stateTime;		
	float groundOffsetX = 0;
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	OrthographicCamera camera;
	Vector2 foxVelocity = new Vector2();
	Vector2 foxPosition = new Vector2();
	Vector2 gravity = new Vector2();
	FoxState foxstate = FoxState.run;
	
	// jAnimator class for the running fox.
	jAnimator foxrun;
	jAnimator foxjump;
	
	// jBackground class for the background hill.
	jBackground bghill;
	// jBackground object for sky background.
	jBackground bgsky;
	
	TextureRegion ground;
    SpriteBatch         	spriteBatch;
    TextureRegion           currentFrame;
    
	
	@Override
	public void create () {
		
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		
		//Create and setup the camera.
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		camera.position.x = 100;
		
		ground = new TextureRegion(new Texture("crosssection_long.png"));
		
		//Create running fox animation.
		foxrun = new jAnimator(new Texture("run_fox_sheet.png"),12,1,true,false,.05f);
		foxjump = new jAnimator(new Texture("jump_fox_sheet.png"),13,2,true,false,.2f);
		
		//Create background item.
		bghill = new jBackground(new Texture("pinehills_distant_1.png"), 400, -75, 0, 1.05f);
		bgsky = new jBackground(new Texture("bg.png"));
		
        stateTime = 0f;
        
        foxPosition.set(FOX_START_X,FOX_START_Y);
        foxVelocity.set(FOX_VELOCITY_X,0);
        gravity.set(0, GRAVITY);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(!this.assetsInit) {
			loadAssets();
		} else {
			updateWorld();
			drawWorld();
		}
	}
	
	private void updateWorld() {
		// TODO Auto-generated method stub
        stateTime += Gdx.graphics.getDeltaTime();
        if(foxstate == FoxState.run) { 
        	currentFrame = foxrun.getCurrentFrame(stateTime); 
        } else if (foxstate == FoxState.jump) {
        	currentFrame = foxjump.getCurrentFrame(stateTime); 
        }
        
        //Check for input
        if(Gdx.input.justTouched() && foxPosition.y <= FOX_START_Y + 3) {
        	foxVelocity.set(FOX_VELOCITY_X, FOX_JUMP_IMPULSE);
        	//foxstate = FoxState.jump;
        }
        
        //Jumping mechanism
        if(foxPosition.y < FOX_START_Y && foxVelocity.y <= 0) { 
        	foxPosition.y = FOX_START_Y;
        	foxVelocity.y = 0;
        	foxstate = FoxState.run;
        } else {
        	foxVelocity.add(gravity);
        }
        
        //Move the fox.
        foxPosition.mulAdd(foxVelocity, Gdx.graphics.getDeltaTime());
        	
        //Reset the camera position
		camera.position.x = foxPosition.x + 325;
		
		//Ensure the ground sprite is always on screen
		if(camera.position.x - groundOffsetX > ground.getRegionWidth() + 400) {
			groundOffsetX += ground.getRegionWidth();
		}
		
		//Update background
		bghill.updateBackgroundX(camera.position.x);
		bgsky.setxCoord(camera.position.x - Gdx.graphics.getWidth() / 2);
	}

	private void drawWorld() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		batch.draw(bgsky.getBgimage(), bgsky.getxCoord(), 0, Gdx.graphics.getWidth()*2, Gdx.graphics.getHeight());
		batch.draw(bghill.getBgimage(), bghill.getxCoord(), bghill.getyCoord());
		batch.draw(ground, groundOffsetX, 0);
		batch.draw(ground, groundOffsetX + ground.getRegionWidth(), 0);
		batch.end();
        
        batch.begin();
        //Draw the current frame of the fox
        batch.draw(currentFrame, foxPosition.x, foxPosition.y);
        batch.end();
	}
	
	private void loadAssets() {
		
	}
	static enum FoxState {
		run, jump
	}
	
}
	
