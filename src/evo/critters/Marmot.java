/*****************************************************************************
 * Name: Aaron Whitmer, Joseph Stevens, Andrew Pryor
 * Class Name: Marmot
 * Purpose: This critter is a mobile critter that is omnivorous.
*****************************************************************************/

package evo.critters;

public class Marmot extends MobileCritter{

	public Marmot()
	{//default
		super();
	}
	public Marmot(int pX, int pY, int pPhase, int pDigest, int pAge, boolean pFull, String pSpecies, String pFileName, String pType)
	{
		super(pX, pY, pPhase, pDigest, pAge, pFull, pSpecies, pFileName, pType);	
		int MoveX =  super.MoveX("marmot");
		int MoveY = super.MoveY("marmot");
	}	
}
