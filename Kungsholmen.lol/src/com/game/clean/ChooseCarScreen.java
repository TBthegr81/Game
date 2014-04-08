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
    private BitmapFont font3;
    private CharSequence str;
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    Point touchPos;
    Array<Car> Cars = new Array<Car>();
    int SelectedCar = 0;
    int SelectedCarLevel = 0;
    int parkeringsplats = 0;
    
    Texture menu_button;
    Sprite menu_button_sprite;
    Texture menu_button_left;
    Sprite menu_button_left_sprite;
    Texture menu_button_right;
    Sprite menu_button_right_sprite;
    Texture whitebox;
    Sprite whitebox_sprite;
    Texture selected_Large;
    Sprite selected_Large_sprite;
    Texture selected_Small;
    Sprite selected_Small_sprite;
    
    float carx = 100;
    int carheight = 295;
    //int carsnurr = 322;
    int carsnurr = 315;
    int currentCarScreen = 0;
    int maxCarScreens = 0;
    
    private static Array<NPC> NPCs = Resources.getNPCs();
    private int lastNPC = -1;
    
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
    
    private void spawnRandomCarToDriveAcrossScreen()
    {
    	NPCs.get(lastNPC+1).setLocation(new Point(width+256,height/2));
    }
    
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        spriteBatch.begin();
        spriteBatch.draw(splsh, 0, 0, width, height);
		str =  "SELECT VEHICLE";
        font2.draw(spriteBatch, str, 30, height-30);
        
        //Move the NPC Car
        NPC npc = NPCs.get(lastNPC+1);
        npc.setLocation(new Point(npc.getLocation().x - 10, height/2));
        if(npc.getLocation().x < -256 )
        {
        	lastNPC++;
        	if(lastNPC >= NPCs.size-1)
            {
            	lastNPC = -1;
            }
        	spawnRandomCarToDriveAcrossScreen();
        }
        
        Car car2 = Resources.getPlayers().get(0).getCurrentCar();
        
        //Draw Selected Car
        Sprite car = null;
        //System.out.println("SelectedCar: " + SelectedCar + " SelectedCarLevel: " + SelectedCarLevel + " CurrentCarScreen: "+ currentCarScreen);
        if(Cars.size > currentCarScreen*4)
        {
        	car = Cars.get(currentCarScreen*4).getSprite();
        	//SelectedCar + ((double) SelectedCar/10) == (0+((double) parkeringsplats)/10)
        	if(SelectedCarLevel == currentCarScreen && SelectedCar == 0)
        	{
        		if(car.getWidth()==256)
    	        {
        			spriteBatch.draw(selected_Large_sprite, 121, height-256-128);
    	        }
    	        else
    	        {
    	        	spriteBatch.draw(selected_Small_sprite, 145, height-256-150);
    	        }
        	}
	        
	        if(car.getWidth()==256)
	        {
	        	carx = 130;
	        }
	        else
	        {
	        	carx = 195;
	        }
	        spriteBatch.draw(car, carx , height-carheight, car.getOriginX(), car.getOriginY(), car.getWidth(), car.getHeight(), 1, 1, carsnurr);
        }
        if(Cars.size > currentCarScreen*4+1)
        {
        	car = Cars.get(currentCarScreen*4+1).getSprite();
        	if(SelectedCarLevel == currentCarScreen && SelectedCar == 1)
        	{
        		if(car.getWidth()==256)
    	        {
        			spriteBatch.draw(selected_Large_sprite, 305, height-256-128);
    	        }
    	        else
    	        {
    	        	spriteBatch.draw(selected_Small_sprite, 330, height-256-150);
    	        }
        	}
	        
	        if(car.getWidth()==256)
	        {
	        	carx = 315;
	        }
	        else
	        {
	        	carx = 380;
	        }
	        spriteBatch.draw(car, carx, height-carheight, car.getOriginX(), car.getOriginY(), car.getWidth(), car.getHeight(), 1, 1, carsnurr);
        }
        
        if(Cars.size > currentCarScreen*4+2)
        {
        	car = Cars.get(currentCarScreen*4+2).getSprite();
        	if(SelectedCarLevel == currentCarScreen && SelectedCar == 2)
        	{
        		if(car.getWidth()==256)
    	        {
        			spriteBatch.draw(selected_Large_sprite, 490, height-256-128);
    	        }
    	        else
    	        {
    	        	spriteBatch.draw(selected_Small_sprite, 510, height-256-150);
    	        }
        	}
        	
	        if(car.getWidth()==256)
	        {
	        	carx = 500;
	        }
	        else
	        {
	        	carx = 560;
	        }
	        spriteBatch.draw(car, carx, height-carheight, car.getOriginX(), car.getOriginY(), car.getWidth(), car.getHeight(), 1, 1, carsnurr);
        }
        if(Cars.size > currentCarScreen*4+3)
        {
        	car = Cars.get(currentCarScreen*4+3).getSprite();
        	if(SelectedCarLevel == currentCarScreen && SelectedCar == 3)
        	{
        		if(car.getWidth()==256)
    	        {
        			spriteBatch.draw(selected_Large_sprite, 670, height-256-128);
    	        }
    	        else
    	        {
    	        	spriteBatch.draw(selected_Small_sprite, 690, height-256-150);
    	        }
        	}
	        
	        if(car.getWidth()==256)
	        {
	        	carx = 680;
	        }
	        else
	        {
	        	carx = 740;
	        }
	        spriteBatch.draw(car, carx, height-carheight, car.getOriginX(), car.getOriginY(), car.getWidth(), car.getHeight(), 1, 1, carsnurr);
        }
        
        //Draw NPC Car
        spriteBatch.draw(npc.getCar().getSprite(), npc.getLocation().x, npc.getLocation().y);
        //Draw NPC Fulhax Box
        spriteBatch.draw(whitebox_sprite, -395, height/2);
        
        //Description box
        spriteBatch.draw(whitebox_sprite, 90, 25);
        
        //Draw Name
        str = car2.getName();
        font.draw(spriteBatch, str, 100, 280);
        
        //Fix Description length, max 20 chars and then \n
        str = car2.getDescription();
        str = str.subSequence(1,str.length());
        int maxCharCount = 23;
        Array<CharSequence> strArray = new Array<CharSequence>();
        
        for(int i = 0; i < ((str.length()/20)+(str.length()%20)); i++)
        {
        	
        	//System.out.println("I:" + i + " *20  "+ i*20);
        	if(i*maxCharCount < str.length() && i*maxCharCount+maxCharCount < str.length())
        	{
	        	//System.out.println(str.subSequence(i*maxCharCount, i*maxCharCount+maxCharCount));
	        	strArray.add(str.subSequence(i*maxCharCount, i*maxCharCount+maxCharCount));
        	}
        	else
        	{
        		//System.out.println("First " + str.length() + " Second: " + str.length()/20 + " Third " + str.length()%maxCharCount + " Fourth: " + i*maxCharCount);
        		//System.out.println(str.subSequence(i*maxCharCount, (i*maxCharCount)+str.length()%maxCharCount));
	        	strArray.add(str.subSequence(i*maxCharCount, (i*maxCharCount)+str.length()%maxCharCount));
	        	break;
        	}
        }
        
        /*Array<CharSequence> strArray = new Array<CharSequence>();
        int maxCharCount = 20;
        int currentCharCount = 0;
        String newStr = "";
        for(int i = 0; i < str.length(); i++)
        {
        	if(currentCharCount == maxCharCount)
        	{
        		strArray.add(newStr);
        		newStr = "";
        		currentCharCount = 0;
        	}
        	else
        	{
        		newStr += str.charAt(i);
        		currentCharCount++;
        	}
        }
        strArray.add(newStr);*/
        //Draw Description
        int startY = 220;
        for(int i = 0; i < strArray.size; i++)
        {
        	//System.out.println(strArray.get(i));
        	font3.draw(spriteBatch, strArray.get(i), 100, startY -(i*35));
        }
        
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
        font2.draw(spriteBatch, str, width-256+30, 100);
        
        //Back Button
        spriteBatch.draw(menu_button_sprite, 510, 50);
        str =  "Back";
        font2.draw(spriteBatch, str, 540, 100);
        
        spriteBatch.end();
        if(Gdx.input.justTouched())
        {
        	touchPos = new Point(Gdx.input.getX(), Gdx.input.getY());
        	System.out.println(touchPos);
        	
        	//First Parkingspace
        	if(touchPos.x >= 150 && touchPos.x <= 300 && touchPos.y >= 175 && touchPos.y <= 235)
        	{
        		System.out.println("Pressed First Parkingspace");
        		System.out.println("Choose car 1");
        		if(Cars.size > currentCarScreen*4)
        		{
        			Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4));
        		}
        		
        		parkeringsplats = 0;
        		SelectedCar = parkeringsplats;
        		SelectedCarLevel = currentCarScreen;
        	}
        	if(touchPos.x >= 200 && touchPos.x <= 350 && touchPos.y >= 235 && touchPos.y <= 325)
        	{
        		System.out.println("Pressed First Parkingspace");
        		System.out.println("Choose car 1");
        		if(Cars.size > currentCarScreen*4)
        		{
        			Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4));
        		}
        		parkeringsplats = 0;
        		SelectedCar = parkeringsplats;
        		SelectedCarLevel = currentCarScreen;
        	}
        	
        	//Second Parkingspace
        	if(touchPos.x >= 300 && touchPos.x <= 450 && touchPos.y >= 175 && touchPos.y <= 235)
        	{
        		System.out.println("Pressed Second Parkingspace");
        		System.out.println("Choose car 2");
        		if(Cars.size > currentCarScreen*4+1)
        		{
        			Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4+1));
        		}
        		parkeringsplats = 1;
        		SelectedCar = parkeringsplats;
        		SelectedCarLevel = currentCarScreen;
        	}
        	if(touchPos.x >= 350 && touchPos.x <= 550 && touchPos.y >= 235 && touchPos.y <= 325)
        	{
        		System.out.println("Pressed Second Parkingspace");
        		System.out.println("Choose car 2");
        		if(Cars.size > currentCarScreen*4+1)
        		{
        			Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4+1));
        		}
        		parkeringsplats = 1;
        		SelectedCar = parkeringsplats;
        		SelectedCarLevel = currentCarScreen;
        	}
        	
        	//Third Parkingspace
        	if(touchPos.x >= 450 && touchPos.x <= 650 && touchPos.y >= 175 && touchPos.y <= 235)
        	{
        		System.out.println("Pressed Third Parkingspace");
        		System.out.println("Choose car 3");
        		if(Cars.size > currentCarScreen*4+2)
        		{
        			Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4+2));
        		}
        		parkeringsplats = 2;
        		SelectedCar = parkeringsplats;
        		SelectedCarLevel = currentCarScreen;
        	}
        	if(touchPos.x >= 550 && touchPos.x <= 700 && touchPos.y >= 235 && touchPos.y <= 325)
        	{
        		System.out.println("Pressed Third Parkingspace");
        		System.out.println("Choose car 3");
        		if(Cars.size > currentCarScreen*4+2)
        		{
        			Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4+2));
        		}
        		parkeringsplats = 2;
        		SelectedCar = parkeringsplats;
        		SelectedCarLevel = currentCarScreen;
        	}
        	
        	//Fourth Parkingspace
        	if(touchPos.x >= 650 && touchPos.x <= 850 && touchPos.y >= 175 && touchPos.y <= 235)
        	{
        		System.out.println("Pressed Fourth Parkingspace");
        		System.out.println("Choose car 4");
        		if(Cars.size > currentCarScreen*4+3)
        		{
        			Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4+3));
        		}
        		parkeringsplats = 3;
        		SelectedCar = parkeringsplats;
        		SelectedCarLevel = currentCarScreen;
        	}
        	if(touchPos.x >= 700 && touchPos.x <= 900 && touchPos.y >= 235 && touchPos.y <= 325)
        	{
        		System.out.println("Pressed Fourth Parkingspace");
        		System.out.println("Choose car 4");
        		if(Cars.size > currentCarScreen*4+3)
        		{
        			Resources.getPlayers().get(0).setCurrentCar(Cars.get(currentCarScreen*4+3));
        		}
        		parkeringsplats = 3;
        		SelectedCar = parkeringsplats;
        		SelectedCarLevel = currentCarScreen;
        	}
        	
        	//Check PreviousCar Button
        	if(touchPos.x >= width-500 && touchPos.x <= width-500+64 && touchPos.y >= 75-64 && touchPos.y <= 75)
        	{
        		System.out.println("Pressed Previous Car");
        		ChangeCar(false);
        	}
        	
        	//Check NextCar Button
        	if(touchPos.x >= width-200 && touchPos.x <= width-200+64 && touchPos.y >= 75-64 && touchPos.y <= 75)
        	{
        		System.out.println("Pressed Next Car");
        		ChangeCar(true);
        	}
        	
        	// Check Race Button
        	if(touchPos.x >= width-256 && touchPos.y>= height-50-64 && touchPos.y<= height-50)
        	{
        		System.out.println("Pressed Race");
        		game.setScreen(new GameScreen(game));
        	}
        	
        	// Check Back Button
        	if(touchPos.x >= 510 && touchPos.x <= 510+256 && touchPos.y>= height-50-64 && touchPos.y<= height-50)
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
        
        font = Resources.getFonts().get(0);
        font2 = Resources.getFonts().get(1);
        font3 = Resources.getFonts().get(2);
        
        menu_button_sprite = new Sprite(Resources.getButtons().get(0));
        menu_button_left_sprite = new Sprite(Resources.getButtons().get(1));
        menu_button_right_sprite = new Sprite(Resources.getButtons().get(2));
        whitebox_sprite = new Sprite(Resources.getButtons().get(3));
        selected_Large_sprite = new Sprite(Resources.getButtons().get(4));
        selected_Small_sprite = new Sprite(Resources.getButtons().get(5));
        
        Cars = Resources.getCars();
        
        Resources.addPlayer();
        
        maxCarScreens = (int) Math.ceil(Cars.size / 4.0);
        
        spawnRandomCarToDriveAcrossScreen();
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

