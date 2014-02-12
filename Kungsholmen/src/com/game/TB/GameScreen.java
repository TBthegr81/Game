package com.game.TB;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
    final GBS game;
    int gameWidth = 1920;
    int gameHeight = 1080;
    
    Texture bg;
    Texture taxiImage;
    Sprite taxiSprite;
    Sprite playerSprite;
    Texture playerImage;
    Texture highbeam;
    Sprite highbeam_sprite;
    Texture checkpointPillar;
    Texture checkpointFlag;
    Sprite checkpointPillarSprite;
    Sprite checkpointFlagSprite;
    //Rectangle checkpoint;
    
    Texture rotorblad;
    Sprite rotorbladSprite;
    //Sound dropSound;
    //Music rainMusic;
    OrthographicCamera camera;
    //Rectangle playerCar;
    //Array<Rectangle> taxis;
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
	
	int ifsnurr;
	int snurr;
	
	
	Array<Rectangle> trains = new Array<Rectangle>();
	int trainTurn;
	int nextNode;
	int lastNode;
	
	int car;
	int turnability;
	ParticleEffectPool bombEffectPool;
	Array<PooledEffect> effects = new Array();
	
	Array<Car> Cars = new Array();
	
	Array<PathNode> GreenLine = new Array();
	
    
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
    
    /*private void calcTrainDegrees(float x, float y)
    {
    	System.out.println("Train X: " + x + " TrainY: " + y);
    	trainTurn = (int) Math.cos(x/y);
    }*/
    
    
    private void turn(boolean way)
    {
    	int tempTurn = 0;
    	if(way) playerTurn -= turnability;
    	else playerTurn += turnability;
    	
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
        car = 14;
        
        Cars.add(new Car("Caddie_taxi", 64, 128, 5, 2, 2));
        Cars.add(new Car("Caddie_copcar", 64, 128, 5, 4, 2));
        Cars.add(new Car("Caddie", 64, 128, 5, 2, 2));
        Cars.add(new Car("Caddie_Monster", 128, 128, 5, 6, 3));
        Cars.add(new Car("Snowmobile", 64, 128, 2, 4, 2));
        Cars.add(new Car("CityBus", 64, 256, 10, 2, 1));
        Cars.add(new Car("Radiant_9000", 32, 64, 2, 4, 2));
        Cars.add(new Car("Delorean_BTTF", 64, 128, 5, 4, 2));
        Cars.add(new Car("Kitt", 64, 128, 5, 5, 2));
        Cars.add(new Car("Truck", 64, 256, 10, 2, 1));
        Cars.add(new Car("Truck_IKEA", 64, 256, 10, 2, 1));
        Cars.add(new Car("Train_engine", 64, 256, 10, 2, 1));
        Cars.add(new Car("Train_cart", 64, 256, 10, 2, 1));
        Cars.add(new Car("Panoz_Roadster", 64, 128, 4, 10, 2));
        Cars.add(new Car("Volvo_copcar", 64, 128, 5, 4, 2));
        Cars.add(new Car("Volvo_Ambulance", 64, 160, 5, 4, 2));
        //Cars.add(new Car("helicopter_apache", 128, 256, 5, 4, 2));
        
        highbeam = new Texture(Gdx.files.internal("highbeam.png"));
        highbeam_sprite = new Sprite(highbeam);
        
        rotorblad = new Texture(Gdx.files.internal("Flyers/rotorblad.png"));
        rotorbladSprite = new Sprite(rotorblad);
        // load the images for the droplet and the bucket, 64x64 pixels each
        //taxiImage = new Texture(Gdx.files.internal("Cars/Caddie_taxi.png"));
        //taxiSprite = new Sprite(taxiImage);
        //taxiSprite.setPosition(1, 1);
        //taxiSprite.setRotation(taxiSprite.getRotation() - 90);    
        
        bg = new Texture(Gdx.files.internal("bg.png"));
        //bg.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        //setTextureWrap(TextureWrap.GL_REPEAT)
        //checkpoint = new Rectangle();
        //checkpoint.setHeight(128);
        //checkpoint.setWidth(64+256+64);
        checkpointPillar = new Texture(Gdx.files.internal("Checkpoint_pillar.png"));
        checkpointFlag = new Texture(Gdx.files.internal("Checkpoint_flag.png"));
        checkpointPillarSprite = new Sprite(checkpointPillar);
        checkpointFlagSprite = new Sprite(checkpointFlag);
        
        //playerImage = new Texture(Gdx.files.internal("Cars/Snowmobile.png"));
        //playerSprite = new Sprite(playerImage);
        //playerSprite.setRotation(playerSprite.getRotation() - 90);
        loadPlayerCar();
        
        // load the drop sound effect and the rain background "music"
        //dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        //rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        //rainMusic.setLooping(true);

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, gameWidth, gameHeight);

        // create a Rectangle to logically represent the bucket
        //playerCar = new Rectangle();
        
        //playerCar.width = 64;
        //playerCar.height = 256;
        PathNode node = new PathNode(-500,-500);
        node.setNextNode(1);
        GreenLine.add(node);
        node = new PathNode(500,-500);
        node.setNextNode(2);
        GreenLine.add(node);
        node = new PathNode(500,500);
        node.setNextNode(3);
        GreenLine.add(node);
        node = new PathNode(-500,500);
        node.setNextNode(0);
        GreenLine.add(node);
        
        lastNode = 0;
        nextNode = GreenLine.get(lastNode).getNextNode();
        Rectangle train;
        train = new Rectangle();
        train.x = GreenLine.get(lastNode).getX(); // center the bucket horizontally
        train.y = GreenLine.get(lastNode).getY(); // bottom left corner of the bucket is 20 pixels above
        train.width = 64;
        train.height = 256;
        trains.add(train);
        
        train = new Rectangle();
        train.x = 0;
        train.y = train.y;
        train.width = 64;
        train.height = 256;
        trains.add(train);
        
        train = new Rectangle();
        train.x = 0;
        train.y = train.y;
        train.width = 64;
        train.height = 256;
        trains.add(train);

        // create the raindrops array and spawn the first raindrop
        taxis = new Array<Rectangle>();
        spawnRaindrop(true);
        
        fpsLogger = new FPSLogger();
        
        ParticleEffect bombEffect = new ParticleEffect();
        bombEffect.load(Gdx.files.internal("Particles/Snowsprayv2"),Gdx.files.internal("Particles/"));
        bombEffectPool = new ParticleEffectPool(bombEffect, 1, 2);
        
        snurr = 1;
    }

    private void spawnRaindrop(boolean thing) {
        //Rectangle taxi = new Rectangle();
        Rectangle taxi = Cars.get(0).getRectangle();
        //taxi.width = 64;
        //taxi.height = 128;
        if(thing){
        	taxi.y = gameHeight;
        	taxi.x = MathUtils.random(0, gameWidth - 64);
        	taxis.add(taxi);
        }
        
        lastDropTime = TimeUtils.nanoTime();
    }
    
    private void loadPlayerCar()
    {
    	playerSprite = Cars.get(car).getSprite();
        playerCar = Cars.get(car).getRectangle();
        //playerCar.x = 0; // center the bucket horizontally
        //playerCar.y = 0; // bottom left corner of the bucket is 20 pixels above
        turnability = Cars.get(car).getTurnability();
        dirVectx = 0;
        dirVecty = 0;
    }
    
    private void drawCheckpoint(int x, int y, int degrees)
    {
    	game.batch.draw(checkpointPillarSprite, x, y, checkpointPillarSprite.getOriginX(), checkpointPillarSprite.getOriginY(), checkpointPillarSprite.getWidth(), checkpointPillarSprite.getHeight(), checkpointPillarSprite.getScaleX(), checkpointPillarSprite.getScaleY(), degrees);
    	game.batch.draw(checkpointFlagSprite, x+64, y+64, checkpointFlagSprite.getOriginX(), checkpointFlagSprite.getOriginY(), checkpointFlagSprite.getWidth(), checkpointFlagSprite.getHeight(), checkpointFlagSprite.getScaleX(), checkpointFlagSprite.getScaleY(), degrees);
    	game.batch.draw(checkpointPillarSprite, x+256+64, y, checkpointPillarSprite.getOriginX(), checkpointPillarSprite.getOriginY(), checkpointPillarSprite.getWidth(), checkpointPillarSprite.getHeight(), checkpointPillarSprite.getScaleX(), checkpointPillarSprite.getScaleY(), degrees);
    }
   

    @Override
    public void render(float delta) {
    	calcDT();

    	fps++;
    	fpsLogger.log();
    	PooledEffect effect = bombEffectPool.obtain();
    	effect.setPosition(playerCar.x + playerCar.width, (playerCar.y + (playerCar.height / 2)));
    	
    	effects.add(effect);
    	
    	//Move Camera to center of car
    	float lerp = 0.1f;
    	Vector3 position = camera.position;
    	position.x += (playerCar.x - position.x) * lerp;
    	position.y += (playerCar.y - position.y) * lerp;
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
        //game.batch.draw(texture, x, y);
        game.batch.draw(bg,0,0);
        for (int i = effects.size - 1; i >= 0; i--) {
            effect = effects.get(i);
            effect.draw(game.batch, delta);
            if (effect.isComplete()) {
                effect.free();
                effects.removeIndex(i);
            }
        }
        game.font.draw(game.batch, "FPS: " + lastFPS, playerCar.x, playerCar.y);
        game.font.draw(game.batch, "Pos: x" + playerCar.x + " y" +playerCar.y, playerCar.x, playerCar.y-20);
        game.batch.draw(playerSprite, playerCar.x, playerCar.y, playerSprite.getOriginX(), playerSprite.getOriginY(), playerSprite.getWidth(), playerSprite.getHeight(), playerSprite.getScaleX(), playerSprite.getScaleY(), playerTurn);
        
        //game.batch.draw(playerSprite, playerCar.x, playerCar.y);
       //game.batch.draw(highbeam_sprite, playerCar.x, playerCar.y, highbeam_sprite.getOriginX(), highbeam_sprite.getOriginY(), highbeam_sprite.getWidth(), highbeam_sprite.getHeight(), highbeam_sprite.getScaleX(), highbeam_sprite.getScaleY(), playerTurn);
        //game.batch.draw(playerSprite, thing.x, thing.y, playerSprite.getOriginX(), playerSprite.getOriginY(), playerSprite.getWidth(), playerSprite.getHeight(), playerSprite.getScaleX(), playerSprite.getScaleY(), playerTurn);
        
        //game.batch.draw(playerSprite, bucket.x, bucket.y, playerSprite.getOriginX(), playerSprite.getOriginY(), playerSprite.getWidth(), playerSprite.getHeight(), 1, 1, 270);
        for (Rectangle taxi : taxis) {
            //game.batch.draw(taxiSprite, raindrop.x, raindrop.y);
        	Sprite taxiSprite = Cars.get(0).getSprite();
        	game.batch.draw(taxiSprite, taxi.x, taxi.y, taxiSprite.getOriginX(), taxiSprite.getOriginY(), taxiSprite.getWidth(), taxiSprite.getHeight(), taxiSprite.getScaleX(), taxiSprite.getScaleY(), 90);
        }
        
        //Train
        Sprite trainSprite = Cars.get(11).getSprite();
        Sprite trainCartSprite = Cars.get(12).getSprite();
        Rectangle train = trains.get(0);
        Rectangle train2 = trains.get(1);
        Rectangle train3 = trains.get(0);
        game.batch.draw(trainSprite, train.x, train.y, trainSprite.getOriginX(), trainSprite.getOriginY(), trainSprite.getWidth(), trainSprite.getHeight(), trainSprite.getScaleX(), trainSprite.getScaleY(), trainTurn);
        game.batch.draw(trainCartSprite, train2.x, train2.y, trainSprite.getOriginX(), trainSprite.getOriginY(), trainSprite.getWidth(), trainSprite.getHeight(), trainSprite.getScaleX(), trainSprite.getScaleY(), trainTurn);
        game.batch.draw(trainSprite, train3.x, train3.y, trainSprite.getOriginX(), trainSprite.getOriginY(), trainSprite.getWidth(), trainSprite.getHeight(), trainSprite.getScaleX(), trainSprite.getScaleY(), trainTurn);
        
        drawCheckpoint(0,0,0);
        drawCheckpoint(500,500,225);
        drawCheckpoint(1000,1000,90);
        if(ifsnurr == 1)
        	{
        		snurr = 45;
        		ifsnurr = 0;
        	}
        else{
        	snurr = 0;
        	ifsnurr = 1;
        }
        //game.batch.draw(rotorbladSprite, playerCar.x, playerCar.y, playerSprite.getOriginX(), playerSprite.getOriginY(), playerSprite.getWidth(), playerSprite.getHeight(), playerSprite.getScaleX(), playerSprite.getScaleY(), snurr);
       
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
        		VeloX *= .99;
             	VeloY *= .99;
        		VeloX -= accforward * dirVectx * dt;
             	VeloY -= accforward * dirVecty * dt;
            }
        	else if (Gdx.input.isKeyPressed(Keys.DOWN))
            {
            	VeloX += accforward * dirVectx * dt;
            	VeloY += accforward * dirVecty * dt;
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
        		VeloX *= .99;
             	VeloY *= .99;
        		VeloX -= accforward * dirVectx * dt;
             	VeloY -= accforward * dirVecty * dt;
            }
        	else if (Gdx.input.isKeyPressed(Keys.DOWN))
            {
            	VeloX += accforward * dirVectx * dt;
            	VeloY += accforward * dirVecty * dt;
        		
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
	        	//VeloX *= .99;
	         	//VeloY *= .99;
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
        	System.out.println("VeloX * dt" + VeloX * dt);
        	System.out.println("VeloY * dt" + VeloY * dt);
        playerCar.x += VeloX * dt;
        playerCar.y += VeloY * dt;
        
        //calcTrainDirectionVector();
        //move train
        //train.x += trainVeloX * dt;
        //train.y += trainVeloY * dt;
        float nextNodeX = GreenLine.get(nextNode).getX();
        System.out.println("NextNodeX : " + nextNodeX);
        float nextNodeY = GreenLine.get(nextNode).getY();
        
        float lastNodeX = GreenLine.get(lastNode).getX();
        float lastNodeY = GreenLine.get(lastNode).getY();
        
        float hiddenCX = nextNodeX - lastNodeX;
        float hiddenCY = nextNodeY - lastNodeY;
        //float distanceX = nextNodeX - train.x;
        //float distanceY = nextNodeY - train.y;
        Triangle tri = new Triangle(lastNodeX, lastNodeY, nextNodeX, nextNodeY, hiddenCX, hiddenCY);
        trainTurn = (int) tri.getAngleB();
        //calcTrainDegrees(distanceX,distanceY );
        for(int i = 0; i < trains.size; i++)
        {
        	train = trains.get(i);
        	if(train.x < nextNodeX)
            {
            	train.x += 1;
            	train2.x += 1;
            	train3.x += 1;
            }
            else if(train.x > nextNodeX)
            {
            	train.x -= 1;
            	train2.x -= 1;
            	train3.x -= 1;
            }
            if(train.y < nextNodeY)
            {
            	train.y += 1;
            	train2.y += 1;
            	train3.y += 1;
            }
            else if(train.y > nextNodeY)
            {
            	train.y -= 1;
            	train2.y -= 1;
            	train3.y -= 1;
            }
        }
        if(train.x == nextNodeX && train.y == nextNodeY)
        {
        	lastNode = nextNode;
        	nextNode = GreenLine.get(nextNode).getNextNode();
        }
        
        // make sure the bucket stays within the screen bounds
    	/*if (playerCar.x < 0)
    		playerCar.x = 0;
        if (playerCar.x > gameWidth - 128)
        	playerCar.x = gameWidth - 128;
        if (playerCar.y < 0)
        	playerCar.y = 0;
        if (playerCar.y > gameHeight - 128)
        	playerCar.y = gameHeight - 128;*/
        if (Gdx.input.isKeyPressed(Keys.PAGE_UP))
        {
        	car++;
        	if(car >= Cars.size)
        	{
        		car = 0;
        	}
        	loadPlayerCar();
        }
        if (Gdx.input.isKeyPressed(Keys.PAGE_DOWN))
        {
        	car--;
        	if(car < 0)
        	{
        		car = (Cars.size-1);
        	}
        	loadPlayerCar();
        }
        if(Gdx.input.isKeyPressed(Keys.F1))
        {
        	car = 0;
        	loadPlayerCar();
        }
        else if(Gdx.input.isKeyPressed(Keys.F2))
        {
        	car = 1;
        	loadPlayerCar();
        }
        else if(Gdx.input.isKeyPressed(Keys.F3))
        {
        	car = 2;
        	loadPlayerCar();
        }
        else if(Gdx.input.isKeyPressed(Keys.F4))
        {
        	car = 3;
        	loadPlayerCar();
        }
        else if(Gdx.input.isKeyPressed(Keys.F5))
        {
        	car = 4;
        	loadPlayerCar();
        }
        else if(Gdx.input.isKeyPressed(Keys.F6))
        {
        	car = 5;
        	loadPlayerCar();
        }
        else if(Gdx.input.isKeyPressed(Keys.F7))
        {
        	car = 6;
        	loadPlayerCar();
        }
        else if(Gdx.input.isKeyPressed(Keys.F8))
        {
        	car = 7;
        	loadPlayerCar();
        }
        else if(Gdx.input.isKeyPressed(Keys.F9))
        {
        	car = 8;
        	loadPlayerCar();
        }
        else if(Gdx.input.isKeyPressed(Keys.F10))
        {
        	car = 9;
        	loadPlayerCar();
        }
        else if(Gdx.input.isKeyPressed(Keys.F11))
        {
        	car = 10;
        	loadPlayerCar();
        }
        else if(Gdx.input.isKeyPressed(Keys.F12))
        {
        	car = 13;
        	loadPlayerCar();
        }
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
