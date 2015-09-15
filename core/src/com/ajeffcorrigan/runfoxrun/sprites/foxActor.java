package com.ajeffcorrigan.runfoxrun.sprites;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.tools.jAssets;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class foxActor extends Sprite {
	
	public enum State { RUNNING, JUMPINGUP, JUMPINGDOWN, DEAD, STANDING, FALLING};
	
	private final int boundsOffset = 55;
		
	public State currentState;
	public State previousState;
	
	private float foxYImpulse;
	private float stateTime;
	
	private TextureRegion foxStand;
	private TextureRegion foxDead;
	private Animation foxRun;
	private Animation foxJumpUp;
	private Animation foxJumpDown;
	
	private Circle foxHead; 
	public Rectangle foxBounds;
	public Ellipse foxBody;
			
	public foxActor() {
		super();
		
		currentState = State.STANDING;
		previousState = State.STANDING;
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 12; i++)
            frames.add(new TextureRegion(jAssets.getTexture("foxrunning"), i * 135, 0, 135, 45));
        foxRun = new Animation(0.08f, frames);
        
        frames.clear();
        
        foxYImpulse = PlayScreen.GRAVITY;
        
        super.set(new Sprite(jAssets.getTexture("foxstill")));
        setPosition(80,80);
        
        foxBounds = new Rectangle(getX()+boundsOffset,getY(),55,getHeight());
        foxHead = new Circle(foxBounds.x + (foxBounds.width / 1.4f),foxBounds.y+(foxBounds.height/1.8f),16);
        foxBody = new Ellipse(foxBounds.x,foxBounds.y+(foxBounds.height/3.4f),foxBounds.width/2,foxBounds.height/1.8f);
        
	}
	
	public void update(float delta) {
		if (currentState == State.JUMPINGUP) { foxYImpulse += PlayScreen.GRAVITY; }
		if (currentState == State.RUNNING) { foxYImpulse = PlayScreen.GRAVITY; }
		if (currentState == State.FALLING) { foxYImpulse += PlayScreen.GRAVITY * (1 + delta); }
		setRegion(getFrame(delta));
		translate(PlayScreen.VELOCITY * delta,foxYImpulse * delta);
		foxBounds.setPosition(getX()+boundsOffset,getY());
		foxHead.setPosition(foxBounds.x + (foxBounds.width / 1.4f),foxBounds.y+(foxBounds.height/ 1.8f));
		foxBody.setPosition(foxBounds.x,foxBounds.y+(foxBounds.height/3.4f));
	}
	

	public void setState(State newState) {
		previousState = currentState;
		currentState = newState;
		
		if(previousState == State.RUNNING && currentState == State.JUMPINGUP) {
			this.foxYImpulse = PlayScreen.JUMP_IMPULSE;
		}
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
	
	public void drawBounds(ShapeRenderer shaperenderer) {
		shaperenderer.rect(foxBounds.x, foxBounds.y, foxBounds.width, foxBounds.height);
		shaperenderer.circle(foxHead.x, foxHead.y, foxHead.radius);
		shaperenderer.ellipse(foxBody.x, foxBody.y, foxBody.width, foxBody.height);
	}

}
