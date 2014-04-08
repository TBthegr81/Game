package com.game.clean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen{
	final KungsholmenGame game;
	private SpriteBatch spriteBatch;
	@SuppressWarnings("unused")
	private Texture splsh;
	@SuppressWarnings("unused")
    private BitmapFont font;
	@SuppressWarnings("unused")
    private BitmapFont font2;
	@SuppressWarnings("unused")
    private CharSequence str;
    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    Point touchPos;
    Vector3 position;
    
    Array<Car> Cars = new Array<Car>();
    Array<NPC> NPCs;
    
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
		position.x += (player.getLocation().x() - position.x) * lerp;
    	position.y += (player.getLocation().y() - position.y) * lerp;
    	camera.update();
    	game.batch.setProjectionMatrix(camera.combined);
    	
		spriteBatch.begin();
		
		//Draw Player
        Sprite car = player.getCurrentCar().getSprite();
        spriteBatch.draw(car, player.getLocation().x(), player.getLocation().y(), car.getOriginX(), car.getOriginY(), car.getWidth(), car.getHeight(), 1, 1, 0);
        
        //Draw NPC's
        for(int i = 0; i < NPCs.size; i++)
        {
        	car = NPCs.get(i).getCar().getSprite();
        	spriteBatch.draw(car, 300 , (i*64/1.3F));
        	System.out.println("Car nr:"+i);
        }

		spriteBatch.end();
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
        
        font = new BitmapFont(Gdx.files.internal("Fonts/test.fnt"),
        Gdx.files.internal("Fonts/test.png"), false);
        font2 = new BitmapFont(Gdx.files.internal("Fonts/YellowItalicBoldVerdana.fnt"),
        Gdx.files.internal("Fonts/YellowItalicBoldVerdana.png"), false);
        
        Cars = Resources.getCars();
        
        //Set player to middle of map
        player.setLocation(new Point(0,0));
        
        //Setup camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        
        //Get some NPC's
        Resources.randNPCs(15);
        NPCs = Resources.getNPCs();
        
        
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
