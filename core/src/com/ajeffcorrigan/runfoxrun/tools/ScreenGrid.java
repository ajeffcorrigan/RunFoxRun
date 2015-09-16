package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.sprites.ScreenTile;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class ScreenGrid {

	public int height;
	public int width;
	public Array<GridRow> rows;
	private Vector2 gridXY;
	private GameLevelManager glm;
	
	public ScreenGrid(int height, int width, Vector2 startXY, int tileSize, PlayScreen screen) {
		this.height = height;
		this.gridXY = new Vector2(startXY);
		rows = new Array<GridRow>(height);
		for(int i = 0; i < height; i++) {
			rows.add(new GridRow(width, gridXY, tileSize, screen));
			gridXY.y += tileSize;
		}
		glm = new GameLevelManager();
	}
	
	public void drawBounds(ShapeRenderer shaperenderer) {
		for(GridRow gr : rows) {
			for (ScreenTile st: gr.tiles) {
				shaperenderer.rect(st.tileBounds.x, st.tileBounds.y, st.tileBounds.width, st.tileBounds.height);
			}
		}
	}
	
	public void draw(SpriteBatch sb) {
		for(GridRow gr : rows) { for (ScreenTile st: gr.tiles) { if(!st.tileEmpty) { st.draw(sb); } } }
	}
	
	public void update(float dt, PlayScreen screen) {
		for(GridRow gr : rows) {
			if(!screen.gamecam.frustum.pointInFrustum((gr.tiles.first().tilePosition.x + gr.tiles.first().tileSize),0,0)) {
				gr.tiles.removeIndex(0);
				gr.tiles.add(glm.constructLevel(gr,screen));
				
			}	
		}
	}
	
}
