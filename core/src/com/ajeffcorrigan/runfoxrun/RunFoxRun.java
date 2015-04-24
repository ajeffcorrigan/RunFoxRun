package com.ajeffcorrigan.runfoxrun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	private static final float UPSPEEDVAL = 0.06f;		//Speed up value.
	private static final float GROUNDLEVEL = 60; 		//Actual ground level.
	
	private float speedMultiplier = 1;					//Speed multiplier	
	private boolean assetsInit = false;					//Checks if assets have loaded. 
	private float stateTime;							//Time passed.
	private float deltaTime;							//Time between cycles.
	private int coinCount = 0;							//Number of coins counted.
	private Random rand = new Random();					//Random number generator object.
	private float distanceRan = 0;						//Distance fox ran
	private float gw;									//Game width
	private float gh;									//Game height
	private boolean upSpeedOk = false;					//Flag to indicate speed up should occur.
    private float maxGroundTile;						//Last ground tile x coordinate.
	
	private BitmapFont font;
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
	
	ArrayList<jBackground> bgitems = new ArrayList<jBackground>();			//Background objects, non-interactive
	ArrayList<jLiveActor> liveItems = new ArrayList<jLiveActor>();			//Interactive items
	ArrayList<jBackground> grounds = new ArrayList<jBackground>();			//Ground tiles, non-interactive

    SpriteBatch spriteBatch;
    TextureRegion currentFrame;
    Rectangle foxBounds = new Rectangle(0,0,BOUNDWIDTH,BOUNDHEIGHT);		//Create the fox bounds.
    
    

	
	@Override
	public void create () {
		this.gw = Gdx.graphics.getWidth();			//Get graphics width.
		this.gh = Gdx.graphics.getHeight();		//Get graphics height.
		
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		
		//Create and setup the camera.
		camera = new OrthographicCamera();
		camera.setToOrtho(false,this.gw,this.gh);
		
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
        	if (foxBounds.overlaps(la.getBounds())) { 
        		if (la.isReSpawn() && !la.isDeadly()) {
        			this.coinCount += 1;
        			this.upSpeedOk = false;
        			int min = (int)la.getImageWidth();
        			la.setxCoord(Gdx.graphics.getWidth() + rand.nextInt(min+(int)gw));
        		} else if (la.isDeadly()) {
        			this.coinCount += 1;
        		}
			} else if (la.getWidthXCoord() < 0 && la.isReSpawn()) {
				int min = (int)la.getImageWidth();
				la.setxCoord(Gdx.graphics.getWidth() + rand.nextInt(min+(int)gw));
			}
        	if (this.upSpeedOk) { la.addToMultiplier(UPSPEEDVAL); }
        }
        
        //Move the fox.
        foxPosition.mulAdd(foxVelocity, deltaTime);
        
        //Be sure all background items are sorted by level.
        Collections.sort(bgitems, new jBackground());
        
        //Update scrolling ground tiles
        maxGroundTile = 0f;
        for(jBackground jb : grounds) {
        	jb.updateBackgroundX(deltaTime);
        	if (this.upSpeedOk) { jb.addToMultiplier(UPSPEEDVAL); }
        	if(jb.getWidthXCoord() > maxGroundTile) { maxGroundTile = jb.getWidthXCoord(); }
        }
        
        //Ensure any ground tiles off screen are repositioned for viewing.
        for(jBackground jb : grounds) {
        	if(jb.getWidthXCoord() <= 0) {
        		jb.setxCoord(maxGroundTile);
        		break;
        	}
        }
        
		//Update background item
		for(jBackground jb : bgitems) {
			jb.updateBackgroundX(deltaTime);
			if (this.upSpeedOk) { jb.addToMultiplier(UPSPEEDVAL); }
			if (jb.getWidthXCoord() < 0 && jb.isReSpawn()) {
				int min = jb.getImageWidth();
				jb.setxCoord(this.gw + rand.nextInt(min+50));
			} 
		}
		
		//Set new speed multiplier and use to calculate distance ran.
		if (this.upSpeedOk) { speedMultiplier += UPSPEEDVAL; }
		this.upSpeedOk = false;
		this.distanceRan += deltaTime * speedMultiplier * .12;
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
        font.draw(batch, "Coins: "+this.coinCount, 20, Gdx.graphics.getHeight()-30);
        font.draw(batch, "Distance Ran: " + (int)this.distanceRan, 20, Gdx.graphics.getHeight()-50);
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
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(234,FOX_START_Y),4,.75f,true,.65f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(455,30),4,.85f,true,.60f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(502,30),4,.65f,true,.75f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(600,FOX_START_Y),4,.55f,true,.85f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(100,30),4,.95f,true,.95f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(200,FOX_START_Y),4,1,true,1.2f));
		
		grounds.add(new jBackground(jAssets.getTexture("ground1"),new Vector2(0,0),0,true));
		grounds.add(new jBackground(jAssets.getTexture("ground1"),new Vector2(jAssets.getTexture("ground1").getWidth(),0),0,true));
		grounds.add(new jBackground(jAssets.getTexture("ground1"),new Vector2(jAssets.getTexture("ground1").getWidth()*2,0),0,true));
		
		liveItems.add(new jLiveActor(jAssets.getTexture("coin"),new Vector2(400,GROUNDLEVEL), 1, true, .80f));
		liveItems.add(new jLiveActor(jAssets.getTexture("coin"),new Vector2(gw,GROUNDLEVEL), 1, true, .95f, true));
		
	}
	static enum FoxState {
		run, jump
	}
	
	private float gameSpeed() {
		return GAME_SPEED * speedMultiplier; 
	}
	
}
	
