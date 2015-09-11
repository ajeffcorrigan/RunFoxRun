package com.ajeffcorrigan.runfoxrun.screens;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.ajeffcorrigan.runfoxrun.sprites.GroundTile;
import com.ajeffcorrigan.runfoxrun.tools.Fox;
import com.ajeffcorrigan.runfoxrun.tools.ScreenGrid;
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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {

	public static final float GRAVITY = -10;			//Gravity force
	public static final float VELOCITY = 78;
	
	private RunFoxRun game;
	
    //basic play screen variables
    public OrthographicCamera gamecam;
    private Viewport gamePort;
    private ShapeRenderer shaperenderer;
    
    private ScreenGrid grid; 
    
    //Fox player
    private Fox player;
		
	public PlayScreen(RunFoxRun game) {
		this.game = game;
		
		gamecam = new OrthographicCamera();
		gamecam.setToOrtho(false);
		
        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(RunFoxRun.gw, RunFoxRun.gh, gamecam);
        
        //initially set our game camera to be centered correctly at the start of of map
        gamecam.position.set(gamePort.getWorldWidth() / 2 , gamePort.getWorldHeight() / 2, 0);
        
        player = new Fox();
        
        grid = new ScreenGrid(10,1);
        grid.fillGrid(new Sprite(jAssets.getTexture("grassMid")),new Vector2(-70,0));       
     
        shaperenderer = new ShapeRenderer();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
        update(delta);
        		
		//Clear the game screen with Black
        Gdx.gl.glClearColor(.5f, .8f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
        
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        grid.drawgrid(game.batch);
        game.batch.end();
        
        shaperenderer.setProjectionMatrix(gamecam.combined);
        shaperenderer.begin(ShapeType.Line);
        shaperenderer.setColor(Color.BLACK);
        shaperenderer.circle(player.headcircle.x, player.headcircle.y, player.headcircle.radius);
        shaperenderer.rect(player.getBoundingRectangle().x, player.getBoundingRectangle().y, player.getBoundingRectangle().width, player.getBoundingRectangle().height);
        shaperenderer.end();
	}

	private void update(float delta) {
		handleInput(delta);
		gamecam.translate(new Vector2(delta * VELOCITY,0));
		
		grid.update(delta, this);
		
		player.update(delta);
		
		for(GroundTile gt : grid.screengrid) {
			if (player.getBoundingRectangle().overlaps(gt.getBoundingRectangle()) && gt.isRigid) { player.setY(gt.getY() + gt.getHeight()); }
		}

		
		
        //update our game camera with correct coordinates after changes
        gamecam.update();		
		
	}

	private void handleInput(float delta) {
		if(Gdx.input.justTouched()) {
			Gdx.app.log("Game cam position X", Float.toString(gamecam.position.x));
			Gdx.app.log("Number of tiles",Integer.toString(RunFoxRun.gw / 70));
			Gdx.app.log("PlayScreen", Float.toString(Gdx.input.getX()));
			Gdx.app.log("Fox Bound Rect X", Float.toString(player.getBoundingRectangle().x));
			Gdx.app.log("Fox Get X", Float.toString(player.getX()));
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
	
}


	
	
