package com.ajeffcorrigan.runfoxrun.sprites;

import com.ajeffcorrigan.runfoxrun.RunFoxRun;
import com.ajeffcorrigan.runfoxrun.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class ScreenTile extends Sprite{

	public boolean isRigid;
	public Rectangle tileBounds;
	public boolean tileEmpty;
	
	public Vector2 tilePosition;
	public int tileSize;
	

	
	public ScreenTile() { }
	
	public ScreenTile(Vector2 startXY, int tileSize) {
		this.tilePosition = new Vector2(startXY);
		this.tileSize = tileSize;
		this.tileBounds = new Rectangle(this.tilePosition.x, this.tilePosition.y, this.tileSize, this.tileSize);
		this.tileEmpty = true;
	}
	
	public ScreenTile(Vector2 startXY, int tileSize, Sprite sprite, PlayScreen screen) {
		super(sprite);
		
	    //create body and fixture variables
	    BodyDef bdef = new BodyDef();
	    Body body;
        
	    World world = screen.getWorld();
		
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(startXY.x, startXY.y);
        body = world.createBody(bdef);
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);
        
        FixtureDef fdef = new FixtureDef();   
        fdef.shape = shape;
        body.createFixture(fdef);
        
		this.tilePosition = new Vector2(startXY);
		super.setPosition(tilePosition.x,tilePosition.y);
		this.tileSize = tileSize;
		this.tileBounds = new Rectangle(this.tilePosition.x, this.tilePosition.y, this.tileSize, this.tileSize);
		this.tileEmpty = false;
		this.isRigid = true;
	}
	
	public void setRigidSprite(Sprite sprite) {
		set(sprite);
		setPosition(this.tilePosition.x, this.tilePosition.y);
		this.tileEmpty = false;
		this.isRigid = true;
	}
}
