package com.ajeffcorrigan.runfoxrun.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ScreenTile extends Sprite{

	public boolean isRigid;
	public Rectangle tileBounds;
	public boolean tileEmpty;
	
	public Vector2 tilePosition;
	public int tileSize;
	
	
	public ScreenTile() { }
	
	public ScreenTile(Vector2 startXY, int tileSize) {
		this.tilePosition = new Vector2(startXY);
		this.tileSize = tileSize;
		this.tileBounds = new Rectangle(this.tilePosition.x, this.tilePosition.y, this.tileSize, this.tileSize);
		this.tileEmpty = true;
	}
	
	public ScreenTile(Vector2 startXY, int tileSize, Sprite sprite) {
		super(sprite);
		this.tilePosition = new Vector2(startXY);
		super.setPosition(tilePosition.x,tilePosition.y);
		this.tileSize = tileSize;
		this.tileBounds = new Rectangle(this.tilePosition.x, this.tilePosition.y, this.tileSize, this.tileSize);
		this.tileEmpty = false;
		this.isRigid = true;
	}
	
	public void setRigidSprite(Sprite sprite) {
		set(sprite);
		setPosition(this.tilePosition.x, this.tilePosition.y);
		this.tileEmpty = false;
		this.isRigid = true;
	}
}
