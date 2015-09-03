package com.ajeffcorrigan.runfoxrun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.tools.jAnimator;
import com.ajeffcorrigan.runfoxrun.tools.jAssets;
import com.ajeffcorrigan.runfoxrun.tools.jBackground;
import com.ajeffcorrigan.runfoxrun.tools.jLiveActor;
import com.ajeffcorrigan.runfoxrun.tools.jStaticActor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class RunFoxRun extends Game {

	public static final int G_WIDTH = 400;
	public static final int G_HEIGHT = 208;
	public static final float PPM = 100;
	public static final boolean DEBUGON = true;			//Is debug enabled.
	
	public SpriteBatch batch;
	
	//Game constants
	private static final float FOX_JUMP_IMPULSE = 330;	//Jump impulse
	private static final float GRAVITY = -10;			//Gravity force
	private static final Vector2 FOX_START = new Vector2(75,40);
	public static final float GAME_SPEED = -320f;		//Base game speed
	private static final Vector2 BOUNDOFFSET = new Vector2(60,20);
	private static final Vector2 BOUNDSIZE = new Vector2(60,40);
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
    private ShapeRenderer shaperenderer;				//Shape renderer used in debugging.
    
	private BitmapFont font;
	
	Vector2 gravity = new Vector2();
	FoxState foxstate = FoxState.run;
	GameState gamestate = GameState.title;				//Set initial state of game, which is title, for now.

	// jAnimator class for the running fox.
	jAnimator foxrun;
	jAnimator foxjump;
	
	ArrayList<jBackground> bgitems = new ArrayList<jBackground>();					//Background objects, non-interactive
	ArrayList<jStaticActor> staticItems = new ArrayList<jStaticActor>();			//Interactive items
	ArrayList<jBackground> grounds = new ArrayList<jBackground>();					//Ground tiles, non-interactive
	
	jLiveActor foxActor;

    TextureRegion currentFrame;
	
	@Override
	public void create () {
		this.gw = Gdx.graphics.getWidth();				//Get graphics width.
		this.gh = Gdx.graphics.getHeight();				//Get graphics height.
		
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
		
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		shaperenderer = new ShapeRenderer();
		
		//Create and setup the camera.
		camera = new OrthographicCamera();
		camera.setToOrtho(false,this.gw,this.gh);
		
        stateTime = 0f;
        
        foxActor = new jLiveActor(new Vector2(FOX_START.x, FOX_START.y + .2f),BOUNDSIZE);
        foxActor.setBoundOffset(BOUNDOFFSET);
        gravity.set(0, GRAVITY);
        
	}


	@Override
	public void render () {
		
		super.render();
		
		Gdx.gl.glClearColor((float)135/255, (float)206/255, (float)235/255, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(!this.assetsInit) {
			loadAssets();
			if(assetsInit) { createWorld(); }
		} else {
			if(gamestate == GameState.title) { 
				titleScreen(); 
				drawWorld();
			}
			if(gamestate == GameState.activegame) { 
				deltaTime = Gdx.graphics.getDeltaTime();
		        stateTime += deltaTime; 
				updateWorld();
				drawWorld();
			}
			if(gamestate == GameState.endgame) {
				endGameScreen();
				drawWorld();
			}		
		}
	}
	
	private void endGameScreen() {
		if(Gdx.input.justTouched()) {
			bgitems.clear();
			staticItems.clear();
			grounds.clear();
			create();
			createWorld();
			distanceRan = 0;
			coinCount = 0;
			gamestate = GameState.activegame;
		}
	}
	
	private void titleScreen() {
		currentFrame = foxrun.getCurrentFrame(0);
		if(Gdx.input.justTouched()) {
			gamestate = GameState.activegame;
		}
	}

	private void updateWorld() {
		       
        //Check for input
        if(Gdx.input.justTouched() && foxActor.getActorPos().y <= FOX_START.y) {
        	foxActor.setActorVelocityY(FOX_JUMP_IMPULSE);
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
        if(foxActor.getActorPos().y < FOX_START.y && foxActor.getActorVelocity().y <= 0) { 
        	foxActor.setActorPosY(FOX_START.y);
        	foxActor.setActorVelocityY(0);
        	foxstate = FoxState.run;
        	foxjump.setAnimationProgress(false);
        } else {
        	foxActor.addToVelocity(gravity);
        }    
        
        for(jStaticActor sa : staticItems) {
        	sa.updActorX(deltaTime);
        	if (foxActor.getActorBounds().overlaps(sa.getItemBounds())) { 
        		if (sa.isReSpawn() && !sa.isDeadly()) {
        			this.coinCount += 1;
        			this.upSpeedOk = false;
        			int min = (int)sa.getImgWidth();
        			sa.setxCoord(Gdx.graphics.getWidth() + rand.nextInt(min+(int)gw));
        		} else if (sa.isDeadly()) {
        			gamestate = GameState.endgame;
        		}
			} else if (sa.getWidthXCoord() < 0 && sa.isReSpawn()) {
				int min = (int)sa.getImgWidth();
				sa.setxCoord(Gdx.graphics.getWidth() + rand.nextInt(min+(int)gw));
			}
        	if (this.upSpeedOk) { sa.addToMultiplier(UPSPEEDVAL); }
        }
        
        //Move the fox.
        foxActor.moveActor(deltaTime);
        
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
				int min = (int) jb.getImgWidth();
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
		for(jStaticActor sa : staticItems) { sa.draw(batch); } 
		batch.end();
						
		//Draw fox actor.
        batch.begin();
        batch.draw(currentFrame, foxActor.getActorPos().x, foxActor.getActorPos().y);
        font.draw(batch, "Coins: "+this.coinCount, 20, gh - 30);
        font.draw(batch, "Distance Ran: " + (int)this.distanceRan, 20, gh - 50);
        batch.end();
        
        if(DEBUGON) {
        	shaperenderer.begin(ShapeType.Line);
            shaperenderer.setColor(Color.BLACK);
            shaperenderer.rect(foxActor.getActorBounds().x, foxActor.getActorBounds().y, foxActor.getActorBounds().width, foxActor.getActorBounds().height);
            //for(jBackground jb : bgitems) { 
            //	shaperenderer.rect(jb.getItemBounds().x, jb.getItemBounds().y, jb.getItemBounds().width, jb.getItemBounds().height);
            //}
            for(jStaticActor sa : staticItems) {
            	shaperenderer.rect(sa.getItemBounds().x, sa.getItemBounds().y, sa.getItemBounds().width, sa.getItemBounds().height);
            } 
            shaperenderer.end();
        }
        
	}
	
	private void loadAssets() {
		jAssets.loadTextureAs("bush1", "bush_1.png");
		jAssets.loadTextureAs("farpines", "pinehills_distant_1.png");
		jAssets.loadTextureAs("ground1", "crosssection_long.png");
		jAssets.loadTextureAs("tree1", "tree_coniferous_1.png");
		jAssets.loadTextureAs("coin", "goldcoin.png");
		jAssets.loadTextureAs("foxrunsheet", "run_fox_sheet.png");
		jAssets.loadTextureAs("foxjumpsheet", "fox_jump_animation.png");
		
		assetsInit = true;
	}
	
	private void createWorld() {
		//Create running fox animation.
		foxrun = new jAnimator(jAssets.getTexture("foxrunsheet"),12,1,true,false,.05f);
		foxjump = new jAnimator(jAssets.getTexture("foxjumpsheet"),8,2,false,false,.071f,true);		
		
		//Create background item.
		bgitems.add(new jBackground(jAssets.getTexture("bush1"),new Vector2(600,30),1,true));
		bgitems.add(new jBackground(jAssets.getTexture("farpines"), new Vector2(400, -75), 100, .020f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(400,30),3,.85f,true));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(234,FOX_START.y),4,.75f,true,.65f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(455,30),4,.85f,true,.60f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(502,30),4,.65f,true,.75f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(600,FOX_START.y),4,.55f,true,.85f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(100,30),4,.95f,true,.95f));
		bgitems.add(new jBackground(jAssets.getTexture("tree1"),new Vector2(200,FOX_START.y),4,1,true,1.2f));
		
		grounds.add(new jBackground(jAssets.getTexture("ground1"),new Vector2(0,0),0,true));
		grounds.add(new jBackground(jAssets.getTexture("ground1"),new Vector2(jAssets.getTexture("ground1").getWidth(),0),0,true));
		grounds.add(new jBackground(jAssets.getTexture("ground1"),new Vector2(jAssets.getTexture("ground1").getWidth()*2,0),0,true));
		
		staticItems.add(new jStaticActor(jAssets.getTexture("coin"),new Vector2(400,GROUNDLEVEL), 1, true, .80f));
		staticItems.add(new jStaticActor(jAssets.getTexture("coin"),new Vector2(gw,GROUNDLEVEL), 1, true, .95f, true));
		
	}
	static enum FoxState {
		run, jump
	}
	
	static enum GameState {
		title, activegame, pausegame, endgame
	}
	
}
	
