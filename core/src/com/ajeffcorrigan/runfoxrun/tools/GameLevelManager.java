package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.sprites.ScreenTile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class GameLevelManager {

	private boolean levelPicked;
	private boolean constructInProgress = false;
	private boolean constructBatchDone = true;
	private String[][] currentChunk;
	private int currentRow = 0;
	private int currentCol = 0;
	private int maxRow = 0;
	private int maxCol = 0;
	
	private GameLevels gl;
	
	public GameLevelManager() {
		gl = new GameLevels();
	}
	
	public ScreenTile constructLevel(GridRow gr, PlayScreen screen) {
		ScreenTile newTile;
		float tileSize = gr.tiles.peek().tileSize;
		Vector2 newPos = new Vector2(gr.tiles.peek().tilePosition.add(tileSize,0));
		String tileCode = getNewTileCode();
		switch(tileCode.charAt(0)) {
			case 'D':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("dirt")),screen,true);
				break;
			case 'E':
				newTile = new ScreenTile(newPos,tileSize);
				break;
			case 'M':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("grassCenter")),screen,true);
				break;
			case 'A':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("grassHillLeft2")),screen,true);
				break;
			case 'B':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("grassHillLeft")),screen,true);
				break;
			case 'C':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("grassHillRight")),screen,true);
				break;
			case 'F':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("grassHillRight2")),screen,true);
				break;
			case 'G':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("grassMid")),screen,true);
				break;
			case 'L':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("grassLeft")),screen,true);
				break;
			case 'R':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("grassRight")),screen,true);
				break;
			case 'W':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("liquidWaterTop")),screen,false);
				newTile.setSensorFixture(screen);
				newTile.endGameIfTouch = true;
				break;
			default:
				newTile = new ScreenTile(newPos,tileSize);
				break;
		}
		return newTile;
	}
	
	public String getNewTileCode() {
		String newCode;
		if(constructBatchDone && !constructInProgress) {
			constructBatchDone = false;
			constructInProgress = true;
			currentChunk = gl.getLevelDetails();
			maxRow = currentChunk.length - 1;
			maxCol = currentChunk[maxRow].length - 1;
			currentRow = maxRow;
		}
		newCode = currentChunk[currentRow][currentCol];
		
		if(currentRow > 0) {
			currentRow--;
		} else {
			currentRow = maxRow;
			currentCol++;
		}

		if(currentRow == maxRow && currentCol > maxCol) {
			constructBatchDone = true;
			constructInProgress = false;
			currentCol = 0;
			currentRow = 0;
		}
		
		return newCode;
	}
	
	
}
