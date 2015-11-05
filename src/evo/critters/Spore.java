package evo.critters;

public class Spore extends StationaryCritter{
	
	public Spore()
	{
		super();
		parent = "";
	}

	public Spore(int pX, int pY, int pPhase, int pDigest, int pAge, boolean pFull, String pSpecies, String pFileName, String pType,  String pParent)
	{	
		super(pX, pY, pPhase, pDigest, pAge, pFull, pSpecies, pFileName, pType);
		setParent(pParent);
	}



}
