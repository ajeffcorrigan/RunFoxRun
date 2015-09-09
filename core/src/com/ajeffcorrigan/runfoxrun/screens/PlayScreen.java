package com.ajeffcorrigan.runfoxrun.screens;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.ajeffcorrigan.runfoxrun.scenes.Hud;
import com.ajeffcorrigan.runfoxrun.tools.B2WorldManager;
import com.ajeffcorrigan.runfoxrun.tools.Fox;
import com.ajeffcorrigan.runfoxrun.tools.jAssets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {
	
	private RunFoxRun game;
	
	//play screen variables
	private OrthographicCamera gamecam;
	private Viewport gameport;
	private Hud hud;
	
	//Box2D world
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private B2WorldManager b2wm;
	
	private Sprite sprite;
	
	Array<Body> bodies = new Array<Body>();
	private Fox player;
	
	
	public PlayScreen(RunFoxRun game) {
		
		this.game = game;
		gamecam = new OrthographicCamera();
		
		gameport = new FitViewport(RunFoxRun.G_WIDTH / RunFoxRun.PPM ,RunFoxRun.G_HEIGHT / RunFoxRun.PPM ,gamecam);
		
		world = new World(new Vector2(0, -10), true);
		    
        gamecam.position.set(gameport.getWorldWidth() / 2f, gameport.getWorldHeight() / 2f, 0);
	       
		debugRenderer = new Box2DDebugRenderer();
		
		sprite = new Sprite(jAssets.getTexture("grassMid32"));
		b2wm = new B2WorldManager(world,sprite);
		
		hud = new Hud(game.batch);	
		
		player = new Fox(world,this);
		
	}
	
	public void update(float dt) {
        //handle user input first
        handleInput(dt);
        
        world.step(1 / 60f, 6, 2);
        
        player.update(dt);
        world.getBodies(bodies);
        //gamecam.position.mulAdd(new Vector3(1f / RunFoxRun.PPM, 0, 0), 1);
                
        //update our game camera with correct coordinates after changes
        gamecam.update();
              
        
        
	}

	private void handleInput(float dt) {
		// TODO Auto-generated method stub
		 if(Gdx.input.justTouched()) {
			 //System.out.println(Gdx.input.getX() / RunFoxRun.PPM);
			 for (Body b : bodies) {
				 Sprite e = (Sprite) b.getUserData();
				 if (e != null) {
					 System.out.println(e.getWidth());
					 System.out.println(e.getX());
	
 
				 }
			 }
		 }
			 
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
		update(delta);
		
        //Clear the game screen with Black
        //Gdx.gl.glClearColor(.5f, .8f, .9f, 1);
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        

        
        //renderer our Box2DDebugLines
        debugRenderer.render(world, gamecam.combined);
        
        
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        for (Body b : bodies) {
            Sprite e = (Sprite) b.getUserData();

            if (e != null) {
                e.setPosition(b.getPosition().x - (e.getWidth() / 2), b.getPosition().y - (e.getHeight() / 2));
                e.draw(game.batch);
            }
            
        }
        game.batch.end();
        

        
        //game.batch.draw(jAssets.getTextureRegion("grassMid"), 70, 0);
        //game.batch.draw(jAssets.getTextureRegion("grassLeft"), 140, 0);
        //game.batch.draw(jAssets.getTexture("coin"), 140, 70);
        //game.batch.draw(jAssets.getTexture("coin"), 200, 100);
        //
        
        //
        //game.batch.begin();
        //
        //game.batch.end();
                
        //Set our batch to now draw what the Hud camera sees.
        //game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        //hud.stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		gameport.update(width,height);
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
		world.dispose();
		debugRenderer.dispose();
		hud.dispose();
	}

}
