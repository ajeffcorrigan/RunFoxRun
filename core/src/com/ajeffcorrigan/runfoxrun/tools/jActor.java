package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class jActor {
	
	public enum State { RUNNING, JUMPINGUP, JUMPINGDOWN, DEAD, STANDING};
	public final float ACTOR_JUMP_IMPULSE = 350;							//Jump impulse
	public final float actorWeight = 2;									//Weight of actor.
	public final float GRAVITY = -10;										//Gravity force
	
	public Texture currentTexture;
	public State currentState;
	public State previousState;
	public Vector2 actorPosition;
	public Vector2 actorVelocity;
	private float stateTimer;
	
	public Circle headcircle;
	public Polyline actorBottomBound;
	public Rectangle actorBound;
	
	
	public jActor() {
		currentState = State.STANDING;
		previousState = State.STANDING;
	}

	
	public jActor(Texture texture) {
		this.currentTexture = texture;
		this.actorPosition = new Vector2(80,80);
		this.actorBound = new Rectangle(actorPosition.x,actorPosition.y,currentTexture.getWidth(),currentTexture.getHeight());
		this.actorVelocity = new Vector2(PlayScreen.VELOCITY,0);
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
	}

	public void update(float dt) {
		if (currentState == State.RUNNING) { actorVelocity.y = this.GRAVITY; } else { actorVelocity.add(0, this.GRAVITY); }	
		this.actorPosition.mulAdd(this.actorVelocity, dt);
		this.actorBound.setPosition(actorPosition);
	}
	
	public void setState(State newState) {
		this.previousState = this.currentState;
		this.currentState = newState;
	}
	
	public void draw(SpriteBatch sb) {
		sb.draw(currentTexture, actorPosition.x, actorPosition.y);
	}


	
}
