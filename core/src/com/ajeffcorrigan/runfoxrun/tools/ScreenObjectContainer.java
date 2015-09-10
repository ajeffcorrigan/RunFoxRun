package com.ajeffcorrigan.runfoxrun.tools;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class ScreenObjectContainer extends Sprite {
	
	protected Sprite sprite;
	
	public ScreenObjectContainer(Sprite sprite, Vector2 startXY) {
		sprite.setPosition(startXY.x, startXY.y);
		this.sprite = sprite;
	}
	
}
	


		
