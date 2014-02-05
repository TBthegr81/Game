package com.game.TB;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
    final GBS game;

    Texture taxiImage;
    Sprite taxiSprite;
    Sprite playerSprite;
    Texture playerImage;
    //Sound dropSound;
    //Music rainMusic;
    OrthographicCamera camera;
    Rectangle playerCar;
    Array<Rectangle> taxis;
    long lastDropTime;
    int fps;
    int lastFPS;
    
    FPSLogger fpsLogger;
    
    int playerTurn;
    
    double dirVectx;
    double dirVecty;
    
    double VeloX;
    double VeloY;
    
    double accforward = 0.001;
    
    long previousTime = TimeUtils.millis();
    long currentTime;
    long dt;
    
	double circleX;
	double circleY;
	double radius = 100;
	int speed = 6;
	double speedScale = (0.001*2*Math.PI)/speed;
	
	Rectangle thing;
    
    private void calcDT()
    {
    	currentTime = TimeUtils.millis();
    	dt = currentTime - previousTime;
    	previousTime = currentTime;
    	//System.out.println("CurrentTime: " + currentTime + " - PreviousTime: " + previousTime);
    	//System.out.println("DT: " + dt);
    }
    
    private void calcDirectionVector()
    {
    	 dirVectx = Math.cos(Math.toRadians(playerTurn));
         dirVecty = Math.sin(Math.toRadians(playerTurn));
         //System.out.println("DirectVelocityX: " + dirVectx + " DirectVelocityY: " + dirVecty);
    }
    
    private void turn(boolean way)
    {
    	int tempTurn = 0;
    	if(way) playerTurn -= 1;
    	else playerTurn += 1;
    	
    	if(playerTurn < 0 && way)
    	{
    		tempTurn = 360;
    		for(int i = playerTurn; i < 0; i++)
    		{
    			tempTurn--;
    		}
    		playerTurn = tempTurn;
    	}
    	if(playerTurn > 360 && !way)
    	{
    		tempTurn = 0;
	    	for(int i = playerTurn; i > 360; i--)
			{
				tempTurn++;
			}
	    	playerTurn = tempTurn;
    	}
    	
    }
    
    public GameScreen(final GBS gam) {
        this.game = gam;

        // load the images for the droplet and the bucket, 64x64 pixels each
        taxiImage = new Texture(Gdx.files.internal("Cars/Caddie_taxi.png"));
        taxiSprite = new Sprite(taxiImage);
        //taxiSprite.setPosition(1, 1);
        taxiSprite.setRotation(taxiSprite.getRotation() - 90);
        
        playerImage = new Texture(Gdx.files.internal("Cars/Delorean-BTTF.png"));
        playerSprite = new Sprite(playerImage);
        //playerSprite.setRotation(playerSprite.getRotation() - 90);

        // load the drop sound effect and the rain background "music"
        //dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        //rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        //rainMusic.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // create a Rectangle to logically represent the bucket
        playerCar = new Rectangle();
        playerCar.x = 800 / 2 - 64 / 2; // center the bucket horizontally
        playerCar.y = 50; // bottom left corner of the bucket is 20 pixels above
                        // the bottom screen edge
        playerCar.width = 64;
        playerCar.height = 128;
        
        thing = new Rectangle();
        thing.x = 800 / 2 - 64 / 2; // center the bucket horizontally
        thing.y = 50; // bottom left corner of the bucket is 20 pixels above
                        // the bottom screen edge
        thing.width = 64;
        thing.height = 128;

        // create the raindrops array and spawn the first raindrop
        taxis = new Array<Rectangle>();
        spawnRaindrop(true);
        
        fpsLogger = new FPSLogger();

    }

    private void spawnRaindrop(boolean thing) {
        Rectangle taxi = new Rectangle();
        
        taxi.width = 64;
        taxi.height = 128;
        if(thing){
        	taxi.y = 480;
        	taxi.x = MathUtils.random(0, 800 - 64);
        	taxis.add(taxi);
        }
        
        lastDropTime = TimeUtils.nanoTime();
    }
   

    @Override
    public void render(float delta) {
    	calcDT();

    	fps++;
    	fpsLogger.log();
    	
    	
    	//thing.x = (float) (circleX + Math.sin(angle)*radius);
    	//thing.y = (float) (circleY + Math.cos(angle)*radius);
    	  
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        
        game.batch.begin();
        game.font.draw(game.batch, "FPS: " + lastFPS, 0, 480);
        //game.batch.draw(playerSprite, bucket.x, bucket.y);
        game.batch.draw(playerSprite, playerCar.x, playerCar.y, playerSprite.getOriginX(), playerSprite.getOriginY(), playerSprite.getWidth(), playerSprite.getHeight(), playerSprite.getScaleX(), playerSprite.getScaleY(), playerTurn);
        //game.batch.draw(playerSprite, thing.x, thing.y, playerSprite.getOriginX(), playerSprite.getOriginY(), playerSprite.getWidth(), playerSprite.getHeight(), playerSprite.getScaleX(), playerSprite.getScaleY(), playerTurn);
        
        //game.batch.draw(playerSprite, bucket.x, bucket.y, playerSprite.getOriginX(), playerSprite.getOriginY(), playerSprite.getWidth(), playerSprite.getHeight(), 1, 1, 270);
        for (Rectangle taxi : taxis) {
            //game.batch.draw(taxiSprite, raindrop.x, raindrop.y);
        	game.batch.draw(taxiSprite, taxi.x, taxi.y, taxiSprite.getOriginX(), taxiSprite.getOriginY(), taxiSprite.getWidth(), taxiSprite.getHeight(), taxiSprite.getScaleX(), taxiSprite.getScaleY(), 90);
        }
        game.batch.end();

        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            playerCar.x = touchPos.x - 64 / 2;
        }
        //double angle = (TimeUtils.millis()*speedScale);
        calcDirectionVector();
        if (Gdx.input.isKeyPressed(Keys.LEFT))
        {
    		turn(false);
    		//playerCar.x = (float) (circleX + Math.sin(angle)*radius);
        	//playerCar.y = (float) (circleY + Math.cos(angle)*radius);
        	if (Gdx.input.isKeyPressed(Keys.UP))
            {
        		VeloX *= .9;
             	VeloY *= .9;
        		VeloX -= accforward * dirVectx * dt;
             	VeloY -= accforward * dirVecty * dt;
            }
        }
        	
        else if (Gdx.input.isKeyPressed(Keys.RIGHT))
        {
        	turn(true);
        	/*VeloX += accforward * Math.sin(angle)*radius * dt;
        	VeloY += accforward * Math.cos(angle)*radius * dt;
        	VeloX *= .99;
        	VeloY *= .99;
        	VeloY += accforward * dirVecty * dt;*/
        	if (Gdx.input.isKeyPressed(Keys.UP))
            {
        		VeloX *= .9;
             	VeloY *= .9;
        		VeloX -= accforward * dirVectx * dt;
             	VeloY -= accforward * dirVecty * dt;
            }
        }
        else if (Gdx.input.isKeyPressed(Keys.UP))
        {
        	 /*if (Gdx.input.isKeyPressed(Keys.LEFT))
             {
             	//VeloX = 0;
             	//VeloY = 0;
        		VeloX *= .99;
             	VeloY *= .99;
             	turn(false);
             	VeloX -= accforward * dirVectx * dt;
             	VeloY -= accforward * dirVecty * dt;
             }
        		//bucket.y -= 200 * Gdx.graphics.getDeltaTime();
        	 if (Gdx.input.isKeyPressed(Keys.RIGHT))
             {
        		//VeloX = 0;
        		//VeloY = 0;
        		VeloX *= .99;
             	VeloY *= .99;
              	turn(true);
              	VeloX -= accforward * dirVectx * dt;
              	VeloY -= accforward * dirVecty * dt;
             }*/
	        	VeloX *= .99;
	         	VeloY *= .99;
            	VeloX -= accforward * dirVectx * dt;
            	VeloY -= accforward * dirVecty * dt;
            	//pos.x += vel.x * dt
            	//pos.y += vel.y * dt
        }
        else if (Gdx.input.isKeyPressed(Keys.DOWN))
        {
        	VeloX += accforward * dirVectx * dt;
        	VeloY += accforward * dirVecty * dt;
        }
        System.out.println("VeloX: " + VeloX + " VeloY: " + VeloY);
        // Simulate friction
    		VeloX *= .99;
        	VeloY *= .99;
        playerCar.x += VeloX * dt;
        playerCar.y += VeloY * dt;

        // make sure the bucket stays within the screen bounds
    	if (playerCar.x < 0)
    		playerCar.x = 0;
        if (playerCar.x > 800 - 128)
        	playerCar.x = 800 - 128;
        if (playerCar.y < 0)
        	playerCar.y = 0;
        if (playerCar.y > 480 - 128)
        	playerCar.y = 480 - 128;

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
        {
        	switch(MathUtils.random(1,2)){
        	case 1: spawnRaindrop(true);
        	break;
        	case 2: spawnRaindrop(true);
        	break;
        	}
            lastFPS = fps;
            fps = 0;
        }

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we increase the 
        // value our drops counter and add a sound effect.
        Iterator<Rectangle> iter = taxis.iterator();
        while (iter.hasNext()) {
            Rectangle taxi = iter.next();
            taxi.y -= 200 * Gdx.graphics.getDeltaTime();
            if (taxi.y + 64 < 0)
                iter.remove();
            if (taxi.overlaps(playerCar)) {	
                //dropSound.play();
                iter.remove();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        //rainMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    	taxiImage.dispose();
    	playerImage.dispose();
    	//playerSprite.dispose();
        //dropSound.dispose();
        //rainMusic.dispose();
    }

}
