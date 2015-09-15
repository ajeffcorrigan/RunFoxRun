package com.ajeffcorrigan.runfoxrun.tools;

public class GameAssetManager {

	public GameAssetManager() {
		
		jAssets.loadTextureAs("worldsheet", "tiles_spritesheet.png");
		
		
		jAssets.loadTextureAs("bush1", "bush_1.png");
		jAssets.loadTextureAs("farpines", "pinehills_distant_1.png");
		jAssets.loadTextureAs("ground1", "crosssection_long.png");
		jAssets.loadTextureAs("tree1", "tree_coniferous_1.png");
		jAssets.loadTextureAs("coin", "goldcoin.png");
		jAssets.loadTextureAs("foxrunsheet", "run_fox_sheet.png");
		jAssets.loadTextureAs("foxjumpsheet", "fox_jump_animation.png");
		jAssets.loadTextureAs("foxrunning", "fox_run_animation_right.png");
		
		
		jAssets.loadTextureAs("foxstill", "fox_still.png");
		
		jAssets.loadTextureAs("grassMid", "grassMid.png");

		jAssets.createTextureRegion("dirt", "worldsheet", 576, 864, 70, 70);
	}
}
