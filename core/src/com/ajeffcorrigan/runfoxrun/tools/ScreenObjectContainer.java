package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class ScreenObjectContainer {
	
	protected World world;
	protected Rectangle itemBounds;					//Item bounds for collision and debugging.
	protected Body body;
	private Texture itemImg;					//Texture to load as image.
	private Vector2 itemPos;					//Items current position.
	private boolean reSpawn;					//Should item re-spawn if eliminated or goes off screen.
	private float imgHeight;					//Item image height.
	private float imgWidth;						//Item image width.
	private float speedMultiplier;				//Speed of the item.
	
	
	public String itemName;						//Name of the item.
	
	public ScreenObjectContainer() {
		
	}
	
	public ScreenObjectContainer(Texture p_img) {
		// TODO Auto-generated constructor stub
		this.itemImg = p_img;
		this.itemPos = new Vector2(0,0);
		this.reSpawn = false;
		this.imgHeight = this.itemImg.getHeight();
		this.imgWidth = this.itemImg.getWidth();
		this.itemBounds = new Rectangle(this.itemPos.x, this.itemPos.y, this.imgWidth, this.imgHeight);
		this.speedMultiplier = 1;
	}
	
	public ScreenObjectContainer(Texture p_img, Vector2 p_pos, float p_imgscale) {
		// TODO Auto-generated constructor stub
		this.itemImg = p_img;
		this.itemPos = new Vector2(p_pos);
		this.reSpawn = false;
		this.imgHeight = this.itemImg.getHeight() * p_imgscale;
		this.imgWidth = this.itemImg.getWidth() * p_imgscale; 
		this.itemBounds = new Rectangle(this.itemPos.x, this.itemPos.y, this.imgWidth, this.imgHeight);
		this.speedMultiplier = 1;
	}
	
	
	protected Texture getItemImg() {
		return itemImg;
	}

	protected void setItemImg(Texture itemImg) {
		this.itemImg = itemImg;
	}
	
	/**
	 * @return the itemPos
	 */
	protected Vector2 getItemPos() {
		return itemPos;
	}

	/**
	 * @param itemPos the itemPos to set
	 */
	protected void setItemPos(Vector2 itemPos) {
		this.itemPos = itemPos;
		this.updateBounds();
	}

	/**
	 * @return the reSpawn
	 */
	protected boolean isReSpawn() {
		return reSpawn;
	}

	/**
	 * @param reSpawn the reSpawn to set
	 */
	protected void setReSpawn(boolean reSpawn) {
		this.reSpawn = reSpawn;
	}

	/**
	 * @return the imgHeight
	 */
	protected float getImgHeight() {
		return imgHeight;
	}

	/**
	 * @param imgHeight the imgHeight to set
	 */
	protected void setImgHeight(float imgHeight) {
		this.imgHeight = imgHeight;
	}

	/**
	 * @return the imgWidth
	 */
	protected float getImgWidth() {
		return imgWidth;
	}

	/**
	 * @param imgWidth the imgWidth to set
	 */
	protected void setImgWidth(float imgWidth) {
		this.imgWidth = imgWidth;
	}

	/**
	 * @return the speedMultiplier
	 */
	protected float getSpeedMultiplier() {
		return speedMultiplier;
	}

	/**
	 * @param speedMultiplier the speedMultiplier to set
	 */
	protected void setSpeedMultiplier(float speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}

	/**
	 * @return the itemBounds
	 */
	protected Rectangle getItemBounds() {
		return itemBounds;
	}

	/**
	 * @param itemBounds the itemBounds to set
	 */
	protected void setItemBounds(Rectangle itemBounds) {
		this.itemBounds = itemBounds;
	}

	/**
	 * @return the itemName
	 */
	protected String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	protected void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public void updateItemX(float dtime) {
		this.itemPos.x += (dtime * (50 * this.speedMultiplier));
		this.updateBounds();
	}
	
	private void updateBounds() {
		this.itemBounds.setPosition(itemPos);
	}
	
	public void draw(SpriteBatch sb) {
		sb.draw(this.itemImg, this.itemPos.x, this.itemPos.y, this.imgWidth, this.imgHeight);
		
	}
	
	public void addToMultiplier(float upSpd) {
		this.speedMultiplier += upSpd;
	}
	
	public float getxCoord() { 
		return this.itemPos.x;
	}
	
	public void setxCoord(float i) {
		this.setItemPos(new Vector2(i,this.itemPos.y));
	}
	
	public float getyCoord() {
		return this.itemPos.y;
	}
	
	public float getWidthXCoord() {
		return this.imgWidth + this.itemPos.x;
	}
	
	public float getHeightYCoord() {
		return this.imgHeight + this.itemPos.y;
	}

}
