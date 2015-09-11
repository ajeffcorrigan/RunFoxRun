package com.ajeffcorrigan.runfoxrun.sprites;

import com.ajeffcorrigan.runfoxrun.tools.ScreenObjectContainer;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ScreenTile extends ScreenObjectContainer{

	public boolean isRigid;
	public Rectangle tileBounds;
	public boolean tileEmpty;
	
	public Vector2 tilePosition;
	public int tileSize;
	
	public ScreenTile() {
		
	}
	
	public ScreenTile(Vector2 startXY, int tileSize) {
		this.tilePosition = startXY;
		this.tileSize = tileSize;
		this.tileBounds = new Rectangle(tilePosition.x, tilePosition.y, tilePosition.x + this.tileSize, tilePosition.y + this.tileSize);
		this.tileEmpty = true;
	}
	
	public ScreenTile(Rectangle tileBounds, boolean tileEmpty) {
		this.tileBounds = tileBounds;
		this.tileEmpty = tileEmpty;
	}
	
	public ScreenTile(Sprite sprite, Vector2 startXY) {
		super(sprite,startXY);
		this.tileEmpty = false;
	}
}
