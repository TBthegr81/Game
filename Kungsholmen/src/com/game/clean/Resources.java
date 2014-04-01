package com.game.clean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Resources {
	private boolean devmode;
	private static Array<Car> Cars = new Array<Car>();
	private static Array<Car> NPV = new Array<Car>(); //Non-Player Vehicles
	private static Array<NPC> NPCs = new Array<NPC>();
	private static Array<Player> Players = new Array<Player>();
	private static Array<Texture> Backgrounds = new Array<Texture>();
	@SuppressWarnings("unused")
	private static Array<PathNode> GreenLine;
	@SuppressWarnings("unused")
	private static Array<PathNode> Blueline;
	private static Array<Texture> Textures;
	@SuppressWarnings("unused")
	private static Array<Train> Trains;
	@SuppressWarnings("unused")
	private static Array<Boat> Boats;
	@SuppressWarnings("unused")
	private static Array<Helicopter> Helicopters;
	@SuppressWarnings("unused")
	private static Array<Plane> Planes;
	//TODO private Array<Music> Musics;
	//TODO private Array<Soundeffect> Soundeffects;
	
	private static boolean carsLoaded = false;
	
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
	
	/*private int turn(boolean way, int currentDirection, int turnability)
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
    }*/
	public static void loadBackgrounds()
	{
		Backgrounds.add(new Texture(Gdx.files.internal("splash.gif")));
		Backgrounds.add(new Texture(Gdx.files.internal("garage.gif")));
	}
	public static void loadVehicles()
	{
		if(!carsLoaded)
		{
			Cars.add(new Car("Audi_Quattro_A2", 64, 128, 5, 2, 2));
			Cars.get(Cars.size-1).setName("Audi Quattro A2");
			Cars.get(Cars.size-1).setDescription("Its a fast rally car");
	        Cars.add(new Car("Audi_R8_Spyder", 64, 128, 5, 2, 2));
	        Cars.get(Cars.size-1).setName("Audi R8 Spyder");
			Cars.get(Cars.size-1).setDescription("A fast sports-car");
	        Cars.add(new Car("BAC_Mono", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Batmobile", 64, 256, 10, 2, 1));
	        Cars.add(new Car("Caddie", 64, 128, 5, 2, 2));	
	        Cars.add(new Car("Caddie_Copcar", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Caddie_Copcar_Undercover", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Caddie_Monster", 128, 128, 5, 6, 3));
	        Cars.add(new Car("Caddie_Taxi", 64, 128, 5, 2, 2));
	        Cars.add(new Car("CityBus", 64, 256, 10, 2, 1));
	        Cars.add(new Car("Delorean_BTTF", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Ecto_1", 64, 256, 10, 2, 1));
	        Cars.add(new Car("F1_Racer", 64, 128, 5, 4, 2));
	        Cars.add(new Car("GeneralLee", 64, 128, 5, 5, 2));
	        Cars.add(new Car("Go_Cart", 64, 128, 5, 5, 2));
	        Cars.add(new Car("Hippie_Bus", 64, 128, 5, 5, 2));
	        Cars.add(new Car("Jeep_Wrangler_Sahara", 64, 128, 5, 5, 2));
	        Cars.add(new Car("Kitt", 64, 128, 5, 5, 2));
	        Cars.add(new Car("Koenigsegg_Agera", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Lamborghini_Gallardo", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Lancia_037", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Lancia_Delta_S4", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Mad_Max_Interceptor_II", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Mini_Cooper_Classic", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Panoz_Roadster", 64, 128, 4, 10, 2));
	        Cars.add(new Car("Pickuptruck", 64, 128, 4, 10, 2));
	        Cars.add(new Car("Porche_911", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Radiant_9000", 32, 64, 2, 4, 2));
	        Cars.add(new Car("Rally_Car", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Snowmobile", 64, 128, 2, 4, 2));
	        Cars.add(new Car("Subaru_Impreza", 64, 128, 5, 4, 2));
	        Cars.add(new Car("Truck", 64, 256, 10, 2, 1));
	        Cars.add(new Car("Truck_IKEA", 64, 256, 10, 2, 1));
	        Cars.add(new Car("Volvo_Ambulance", 64, 160, 5, 4, 2));
	        Cars.add(new Car("Volvo_Copcar", 64, 128, 5, 4, 2));
	        Cars.get(Cars.size-1).setName("Volvo V70 Police");
			Cars.get(Cars.size-1).setDescription("Policecar based on the Volvo V70");
			carsLoaded = true;
		}
	}
	
	public static void loadVehicles2()
	{
		if(!carsLoaded)
		{
			ArrayList<String> configfile = null;
			try {
				configfile = readFile("./bin/cars.cfg");
			} catch (IOException e) {
				System.err.println("Could not read file \"cars.cfg\"");
			}
			String CarFileName = "";
			String CarName = "";
			String CarDesc = "";
			Integer[] CarStats = new Integer[5];
			for(int i = 0; i < configfile.size(); i++)
			{
				String row = configfile.get(i);
				System.err.println(row);
				String[] rowParts = row.split(" ");
				if(row.equals("") || row.equalsIgnoreCase(" ") || row == null) 
				{
					if(!CarFileName.equals("") && !CarName.equals("") && !CarDesc.equals(""))
					{
					//End of a car
					//System.out.println("CFN:" + CarFileName + "CN:" + CarName + "CD:" + CarDesc);
					Cars.add(new Car(CarFileName, CarStats[0], CarStats[1], CarStats[2], CarStats[3], CarStats[4]));
					Cars.get(Cars.size-1).setName(CarName);
					Cars.get(Cars.size-1).setDescription(CarDesc);
					CarFileName = "";
					CarName = "";
					CarDesc = "";
					CarStats = new Integer[5];
					
					System.out.println(Cars.get(Cars.size-1).getName() + " " + Cars.get(Cars.size-1).getDescription());
					}
				}
				else if(rowParts[0].equals("filename:"))
				{
					CarFileName = rowParts[1];
				}
				else if(rowParts[0].equals("name:"))
				{
					CarName = row.replaceAll("name: ", "");
				}
				else if(rowParts[0].equals("desc:"))
				{
					CarDesc = row.replaceAll("desc:", "");
				}
				else if(rowParts[0].equals("stats:"))
				{
					CarStats[0] = Integer.parseInt(rowParts[1].replaceAll(",", ""));
					CarStats[1] = Integer.parseInt(rowParts[2].replaceAll(",", ""));
					CarStats[2] = Integer.parseInt(rowParts[3].replaceAll(",", ""));
					CarStats[3] = Integer.parseInt(rowParts[4].replaceAll(",", ""));
					CarStats[4] = Integer.parseInt(rowParts[5].replaceAll(",", ""));
				}
			}
			carsLoaded = true;
		}
	}
	
	public static Array<Car> getCars()
	{
		return Cars;
	}
	
	public static Array<NPC> getNPCs()
	{
		return NPCs;
	}
	
	public static Array<Player> getPlayers()
	{
		return Players;
	}
	
	public static void addPlayer()
	{
		Players.add(new Player());
		Players.get(Players.size-1).setCurrentCar(Cars.get(0));
	}
	
	public static Array<Texture> getBackgrounds()
	{
		return Backgrounds;
	}
	
	public static void loadNPV()
	{
		NPV.add(new Car("Train_Engine", 64, 256, 10, 2, 1));
        NPV.add(new Car("Train_Cart", 64, 256, 10, 2, 1));
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
    
    public static void randNPCs(int ammount)
    {
    	for(int i = 0; i < ammount; i++)
    	{
    		NPCs.add(new NPC(Cars.get(MathUtils.random(0, Cars.size-1))));
    	}
    }
    
    // Method to read file and return array with whats inside
 	public static ArrayList<String> readFile(String path) throws IOException
 	{
 		ArrayList<String> sb;
 		BufferedReader br = new BufferedReader(new FileReader(path));
 	    try {
 	        sb = new ArrayList<String>();
 	        String line = br.readLine();

 	        while (line != null) {
 	            sb.add(line);
 	            line = br.readLine();
 	        }
 	    } finally {
 	        br.close();
 	    }
 	    return sb;
 	}
}
