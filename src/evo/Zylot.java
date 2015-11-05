/*****************************************************************************
 * Name: Aaron Whitmer, Joseph Stevens, Andrew Pryor
 * Class Name: Zylot
 * Purpose: This critter is a mobile critter that is carnivorous. It is the apex predator
*****************************************************************************/

package evo;

import evo.critters.MobileCritter;

public class Zylot extends MobileCritter {
	public Zylot()
	{//default
		super();
	}
	public Zylot(int pX, int pY, int pPhase, int pDigest, int pAge, boolean pFull, String pSpecies, String pFileName, String pType)
	{
		super(pX, pY, pPhase, pDigest, pAge, pFull, pSpecies, pFileName, pType);	
		int MoveX = super.MoveX("zylot");
		int MoveY =  super.MoveY("zylot");
	}
}
