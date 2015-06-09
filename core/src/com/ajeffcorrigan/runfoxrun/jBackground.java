package com.ajeffcorrigan.runfoxrun;

import java.util.Comparator;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class jBackground extends ScreenObjectContainer implements Comparator<jBackground> {
	
	private int bglevel;

	public jBackground() {
		// TODO Auto-generated constructor stub
	}
	
	public jBackground(Texture p_img) {
		super.setItemImg(p_img);
		this.bglevel = 0;
		this.bgPos = new Vector2(0,0);
		this.speedMultiplier = 1;
		this.reSpawn = false;
		this.imgHeight = this.getImageHeight();
		this.imgWidth = this.getImageWidth();
	}
	
	public jBackground(Texture p_img, int p_level) {
		super.setItemImg(p_img);
		this.bglevel = p_level;
		this.bgPos = new Vector2(0,0);
		this.speedMultiplier = 1;
		this.reSpawn = false;
		this.imgHeight = this.getImageHeight();
		this.imgWidth = this.getImageWidth();
	}

	public jBackground(Texture p_img, Vector2 p_pos, int p_bglev, float p_speed) {
		// TODO Auto-generated constructor stub
		super.setItemImg(p_img);
		this.bglevel = p_bglev;
		this.bgPos = new Vector2(p_pos);
		this.speedMultiplier = p_speed;
		this.imgHeight = this.getImageHeight();
		this.imgWidth = this.getImageWidth();
	}
	
	public jBackground(Texture p_img, Vector2 p_pos, int p_bglev, float p_speed, boolean p_respawn) {
		// TODO Auto-generated constructor stub
		super.setItemImg(p_img);
		this.bglevel = p_bglev;
		this.bgPos = new Vector2(p_pos);
		this.speedMultiplier = p_speed;
		this.reSpawn = p_respawn;
		this.imgHeight = this.getImageHeight();
		this.imgWidth = this.getImageWidth();
	}
	
	public jBackground(Texture p_img, Vector2 p_pos, int p_bglev, boolean p_respawn) {
		// TODO Auto-generated constructor stub
		super.setItemImg(p_img);
		this.bglevel = p_bglev;
		this.bgPos = new Vector2(p_pos);
		this.speedMultiplier = 1;
		this.reSpawn = p_respawn;
		this.imgHeight = this.getImageHeight();
		this.imgWidth = this.getImageWidth();
	}
	
	
	public jBackground(Texture p_img, Vector2 p_pos, int p_bglev, float p_speed, boolean p_respawn, float p_scale) {
		// TODO Auto-generated constructor stub
		super.setItemImg(p_img);
		this.bglevel = p_bglev;
		this.bgPos = new Vector2(p_pos);
		this.speedMultiplier = p_speed;
		this.reSpawn = p_respawn;
		this.imgHeight = this.getImageHeight() * p_scale;
		this.imgWidth = this.getImageWidth() * p_scale;
	}
	
	public void updateBackgroundX(float dtime) {
		this.bgPos.x += (dtime * (RunFoxRun.GAME_SPEED * this.speedMultiplier)); 
	}
	public float getxCoord() { 
		return this.bgPos.x; 
	}
	public void setxCoord(float xCoord) { 
		this.bgPos.x = xCoord; 
	}
	public float getyCoord() { 
		return this.bgPos.y; 
	}
	
	public float getWidthXCoord() {
		return this.imgWidth + this.bgPos.x;
	}

	/**
	 * @return the bglevel
	 */
	public int getBglevel() { return bglevel; }

	/**
	 * @param bglevel the bglevel to set
	 */
	public void setBglevel(int bglevel) { this.bglevel = bglevel; }

	/**
	 * @return the reSpawn
	 */
	public boolean isReSpawn() { return reSpawn; }

	/**
	 * @param reSpawn the reSpawn to set
	 */
	public void setReSpawn(boolean reSpawn) {
		this.reSpawn = reSpawn;
	}
	
	public int getImageWidth() {
		return this.bgimage.getWidth();
	}
	
	public int getImageHeight() {
		return this.bgimage.getHeight();
	}

	@Override
	public int compare(jBackground bgi1, jBackground bgi2) {
		// TODO Auto-generated method stub
		return bgi2.bglevel - bgi1.bglevel;
	}
	
	public void draw(SpriteBatch sb) {
		sb.draw(this.bgimage, this.bgPos.x, this.bgPos.y, this.imgWidth, this.imgHeight);
	}
	
	public void addToMultiplier(float upSpd) {
		this.speedMultiplier += upSpd;
	}

}
