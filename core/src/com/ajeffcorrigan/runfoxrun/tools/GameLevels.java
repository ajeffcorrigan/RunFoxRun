package com.ajeffcorrigan.runfoxrun.tools;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;

public class GameLevels {
	
	private Array<char[][]> levelDetails;
	private RandomXS128 randomChunk;
	private char[][] sflat = {
			{'E','E','E'},
			{'E','E','E'},
			{'E','E','E'},
			{'E','E','E'},
			{'G','G','G'} };

	private char[][] midhole = {
			{'E','E','E','E','E'},
			{'E','E','E','E','E'},
			{'E','E','E','E','E'},
			{'E','E','E','E','E'},
			{'G','W','W','G','G'} };
	
	private char[][] flatmount = {
			{'E','E','E','E','E'},
			{'E','E','E','E','E'},
			{'E','E','G','E','E'},
			{'E','G','D','G','E'},
			{'G','G','G','G','G'} };
	
	private char[][] holeflat = {
			{'E','E','E','E','E'},
			{'E','E','E','E','E'},
			{'E','L','G','R','E'},
			{'E','E','E','E','E'},
			{'G','G','G','G','G'} };
	
	private char[][] smallholeflat = {
			{'E','E','E','E','E'},
			{'E','E','E','E','E'},
			{'E','E','L','R','E'},
			{'E','E','E','E','E'},
			{'G','G','G','G','G'} };
	
	private char[][] l1 = {
			{'E','E','E','E','E'},
			{'E','E','E','E','E'},
			{'E','E','E','E','E'},
			{'E','E','E','E','E'},
			{'G','W','W','W','G'} };
	private char[][] l2 = {
			{'E','E','E','E','E','E'},
			{'E','E','E','E','E','E'},
			{'E','E','E','E','E','E'},
			{'E','E','E','E','E','E'},
			{'G','W','W','W','W','G'} };
	private char[][] l3 = {
			{'E','E','E','E','E','E','E'},
			{'E','E','E','E','E','E','E'},
			{'E','E','E','L','R','E','E'},
			{'E','E','E','E','E','E','E'},
			{'G','W','W','W','W','W','G'} };
	
	
	public GameLevels() {
		 levelDetails = new Array<char[][]>();
		 levelDetails.add(sflat);
		 levelDetails.add(midhole);
		 levelDetails.add(flatmount);
		 levelDetails.add(holeflat);
		 levelDetails.add(smallholeflat);
		 levelDetails.add(l1);
		 levelDetails.add(l2);
		 levelDetails.add(l3);
		 randomChunk = new RandomXS128();
	}
	
	public char[][] getLevelDetails() {
		return levelDetails.get(randomChunk.nextInt(levelDetails.size));
	}

}