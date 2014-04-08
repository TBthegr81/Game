package com.game.TB;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Checkpoint {
	Texture checkpointPillar;
    Texture checkpointFlag;
    Sprite checkpointPillarSprite;
    Sprite checkpointFlagSprite;
    Rectangle checkpointMiddle;
    
    
    
    public Checkpoint(float x, float y, int rotate)
    {
    	checkpointPillar = new Texture(Gdx.files.internal("Checkpoint_pillar.png"));
        checkpointFlag = new Texture(Gdx.files.internal("Checkpoint_flag.png"));
        checkpointPillarSprite = new Sprite(checkpointPillar);
        checkpointFlagSprite = new Sprite(checkpointFlag);
        checkpointPillarSprite.setX(x);
        checkpointPillarSprite.setY(y);
        
    	checkpointMiddle = new Rectangle();
        checkpointMiddle.setHeight(checkpointPillarSprite.getHeight()-checkpointFlag.getHeight());
        checkpointMiddle.setWidth(checkpointFlag.getWidth());
        checkpointMiddle.setPosition(checkpointPillarSprite.getX()+checkpointPillarSprite.getWidth(), checkpointPillarSprite.getX());
    }
    
    public Sprite getSprite()
    {
    	return checkpointPillarSprite;
    }
    
}
