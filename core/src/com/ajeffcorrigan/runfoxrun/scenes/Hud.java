package com.ajeffcorrigan.runfoxrun.scenes;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Hud implements Disposable {
	
	//Scene2d stage and viewport
	public Stage stage;
	private Viewport viewport;
	private BitmapFont bmfont;
	
	//game variables
	public int coinsGrabbed;
	public int distanceRan;
	
	//Scene2d widgets
	private Label distanceLabel;
	private Label coinsLabel;
	private Label distanceRanLabel;
	private Label coinsGrabbedLabel;
			
	public Hud(SpriteBatch sb) {
		coinsGrabbed = 0;
		distanceRan = 0;
		
		viewport = new FitViewport(RunFoxRun.G_WIDTH, RunFoxRun.G_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport,sb);
		
		bmfont = new BitmapFont(Gdx.files.internal("KenPixel.fnt"));
		
		Table table = new Table();
		
		table.top();
		
		table.setFillParent(true);
		
		distanceLabel = new Label("Distance:", new Label.LabelStyle(bmfont, Color.BLACK));
		coinsLabel = new Label("Coins:", new Label.LabelStyle(bmfont, Color.BLACK));
		distanceRanLabel = new Label(String.format("%03d", distanceRan), new Label.LabelStyle(bmfont, Color.BLACK));
		coinsGrabbedLabel = new Label(String.format("%03d", coinsGrabbed), new Label.LabelStyle(bmfont, Color.BLACK));
		
		table.left();
		table.add(distanceLabel);
		table.add(distanceRanLabel).padRight(20);
		table.add(coinsLabel);
		table.add(coinsGrabbedLabel);
		
		stage.addActor(table);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
}
