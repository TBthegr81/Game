package com.game.clean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MultiplayerScreen implements Screen{
	final KungsholmenGame game;
	private SpriteBatch spriteBatch;
	private Texture splsh;
	//private Game myGame;
    private BitmapFont font;
    private CharSequence str;
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    Point touchPos;
    Texture menu_button;
    Sprite menu_button_sprite;
    
    public MultiplayerScreen(KungsholmenGame game)
    {
            this.game = game;
    }
    
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(splsh, 0, 0, width, height);
        
		str =  "Multiplayer";
        font.draw(spriteBatch, str, 30, height-30);
        
        //Back button
        spriteBatch.draw(menu_button_sprite, 10, 50);
        str =  "Back";
        font.draw(spriteBatch, str, 30, 100);
        spriteBatch.end();
        
        if(Gdx.input.justTouched())
        {
        	touchPos = new Point(Gdx.input.getX(), Gdx.input.getY());
        	System.out.println(touchPos);
        	
        	if(touchPos.x() <= 256+10 && touchPos.y()>= height-50-64 && touchPos.y()<= height-50)
        	{
        		System.out.println("Pressed Back");
        		game.setScreen(new SplashScreen(game));
        	}
        }
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
        splsh = Resources.getBackgrounds().get(0);
        
        font = new BitmapFont(Gdx.files.internal("Fonts/test.fnt"),
        Gdx.files.internal("Fonts/test.png"), false);
        
        menu_button =  new Texture(Gdx.files.internal("Menu_Button.png"));
        menu_button_sprite = new Sprite(menu_button);
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
