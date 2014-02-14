package com.game.clean;

import com.badlogic.gdx.Game;

public class KungsholmenGame extends Game
{
	@Override
    public void create()
    {               
            this.setScreen(new SplashScreen(this));
    }
}
