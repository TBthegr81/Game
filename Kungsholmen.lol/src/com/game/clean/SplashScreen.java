package com.game.clean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen{
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

    
    public SplashScreen(KungsholmenGame game)
    {
            this.game = game;
            Gdx.graphics.setTitle("Kungsholmen The Game");
    }
    
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();

        spriteBatch.draw(splsh, 0, 0, width, height);
        
        str = "Version" + 0.6;
        font.draw(spriteBatch,str,0,0);
        //New Game
        spriteBatch.draw(menu_button_sprite, 10, height-200);
        str =  "New Game";
        font.draw(spriteBatch, str, 30, height-200+50);
        //Load Game
        spriteBatch.draw(menu_button_sprite, 10, height-300);
        str =  "Load Game";
        font.draw(spriteBatch, str, 30, height-300+50);
        //Multiplayer
        spriteBatch.draw(menu_button_sprite, 10, height-400);
        str =  "Multiplayer";
        font.draw(spriteBatch, str, 30, height-400+50);
        //Settings
        spriteBatch.draw(menu_button_sprite, 10, height-500);
        str =  "Settings";
        font.draw(spriteBatch, str, 30, height-500+50);
        //Exit to DOS
        spriteBatch.draw(menu_button_sprite, 10, height-600);
        str =  "Exit to DOS";
        font.draw(spriteBatch, str, 30, height-600+50);
        spriteBatch.end();
        
        if(Gdx.input.justTouched())
        {
        	//Check witch button was pressed.
        	touchPos = new Point(Gdx.input.getX(), Gdx.input.getY());
        	System.out.println(touchPos);
        	if(touchPos.x() <= 256+10)
        	{
        		//If touchpos is below 200, one of the buttons where probably pressed
        		//Now we check each button seperatly
        		if(touchPos.y()>= 200-64 && touchPos.y()<= 200)
        		{
        			//New game is pressed
        			System.out.println("Pressed New Game");
        			game.setScreen(new ChooseCarScreen(game));
        		}
        		else if(touchPos.y()>= 300-64 && touchPos.y()<= 300)
        		{
        			//Load Game is pressed
        			System.out.println("Pressed Load Game");
        			game.setScreen(new LoadGameScreen(game));
        		}
        		else if(touchPos.y()>= 400-64 && touchPos.y()<= 400)
        		{
        			//Load Game is pressed
        			System.out.println("Pressed Multiplayer");
        			game.setScreen(new MultiplayerScreen(game));
        		}
        		else if(touchPos.y()>= 500-64 && touchPos.y()<= 500)
        		{
        			//Load Game is pressed
        			System.out.println("Pressed Settings");
        			game.setScreen(new SettingsScreen(game));
        		}
        		else if(touchPos.y()>= 600-64 && touchPos.y()<= 600)
        		{
        			//Load Game is pressed
        			System.out.println("Pressed Exit to DOS");
        			System.exit(0);
        		}
        	}
        }
                //myGame.setScreen(new GameScreen());
        
        /*if(TimeUtils.nanoTime() - lastBlinkTime > 500000000)
        {
        	lastBlinkTime = TimeUtils.nanoTime();
        	if(blink == 1) blink = 0;
        	else if(blink == 0) blink = 1;
        }*/
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		Resources.loadBackgrounds();
		splsh = Resources.getBackgrounds().get(0);
        
        font = new BitmapFont(Gdx.files.internal("Fonts/test.fnt"),
        Gdx.files.internal("Fonts/test.png"), false);
        
        menu_button =  new Texture(Gdx.files.internal("Menu_Button.png"));
        menu_button_sprite = new Sprite(menu_button);
        
        Resources.loadVehicles2();
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
