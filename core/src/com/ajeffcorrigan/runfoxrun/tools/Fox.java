package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Fox extends Sprite{
	
	public enum State { RUNNING, JUMPINGUP, JUMPINGDOWN, DEAD, STANDING};
	public State currentState;
	public State previousState;
	public World world;
	public Body b2body;
	
	private Texture foxstand;
	private float stateTimer;
	
	public Fox(World world, PlayScreen screen) {
		//super(jAssets.getTexture("foxstill"));
		this.world = world;
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		foxstand = jAssets.getTexture("foxstill");
		
		defineFox();
	}
	
	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
	}
	
	public void defineFox() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(32 / RunFoxRun.PPM, 32 / RunFoxRun.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(6 / RunFoxRun.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
		b2body.setLinearVelocity(60 / RunFoxRun.PPM, 0f);
		
	}

	
}
