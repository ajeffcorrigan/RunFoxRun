package com.ajeffcorrigan.runfoxrun.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		 Fixture fixA = contact.getFixtureA();
	     Fixture fixB = contact.getFixtureB();
	     
	     if(fixA.getUserData() == "foxface" || fixB.getUserData() == "foxface"){
	            Fixture head = fixA.getUserData() == "foxface" ? fixA : fixB;
	            Fixture object = head == fixA ? fixB : fixA;
	            Gdx.app.log("beginContact", "between fox: " + head.toString() + " and " + object.toString());
	     }
	     
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
