package com.eversong.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.eversong.game.controller.Eversong;
import com.eversong.game.controller.MenuController;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MenuController(), config);

		config.width = 540;
		config.height = 960;
	}
}
