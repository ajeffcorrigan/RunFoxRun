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
	private boolean customAni = false;
	private float stateTime;
	private boolean animationInProgress = false;
	private int cFrame;
	private int endFrame;
	
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
	
	public jAnimator(Texture p_sprite, int p_cols, int p_rows, boolean p_flipx, boolean p_flipy, float p_speed, boolean p_customani) {
		this.jSpriteSheet = p_sprite;
		this.iCols = p_cols;
		this.iRows = p_rows;
		this.jFlipX = p_flipx;
		this.jFlipY = p_flipy;
		this.fSpeed = p_speed;
		this.customAni = p_customani;
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
        if(!this.customAni) { 
        	this.jAnimate = new Animation(this.fSpeed, this.jSheetSplit); 
        } else {
        	this.endFrame = this.jSheetSplit.length - 1;
        }
	}
	

	/**
	 * Return the current frame in the animation.
	 * @param sTime State time to gather frame.
	 * @return TextureRegion of frame.
	 */
	public TextureRegion getCurrentFrame(float sTime) {
		if(this.customAni) {
			if(this.stateTime + this.fSpeed <= sTime) {
				this.stateTime = sTime;
				this.cFrame++;
				if (this.cFrame >= this.endFrame) { this.cFrame = this.endFrame; }
			}
			return this.jSheetSplit[this.cFrame];
		} else {
			return this.jAnimate.getKeyFrame(sTime, true);
		}
	}
	
	/**
	 * @param sTime State time to gather frame.
	 * @param ploop Should the animation loop.
	 * @return
	 */
	public TextureRegion getCurrentFrame(float sTime, boolean ploop) {
		return this.jAnimate.getKeyFrame(sTime, ploop);
	}
	
	public void setPlayMode(Animation.PlayMode plmode) {
		this.jAnimate.setPlayMode(plmode);
	}
	
	/**
	 * @param time
	 */
	public void setStateTime(float time) {
		this.stateTime = time;
	}
	
	public void setAnimationProgress(boolean animove) {
		if (!this.animationInProgress) {
			this.cFrame = 0;
		} 
		this.animationInProgress = animove;
	}

}
