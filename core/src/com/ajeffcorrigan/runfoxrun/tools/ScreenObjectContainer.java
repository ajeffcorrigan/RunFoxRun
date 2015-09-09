package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class ScreenObjectContainer {
	
	protected World world;
	protected Body body;
	protected Sprite sprite;
	protected Fixture fixture;
	
	public ScreenObjectContainer(World world, Sprite sprite, Vector2 orginXY) {
		this.world = world;
		this.sprite = sprite;
		
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((orginXY.x + sprite.getWidth() / 2) / RunFoxRun.PPM, (orginXY.y + sprite.getHeight() / 2) / RunFoxRun.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(sprite.getWidth() / 2 / RunFoxRun.PPM, sprite.getHeight() / 2 / RunFoxRun.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
        
        sprite.setSize(sprite.getWidth() / RunFoxRun.PPM, sprite.getHeight() / RunFoxRun.PPM);
        body.setUserData(sprite);
		
	}

}
