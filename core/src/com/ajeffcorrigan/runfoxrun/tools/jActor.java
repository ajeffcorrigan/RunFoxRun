package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class jActor extends Sprite {
	
	public enum State { RUNNING, JUMPINGUP, JUMPINGDOWN, DEAD, STANDING, FALLING};
	protected final float ACTOR_JUMP_IMPULSE = 360;							//Jump impulse
	public final float actorWeight = 2;									//Weight of actor.
	public final float GRAVITY = -10;										//Gravity force
	
	public State currentState;
	public State previousState;
	public Vector2 actorPosition;
	public Vector2 actorVelocity;
	private float stateTimer;
	private TextureAtlas atlas;
	public Circle headcircle;
	public Polyline actorBottomBound;
	public Rectangle actorBound;
	private AtlasRegion atlasRegion;
	
	public jActor() {
		currentState = State.STANDING;
		previousState = State.STANDING;
	}

	
	public jActor(Texture texture) {
		super(texture);
		setPosition(80,80);
		
		this.actorPosition = new Vector2(80,80);
		this.actorBound = new Rectangle(actorPosition.x,actorPosition.y,currentTexture.getWidth(),currentTexture.getHeight());
		this.actorVelocity = new Vector2(PlayScreen.VELOCITY,0);
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		
		

        
        
	}

	public void update(float dt) {

		this.actorPosition.mulAdd(this.actorVelocity, dt);
		this.actorBound.setPosition(actorPosition);
	}
	
	public void setState(State newState) {
		this.previousState = this.currentState;
		this.currentState = newState;
	}
	


	
}
