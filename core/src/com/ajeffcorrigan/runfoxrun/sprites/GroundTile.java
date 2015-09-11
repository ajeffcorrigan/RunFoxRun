package com.ajeffcorrigan.runfoxrun.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GroundTile extends ScreenTile {

	public Rectangle bounds;
	public boolean isRigid;
	
	public GroundTile(Sprite sprite, Vector2 orginXY) {
		super(sprite, orginXY);
		this.bounds = sprite.getBoundingRectangle();
		this.isRigid = true;
	}
}
