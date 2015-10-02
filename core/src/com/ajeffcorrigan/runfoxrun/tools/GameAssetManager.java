package com.ajeffcorrigan.runfoxrun.tools;

public class GameAssetManager {

	public GameAssetManager() {
		
		//world tile sprites and textures
		jAssets.loadTextureAs("worldsheet", "tiles_spritesheet.png");
		jAssets.createTextureRegion("dirt", "worldsheet", 576, 864, 70, 70);
		jAssets.createTextureRegion("grassLeft", "worldsheet", 504, 648, 70, 70);
		jAssets.createTextureRegion("grassMid", "worldsheet", 504, 576, 70, 70);
		jAssets.createTextureRegion("grassRight", "worldsheet", 504, 504, 70, 70);
		jAssets.createTextureRegion("grassCenter", "worldsheet", 648, 0, 70, 70);
		jAssets.createTextureRegion("liquidLava", "worldsheet", 504, 0, 70, 70);
		jAssets.createTextureRegion("liquidWater", "worldsheet", 504, 216, 70, 70);
		jAssets.createTextureRegion("liquidWaterTop", "worldsheet", 432, 648, 70, 70);
		jAssets.createTextureRegion("grassHillLeft", "worldsheet", 576, 144, 70, 70);
		jAssets.createTextureRegion("grassHillLeft2", "worldsheet", 576, 72, 70, 70);
		jAssets.createTextureRegion("grassHillRight", "worldsheet", 576, 0, 70, 70);
		jAssets.createTextureRegion("grassHillRight2", "worldsheet", 504, 864, 70, 70);
		jAssets.createTextureRegion("lock_blue", "worldsheet", 432, 504, 70, 70);
		jAssets.createTextureRegion("lock_green", "worldsheet", 72, 576, 70, 70);
		jAssets.createTextureRegion("lock_red", "worldsheet", 432, 360, 70, 70);
		jAssets.createTextureRegion("lock_yellow", "worldsheet", 432, 288, 70, 70);
		
		//fox textures and sprites
		jAssets.loadTextureAs("foxrunning", "fox_run_animation_right.png");
		jAssets.loadTextureAs("foxstill", "fox_still.png");
		jAssets.loadTextureAs("fox_crash", "fox_crash.png");
		
		
		
		jAssets.loadTextureAs("bush1", "bush_1.png");
		jAssets.loadTextureAs("farpines", "pinehills_distant_1.png");
		jAssets.loadTextureAs("tree1", "tree_coniferous_1.png");
		jAssets.loadTextureAs("foxjumpsheet", "fox_jump_animation.png");
		
		

		

		
	}
}
