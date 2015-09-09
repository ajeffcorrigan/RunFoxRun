package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class B2WorldManager {

	public B2WorldManager(World world, Sprite sprite ) {
		//create body and fixture variables
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        Rectangle rect = sprite.getBoundingRectangle();

        bdef.type = BodyDef.BodyType.StaticBody;
        System.out.println(rect.getX());
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / RunFoxRun.PPM, (rect.getY() + rect.getHeight() / 2) / RunFoxRun.PPM);
        
        body = world.createBody(bdef);

        shape.setAsBox(rect.getWidth() / 2 / RunFoxRun.PPM, rect.getHeight() / 2 / RunFoxRun.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
        
        sprite.setSize(sprite.getWidth() / RunFoxRun.PPM, sprite.getHeight() / RunFoxRun.PPM);
        body.setUserData(sprite);
       
	}
	
	public void addTile(World world, Sprite sprite) {
		//create body and fixture variables
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        Rectangle rect = sprite.getBoundingRectangle();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / RunFoxRun.PPM, (rect.getY() + rect.getHeight() / 2) / RunFoxRun.PPM);
        
        body = world.createBody(bdef);

        shape.setAsBox(rect.getWidth() / 2 / RunFoxRun.PPM, rect.getHeight() / 2 / RunFoxRun.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
        
        sprite.setSize(sprite.getWidth() / RunFoxRun.PPM, sprite.getHeight() / RunFoxRun.PPM);
        body.setUserData(sprite);
	}
}
