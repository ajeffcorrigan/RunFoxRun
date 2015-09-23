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
	private char[][] currentChunk;
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
		switch(getNewTileCode()) {
			case 'D':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("dirt")),screen);
				break;
			case 'E':
				newTile = new ScreenTile(newPos,tileSize);
				break;
			case 'G':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("grassMid")),screen);
				break;
			case 'L':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("grassLeft")),screen);
				break;
			case 'R':
				newTile = new ScreenTile(newPos,tileSize,new Sprite(jAssets.getTextureRegion("grassRight")),screen);
				break;
			default:
				newTile = new ScreenTile(newPos,tileSize);
				break;
		}
		return newTile;
	}
	
	public char getNewTileCode() {
		char newCode;
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
