package com.ajeffcorrigan.runfoxrun.sprites;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.tools.jAssets;
import com.ajeffcorrigan.runfoxrun.tools.jActor.State;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class foxActor extends Sprite {
	
	public enum State { RUNNING, JUMPINGUP, JUMPINGDOWN, DEAD, STANDING, FALLING};
	
	public State currentState;
	public State previousState;
	
	private Vector2 foxPosition;
	private Vector2 foxVelocity;
	private PlayScreen screen;
	
	private TextureRegion foxStand;
	private TextureRegion foxDead;
	private Animation foxRun;
	private Animation foxJumpUp;
	private Animation foxJumpDown;
		
	public foxActor(PlayScreen screen) {
		super();
		
		currentState = State.STANDING;
		previousState = State.STANDING;
		
		this.foxPosition = new Vector2(80,80);
		this.foxVelocity = new Vector2(0,screen.VELOCITY);
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 12; i++)
            frames.add(new TextureRegion(jAssets.getTexture("foxrunsheet2"), i * 130, 0, 130, 44));
        foxRun = new Animation(0.1f, frames);
        
        frames.clear();  
        
	}
	
	public void update(float delta) {
		this.foxPosition.mulAdd(foxVelocity, delta);
		setPosition(foxPosition.x,foxPosition.y);
	}
	
	public void setState(State newState) {
		previousState = currentState;
		currentState = newState;
		
		if(previousState == State.RUNNING && currentState == State.JUMPINGUP) {
			foxVelocity.y = screen.JUMP_IMPULSE;
		}
		
	}

}
