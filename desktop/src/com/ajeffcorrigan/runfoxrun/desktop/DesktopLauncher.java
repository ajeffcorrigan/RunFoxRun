package com.ajeffcorrigan.runfoxrun.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ajeffcorrigan.runfoxrun.RunFoxRun;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1440;
        config.height = 860;
		new LwjglApplication(new RunFoxRun(), config);
	}
}
