package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.sprites.GroundTile;
import com.ajeffcorrigan.runfoxrun.sprites.ScreenTile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GridRow {
	
	public String[][] grid;
	public Array<String>[] test;
	public Array<GroundTile> screengrid;
	
	
	public Array<ScreenTile> tiles;
	private int width;
	private Vector2 startXY;
	private Vector2 currentXY;
	private int tileSize;
	
	
	public GridRow(int width, Vector2 startXY, int tileSize) {
		this.width = width;
		this.startXY = startXY;
		this.tileSize = tileSize;
		this.currentXY = this.startXY;
		tiles = new Array<ScreenTile>(width);
		for(int i = 0; i < width; i++) {
			tiles.add(new ScreenTile(this.currentXY, tileSize));
			this.currentXY.add(this.tileSize, 0);
		}
	}
	
	public GridRow(int wsize, int hsize) {
		screengrid = new Array<GroundTile>(true,wsize);
		this.width = wsize;
		
	}
	
	public void addToGrid(Sprite sprite, Vector2 tilePos) {
		screengrid.add(new GroundTile(sprite,tilePos));
	}
	
	public void fillGrid(Sprite sprite, Vector2 startXY) {
		if(screengrid.size == 0) {
			Gdx.app.log("ScreenGrid","Array size is empty!");
			screengrid.add(new GroundTile(sprite,startXY));	
		}
		for(int i = 0; i < this.width; i++) {
			screengrid.add(new GroundTile(sprite,new Vector2((screengrid.peek().getX() + screengrid.peek().getWidth()),screengrid.peek().getY())));
		}
		
	}
	
	public void drawgrid(SpriteBatch sb) {
		for (GroundTile gt : screengrid) {
			gt.draw(sb);
		}
	}
	
	public void update(float dt, PlayScreen screen) {
		GroundTile gt = this.screengrid.first();	
		if(!screen.gamecam.frustum.pointInFrustum((gt.getX()+gt.getWidth()), 0, 0)) {
			this.screengrid.removeIndex(0);
			this.screengrid.add(new GroundTile(new Sprite(this.screengrid.peek().getTexture()),new Vector2((this.screengrid.peek().getX() + screengrid.peek().getWidth()),screengrid.peek().getY())));
		}
	}	
}
