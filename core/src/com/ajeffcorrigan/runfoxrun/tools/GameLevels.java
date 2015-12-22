package com.ajeffcorrigan.runfoxrun.tools;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;

public class GameLevels {
	
	private RandomXS128 randomChunk;
	private Array<String[][]> levelDetails;
	
	private String[][] flat1 = { {"E00"}, {"E00"}, {"E00"}, {"E00"}, {"G00"} };
	private String[][] flat2 = { {"E00","E00"}, {"E00","E00"}, {"E00","E00"}, {"E00","E00"}, {"G00","G00"} };

	private String[][] smhole = {
			{"E00","E00","E00","E00"},
			{"E00","E00","E00","E00"},
			{"E00","E00","E00","E00"},
			{"E00","E00","E00","E00"},
			{"G00","W00","W00","G00"} };
	
	private String[][] midhole = {
			{"E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00"},
			{"G00","W00","W00","W00","G00"} };
	
	private String[][] lghole = {
			{"E00","E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00","E00"},
			{"G00","W00","W00","W00","W00","G00"} };
	
	private String[][] mplatformhole = {
			{"E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00"},
			{"E00","E00","L00","R00","E00"},
			{"E00","E00","E00","E00","E00"},
			{"W00","W00","W00","W00","W00"} };
	
	private String[][] mplatformhole2 = {
			{"E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00"},
			{"E00","L00","R00","E00","E00"},
			{"W00","W00","W00","W00","W00"} };
	
	private String[][] platform1 = {
			{"E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00"},
			{"E00","L00","G00","G00","R00"},
			{"E00","E00","E00","E00","E00"},
			{"G00","G00","G00","G00","G00"} };
	
	private String[][] platform2 = {
			{"E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00"},
			{"E00","L00","G00","R00","E00"},
			{"E00","E00","E00","E00","E00"},
			{"G00","G00","G00","G00","G00"} };
	
	private String[][] platform3 = {
			{"E00","E00","E00","E00"},
			{"E00","E00","E00","E00"},
			{"E00","L00","R00","E00"},
			{"E00","E00","E00","E00"},
			{"G00","G00","G00","G00"} };
	
	private String[][] platform4 = {
			{"E00","E00","E00","E00"},
			{"E00","L00","G00","R00"},
			{"E00","E00","E00","E00"},
			{"E00","E00","E00","E00"},
			{"G00","G00","G00","G00"} };

	private String[][] hill1 = {
			{"E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00"},
			{"E00","B00","G00","C00","E00"},
			{"G00","A00","D00","F00","G00"} };
	
	private String[][] hill2 = {
			{"E00","E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00","E00"},
			{"E00","E00","E00","E00","E00","E00"},
			{"E00","B00","G00","G00","C00","E00"},
			{"G00","A00","D00","D00","F00","G00"} };
	private String[][] hill3 = {
			{"E00","E00","E00","E00","E00","E00","E00"},
			{"E00","E00","E00","B00","G00","G00","E00"},
			{"E00","E00","B00","A00","D00","D00","E00"},
			{"E00","B00","A00","D00","D00","D00","E00"},
			{"G00","A00","D00","D00","D00","D00","G00"} };
	
	public GameLevels() {
		 levelDetails = new Array<String[][]>();
		 levelDetails.add(flat1);
		 levelDetails.add(flat2);
		 levelDetails.add(smhole);
		 levelDetails.add(midhole);
		 levelDetails.add(lghole);
		 levelDetails.add(mplatformhole);
		 levelDetails.add(mplatformhole2);
		 levelDetails.add(platform1);
		 levelDetails.add(platform2);
		 levelDetails.add(platform3);
		 levelDetails.add(platform4);
		 levelDetails.add(hill1);
		 levelDetails.add(hill2);
		 levelDetails.add(hill3);
		 
		 randomChunk = new RandomXS128();
	}
	
	public String[][] getLevelDetails() {
		return levelDetails.get(randomChunk.nextInt(levelDetails.size));
	}

}
