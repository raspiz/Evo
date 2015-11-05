/*****************************************************************************
 * Name: Aaron Whitmer, Joseph Stevens, Andrew Pryor
 * Class Name: Catcher
 * Purpose: This critter is a stationary plantlike critter that is carnivorous.
*****************************************************************************/

package evo.critters;

public class Catcher extends StationaryCritter {

	public Catcher()
	{
		super();
	}
	public Catcher(int pX, int pY, int pPhase, int pDigest, int pAge, boolean pFull, String pSpecies, String pFileName, String pType)
	{	
		super(pX, pY, pPhase, pDigest, pAge, pFull, pSpecies, pFileName, pType);
	}
	
}
