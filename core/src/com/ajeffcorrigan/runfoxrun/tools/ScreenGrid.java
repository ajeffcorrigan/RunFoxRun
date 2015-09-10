package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.sprites.GroundTile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class ScreenGrid {
	
	public Array<GroundTile> screengrid;
	private int capacity;
	
	public ScreenGrid(int wsize, int hsize) {
		screengrid = new Array<GroundTile>(true,wsize);
		this.capacity = wsize;
	}
	
	public void fillGrid(Sprite sprite, Vector2 startXY) {
		if(screengrid.size == 0) {
			Gdx.app.log("ScreenGrid","Array size is empty!");
			screengrid.add(new GroundTile(sprite,startXY));
			
		}
		for(int i = 0; i < this.capacity; i++) {
			screengrid.add(new GroundTile(sprite,new Vector2((screengrid.peek().getX() + screengrid.peek().getWidth()),screengrid.peek().getY())));
		}
		
	}
	
	public void drawgrid(SpriteBatch sb) {
		for (GroundTile gt : screengrid) {
			gt.draw(sb);
		}
	}
	
	public void update(float dt, PlayScreen screen) {
		GroundTile gt = screengrid.first();	
		if(!screen.gamecam.frustum.pointInFrustum((gt.getX()+gt.getWidth()), 0, 0)) {
			screengrid.removeIndex(0);
			screengrid.add(new GroundTile(new Sprite(screengrid.peek().getTexture()),new Vector2((screengrid.peek().getX() + screengrid.peek().getWidth()),screengrid.peek().getY())));
		}
	}
	
}
