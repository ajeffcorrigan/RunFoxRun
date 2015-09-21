package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.sprites.ScreenTile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GridRow {
		
	public Array<ScreenTile> tiles;
	private Vector2 rowXY;
	
	public GridRow(int width, Vector2 startXY, float tileSize, PlayScreen screen) {
		this.rowXY = new Vector2(startXY);
		tiles = new Array<ScreenTile>(width);
		for(int i = 0; i < width; i++) {
			tiles.add(new ScreenTile(rowXY, tileSize));
			rowXY.add(tileSize, 0);
		}
	}
}
