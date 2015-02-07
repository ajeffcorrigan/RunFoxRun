package com.ajeffcorrigan.runfoxrun;

import java.util.Comparator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class jBackground implements Comparator<jBackground>{
	
	private Texture bgimage;	//Texture to load as image.
	private int bglevel;
	private float xCoord;		//X Location of the texture.
	private float yCoord;		//Y Location of the texture.
	private float bgSpeed;
	private boolean reSpawn;
	private float imgHeight;
	private float imgWidth;
	public String bgName;

	public jBackground() {
		// TODO Auto-generated constructor stub
	}
	
	public jBackground(Texture p_img) {
		this.bgimage = p_img;
		this.bglevel = 0;
		this.xCoord = 0;
		this.yCoord = 0;
		this.bgSpeed = 0;
		this.reSpawn = false;
		this.imgHeight = this.getImageHeight();
		this.imgWidth = this.getImageWidth();
	}
	
	public jBackground(Texture p_img, int p_level) {
		this.bgimage = p_img;
		this.bglevel = p_level;
		this.xCoord = 0;
		this.yCoord = 0;
		this.bgSpeed = 0;
		this.reSpawn = false;
		this.imgHeight = this.getImageHeight();
		this.imgWidth = this.getImageWidth();
	}

	public jBackground(Texture p_img, float p_xc, float p_yc, int p_bglev, float p_speed) {
		// TODO Auto-generated constructor stub
		this.bgimage = p_img;
		this.bglevel = p_bglev;
		this.xCoord = p_xc;
		this.yCoord = p_yc;
		this.bgSpeed = p_speed;
		this.imgHeight = this.getImageHeight();
		this.imgWidth = this.getImageWidth();
	}
	
	public jBackground(Texture p_img, float p_xc, float p_yc, int p_bglev, float p_speed, boolean p_respawn) {
		// TODO Auto-generated constructor stub
		this.bgimage = p_img;
		this.bglevel = p_bglev;
		this.xCoord = p_xc;
		this.yCoord = p_yc;
		this.bgSpeed = p_speed;
		this.reSpawn = p_respawn;
		this.imgHeight = this.getImageHeight();
		this.imgWidth = this.getImageWidth();
	}
	
	public jBackground(Texture p_img, float p_xc, float p_yc, int p_bglev, float p_speed, boolean p_respawn, float p_scale) {
		// TODO Auto-generated constructor stub
		this.bgimage = p_img;
		this.bglevel = p_bglev;
		this.xCoord = p_xc;
		this.yCoord = p_yc;
		this.bgSpeed = p_speed;
		this.reSpawn = p_respawn;
		this.imgHeight = this.getImageHeight() * p_scale;
		this.imgWidth = this.getImageWidth() * p_scale;
	}
	
	public void updateBackgroundX(float dtime) { this.xCoord += (dtime * this.bgSpeed); }
	public Texture getBgimage() { return bgimage; }
	public float getxCoord() { return xCoord; }
	public void setxCoord(float xCoord) { this.xCoord = xCoord; }
	public float getyCoord() { return yCoord; }
	
	public float getWidthXCoord() {
		return this.imgWidth + this.xCoord;
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
		sb.draw(this.bgimage, this.xCoord, this.yCoord, this.imgWidth, this.imgHeight);
	}

}
