package com.game.test2;

import com.badlogic.gdx.Game;
import com.game.test2.MainMenuScreen;

public class racing_simulation_2d extends Game {

	@Override
	public void create() {
		this.setScreen(new GameScreen(this));
	}

}
