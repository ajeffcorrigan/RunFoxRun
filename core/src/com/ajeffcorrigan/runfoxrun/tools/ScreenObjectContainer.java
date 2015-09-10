package com.ajeffcorrigan.runfoxrun.tools;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class ScreenObjectContainer extends Sprite {
	
	public ScreenObjectContainer(Sprite sprite, Vector2 startXY) {
		super(sprite);
		setPosition(startXY.x, startXY.y);
	}
	
}
	


		
