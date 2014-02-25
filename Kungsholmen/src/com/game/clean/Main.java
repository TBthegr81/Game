package com.game.clean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Main {
	private boolean devmode;
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
		//Train
		Textures.add(new Texture(Gdx.files.internal("Cars/Train_engine.png")));
    	Textures.add(new Texture(Gdx.files.internal("Cars/Train_cart.png")));

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
    
    private void loadVehicles()
    {
    	Textures.add(new Texture(Gdx.files.internal("Cars/Caddie_taxi.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Caddie Taxi
    	Textures.add(new Texture(Gdx.files.internal("Cars/Caddie_copcar.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 4, 2)); //Caddie Copcar
    	Textures.add(new Texture(Gdx.files.internal("Cars/Caddie_copcar_Undercover.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 4, 2)); //Caddie Copcar Undercover
    	Textures.add(new Texture(Gdx.files.internal("Cars/Caddie.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Caddie
    	Textures.add(new Texture(Gdx.files.internal("Cars/Caddie_Monster.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 128, 128, 5, 6, 3)); //Caddie Monstertruck
    	Textures.add(new Texture(Gdx.files.internal("Cars/Snowmobile.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 2, 4, 2)); //Snowmobile
    	Textures.add(new Texture(Gdx.files.internal("Cars/CityBus.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 256, 10, 2, 1)); //City Bus
    	Textures.add(new Texture(Gdx.files.internal("Cars/Radiant_9000.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 32, 64, 2, 4, 2)); //Radiant 9000 Motorcycle
    	Textures.add(new Texture(Gdx.files.internal("Cars/Delorean_BTTF.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 4, 2)); //Delorean Back to the Future
    	Textures.add(new Texture(Gdx.files.internal("Cars/Kitt.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 5, 2)); //Knights Industries 2000 (KITT)
    	Textures.add(new Texture(Gdx.files.internal("Cars/Truck.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 256, 10, 2, 1)); //Truck
    	Textures.add(new Texture(Gdx.files.internal("Cars/Truck_IKEA.png"))); //10
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 256, 10, 2, 1)); //IKEA Truck
    	Textures.add(new Texture(Gdx.files.internal("Cars/Volvo_copcar.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 4, 10, 2)); //Volvo V70 Copcar
    	Textures.add(new Texture(Gdx.files.internal("Cars/Volvo_Ambulance.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 4, 2)); //Volvo V70 Ambulance
    	Textures.add(new Texture(Gdx.files.internal("Cars/Panoz_Roadster.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 160, 5, 4, 2)); //Panoz Roadster
    	Textures.add(new Texture(Gdx.files.internal("Cars/Batmobile.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 256, 8, 10, 2)); //Tim Burtons Batmobile
    	Textures.add(new Texture(Gdx.files.internal("Cars/Ecto_1.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 256, 8, 6, 2)); //ECTO-1 Ghostbusters
    	Textures.add(new Texture(Gdx.files.internal("Cars/Audi_R8_Spyder.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Audi R8 Spyder
    	Textures.add(new Texture(Gdx.files.internal("Cars/Audi_Quattro_A2.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Audi Quattro A2
    	Textures.add(new Texture(Gdx.files.internal("Cars/BAC_Mono.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //BAC Mono
    	Textures.add(new Texture(Gdx.files.internal("Cars/F1_Racer.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 256, 5, 2, 2)); //F1 Racer
    	Textures.add(new Texture(Gdx.files.internal("Cars/GeneralLee.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //General Lee
    	Textures.add(new Texture(Gdx.files.internal("Cars/Go_Cart.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Go Cart
    	Textures.add(new Texture(Gdx.files.internal("Cars/Hippie_Bus.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Hippie Bus
    	Textures.add(new Texture(Gdx.files.internal("Cars/Jeep_Wrangler_Sahara.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Jeep Wrangler Sahara (Jurassic Park)
    	Textures.add(new Texture(Gdx.files.internal("Cars/Koenigsegg_Agera.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Koenigsegg Agera
    	Textures.add(new Texture(Gdx.files.internal("Cars/Lamborghini_Gallardo.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Lamborghini Gallardo
    	Textures.add(new Texture(Gdx.files.internal("Cars/Lancia_037.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Lancia 037
    	Textures.add(new Texture(Gdx.files.internal("Cars/Lancia_Delta_S4.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Lancia Delta S4
    	Textures.add(new Texture(Gdx.files.internal("Cars/Mad_Max_Interceptor_II.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Mad Max Interceptor II
    	Textures.add(new Texture(Gdx.files.internal("Cars/Mini_Cooper_Classic.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Mini Cooper Classic
    	Textures.add(new Texture(Gdx.files.internal("Cars/Pickuptruck.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Pickuptruck
    	Textures.add(new Texture(Gdx.files.internal("Cars/Porche_911.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Porche_911
    	Textures.add(new Texture(Gdx.files.internal("Cars/Rally_Car.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Rally_Car
    	Textures.add(new Texture(Gdx.files.internal("Cars/Subaru_Impreza.png")));
    	GameCars.add(new Car(Textures.get(Textures.size-1), 64, 128, 5, 2, 2)); //Subaru_Impreza
    	
    	Textures.add(new Texture(Gdx.files.internal("Flyers/helicopter_apache.png")));
    	Helicopters.add(new Helicopter(Textures.get(Textures.size-1), 256, 128, 0,0,0)); //Apache Attack Helicopter
    	
    	Textures.add(new Texture(Gdx.files.internal("Boats/Fisherboat.png")));
    	Boats.add(new Boat(Textures.get(Textures.size-1), 256, 128, 0,0,0)); //Fisherboat
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
    
    public void checkInput()
    {
    	if(Gdx.input.isKeyPressed(Keys.LEFT))
    	{
    		if(Gdx.input.isKeyPressed(Keys.SPACE))
    		{
    			//TODO Lock Wheels, no acceleration possible. Grip looses
    		}
    		else if(Gdx.input.isKeyPressed(Keys.UP))
    		{
    			//TODO Turn Left and accelerate
    		}
    		else if(Gdx.input.isKeyPressed(Keys.DOWN))
    		{
    			//TODO Turn Left and de-accelerate
    		}
    		else
    		{
    			//TODO Turn Left, no acceleration
    		}
    	}
    	else if(Gdx.input.isKeyPressed(Keys.RIGHT))
    	{
    		if(Gdx.input.isKeyPressed(Keys.SPACE))
    		{
    			//TODO Lock Wheels, no acceleration possible. Grip looses
    		}
    		else if(Gdx.input.isKeyPressed(Keys.UP))
    		{
    			//TODO Turn Right and accelerate
    		}
    		else if(Gdx.input.isKeyPressed(Keys.DOWN))
    		{
    			//TODO Turn Right and de-accelerate
    		}
    		else
    		{
    			//TODO Turn Right, no acceleration
    		}
    	}
    	else if (Gdx.input.isKeyPressed(Keys.UP))
        {
    		//TODO Accelerate in the direction you are facing
        }
    	else if(Gdx.input.isKeyPressed(Keys.DOWN))
    	{
    		//TODO De-accelerate
    	}
    	else if(Gdx.input.isKeyPressed(Keys.SPACE))
    	{
    		//TODO Lock Wheels, no acceleration possible. Grip looses
    	}
    	if(devmode)
    	{
	    	for(int i = 0; i < 12; i++)
	    	{
	    		if(Gdx.input.isKeyPressed(Keys.valueOf("F" + i)))
	    		{
	    			//TODO Change Car. Only avalible in dev-mode
	    		}
	    	}
    	}
    }
}
