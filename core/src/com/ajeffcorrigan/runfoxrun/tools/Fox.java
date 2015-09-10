package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Vector2;

public class Fox extends Sprite {
	
	public enum State { RUNNING, JUMPINGUP, JUMPINGDOWN, DEAD, STANDING};
	public static final float FOX_JUMP_IMPULSE = 330;							//Jump impulse
	public static final float foxWeight = 25;
	public State currentState;
	public State previousState;
	public float foxYVelocity;
	public Circle headcircle;
	public Polyline foxfeet;
	
	
	public Fox() {
		super(jAssets.getTexture("foxstill"));
		currentState = State.STANDING;
		previousState = State.STANDING;
		setPosition(80,100);
		headcircle = new Circle(new Vector2(getX() + getWidth() - 18, getY() + (getHeight()/2) + 5), 15);

	}
	
	public void update(float dt) {
		super.translateX(dt * PlayScreen.VELOCITY);
		super.translateY(dt * PlayScreen.GRAVITY * foxWeight);
		headcircle.setPosition(getX() + getWidth() - 18, getY() + (getHeight()/2) + 5);
	}
	
}
