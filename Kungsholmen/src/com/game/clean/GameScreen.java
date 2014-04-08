package com.game.clean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen{
	final KungsholmenGame game;
	private SpriteBatch spriteBatch;
	@SuppressWarnings("unused")
	private Texture splsh;
	@SuppressWarnings("unused")
    private BitmapFont font;
    private BitmapFont font2;
    private CharSequence str;
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    Point touchPos;
    Vector3 position;
    Sprite background;
    
    int[] backgrounds = new int[]{0,512,1024};
    
    Array<Car> Cars = new Array<Car>();
    Array<NPC> NPCs;
    int[] lanes = new int[]{height/100*7, height/100*20, height/100*32, height/100*64, height/100*77, height/100*90};
    
    int score;
    int time;
    int health;
    boolean gameOver;
    int currentCars;
    int maxCars;
    Point newPlayerLocation;
    
    @SuppressWarnings("unused")
    private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	
	Player player = Resources.getPlayers().get(0);
    //int playerY;
    //int playerX;
    int SelectedCar = 0;
    
    public GameScreen(KungsholmenGame game)
    {
            this.game = game;
    }
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Move camera to center car position
		float lerp = 0.1f;
		position = camera.position;
		position.x += (player.getLocation().x - position.x) * lerp;
		position.y += (player.getLocation().y - position.y) * lerp;
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		if(!gameOver)
		{
		spriteBatch.begin();
		//Move and Draw Background
		for(int i = 0; i < backgrounds.length; i++)
		{
			backgrounds[i] = backgrounds[i]-15;
			if(backgrounds[i] < -500)
			{
				backgrounds[i] = 1024;
			}
			System.out.println(backgrounds[i]);
			spriteBatch.draw(background, backgrounds[i], 0);
		}
		
		
		//Draw Score Text
		str = "SCORE: ";
		font2.draw(spriteBatch, str, width/100*5, height/100*99);
		str = "" + score;
		font2.draw(spriteBatch, str, width/100*5+150, height/100*99);
		
		//Draw Time Text
		str = "TIME: ";
		font2.draw(spriteBatch, str, width/100*50, height/100*99);
		str = "" + time;
		font2.draw(spriteBatch, str, width/100*50+120, height/100*99);
		
		//Draw Health
		str = "HP: ";
		font2.draw(spriteBatch, str, width/100*5, height/100*95);
		str = "" + health;
		font2.draw(spriteBatch, str, width/100*5+80, height/100*95);
		
		//Check input
				if(Gdx.input.isKeyPressed(Keys.UP))
				{
					//Move car up
					if(player.getLocation().y < height-150)
					{
						//player.setLocation(new Point(player.getLocation().x, player.getLocation().y + 10));
						newPlayerLocation.setX(player.getLocation().x);
						newPlayerLocation.setY(player.getLocation().y + 10);
					}
					
				}
				if(Gdx.input.isKeyPressed(Keys.DOWN))
				{
					//Move car up
					if(player.getLocation().y > 0)
					{
						//player.setLocation(new Point(player.getLocation().x, player.getLocation().y - 10));
						newPlayerLocation.setX(player.getLocation().x);
						newPlayerLocation.setY(player.getLocation().y - 10);
					}
				}
				
		//Draw Player
	    Sprite carSprite = player.getCurrentCar().getSprite();
	    spriteBatch.draw(carSprite, player.getLocation().x, player.getLocation().y, carSprite.getOriginX(), carSprite.getOriginY(), carSprite.getWidth(), carSprite.getHeight(), 1, 1, 180);
	    
	    //Move and Draw NPC's
	    Rectangle r2 = new Rectangle(player.getLocation().x, player.getLocation().y, player.getCurrentCar().getSprite().getWidth(), player.getCurrentCar().getSprite().getHeight());
		
	    for(NPC npc : NPCs)
	    {
	    	Car car = npc.getCar();
	    	Rectangle r1 = new Rectangle(npc.getLocation().x, npc.getLocation().y, npc.getCar().getSprite().getWidth(), npc.getCar().getSprite().getHeight());
			
			if(npc.getDirection())
	    	{
				//Check Collisons
				if(!r1.overlaps(r2))
				{
					npc.setLocation(new Point(npc.getLocation().x+10, npc.getLocation().y));
					player.setLocation(newPlayerLocation);
				}
				else
					{
					System.out.println("boom");
					score-=20;
					//health--;
					if(health <= 0)
					{
						gameOver = true;
					}
				}
	    		
				//If a car is outside screen, remove it and add score
	    		if(npc.getLocation().x > width+500)
	        	{
	        		npc.setLocation(new Point(MathUtils.random(-5000, 0), lanes[MathUtils.random(0, 2)]));
	        		score +=5;
	        		currentCars--;
	        	}
	    		
	    		//Draw NPC Car if its inside the window
	    		if(npc.getLocation().x > 0-200 && npc.getLocation().x < width+500)
	        	{
	    			currentCars++;
	        		spriteBatch.draw(car.getSprite(), npc.getLocation().x , npc.getLocation().y, car.getSprite().getOriginX(), car.getSprite().getOriginY(), car.getSprite().getWidth(), car.getSprite().getHeight(), 1,1,180);
	        	}
	    	}
	    	else
	    	{
	    		//Check Collisons
	    		if(!r1.overlaps(r2))
				{
	    			npc.setLocation(new Point(npc.getLocation().x-10, npc.getLocation().y));
	    			player.setLocation(newPlayerLocation);
				}
	    		else
	    		{
	    			System.out.println("boom");
					score-=20;
					//health--;
					if(health <= 0)
					{
						gameOver = true;
					}
	    		}
	    		
	    		//If a car is outside screen, remove it and add score
	    		if(npc.getLocation().x < -500)
	        	{
	    			npc.setLocation(new Point(width + MathUtils.random(0, 5000), lanes[MathUtils.random(3, 5)]));
	    			score +=5;
	    			currentCars--;
	        	}
	    		
	    		//Draw NPC Car if its inside the window
	    		if(npc.getLocation().x < width + 500 && npc.getLocation().x > 0-200)
	        	{
	    			currentCars++;
	    			spriteBatch.draw(car.getSprite(), npc.getLocation().x , npc.getLocation().y, car.getSprite().getOriginX(), car.getSprite().getOriginY(), car.getSprite().getWidth(), car.getSprite().getHeight(), 1,1,0);
	        	}
	    	}
	    }
	    
	    //Move and Draw Trains
	
	    spriteBatch.end();
		
		
		
		time++;
		}
		else
		{
			spriteBatch.begin();
			//Draw GameOver
			str = "GAME OVER";
			font2.draw(spriteBatch, str, width/100*40, height/100*70);

			//Draw Score Text
			str = "SCORE: ";
			font2.draw(spriteBatch, str, width/100*40, height/100*65);
			str = "" + score;
			font2.draw(spriteBatch, str, width/100*40+150, height/100*65);
			
			//Draw Time Text
			str = "TIME: ";
			font2.draw(spriteBatch, str, width/100*40, height/100*60);
			str = "" + time;
			font2.draw(spriteBatch, str, width/100*40+120, height/100*60);
			spriteBatch.end();
		}
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
        
		font = Resources.getFonts().get(0);
        font2 = Resources.getFonts().get(1);
        
        Cars = Resources.getCars();
        
        background = new Sprite(Resources.getBackgrounds().get(2));
        
        //Set player to middle of map
        newPlayerLocation = new Point(width/2, height/2);
        player.setLocation(newPlayerLocation);
        
        //Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        
        //Get some NPC's
        NPCs = Resources.getNPCs();
        for(NPC npc : NPCs)
        {
        	if(npc.getDirection())
        	{
        		npc.setLocation(new Point(width + MathUtils.random(0, 5000), lanes[MathUtils.random(0, 2)]));
        	}
        	else
        	{
        		npc.setLocation(new Point(width + MathUtils.random(0, 5000), lanes[MathUtils.random(3, 5)]));
        	}
        	
        }
        
        health = 100;
        gameOver = false;
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
	
	/*public boolean collide(Rectangle r1, Rectangle r2)
	{
		System.out.println("r2 :" + r2.x + " " + (r2.x+r2.width) + " " + r2.y + " " + (r2.y+r2.height));
		System.out.println("r1 :" + r1.x + " " + (r1.x+r1.width) + " " + r1.y + " " + (r1.y+r1.height));
		/*if(r2.x > r1.x+r1.width 
				|| r2.x + r2.width < r1.x 
				|| r2.y+r2.height > r1.y 
				|| r2.y < r1.y+r1.height)
		{
			System.out.println("NO COLLISON");
			return false;
		}
		else
		{
			System.out.println("BOOOM");
			return true;
		}
		return !(r2. > r1.x+r1.width
		        || r2.x+r2.width < r1.x
		        || r2.y+r2.height > r1.y
		        || r2.y < r1.y+r1.height);
	}*/
}
