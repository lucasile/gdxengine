package com.lucasile.battlerpg.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lucasile.battlerpg.engine.settings.GameSettings;
import com.lucasile.battlerpg.game.RPGGame;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = GameSettings.NAME;
		config.width = GameSettings.CAMERA_WIDTH;
		config.height = GameSettings.CAMERA_HEIGHT;

		config.vSyncEnabled = true;

		RPGGame game = new RPGGame();

		new LwjglApplication(game.getEngine(), config);
	}
}
