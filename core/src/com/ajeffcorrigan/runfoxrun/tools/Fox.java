package com.ajeffcorrigan.runfoxrun.tools;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Fox extends Sprite {
	
	public enum State { RUNNING, JUMPINGUP, JUMPINGDOWN, DEAD, STANDING};
	public static final float FOX_JUMP_IMPULSE = 330;							//Jump impulse
	public State currentState;
	public State previousState;
	
	
	public Fox() {
		super(jAssets.getTexture("foxstill"));
		currentState = State.STANDING;
		previousState = State.STANDING;
		setPosition(40,40);

	}
	
	public void update(float dt) {
		super.setPosition(getX() * dt, getY());
	}
	
}
