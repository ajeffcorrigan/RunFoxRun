package com.ajeffcorrigan.runfoxrun.tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class jStaticActor extends ScreenObjectContainer {
		
	private boolean deadly;						//Is item considered deadly.
	
	public jStaticActor(Texture p_img) {
		super(p_img);
		this.deadly = false;
	}
	
	public jStaticActor(Texture p_img, Vector2 p_pos) {
		super(p_img,p_pos,1);
		this.deadly = false;
	}
	
	public jStaticActor(Texture p_img, Vector2 p_pos, float p_speed) {
		super(p_img,p_pos,1);
		super.setSpeedMultiplier(p_speed);
		this.deadly = false;
	}
	
	public jStaticActor(Texture p_img, Vector2 p_pos, float p_speed, boolean p_respawn) {
		super(p_img,p_pos,1);
		super.setSpeedMultiplier(p_speed);
		super.setReSpawn(p_respawn);
		this.deadly = false;
	}
	
	public jStaticActor(Texture p_img, Vector2 p_pos, float p_speed, boolean p_respawn, float p_imgscale) {
		super(p_img,p_pos,p_imgscale);
		super.setSpeedMultiplier(p_speed);
		super.setReSpawn(p_respawn);
		this.deadly = false;
	}
	
	public jStaticActor(Texture p_img, Vector2 p_pos, float p_speed, boolean p_respawn, float p_imgscale, boolean p_deadly) {
		super(p_img,p_pos,p_imgscale);
		super.setSpeedMultiplier(p_speed);
		super.setReSpawn(p_respawn);
		this.deadly = p_deadly;
	}
	
	/**
	 * @param dtime is the delta time
	 */
	public void updActorX(float dtime) {
		super.updateItemX(dtime);
	}
	
	public boolean isDeadly() {
		return this.deadly;
	}

}
