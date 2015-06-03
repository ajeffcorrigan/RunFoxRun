package com.ajeffcorrigan.runfoxrun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class jLiveActor{
	
	private Texture itemImg;				//Texture to load as image.
	private Vector2 actorPos;		
	private boolean reSpawn;
	private float imgHeight;
	private float imgWidth;
	private Rectangle itemBounds;
	public String bgName;
	private float speedMultiplier;
	private boolean deadly;					//Is item considered deadly.
	
	public jLiveActor(Texture p_img) {
		this.itemImg = p_img;
		this.actorPos = new Vector2(0,0);
		this.speedMultiplier = 1;
		this.reSpawn = false;
		this.imgHeight = this.itemImg.getHeight();
		this.imgWidth = this.itemImg.getWidth();
		this.itemBounds = new Rectangle(this.actorPos.x, this.actorPos.y, this.imgWidth, this.imgHeight);
		this.deadly = false;
	}
	
	public jLiveActor(Texture p_img, Vector2 p_pos) {
		this.itemImg = p_img;
		this.actorPos = new Vector2(p_pos);
		this.speedMultiplier = 1;
		this.reSpawn = false;
		this.imgHeight = this.itemImg.getHeight();
		this.imgWidth = this.itemImg.getWidth();
		this.itemBounds = new Rectangle(this.actorPos.x, this.actorPos.y, this.imgWidth, this.imgHeight);
		this.deadly = false;
	}
	
	public jLiveActor(Texture p_img, Vector2 p_pos, float p_speed) {
		this.itemImg = p_img;
		this.actorPos = new Vector2(p_pos);
		this.speedMultiplier = p_speed;
		this.reSpawn = false;
		this.imgHeight = this.itemImg.getHeight();
		this.imgWidth = this.itemImg.getWidth();
		this.itemBounds = new Rectangle(this.actorPos.x, this.actorPos.y, this.imgWidth, this.imgHeight);
		this.deadly = false;
	}
	
	public jLiveActor(Texture p_img, Vector2 p_pos, float p_speed, boolean p_respawn) {
		this.itemImg = p_img;
		this.actorPos = new Vector2(p_pos);
		this.speedMultiplier = p_speed;
		this.reSpawn = p_respawn;
		this.imgHeight = this.itemImg.getHeight();
		this.imgWidth = this.itemImg.getWidth();
		this.itemBounds = new Rectangle(this.actorPos.x, this.actorPos.y, this.imgWidth, this.imgHeight);
		this.deadly = false;
	}
	
	public jLiveActor(Texture p_img, Vector2 p_pos, float p_speed, boolean p_respawn, float p_imgscale) {
		this.itemImg = p_img;
		this.actorPos = new Vector2(p_pos);
		this.speedMultiplier = p_speed;
		this.reSpawn = p_respawn;
		this.imgHeight = this.itemImg.getHeight() * p_imgscale;
		this.imgWidth = this.itemImg.getWidth() * p_imgscale;
		this.itemBounds = new Rectangle(this.actorPos.x, this.actorPos.y, this.imgWidth, this.imgHeight);
		this.deadly = false;
	}
	
	public jLiveActor(Texture p_img, Vector2 p_pos, float p_speed, boolean p_respawn, float p_imgscale, boolean p_deadly) {
		this.itemImg = p_img;
		this.actorPos = new Vector2(p_pos);
		this.speedMultiplier = p_speed;
		this.reSpawn = p_respawn;
		this.imgHeight = this.itemImg.getHeight() * p_imgscale;
		this.imgWidth = this.itemImg.getWidth() * p_imgscale;
		this.itemBounds = new Rectangle(this.actorPos.x, this.actorPos.y, this.imgWidth, this.imgHeight);
		this.deadly = p_deadly;
	}

	public float getImageWidth() {
		return this.imgWidth;
	}

	public float getImageHeight() {
		return this.imgHeight;
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
		return this.reSpawn;
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
