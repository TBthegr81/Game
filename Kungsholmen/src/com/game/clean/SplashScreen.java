package com.game.clean;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class SplashScreen implements Screen{
	
	private SpriteBatch spriteBatch;
    private Texture splsh;
    private Game myGame;
    private BitmapFont font;
    private CharSequence str = "PRESS START!";
    long lastBlinkTime;
    int blink = 1;

    
    public SplashScreen(Game game)
    {
            myGame = game;
    }
    
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(splsh, 0, 0);
        if(blink == 1)
        {
        	font.draw(spriteBatch, str, 400, 700);
        	
        }
        
        spriteBatch.end();
        
        if(Gdx.input.justTouched())
                myGame.setScreen(new GameScreen());
        
        if(TimeUtils.nanoTime() - lastBlinkTime > 500000000)
        {
        	lastBlinkTime = TimeUtils.nanoTime();
        	if(blink == 1)blink = 0;
        	else if(blink == 0) blink = 1;
        }
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
        splsh = new Texture(Gdx.files.internal("splash.gif"));
        
        font = new BitmapFont(Gdx.files.internal("Fonts/test.fnt"),
        Gdx.files.internal("Fonts/test.png"), false);
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
