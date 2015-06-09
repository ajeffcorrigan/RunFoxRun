package com.ajeffcorrigan.runfoxrun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class jLiveActor extends ScreenObjectContainer {
		
	private boolean deadly;					//Is item considered deadly.
	
	public jLiveActor(Texture p_img) {
		super(p_img);
		this.deadly = false;
	}
	
	public jLiveActor(Texture p_img, Vector2 p_pos) {
		super(p_img,p_pos,1);
		this.deadly = false;
	}
	
	public jLiveActor(Texture p_img, Vector2 p_pos, float p_speed) {
		super(p_img,p_pos,1);
		super.setSpeedMultiplier(p_speed);
		this.deadly = false;
	}
	
	public jLiveActor(Texture p_img, Vector2 p_pos, float p_speed, boolean p_respawn) {
		super(p_img,p_pos,1);
		super.setSpeedMultiplier(p_speed);
		super.setReSpawn(p_respawn);
		this.deadly = false;
	}
	
	public jLiveActor(Texture p_img, Vector2 p_pos, float p_speed, boolean p_respawn, float p_imgscale) {
		super(p_img,p_pos,p_imgscale);
		super.setSpeedMultiplier(p_speed);
		super.setReSpawn(p_respawn);
		this.deadly = false;
	}
	
	public jLiveActor(Texture p_img, Vector2 p_pos, float p_speed, boolean p_respawn, float p_imgscale, boolean p_deadly) {
		super(p_img,p_pos,p_imgscale);
		super.setSpeedMultiplier(p_speed);
		super.setReSpawn(p_respawn);
		this.deadly = p_deadly;
	}
	
	public void updateLiveActorX(float dtime) {
		this.actorPos.x += (dtime * (RunFoxRun.GAME_SPEED * this.speedMultiplier));
		this.updateBounds();
	}
	
	public void draw(SpriteBatch sb) {
		sb.draw(this.itemImg, this.actorPos.x, this.actorPos.y, this.imgWidth, this.imgHeight);
	}

	public float getWidthXCoord() {
		return this.imgWidth + this.actorPos.x;
	}

	public boolean isReSpawn() {
		return super.isReSpawn();
	}

	public void setxCoord(int i) {
		this.actorPos.x = i;
		this.updateBounds();
	}
	
	private void updateBounds() {
		this.itemBounds.setPosition(actorPos);
	}
	
	public Rectangle getBounds() {
		return this.itemBounds;
	}
	
	public void addToMultiplier(float upSpd) {
		this.speedMultiplier += upSpd;
	}
	
	public boolean isDeadly() {
		return this.deadly;
	}
	

}
