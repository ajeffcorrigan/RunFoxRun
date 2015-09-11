package com.ajeffcorrigan.runfoxrun.sprites;

import com.ajeffcorrigan.runfoxrun.tools.ScreenObjectContainer;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class ScreenTile extends ScreenObjectContainer{

	public boolean isRigid; 
	
	public ScreenTile() {
		
	}
	
	public ScreenTile(Sprite sprite, Vector2 startXY) {
		super(sprite,startXY);
	}
}
