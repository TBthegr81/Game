package com.game.clean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class BackupOfChooseCarScreen implements Screen{
	final KungsholmenGame game;
	private SpriteBatch spriteBatch;
	private Texture splsh;
	//private Game myGame;
    private BitmapFont font;
    private BitmapFont font2;
    private CharSequence str;
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    Point touchPos;
    Array<Car> Cars = new Array<Car>();
    int SelectedCar = 0;
    
    Texture menu_button;
    Sprite menu_button_sprite;
    Texture menu_button_left;
    Sprite menu_button_left_sprite;
    Texture menu_button_right;
    Sprite menu_button_right_sprite;
    
    
    
    public BackupOfChooseCarScreen(KungsholmenGame game)
    {
           this.game = game;
    }
    
    private void ChangeCar(boolean way)
    {
    		if(way)
    		{
    			//Next Car
    			if(SelectedCar != Cars.size-1)
    			{
    				SelectedCar++;
    			}
    			else if(SelectedCar == Cars.size-1)
    			{
    				SelectedCar = 0;
    			}
    		}
    		else if(!way)
    		{
    			//Previous Car
    			if(SelectedCar != 0)
    			{
    				SelectedCar--;
    			}
    			else if(SelectedCar == 0)
    			{
    				SelectedCar = Cars.size-1;
    			}
    		}
    		System.out.println("Selected Car: " + SelectedCar);
    		
    }
    
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        spriteBatch.begin();
        spriteBatch.draw(splsh, 0, 0, width, height);
        
		str =  "SELECT VEHICLE";
        font2.draw(spriteBatch, str, 30, height-30);
        
        //Draw Selected Car
        Sprite car = Cars.get(SelectedCar).getSprite();
        spriteBatch.draw(car, width/2-(car.getWidth()/2), height/2, car.getOriginX(), car.getOriginY(), car.getWidth(), car.getHeight(), 3, 3, 0);
        
        //Draw Name
        //str = Cars.get(SelectedCar).getName();
        //font.draw(spriteBatch, str, 200, 200);
        
        //Draw Description
        //str = Cars.get(SelectedCar).getDescription();
        //font.draw(spriteBatch, str, 200, 150);
        
        //Left Button
        spriteBatch.draw(menu_button_left_sprite, 0, height/2);
        str =  "Previous";
        font.draw(spriteBatch, str, 0, height/2);
        
        //Right Button
        spriteBatch.draw(menu_button_right_sprite, width-64, height/2);
        str =  "Next";
        font.draw(spriteBatch, str, width-90, height/2);
        
        //Race Button
        spriteBatch.draw(menu_button_sprite, width-256, 50);
        str =  "Race";
        font.draw(spriteBatch, str, width-256+30, 100);
        
        //Back Button
        spriteBatch.draw(menu_button_sprite, 10, 50);
        str =  "Back";
        font.draw(spriteBatch, str, 30, 100);
        
        spriteBatch.end();
        if(Gdx.input.justTouched())
        {
        	touchPos = new Point(Gdx.input.getX(), Gdx.input.getY());
        	System.out.println(touchPos);
        	
        	//Check PreviousCar Button
        	if(touchPos.x() <= 64 && touchPos.y()>= height/2-64 && touchPos.y()<= height/2)
        	{
        		System.out.println("Pressed Previous Car");
        		ChangeCar(false);
        	}
        	
        	//Check NextCar Button
        	if(touchPos.x() >= width-64 && touchPos.y()>= height/2-64 && touchPos.y()<= height/2)
        	{
        		System.out.println("Pressed Next Car");
        		ChangeCar(true);
        	}
        	
        	// Check Race Button
        	if(touchPos.x() >= width-256 && touchPos.y()>= height-50-64 && touchPos.y()<= height-50)
        	{
        		System.out.println("Pressed Race");
        		game.setScreen(new GameScreen(game));
        	}
        	
        	// Check Back Button
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
        splsh = new Texture(Gdx.files.internal("splash.gif"));
        
        font = new BitmapFont(Gdx.files.internal("Fonts/test.fnt"),
        Gdx.files.internal("Fonts/test.png"), false);
        font2 = new BitmapFont(Gdx.files.internal("Fonts/YellowItalicBoldVerdana.fnt"),
        Gdx.files.internal("Fonts/YellowItalicBoldVerdana.png"), false);
        
        menu_button =  new Texture(Gdx.files.internal("Menu_Button.png"));
        menu_button_sprite = new Sprite(menu_button);
        
        menu_button_left =  new Texture(Gdx.files.internal("Menu_Button_Left.png"));
        menu_button_left_sprite = new Sprite(menu_button_left);
        
        menu_button_right =  new Texture(Gdx.files.internal("Menu_Button_Right.png"));
        menu_button_right_sprite = new Sprite(menu_button_right);
        
        Cars = Resources.getCars();
        
        Resources.addPlayer();
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

