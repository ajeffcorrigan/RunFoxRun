package com.ajeffcorrigan.runfoxrun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RunFoxRun extends ApplicationAdapter {

	//Game constants
	private static final float FOX_JUMP_IMPULSE = 350;	//Jump impulse
	private static final float GRAVITY = -10;			//Gravity force
	private static final float FOX_START_Y = 40;		//Fox starting y coordinate
	private static final float FOX_START_X = 75;		//Fox starting x coordinate
	public static final float GAME_SPEED = -200f;		//Base game speed
	private static final float BOUNDXOFFSET = 60;		//Fox bound x coordinate offset
	private static final float BOUNDYOFFSET = 20;		//Fox bound y coordinate offset
	private static final float BOUNDHEIGHT = 50;		//Fox bound height
	private static final float BOUNDWIDTH = 75;			//Fox bound width
	private static float speedMultiplier = 1;			//Speed multiplier	
	private boolean assetsInit = false;					//Checks if assets have loaded. 
	private float stateTime;							//Time passed.
	private float deltaTime;							//Time between cycles.
	private int coinCount = 0;							//Number of coins counted.
	private Random rand = new Random();					//Random number generator object.
	private float distanceRan = 0;						//Distance fox ran
	
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
	
	ArrayList<jBackground> bgitems = new ArrayList<jBackground>();	//Background objects
	ArrayList<jLiveActor> liveItems = new ArrayList<jLiveActor>();
	jBackground[] grounds = new jBackground[2];
    SpriteBatch   spriteBatch;
    TextureRegion currentFrame;
    Rectangle foxBounds = new Rectangle(0,0,BOUNDWIDTH,BOUNDHEIGHT);		//Create the fox bounds.
    
    private float upSpeedVal;
    private boolean upSpeedOk = false;
	
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
        
        //Update the fox bounds.
        foxBounds.setPosition(foxPosition.x + BOUNDXOFFSET, foxPosition.y + BOUNDYOFFSET);
        
        for(jLiveActor la : liveItems) {
        	la.updateLiveActorX(deltaTime);
        	if (foxBounds.overlaps(la.getBounds()) && la.isReSpawn()) {
        		this.coinCount += 1;
        		this.upSpeedOk = true;
        		this.upSpeedVal = .07f;
				int min = (int)la.getImageWidth();
				la.setxCoord(Gdx.graphics.getWidth() + rand.nextInt(min+50));
        	} else if (la.getWidthXCoord() < 0 && la.isReSpawn()) {
				int min = (int)la.getImageWidth();
				la.setxCoord(Gdx.graphics.getWidth() + rand.nextInt(min+50));
			}
        	if (this.upSpeedOk) { la.addToMultiplier(upSpeedVal); }
        }
        
        //Move the fox.
        foxPosition.mulAdd(foxVelocity, deltaTime);
        
        //Be sure all background items are sorted by level.
        Collections.sort(bgitems, new jBackground());
        
        //Update ground texture so always on screen
        if (this.upSpeedOk) {
        	grounds[0].addToMultiplier(upSpeedVal);
        	grounds[1].addToMultiplier(upSpeedVal);
        }
        grounds[0].updateBackgroundX(deltaTime);
        grounds[1].updateBackgroundX(deltaTime);
        if(grounds[0].getWidthXCoord() <= 0) {
        	grounds[0].setxCoord(grounds[1].getWidthXCoord());
        }
        if(grounds[1].getWidthXCoord() <= 0) {
        	grounds[1].setxCoord(grounds[0].getWidthXCoord());
        }
        
        
		//Update background item
		for(jBackground jb : bgitems) {
			jb.updateBackgroundX(deltaTime);
			if (this.upSpeedOk) { jb.addToMultiplier(upSpeedVal); }
			if (jb.getWidthXCoord() < 0 && jb.isReSpawn()) {
				int min = jb.getImageWidth();
				jb.setxCoord(Gdx.graphics.getWidth() + rand.nextInt(min+50));
			} 
		}
		this.upSpeedOk = false;

	}

	private void drawWorld() {
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
				
		//Draw non interactive background items and ground texture
		batch.begin();
		for(jBackground jb : bgitems) { jb.draw(batch); }
		for(jBackground gr : grounds) { gr.draw(batch); }
		batch.end();
		
		//Draw live objects
		batch.begin();
		for(jLiveActor la : liveItems) { la.draw(batch); } 
		batch.end();
						
		//Draw fox actor.
        batch.begin();
        batch.draw(currentFrame, foxPosition.x, foxPosition.y);
        batch.end();
        
        //Debug collision        
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(foxBounds.x, foxBounds.y, foxBounds.width, foxBounds.height);
        for(jLiveActor la : liveItems) {
        	shapeRenderer.rect(la.getBounds().x, la.getBounds().y, la.getBounds().width, la.getBounds().height);
        } 
        shapeRenderer.end();
	}
	
	private void loadAssets() {
		jAssets.loadTextureAs("bush1", "bush_1.png");
		jAssets.loadTextureAs("farpines", "pinehills_distant_1.png");
		jAssets.loadTextureAs("ground1", "crosssection_long.png");
		jAssets.loadTextureAs("tree1", "tree_coniferous_1.png");
		jAssets.loadTextureAs("coin", "goldcoin.png");
		
		assetsInit = true;
	}
	
	private void createWorld() {
		//Create background item.
		bgitems.add(new jBackground(jAssets.getTexture("bush1"),new Vector2(600,30),1,true));
		bgitems.add(new jBackground(jAssets.getTexture("farpines"), new Vector2(400, -75), 100, .035f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(400,30),3,.85f,true));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(234,30),4,.75f,true,.65f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(455,30),4,.85f,true,.60f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(502,30),4,.65f,true,.75f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(600,30),4,.55f,true,.85f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(100,30),4,.95f,true,.95f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(200,30),4,1,true,1.2f));
		
		grounds[0] = new jBackground(jAssets.getTexture("ground1"),new Vector2(0,0),0,true);
		grounds[1] = new jBackground(jAssets.getTexture("ground1"),new Vector2(grounds[0].getWidthXCoord(),0),0,true);
		
		liveItems.add(new jLiveActor(jAssets.getTexture("coin"),new Vector2(400,60), 1, true, .80f));
		
	}
	static enum FoxState {
		run, jump
	}
	
	private float gameSpeed() {
		return GAME_SPEED * speedMultiplier; 
	}
	
}
	
