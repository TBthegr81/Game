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
	private Array<Boat> Boats;
	private Array<Helicopter> Helicopters;
	private Array<Plane> Planes;
	//TODO private Array<Music> Musics;
	//TODO private Array<Soundeffect> Soundeffects;
	
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
		Textures.add(new Texture(Gdx.files.internal("Cars/Truck_IKEA.png"))); //10
		Textures.add(new Texture(Gdx.files.internal("Cars/Train_engine.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Train_cart.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Panoz_Roadster.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Volvo_copcar.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Volvo_Ambulance.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Batmobile.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Ecto_1.png")));
		Textures.add(new Texture(Gdx.files.internal("Cars/Radiant_9000_triwheel.png"))); //18
		
		// Flying vehicles
		Textures.add(new Texture(Gdx.files.internal("Flyers/helicopter_apache.png")));
		
		// Floating vehicles
		Textures.add(new Texture(Gdx.files.internal("Boats/Fisherboat.png")));
		
		// Other things
		Textures.add(new Texture(Gdx.files.internal("highbeam.png")));
		Textures.add(new Texture(Gdx.files.internal("Flyers/rotorblad.png")));
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
    	GameCars.add(new Car(Textures.get(0), 64, 128, 5, 2, 2)); //Caddie Taxi
    	GameCars.add(new Car(Textures.get(1), 64, 128, 5, 4, 2)); //Caddie Copcar
    	GameCars.add(new Car(Textures.get(2), 64, 128, 5, 2, 2)); //Caddie
    	GameCars.add(new Car(Textures.get(3), 128, 128, 5, 6, 3)); //Caddie Monstertruck
    	GameCars.add(new Car(Textures.get(4), 64, 128, 2, 4, 2)); //Snowmobile
    	GameCars.add(new Car(Textures.get(5), 64, 256, 10, 2, 1)); //City Bus
    	GameCars.add(new Car(Textures.get(6), 32, 64, 2, 4, 2)); //Radiant 9000 Motorcycle
    	GameCars.add(new Car(Textures.get(7), 64, 128, 5, 4, 2)); //Delorean Back to the Future
    	GameCars.add(new Car(Textures.get(8), 64, 128, 5, 5, 2)); //Knights Industries 2000 (KITT)
    	GameCars.add(new Car(Textures.get(9), 64, 256, 10, 2, 1)); //Truck
    	GameCars.add(new Car(Textures.get(10), 64, 256, 10, 2, 1)); //IKEA Truck
    	GameCars.add(new Car(Textures.get(13), 64, 128, 4, 10, 2)); //Volvo V70 Copcar
    	GameCars.add(new Car(Textures.get(14), 64, 128, 5, 4, 2)); //Volvo V70 Ambulance
    	GameCars.add(new Car(Textures.get(15), 64, 160, 5, 4, 2)); //Panoz Roadster
    	GameCars.add(new Car(Textures.get(16), 64, 256, 8, 10, 2)); //Tim Burtons Batmobile
    	GameCars.add(new Car(Textures.get(17), 64, 256, 8, 6, 2)); //ECTO-1 Ghostbusters
    	GameCars.add(new Car(Textures.get(18), 64, 64, 2, 4, 2)); //Radiant 9000 Motorcycle with Sidecarrige
    }
    
    private void loadFlyers()
    {
    	//TODO Make Planes
    	//Planes.add(new Plane());
    	Helicopters.add(new Helicopter(Textures.get(19), 256, 128, 0,0,0)); //Apache Attack Helicopter
    }
    
    private void loadBoats()
    {
    	Boats.add(new Boat(Textures.get(20), 256, 128, 0,0,0)); //Fisherboat
    }
    
    /* TODO
    private void loadSoundEffects()
    {
    	Soundeffects.add(new Soundeffect());
    }
    
    private void loadMusic()
    {
    	Musics.add(new Music());
    }
    */
}
