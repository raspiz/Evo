/*****************************************************************************
 * Name: Aaron Whitmer, Joseph Stevens, Andrew Pryor
 * Class Name: StationaryCritter
 * Purpose: This is an abstract class that works as the template for all stationary critters in the game evo.
 * It extends Critter. Additional game parameters could be added here in the future.
*****************************************************************************/

package evo.critters;

import evo.Critter;

public abstract class StationaryCritter extends Critter {
	
	public StationaryCritter()
	{
		super();
	}
	
	public StationaryCritter(int pX, int pY, int pPhase, int pDigest, int pAge, boolean pFull, String pSpecies, String pFileName, String pType)
	{
		super(pX, pY, pPhase, pDigest, pAge, pFull, pSpecies, pFileName, pType);		
	}
	
}
