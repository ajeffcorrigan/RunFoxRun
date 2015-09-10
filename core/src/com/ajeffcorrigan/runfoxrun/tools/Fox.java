package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Fox extends Sprite {
	
	public enum State { RUNNING, JUMPINGUP, JUMPINGDOWN, DEAD, STANDING};
	public static final float FOX_JUMP_IMPULSE = 330;							//Jump impulse
	public static final float foxWeight = 25;
	public State currentState;
	public State previousState;
	public float foxYVelocity;
	
	
	public Fox() {
		super(jAssets.getTexture("foxstill"));
		currentState = State.STANDING;
		previousState = State.STANDING;
		setPosition(100,100);
	}
	
	public void update(float dt) {
		translateX(dt * PlayScreen.VELOCITY);
		translateY(dt * PlayScreen.GRAVITY * foxWeight);
	}
	
}
