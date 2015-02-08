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
	private static final float GAME_SPEED = -90f;
	
	
	private boolean assetsInit = false;				//Checks if assets have loaded. 
	private float stateTime;						//Time passed.
	float deltaTime;								//Time between cycles.
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	OrthographicCamera camera;
	Vector2 foxVelocity = new Vector2();
	Vector2 foxPosition = new Vector2();
	Vector2 gravity = new Vector2();
	FoxState foxstate = FoxState.run;				
	int coinCount = 0;								//Number of coins counted.
	Random rand = new Random();						//Random number generator object.
	
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
		camera.setToOrtho(false,800,600);
		
		//Create running fox animation.
		foxrun = new jAnimator(new Texture("run_fox_sheet.png"),12,1,true,false,.05f);
		foxjump = new jAnimator(new Texture("fox_jump_animation.png"),8,2,false,false,.071f,true);
		
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
			if(assetsInit) { createWorld(); }
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
				jb.setxCoord(Gdx.graphics.getWidth() + rand.nextInt(min+50));
			} 
		}				

	}

	private void drawWorld() {
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
			
		
		//Draw non interactive background items
		batch.begin();
		for(jBackground jb : bgitems) { jb.draw(batch); }
		batch.end();
		
		//Draw live objects
		
		
		//Draw fox actor.
        batch.begin();
        batch.draw(currentFrame, foxPosition.x, foxPosition.y);
        batch.end();
	}
	
	private void loadAssets() {
		jAssets.loadTextureAs("bush1", "bush_1.png");
		jAssets.loadTextureAs("farpines", "pinehills_distant_1.png");
		jAssets.loadTextureAs("ground1", "crosssection_long.png");
		jAssets.loadTextureAs("tree1", "tree_coniferous_1.png");
		
		assetsInit = true;
	}
	
	private void createWorld() {
		//Create background item.
		bgitems.add(new jBackground(jAssets.getTexture("bush1"),600,20,1,GAME_SPEED,true));
		bgitems.add(new jBackground(jAssets.getTexture("farpines"), 400, -75, 100, -1.45f));
		bgitems.add(new jBackground(jAssets.getTexture("ground1"),0,0,0,0,false));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),400,30,3,GAME_SPEED + 5,true));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),234,30,4,GAME_SPEED + 40,true,.65f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),455,30,4,GAME_SPEED + 45,true,.60f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),502,30,4,GAME_SPEED + 20,true,.75f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),600,30,4,GAME_SPEED + 10,true,.85f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),100,30,4,GAME_SPEED + 5,true,.95f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),200,30,4,GAME_SPEED,true,1.2f));
	}
	static enum FoxState {
		run, jump
	}
	
}
	
