package com.game.clean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class ChooseCarScreen implements Screen{
	final KungsholmenGame game;
	private SpriteBatch spriteBatch;
	private Texture splsh;
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
    Texture whitebox;
    Sprite whitebox_sprite;
    Texture selected;
    Sprite selected_sprite;
    
    float carx = 100;
    int carheight = 220;
    int carsnurr = 322;
    int currentCarScreen = 0;
    int maxCarScreens = 0;
    
    int parkeringsplats = 0;
    
    
    public ChooseCarScreen(KungsholmenGame game)
    {
           this.game = game;
    }
    
    private void ChangeCar(boolean way)
    {
    		if(way)
    		{
    			//Next Car
    			/*if(SelectedCar != Cars.size-1)
    			{
    				SelectedCar++;
    			}
    			else if(SelectedCar == Cars.size-1)
    			{
    				SelectedCar = 0;
    			}*/
    			//Next Car screen
    			System.out.println("CCS: " + currentCarScreen + " MCS:" + maxCarScreens +  " Num Cars: " + Cars.size);
    			if(currentCarScreen != maxCarScreens-1)
    			{
    				currentCarScreen++;
    			}
    			else if(currentCarScreen == maxCarScreens-1)
    			{
    				currentCarScreen = 0;
    			}
    			System.out.println("POST CCS: " + currentCarScreen + "POST MCS:" + maxCarScreens +  " Num Cars: " + Cars.size);
    		}
    		else if(!way)
    		{
    			//Previous Car
    			/*if(SelectedCar != 0)
    			{
    				SelectedCar--;
    			}
    			else if(SelectedCar == 0)
    			{
    				SelectedCar = Cars.size-1;
    			}*/
    			//Previous Car Screen
    			System.out.println("CCS: " + currentCarScreen + " " + maxCarScreens +  " Num Cars: " + Cars.size);
    			if(currentCarScreen != 0)
    			{
    				currentCarScreen--;
    			}
    			else if(currentCarScreen == 0)
    			{
    				currentCarScreen = maxCarScreens-1;
    			}
    			System.out.println("POST CCS: " + currentCarScreen + "POST MCS:" + maxCarScreens +  " Num Cars: " + Cars.size);
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
        
        Car car2 = Resources.getPlayers().get(0).getCurrentCar();
        
        //Draw Selected Car
        Sprite car;

        if(Cars.size > currentCarScreen*4)
        {
        	if(parkeringsplats == 0)
        	{
        		spriteBatch.draw(selected_sprite, 90, height-256-130);
        	}
	        car = Cars.get(currentCarScreen*4).getSprite();
	        if(car.getWidth()==256)
	        {
	        	carx = 130;
	        }
	        else
	        {
	        	carx = 185;
	        }
	        spriteBatch.draw(car, carx , height-carheight, car.getOriginX(), car.getOriginY(), car.getWidth(), car.getHeight(), 1, 1, carsnurr);
        }
        
        if(Cars.size > currentCarScreen*4+1)
        {
        	if(parkeringsplats == 1)
        	{
        		spriteBatch.draw(selected_sprite, 275, height-256-130);
        	}
	        car = Cars.get(currentCarScreen*4+1).getSprite();
	        if(car.getWidth()==256)
	        {
	        	carx = 300;
	        }
	        else
	        {
	        	carx = 360;
	        }
	        spriteBatch.draw(car, carx, height-carheight, car.getOriginX(), car.getOriginY(), car.getWidth(), car.getHeight(), 1, 1, carsnurr);
        }
        
        if(Cars.size > currentCarScreen*4+2)
        {
        	if(parkeringsplats == 2)
        	{
        		spriteBatch.draw(selected_sprite, 450, height-256-130);
        	}
        	car = Cars.get(currentCarScreen*4+2).getSprite();
	        if(car.getWidth()==256)
	        {
	        	carx = 480;
	        }
	        else
	        {
	        	carx = 550;
	        }
	        spriteBatch.draw(car, carx, height-carheight, car.getOriginX(), car.getOriginY(), car.getWidth(), car.getHeight(), 1, 1, carsnurr);
        }
        if(Cars.size > currentCarScreen*4+3)
        {
        	if(parkeringsplats == 3)
        	{
        		spriteBatch.draw(selected_sprite, 640, height-256-130);
        	}
	        car = Cars.get(currentCarScreen*4+3).getSprite();
	        if(car.getWidth()==256)
	        {
	        	carx = 670;
	        }
	        else
	        {
	        	carx = 730;
	        }
	        spriteBatch.draw(car, carx, height-carheight, car.getOriginX(), car.getOriginY(), car.getWidth(), car.getHeight(), 1, 1, carsnurr);
        }
        
        //Description box
        spriteBatch.draw(whitebox_sprite, 90, 25);
        
        //Draw Name
        str = car2.getName();
        font.draw(spriteBatch, str, 100, 280);
        
        //Draw Description
        str = car2.getDescription();
        font.draw(spriteBatch, str, 100, 200);
        
        //Write category
        str =  "Level " + currentCarScreen;
        font.draw(spriteBatch, str, width-400, height-30);
        
        //Left Button
        spriteBatch.draw(menu_button_left_sprite, width-500, height-75);
        
        //Right Button
        spriteBatch.draw(menu_button_right_sprite, width-200, height-75);
        
        //Race Button
        spriteBatch.draw(menu_button_sprite, width-256, 50);
        str =  "Race";
        font.draw(spriteBatch, str, width-256+30, 100);
        
        //Back Button
        spriteBatch.draw(menu_button_sprite, 510, 50);
        str =  "Back";
        font.draw(spriteBatch, str, 540, 100);
        
        spriteBatch.end();
        if(Gdx.input.justTouched())
        {
        	touchPos = new Point(Gdx.input.getX(), Gdx.input.getY());
        	System.out.println(touchPos);
        	
        	//First Parkingspace
        	if(touchPos.x() >= 150 && touchPos.x() <= 300 && touchPos.y() >= 130 && touchPos.y() <= 190)
        	{
        		System.out.println("Pressed First Parkingspace");
        		System.out.println("Choose car 1");
        		Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4));
        		parkeringsplats = 0;
        	}
        	if(touchPos.x() >= 200 && touchPos.x() <= 350 && touchPos.y() >= 190 && touchPos.y() <= 250)
        	{
        		System.out.println("Pressed First Parkingspace");
        		System.out.println("Choose car 1");
        		Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4));
        		parkeringsplats = 0;
        	}
        	
        	//Second Parkingspace
        	if(touchPos.x() >= 300 && touchPos.x() <= 450 && touchPos.y() >= 130 && touchPos.y() <= 190)
        	{
        		System.out.println("Pressed Second Parkingspace");
        		System.out.println("Choose car 2");
        		Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4+1));
        		parkeringsplats = 1;
        	}
        	if(touchPos.x() >= 350 && touchPos.x() <= 550 && touchPos.y() >= 190 && touchPos.y() <= 250)
        	{
        		System.out.println("Pressed Second Parkingspace");
        		System.out.println("Choose car 2");
        		Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4+1));
        		parkeringsplats = 1;
        	}
        	
        	//Third Parkingspace
        	if(touchPos.x() >= 450 && touchPos.x() <= 650 && touchPos.y() >= 130 && touchPos.y() <= 190)
        	{
        		System.out.println("Pressed Third Parkingspace");
        		System.out.println("Choose car 3");
        		Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4+2));
        		parkeringsplats = 2;
        	}
        	if(touchPos.x() >= 550 && touchPos.x() <= 700 && touchPos.y() >= 190 && touchPos.y() <= 250)
        	{
        		System.out.println("Pressed Third Parkingspace");
        		System.out.println("Choose car 3");
        		Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4+2));
        		parkeringsplats = 2;
        	}
        	
        	//Fourth Parkingspace
        	if(touchPos.x() >= 650 && touchPos.x() <= 850 && touchPos.y() >= 130 && touchPos.y() <= 190)
        	{
        		System.out.println("Pressed Fourth Parkingspace");
        		System.out.println("Choose car 4");
        		Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4+3));
        		parkeringsplats = 3;
        	}
        	if(touchPos.x() >= 700 && touchPos.x() <= 900 && touchPos.y() >= 190 && touchPos.y() <= 250)
        	{
        		System.out.println("Pressed Fourth Parkingspace");
        		System.out.println("Choose car 4");
        		Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4+3));
        		parkeringsplats = 3;
        	}
        	
        	//Check PreviousCar Button
        	if(touchPos.x() >= width-500 && touchPos.x() <= width-500+64 && touchPos.y() >= 75-64 && touchPos.y() <= 75)
        	{
        		System.out.println("Pressed Previous Car");
        		ChangeCar(false);
        	}
        	
        	//Check NextCar Button
        	if(touchPos.x() >= width-200 && touchPos.x() <= width-200+64 && touchPos.y() >= 75-64 && touchPos.y() <= 75)
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
        	if(touchPos.x() >= 510 && touchPos.x() <= 510+256 && touchPos.y()>= height-50-64 && touchPos.y()<= height-50)
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
		splsh = Resources.getBackgrounds().get(1);
		
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
        
        whitebox =  new Texture(Gdx.files.internal("whitebox.png"));
        whitebox_sprite = new Sprite(whitebox);
        
        selected =  new Texture(Gdx.files.internal("selected.gif"));
        selected_sprite = new Sprite(selected);
        
        Cars = Resources.getCars();
        
        Resources.addPlayer();
        
        maxCarScreens = (int) Math.ceil(Cars.size / 4.0);
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

