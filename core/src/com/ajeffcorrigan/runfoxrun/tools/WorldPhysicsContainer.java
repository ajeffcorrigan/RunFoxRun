package com.ajeffcorrigan.runfoxrun.tools;

import com.ajeffcorrigan.runfoxrun.sprites.ScreenTile;
import com.ajeffcorrigan.runfoxrun.sprites.foxActor;

public class WorldPhysicsContainer {

	public WorldPhysicsContainer() {
		
	}
	
	public boolean topContactMade(ScreenTile st, foxActor fox) {
		
		return true;
	}
	
	public boolean contactMade(ScreenTile st, foxActor fox) {
		
		return true;
	}
}
