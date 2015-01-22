/**
 * 
 */
package com.ajeffcorrigan.runfoxrun;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author CorriganJ
 *
 */
public class jAnimator {

	private int iCols;
	private int iRows;
	private Animation jAnimate;
	private Texture jSpriteSheet;
	private TextureRegion[] jSheetSplit;
	private boolean jFlipX = false;
	private boolean jFlipY = false;
	private float fSpeed;
	
	/**
	 * 
	 */
	public jAnimator() {
		// TODO Auto-generated constructor stub
	}

	public jAnimator(Texture p_sprite) {
		// Set parent sprite sheet.
		this.jSpriteSheet = p_sprite;		
	}
	
	public jAnimator(Texture p_sprite, int p_cols, int p_rows, boolean p_flipx, boolean p_flipy, float p_speed) {
		this.jSpriteSheet = p_sprite;
		this.iCols = p_cols;
		this.iRows = p_rows;
		this.jFlipX = p_flipx;
		this.jFlipY = p_flipy;
		this.fSpeed = p_speed;
		createAnimation();
	}
	
	private void createAnimation() {
		int index = 0;
		
		TextureRegion[][] tmp = TextureRegion.split(this.jSpriteSheet, 
				this.jSpriteSheet.getWidth() / this.iCols, 
				this.jSpriteSheet.getHeight() / this.iRows);
		this.jSheetSplit = new TextureRegion[this.iCols * this.iRows];
        
        for (int i = 0; i < this.iRows; i++) {
            for (int j = 0; j < this.iCols; j++) {
            	tmp[i][j].flip(this.jFlipX, this.jFlipY);
            	this.jSheetSplit[index++] = tmp[i][j];
            }
        }
        
        this.jAnimate = new Animation(this.fSpeed, this.jSheetSplit);
        
	}	
	
	/**
	 * Return the current frame in the animation.
	 * @param sTime State time to gather frame.
	 * @return TextureRegion of frame.
	 */
	public TextureRegion getCurrentFrame(float sTime) {
		return this.jAnimate.getKeyFrame(sTime, true);
	}
	
	/**
	 * @param sTime State time to gather frame.
	 * @param ploop Should the animation loop.
	 * @return
	 */
	public TextureRegion getCurrentFrame(float sTime, boolean ploop) {
		return this.jAnimate.getKeyFrame(sTime, ploop);
	}

}
