package com.ajeffcorrigan.runfoxrun;

import com.badlogic.gdx.graphics.Texture;

public class jBackground {
	
	private Texture bgimage;	//Texture to load as image.
	private int bglevel;
	private float xCoord;		//X Location of the texture.
	private float yCoord;		//Y Location of the texture.
	private float bgSpeed;

	public jBackground() {
		// TODO Auto-generated constructor stub
	}
	
	public jBackground(Texture p_img) {
		// TODO Auto-generated constructor stub
		this.bgimage = p_img;
		this.bglevel = 0;
		this.xCoord = 0;
		this.yCoord = 0;
	}

	public jBackground(Texture p_img, float p_xc, float p_yc, int p_bglev, float p_speed) {
		// TODO Auto-generated constructor stub
		this.bgimage = p_img;
		this.bglevel = p_bglev;
		this.xCoord = p_xc;
		this.yCoord = p_yc;
		this.bgSpeed = p_speed;
	}
	
	public void updateBackground(float camera_x, float camera_y) {
		this.xCoord = camera_x / this.bgSpeed;
		this.yCoord = camera_y / this.bgSpeed;
	}
	
	public void updateBackgroundX(float camera_x) {
		this.xCoord = camera_x / this.bgSpeed;
	}

	public Texture getBgimage() {
		return bgimage;
	}

	public float getxCoord() {
		return xCoord;
	}
	
	public void setxCoord(float xCoord) {
		this.xCoord = xCoord;
	}

	public float getyCoord() {
		return yCoord;
	}
	
	

}
