package com.game.test2;

import com.badlogic.gdx.Screen;
import com.game.TB.GBS;

public class GameScreen implements Screen{
	final racing_simulation_2d game;
	
	public GameScreen(final racing_simulation_2d gam) {
		this.game = gam;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

class Timer
{
    //store last time sample
    private int lastTime = Environment.TickCount;
    private float etime;

    //calculate and return elapsed time since last call
    public float GetETime()
    {
        etime = (Environment.TickCount - lastTime) / 1000.0f;
        lastTime = Environment.TickCount;

        return etime;
    }
}
