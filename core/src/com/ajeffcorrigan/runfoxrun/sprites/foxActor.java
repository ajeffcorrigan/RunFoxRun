package com.ajeffcorrigan.runfoxrun.sprites;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.tools.jAssets;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class foxActor extends Sprite {
	
	public enum State { RUNNING, JUMPINGUP, JUMPINGDOWN, DEAD, STANDING, FALLING};
	
	public State currentState;
	public State previousState;
	
	private float stateTime;
	
	private TextureRegion foxStand;
	private TextureRegion foxDead;
	private Animation foxRun;
	private Animation foxJumpUp;
	private Animation foxJumpDown;
	
	public World world;
	public Body b2body;
			
	public foxActor(PlayScreen screen) {
		super();
		
		currentState = State.STANDING;
		previousState = State.STANDING;
		this.world = screen.getWorld();
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 12; i++)
            frames.add(new TextureRegion(jAssets.getTexture("foxrunning"), i * 135, 0, 135, 45));
        foxRun = new Animation(0.08f, frames);
        
        frames.clear();
          
        super.set(new Sprite(jAssets.getTexture("foxstill")));
        setBounds(0,0, getWidth() / RunFoxRun.PTM, getHeight() / RunFoxRun.PTM);
        defineBody();
        
        b2body.setLinearVelocity(2.5f, 0);
          
	}
	
	private void defineBody() {
		
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        //Set initial body position, since sprite is attached this is starting sprite position too.
        bdef.position.set(120 / RunFoxRun.PTM, 80 / RunFoxRun.PTM);

        b2body = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        
        CircleShape foxhead = new CircleShape();
        foxhead.setPosition(new Vector2(getWidth() / 3f,0));
        foxhead.setRadius(16 / RunFoxRun.PTM);
        fixtureDef.shape = foxhead;
        b2body.createFixture(fixtureDef);
        
        PolygonShape foxbody = new PolygonShape();
        foxbody.setAsBox(super.getWidth() / 5f, super.getHeight() / 3.5f, new Vector2(13 / RunFoxRun.PTM ,0), 0);
        fixtureDef.shape = foxbody;
        b2body.createFixture(fixtureDef);
        
        EdgeShape foxfeet = new EdgeShape();
        foxfeet.set(new Vector2(-(getWidth() / 9f), -(getHeight()/2.7f)),new Vector2(getWidth() / 3.1f, -(getHeight()/2.7f)));
        fixtureDef.shape = foxfeet;
        b2body.createFixture(fixtureDef);
        
        foxhead.dispose();
        foxfeet.dispose();
        foxbody.dispose();
        

	}

	public void update(float delta) {
		/*
		if (currentState == State.JUMPINGUP) { foxYImpulse += PlayScreen.GRAVITY; }
		if (currentState == State.RUNNING) { foxYImpulse = PlayScreen.GRAVITY; }
		if (currentState == State.FALLING) { foxYImpulse += PlayScreen.GRAVITY * (1 + delta); }
		setRegion(getFrame(delta));
		translate(PlayScreen.VELOCITY * delta,foxYImpulse * delta);
		foxBounds.setPosition(getX()+boundsOffset,getY());
		foxHead.setPosition(foxBounds.x + (foxBounds.width / 1.4f),foxBounds.y+(foxBounds.height/ 1.8f));
		foxBody.setPosition(foxBounds.x,foxBounds.y+(foxBounds.height/3.4f));
		*/
		//b2body.setLinearVelocity(2.5f, b2body.getLinearVelocity().y);
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
		setRegion(getFrame(delta));
	}
	

	public void setState(State newState) {
		previousState = currentState;
		currentState = newState;		
	}
	
	public TextureRegion getFrame(float dt) {
		TextureRegion region;
		
		switch (currentState) {
			case RUNNING:
				region = foxRun.getKeyFrame(stateTime, true);
				break;
			default:
				region = foxRun.getKeyFrame(stateTime, true);
				break;
		}
		
		stateTime = currentState == previousState ? stateTime + dt : 0;
		return region;
	}
	
}
