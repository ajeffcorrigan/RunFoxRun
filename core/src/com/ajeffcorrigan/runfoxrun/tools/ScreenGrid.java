package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.sprites.ScreenTile;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class ScreenGrid {

	public int height;
	public Array<GridRow> rows;
	public Vector2 startXY;
	private Vector2 currentXY;
	public int tileSize;
	
	public ScreenGrid(int height, int width, Vector2 startXY, int tileSize) {
		this.height = height;
		this.startXY = startXY;
		this.tileSize = tileSize;
		rows = new Array<GridRow>(height);
		this.currentXY = startXY;
		for(int i = 0; i < height; i++) {
			rows.add(new GridRow(width, this.currentXY, this.tileSize));
			currentXY.add(0, this.tileSize);
		}
	}
	
	public void drawBounds(ShapeRenderer shaperenderer) {
		for(GridRow gr : rows) {
			for (ScreenTile st: gr.tiles) {
				shaperenderer.rect(st.tileBounds.x, st.tileBounds.y, st.tileBounds.width, st.tileBounds.height);
			}
		}
	}
	
}
