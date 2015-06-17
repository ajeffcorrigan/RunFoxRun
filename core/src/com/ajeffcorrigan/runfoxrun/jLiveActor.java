package com.ajeffcorrigan.runfoxrun;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class jLiveActor {
	
	private Vector2 actorVelocity;
	private Vector2 actorPos;
	private Rectangle actorBounds;
	private Vector2 boundOffset;
	
	public jLiveActor() {
		// TODO Auto-generated constructor stub
	}
	
	public jLiveActor(Vector2 start_pos) {
		this.actorPos = start_pos;
		this.setActorVelocity(new Vector2(0,0));
		this.actorBounds = new Rectangle();
	}
	
	public jLiveActor(Vector2 start_pos,Vector2 bnd_box) {
		this.actorPos = start_pos;
		this.setActorVelocity(new Vector2(0,0));
		this.actorBounds = new Rectangle(0,0,bnd_box.x,bnd_box.y);
	}
	

	/**
	 * @return the actorPos
	 */
	public Vector2 getActorPos() {
		return actorPos;
	}

	/**
	 * @param aPos the actorPos to set
	 */
	public void setActorPos(Vector2 aPos) {
		this.actorPos.set(aPos);
		this.updateActorBounds();
	}
	
	/**
	 * @param aPos the actorPos to set
	 */
	public void setActorPosY(float yVal) {
		this.actorPos.y = yVal;
		this.updateActorBounds();
	}

	/**
	 * @return the actorVelocity
	 */
	public Vector2 getActorVelocity() {
		return actorVelocity;
	}

	/**
	 * @param av the actorVelocity to set
	 */
	public void setActorVelocity(Vector2 av) {
		this.actorVelocity = av;
	}
	/**
	 * @param y_vel the y velocity to set
	 */
	public void setActorVelocityY(float y_vel) {
		this.actorVelocity.y = y_vel;
	}
	/**
	 * @param v2_add the actorVelocity to set
	 */
	public void addToVelocity(Vector2 v2_add) {
		this.actorVelocity.add(v2_add);
	}

	/**
	 * @return the actorBounds
	 */
	public Rectangle getActorBounds() {
		return actorBounds;
	}

	/**
	 * @param actorBounds the actorBounds to set
	 */
	public void setActorBounds(Rectangle actorBounds) {
		this.actorBounds = actorBounds;
	}
	
	/**
	 * @param actorBounds the actorBounds to set
	 */
	private void updateActorBounds() {
		this.actorBounds.setPosition(this.actorPos.x + this.boundOffset.x, this.actorPos.y + this.boundOffset.y);
	}

	/**
	 * @return the boundOffset
	 */
	public Vector2 getBoundOffset() {
		return boundOffset;
	}

	/**
	 * @param boundOffset the boundOffset to set
	 */
	public void setBoundOffset(Vector2 boundOffset) {
		this.boundOffset = boundOffset;
	}
	
	public void moveActor(float d_time) {
		this.actorPos.mulAdd(this.actorVelocity, d_time);
		this.updateActorBounds();
	}

}
