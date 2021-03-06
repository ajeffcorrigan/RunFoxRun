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

public class ScreenTile extends Sprite {

	public boolean isRigid;
	public Rectangle tileBounds;
	public boolean tileEmpty;
	
	public Vector2 tilePosition;
	public float tileSize;
	public boolean endGameIfTouch = false;
	
	public Body body;
	
	public ScreenTile() { }
	
	public ScreenTile(Vector2 startXY, float tSize) {
		this.tilePosition = new Vector2(startXY);
		this.tileSize = tSize;
		this.tileBounds = new Rectangle(this.tilePosition.x, this.tilePosition.y, this.tileSize, this.tileSize);
		this.tileEmpty = true;
	}
	
	public ScreenTile(Vector2 startXY, float tSize, Sprite sprite, PlayScreen screen, boolean isRigid) {
		super(sprite);
		this.tilePosition = startXY;
		setBounds(this.tilePosition.x, this.tilePosition.y, getWidth() / RunFoxRun.PTM + .01f, getHeight() / RunFoxRun.PTM);
		this.tileSize = tSize;
		this.tileEmpty = false;
		this.isRigid = isRigid;
		if(this.isRigid) { defineBlock(screen); }
	}
	public ScreenTile(Vector2 startXY, float tSize, Sprite sprite, PlayScreen screen, boolean isRigid, boolean isTriAngle) {
		super(sprite);
		this.tilePosition = startXY;
		setBounds(this.tilePosition.x, this.tilePosition.y, getWidth() / RunFoxRun.PTM + .01f, getHeight() / RunFoxRun.PTM);
		this.tileSize = tSize;
		this.tileEmpty = false;
		this.isRigid = isRigid;
		if(isTriAngle && this.isRigid) { defineRectangle(screen); }
	}
	
	private void defineBlock(PlayScreen screen) {
	    //create body and fixture variables
	    BodyDef bdef = new BodyDef();

	    World world = screen.getWorld();
		
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(this.tilePosition.x + (getWidth()/2), this.tilePosition.y + (getHeight()/2));
        body = world.createBody(bdef);
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);
        
        FixtureDef fdef = new FixtureDef();   
        fdef.shape = shape;
        body.createFixture(fdef);
	}
	
	private void defineRectangle(PlayScreen screen) {

		//create body and fixture variables
	    BodyDef bdef = new BodyDef();
	    World world = screen.getWorld();
		
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(this.tilePosition.x + (getWidth()/2), this.tilePosition.y + (getHeight()/2));
        body = world.createBody(bdef);
        
        PolygonShape shape = new PolygonShape();
        Vector2[] triVerticies = new Vector2[3];
        triVerticies[0] = new Vector2(getWidth()/-2,getHeight()/-2);
        triVerticies[1] = new Vector2(getWidth()/2,0);
        triVerticies[2] = new Vector2(getWidth()/2,getHeight()/2);
        
        shape.set(triVerticies);
              
        FixtureDef fdef = new FixtureDef();   
        fdef.shape = shape;
        body.createFixture(fdef);
		
	}
	
	public void setRigidSprite(Sprite sprite, PlayScreen screen) {
		set(sprite);
		setBounds(this.tilePosition.x, this.tilePosition.y, getWidth() / RunFoxRun.PTM + .01f, getHeight() / RunFoxRun.PTM);
		this.tileEmpty = false;
		this.isRigid = true;
		defineBlock(screen);
	}
	
	public void setSensorFixture(PlayScreen screen) {
	    //create body and fixture variables
	    BodyDef bdef = new BodyDef();

	    World world = screen.getWorld();
		
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(this.tilePosition.x + (getWidth()/2), this.tilePosition.y + (getHeight()/2));
        body = world.createBody(bdef);
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);
        
        FixtureDef fdef = new FixtureDef();   
        fdef.shape = shape;
        fdef.isSensor = true;
        body.createFixture(fdef);
	}
	
	public void dispose(PlayScreen screen) {
		screen.getWorld().destroyBody(this.body);
	}
	
}
