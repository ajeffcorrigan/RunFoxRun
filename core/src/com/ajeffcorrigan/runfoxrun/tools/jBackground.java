package com.ajeffcorrigan.runfoxrun.tools;

import java.util.Comparator;

import com.ajeffcorrigan.runfoxrun.ScreenObjectContainer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class jBackground extends ScreenObjectContainer implements Comparator<jBackground> {
	
	private int bglevel;

	public jBackground() {
		super();
	}
	
	public jBackground(Texture p_img) {
		super(p_img);
		this.bglevel = 0;
	}
	
	public jBackground(Texture p_img, int p_level) {
		super(p_img);
		this.bglevel = p_level;
	}

	public jBackground(Texture p_img, Vector2 p_pos, int p_bglev, float p_speed) {
		// TODO Auto-generated constructor stub
		super(p_img,p_pos,1);
		super.setSpeedMultiplier(p_speed);
		this.bglevel = p_bglev;
	}
	
	public jBackground(Texture p_img, Vector2 p_pos, int p_bglev, float p_speed, boolean p_respawn) {
		// TODO Auto-generated constructor stub
		super(p_img,p_pos,1);
		super.setSpeedMultiplier(p_speed);
		super.setReSpawn(p_respawn);
		this.bglevel = p_bglev;
	}
	
	public jBackground(Texture p_img, Vector2 p_pos, int p_bglev, boolean p_respawn) {
		// TODO Auto-generated constructor stub
		super(p_img,p_pos,1);
		this.bglevel = p_bglev;
		super.setReSpawn(p_respawn);
	}
	
	public jBackground(Texture p_img, Vector2 p_pos, int p_bglev, float p_speed, boolean p_respawn, float p_scale) {
		// TODO Auto-generated constructor stub
		super(p_img,p_pos,p_scale);
		this.bglevel = p_bglev;
		super.setSpeedMultiplier(p_speed);
		super.setReSpawn(p_respawn);
	}
	
	public void updateBackgroundX(float dtime) {
		super.updateItemX(dtime); 
	}

	/**
	 * @return the bglevel
	 */
	public int getBglevel() { return bglevel; }

	/**
	 * @param bglevel the bglevel to set
	 */
	public void setBglevel(int bglevel) { this.bglevel = bglevel; }
	
	@Override
	public int compare(jBackground bgi1, jBackground bgi2) {
		// TODO Auto-generated method stub
		return bgi2.bglevel - bgi1.bglevel;
	}
	
}
