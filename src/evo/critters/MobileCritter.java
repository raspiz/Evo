/*****************************************************************************
 * Name: Aaron Whitmer, Joseph Stevens, Andrew Pryor
 * Class Name: MobileCritter
 * Purpose: This is an abstract class that works as the template for all mobile critters in the game evo.
 * It extends Critter. The number of moves per turn for each critter are defined here
*****************************************************************************/

package evo.critters;

import evo.Critter;

public abstract class MobileCritter extends Critter {
	protected int moveX, moveY, maxAge;	
 
	public MobileCritter()
	{ //default constructor
		super();
		moveX = 0;
		moveY = 0;
		maxAge = 0;
	}
	
	public MobileCritter(int pX, int pY, int pPhase, int pDigest, int pAge, boolean pFull, String pSpecies, String pFileName, String pType)
	{ //constructor
		super(pX, pY, pPhase, pDigest, pAge, pFull, pSpecies, pFileName, pType);
		MoveX(pSpecies);
		MoveY(pSpecies);		
	}
	 public int MoveX(String pSpecies)
	 {   //check the species of the critter
		 //generate a random up to the max move value of the species
		 if (pSpecies == "chingling" )
		 {
			 moveX = 1;
		 }
		 else if (pSpecies == "marmot")
		 {
			 moveX = 5;
		 }
		 else if (pSpecies == "zylot")
		 {
			 moveX = 10;
		 }
		 return moveX;
	 }
	 public int MoveY(String pSpecies)
	 {
		 //same as above
		 if (pSpecies == "chingling" )
		 {
			 moveY = 1;
		 }
		 else if (pSpecies == "marmot")
		 {
			 moveY = 5;
		 }
		 else if (pSpecies == "zylot")
		 {
			 moveY = 10;
		 }
		 
		 return moveY;
	 }

	}
	
