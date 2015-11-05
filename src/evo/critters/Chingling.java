/*****************************************************************************
 * Name: Aaron Whitmer, Joseph Stevens, Andrew Pryor
 * Class Name: Chingling
 * Purpose: This critter is a mobile critter that is omnivorous.
*****************************************************************************/

package evo.critters;

public class Chingling extends MobileCritter {

	public Chingling()
	{//default
		super();
	}
	public Chingling(int pX, int pY, int pPhase, int pDigest, int pAge, boolean pFull, String pSpecies, String pFileName, String pType)
	{
		super(pX, pY, pPhase, pDigest, pAge, pFull, pSpecies, pFileName, pType);	
		int MoveX = super.MoveX("chingling");
		int MoveY = super.MoveY("chingling");
	}
	
	
}
