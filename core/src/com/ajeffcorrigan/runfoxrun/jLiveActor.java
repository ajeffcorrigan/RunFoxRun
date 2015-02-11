package com.ajeffcorrigan.runfoxrun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class jLiveActor{
	
	private Texture itemImg;	//Texture to load as image.
	private Vector2 actorPos;
	private float bgSpeed;		
	private boolean reSpawn;
	private float imgHeight;
	private float imgWidth;
	private Rectangle itemBounds;
	public String bgName;
	
	
	public jLiveActor() {
		// TODO Auto-generated constructor stub
	}
	
	public jLiveActor(Texture p_img) {
		this.itemImg = p_img;
		this.actorPos.set(0, 0);
		this.bgSpeed = 0;
		this.reSpawn = false;
		this.imgHeight = this.getImageHeight();
		this.imgWidth = this.getImageWidth();
		this.itemBounds = new Rectangle(this.actorPos.x, this.actorPos.y, this.imgWidth, this.imgHeight);
	}

	private float getImageWidth() {
		// TODO Auto-generated method stub
		return this.itemImg.getWidth();
	}

	private float getImageHeight() {
		// TODO Auto-generated method stub
		return this.itemImg.getHeight();
	}
	
	public void updateLiveActor(Vector2 newPos) {
		this.actorPos.set(newPos);
		this.itemBounds.setPosition(newPos);
	}
	
	

}
