package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.sprites.ScreenTile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GridRow {
		
	public Array<ScreenTile> tiles;
	private Vector2 rowXY;
	
	public GridRow(int width, Vector2 startXY, int tileSize) {
		this.rowXY = new Vector2(startXY);
		tiles = new Array<ScreenTile>(width);
		for(int i = 0; i < width; i++) {
			tiles.add(new ScreenTile(rowXY, tileSize));
			rowXY.add(tileSize, 0);
		}
	}
	
	public void copyLastTile() {
		int tileSize = tiles.peek().tileSize;
		Vector2 tPos = new Vector2(tiles.peek().tilePosition.add(tileSize,0));
		if(tiles.peek().tileEmpty) {
			tiles.add(new ScreenTile(tPos, tileSize));
		} else {
			tiles.add(new ScreenTile(tPos,tileSize,new Sprite(tiles.peek().getTexture())));
		}	
	}
}
