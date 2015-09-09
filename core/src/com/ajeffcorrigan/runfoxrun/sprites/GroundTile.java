package com.ajeffcorrigan.runfoxrun.sprites;

import com.ajeffcorrigan.runfoxrun.tools.ScreenObjectContainer;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GroundTile extends ScreenObjectContainer {

	public GroundTile(World world, Sprite sprite, Vector2 orginXY) {
		super(world, sprite, orginXY);
	}

}
