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
    Rectangle bucket;
    Array<Rectangle> raindrops;
    Array<Rectangle> raindrops2;
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
    	if(way) playerTurn -= 10;
    	else playerTurn += 10;
    	
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
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
        bucket.y = 50; // bottom left corner of the bucket is 20 pixels above
                        // the bottom screen edge
        bucket.width = 64;
        bucket.height = 128;

        // create the raindrops array and spawn the first raindrop
        raindrops = new Array<Rectangle>();
        spawnRaindrop(true);
        
        fpsLogger = new FPSLogger();

    }

    private void spawnRaindrop(boolean thing) {
        Rectangle raindrop = new Rectangle();
        
        raindrop.width = 64;
        raindrop.height = 128;
        if(thing){
        	raindrop.y = 480;
        	raindrop.x = MathUtils.random(0, 800 - 64);
        	raindrops.add(raindrop);
        }
        else{
        	raindrop.y = 0;
        	raindrop.x = MathUtils.random(400, 800 - 64);
        	raindrops2.add(raindrop);
        }
        
        lastDropTime = TimeUtils.nanoTime();
    }
   

    @Override
    public void render(float delta) {
    	calcDT();
        calcDirectionVector();
    	fps++;
    	fpsLogger.log();
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
        game.batch.draw(playerSprite, bucket.x, bucket.y, playerSprite.getOriginX(), playerSprite.getOriginY(), playerSprite.getWidth(), playerSprite.getHeight(), playerSprite.getScaleX(), playerSprite.getScaleY(), playerTurn);
        //game.batch.draw(playerSprite, bucket.x, bucket.y, playerSprite.getOriginX(), playerSprite.getOriginY(), playerSprite.getWidth(), playerSprite.getHeight(), 1, 1, 270);
        for (Rectangle raindrop : raindrops) {
            //game.batch.draw(taxiSprite, raindrop.x, raindrop.y);
        	game.batch.draw(taxiSprite, raindrop.x, raindrop.y, taxiSprite.getOriginX(), taxiSprite.getOriginY(), taxiSprite.getWidth(), taxiSprite.getHeight(), taxiSprite.getScaleX(), taxiSprite.getScaleY(), 90);
        }
        game.batch.end();

        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - 64 / 2;
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            turn(false);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            turn(true);
        if (Gdx.input.isKeyPressed(Keys.UP))
        {
        		//bucket.y -= 200 * Gdx.graphics.getDeltaTime();
            	VeloX += accforward * dirVectx * dt;
            	VeloY += accforward * dirVecty * dt;
            	System.out.println("VeloX: " + VeloX + " VeloY: " + VeloY);
            	// Simulate friction
            	VeloX *= .99;
            	VeloY *= .99;
            	//pos.x += vel.x * dt
            	//pos.y += vel.y * dt
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN))
        {
        	bucket.y -= 200 * Gdx.graphics.getDeltaTime();
        }
        bucket.x += VeloX * dt;
    	bucket.y += VeloY * dt;

        // make sure the bucket stays within the screen bounds
    	if (bucket.x < 0)
            bucket.x = 0;
        if (bucket.x > 800 - 128)
            bucket.x = 800 - 128;
        if (bucket.y < 0)
            bucket.y = 0;
        if (bucket.y > 480 - 128)
            bucket.y = 480 - 128;

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
        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0)
                iter.remove();
            if (raindrop.overlaps(bucket)) {	
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
