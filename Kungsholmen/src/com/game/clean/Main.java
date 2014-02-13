package com.game.clean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Main {
	private Array<Car> GameCars;
	private Array<NPC> NPCs;
	private Array<Player> Players;
	private Array<PathNode> GreenLine;
	private Array<PathNode> Blueline;
	private Array<Texture> Textures;
	private Array<Train> Trains;
	
	long previousTime = TimeUtils.millis();
	
	public void loadTextures()
	{
		// Ground-based vehicles
		Textures.add(new Texture(Gdx.files.internal("Cars/Caddie_taxi.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Caddie_copcar.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Caddie.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Caddie_Monster.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Snowmobile.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/CityBus.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Radiant_9000.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Delorean_BTTF.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Kitt.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Truck.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Truck_IKEA.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Train_engine.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Train_cart.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Panoz_Roadster.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Volvo_copcar.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Volvo_Ambulance.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Batmobile.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Ecto_1.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Radiant_9000_triwheel.png")));
		
		// Flying vehicles
		Textures.add(new Texture(Gdx.files.internal("Flyers/helicopter_apache.png")));
		
		// Floating vehicles
		Textures.add(new Texture(Gdx.files.internal("Flyers/helicopter_apache.png")));
		
		// Other things
		Textures.add(new Texture(Gdx.files.internal("highbeam.png")));
	}
	
	private int turn(boolean way, int currentDirection, int turnability)
    {
    	int tempTurn = 0;
    	if(way) currentDirection -= turnability;
    	else currentDirection += turnability;
    	
    	if(currentDirection < 0 && way)
    	{
    		tempTurn = 360;
    		for(int i = currentDirection; i < 0; i++)
    		{
    			tempTurn--;
    		}
    		currentDirection = tempTurn;
    	}
    	if(currentDirection > 360 && !way)
    	{
    		tempTurn = 0;
	    	for(int i = currentDirection; i > 360; i--)
			{
				tempTurn++;
			}
	    	currentDirection = tempTurn;
    	}
    	return currentDirection;
    }
	
	private long calcDT()
    {
		long dt;
	    long currentTime;
    	currentTime = TimeUtils.millis();
    	dt = currentTime - previousTime;
    	previousTime = currentTime;
    	return dt;
    	//System.out.println("CurrentTime: " + currentTime + " - PreviousTime: " + previousTime);
    	//System.out.println("DT: " + dt);
    }
    
    private DirectionalVector calcDirectionVector(int currentDirection)
    {
    	double dirVectx;
   	 	double dirVecty;
    	dirVectx = Math.cos(Math.toRadians(currentDirection));
        dirVecty = Math.sin(Math.toRadians(currentDirection));
        return new DirectionalVector(dirVectx, dirVecty);
         //System.out.println("DirectVelocityX: " + dirVectx + " DirectVelocityY: " + dirVecty);
    }
    
    private void loadCars()
    {
    	GameCars.add(new Car());
    }
}
