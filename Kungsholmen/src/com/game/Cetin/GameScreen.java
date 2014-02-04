package com.game.Cetin;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
	final Drop game;

	Texture playerCar;
	Texture enemyCar;
	Sound dropSound;
	Music rainMusic;
	OrthographicCamera camera;
	Rectangle player;
	Array<Rectangle> enemies;
	long lastEnemyTime;
	int hits;

	public GameScreen(final Drop gam) {
		this.game = gam;

		// load the images for the cars, 64x64 pixels each
		playerCar = new Texture(Gdx.files.internal("Cars/Caddie_taxi.png"));
		enemyCar = new Texture(Gdx.files.internal("Cars/Kitt.png"));

		// load the drop sound effect and the rain background "music"
		// dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		// rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		// rainMusic.setLooping(true);

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// create a Rectangle to logically represent the bucket
		player = new Rectangle();
		player.x = 800 / 2 - 128 / 2; // center the bucket horizontally
		player.y = 20; // bottom left corner of the bucket is 20 pixels above
						// the bottom screen edge
		player.width = 128;
		player.height = 64;

		// create the enemies array and spawn the first enemy
		enemies = new Array<Rectangle>();
		spawnEnemy();

	}

	private void spawnEnemy() {
		Rectangle enemy = new Rectangle();
		enemy.x = MathUtils.random(0, 800 - 128);
		enemy.y = 480;
		enemy.width = 128;
		enemy.height = 64;
		enemies.add(enemy);
		lastEnemyTime = TimeUtils.nanoTime();
	}

	@Override
	public void render(float delta) {
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
		game.font.draw(game.batch, "Cars Destroyed: " + hits, 0, 480);
		game.batch.draw(enemyCar, player.x, player.y);
		for (Rectangle raindrop : enemies) {
			game.batch.draw(playerCar, raindrop.x, raindrop.y);
		}
		
		game.batch.end();

		// process user input
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			player.x = touchPos.x - 64 / 2;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			player.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			player.x += 200 * Gdx.graphics.getDeltaTime();

		// make sure the bucket stays within the screen bounds
		if (player.x < 0)
			player.x = 0;
		if (player.x > 800 - 64)
			player.x = 800 - 64;

		// check if we need to create a new raindrop
		if (TimeUtils.nanoTime() - lastEnemyTime > 600000000)
			spawnEnemy();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we increase the
		// value our drops counter and add a sound effect.
		Iterator<Rectangle> iter = enemies.iterator();
		while (iter.hasNext()) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 500 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0)
				iter.remove();
			if (raindrop.overlaps(player)) {
				hits++;
				// dropSound.play();
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
		// rainMusic.play();
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
		playerCar.dispose();
		enemyCar.dispose();
		// dropSound.dispose();
		// rainMusic.dispose();
	}

}