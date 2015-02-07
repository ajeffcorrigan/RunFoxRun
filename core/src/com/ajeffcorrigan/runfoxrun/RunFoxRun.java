package com.ajeffcorrigan.runfoxrun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
	private static final float FOX_JUMP_IMPULSE = 350;
	private static final float GRAVITY = -10;
	private static final float FOX_START_Y = 40;
	private static final float FOX_START_X = 75;
	private static final float GAME_SPEED = -80f;
	
	//Checks if assets have loaded.
	private boolean assetsInit = true;
	
	float stateTime;
	float deltaTime;
	float groundOffsetX = 0;
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	OrthographicCamera camera;
	Vector2 foxVelocity = new Vector2();
	Vector2 foxPosition = new Vector2();
	Vector2 gravity = new Vector2();
	FoxState foxstate = FoxState.run;
	int coinCount = 0;
	Random rand = new Random();
	
	// jAnimator class for the running fox.
	jAnimator foxrun;
	jAnimator foxjump;
	
	// jBackground objects
	ArrayList<jBackground> bgitems = new ArrayList<jBackground>();
	
	TextureRegion ground;
    SpriteBatch   spriteBatch;
    TextureRegion currentFrame;
    
	
	@Override
	public void create () {
		
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		
		//Create and setup the camera.
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		
		//Create running fox animation.
		foxrun = new jAnimator(new Texture("run_fox_sheet.png"),12,1,true,false,.05f);
		foxjump = new jAnimator(new Texture("fox_jump_animation.png"),8,2,false,false,.071f,true);
		
		//Create sky background.
		
		//Create background item.
		bgitems.add(new jBackground(new Texture("bush_1.png"),600,20,1,GAME_SPEED,true));
		bgitems.add(new jBackground(new Texture("pinehills_distant_1.png"), 400, -75, 100, -1.45f));
		bgitems.add(new jBackground(new Texture("crosssection_long.png"),0,0,0,0,false));
		bgitems.add(new jBackground(new Texture("tree_coniferous_1.png"),400,30,3,GAME_SPEED + 5,true));
		bgitems.add(new jBackground(new Texture("tree_coniferous_1.png"),234,30,4,GAME_SPEED + 40,true,.65f));
		
        stateTime = 0f;
        
        foxPosition.set(FOX_START_X,FOX_START_Y);
        foxVelocity.set(0,0);
        gravity.set(0, GRAVITY);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor((float)135/255, (float)206/255, (float)235/255, 0f);
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
		deltaTime = Gdx.graphics.getDeltaTime();
        stateTime += deltaTime;
        //Check for input
        if(Gdx.input.justTouched() && foxPosition.y <= FOX_START_Y) {
        	foxVelocity.set(0, FOX_JUMP_IMPULSE);
        	foxjump.setStateTime(stateTime);
        	foxjump.setAnimationProgress(true);
        	currentFrame = foxjump.getCurrentFrame(stateTime); 
        	foxstate = FoxState.jump;
        }
        
        //Determine which animation to use.
        if(foxstate == FoxState.run) { 
        	currentFrame = foxrun.getCurrentFrame(stateTime); 
        } else if (foxstate == FoxState.jump) {
        	currentFrame = foxjump.getCurrentFrame(stateTime); 
        }
        
        //Jumping mechanism
        if(foxPosition.y < FOX_START_Y && foxVelocity.y <= 0) { 
        	foxPosition.y = FOX_START_Y;
        	foxVelocity.y = 0;
        	foxstate = FoxState.run;
        	foxjump.setAnimationProgress(false);
        } else {
        	foxVelocity.add(gravity);
        }
        
        //Move the fox.
        foxPosition.mulAdd(foxVelocity, deltaTime);
        
        //Be sure all background items are sorted by level.
        Collections.sort(bgitems, new jBackground());
        
		//Update background item
		for(jBackground jb : bgitems) {
			jb.updateBackgroundX(deltaTime);
			if (jb.getWidthXCoord() < 0 && jb.isReSpawn()) {
				int min = jb.getImageWidth();
				jb.setxCoord(Gdx.graphics.getWidth() + rand.nextInt(Gdx.graphics.getWidth() - min + 1) + min);
			} 
		}				

	}

	private void drawWorld() {
		//camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		//Draw non interactive background items
		batch.begin();
		for(jBackground jb : bgitems) {
			jb.draw(batch);
		}
		batch.end();
		
		//Draw live objects
		
		
		//Draw fox actor.
        batch.begin();
        batch.draw(currentFrame, foxPosition.x, foxPosition.y);
        batch.end();
	}
	
	private void loadAssets() {
		
	}
	static enum FoxState {
		run, jump
	}
	
}
	
