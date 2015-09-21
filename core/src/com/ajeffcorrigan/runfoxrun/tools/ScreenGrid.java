package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.ajeffcorrigan.runfoxrun.sprites.ScreenTile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class ScreenGrid {

	public int rowHeight;
	public int colWidth;
	public Array<GridRow> rows;
	public float tileSize;
	
	private Vector2 gridXY;
	private GameLevelManager glm;
	
	public ScreenGrid(int height, int width, Vector2 startXY, float tSize, PlayScreen screen) {
		
		this.rowHeight = height;
		this.colWidth = width;
		this.gridXY = new Vector2(startXY);
		this.tileSize = tSize / RunFoxRun.PTM;
		this.glm = new GameLevelManager();
		
		rows = new Array<GridRow>(this.rowHeight);
		for(int i = 0; i < this.rowHeight; i++) {
			rows.add(new GridRow(this.colWidth, this.gridXY, this.tileSize, screen));
			gridXY.y += tileSize;
		}
		
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
				if (gr.tiles.get(0).isRigid) { gr.tiles.get(0).dispose(screen); } 
				gr.tiles.removeIndex(0);
				gr.tiles.add(glm.constructLevel(gr,screen));
				
			}	
		}
	}
	
}
