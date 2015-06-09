package com.ajeffcorrigan.runfoxrun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ScreenObjectContainer {
	
	private Texture itemImg;					//Texture to load as image.
	private Vector2 itemPos;					//Items current position.
	private boolean reSpawn;					//Should item re-spawn if eliminated or goes off screen.
	private float imgHeight;					//Item image height.
	private float imgWidth;						//Item image width.
	private float speedMultiplier;				//Speed of the item.
	private Rectangle itemBounds;				//Item bounds for collision and debugging.
	
	public String itemName;						//Name of the item.
	
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

}
