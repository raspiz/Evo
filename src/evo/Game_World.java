/*****************************************************************************
 * Name: Aaron Whitmer, Joseph Stevens, Andrew Pryor
 * Class Name: Game_World
 * Purpose: This class contains parameters for the game Evo. An object of this class is used by
 * Game_Evo. This class will hold the values that can be changed by game conditions by the user 
 * such as sun, rain, and wind. Some of the variables are stored as arrays so that they can be
 * cycled through procedurally in the main game functions
 * 
*****************************************************************************/

package evo;

// settings of the world
public class Game_World {

	public final int SPECIES_TYPES = 6;
	
	// make arrays to hold this stuff so they can be pulled up in game evo
	
	
	private int speed;
	private int generation;
	private int sporesReleased;
	private int sporeDistance;
	private int growChance; 
	private int deathChance;	
	
	private int catcherHuntChance;
	
	// max ages
	private int[] maxAge;
	private int[] maxMoves;
	
	
	// constructor
	public Game_World() {
		
		// need to make sure the values based on percent never exceed <0 and >99
		speed = 100; // base value. 100ms per cycle/year of the game
		generation = 0; // age of the game world
		sporesReleased = 100; // base value. 1 spore per turn released
		sporeDistance = 20; // base value. spore will move (20 spaces + size of parent) away from parent when released 
		growChance = 40; // base value. spore has a 40% to take root and grow into a new species
		deathChance = 5; // base value. critter has a 5% chance to die after reaching max age		
		
		catcherHuntChance = 50; //base value of 50% chance
		
		
		// species max ages
		// base value. age after which critters will stop reproducing, phasing up, and may possibly die of old age
		maxAge = new int[6];	
		FillMaxAge();
		maxMoves = new int[6];
		FillMaxMoves();
			
	}

	// indexes for these:
	//0-deeproot, 1-catcher, 2-chingling, 3-marmot, 4-zylot, 5-spore
	public void FillMaxAge()
	{
		maxAge[0] = 100; 
		maxAge[1] = 150;
		maxAge[2] = 100;
		maxAge[3] = 50;
		maxAge[4] = 50;	
		maxAge[5] = 5;		
	}
	
	public void FillMaxMoves()
	{
		maxMoves[0] = 0; 
		maxMoves[1] = 0;
		maxMoves[2] = 3;
		maxMoves[3] = 5;
		maxMoves[4] = 10;	
		maxMoves[5] = 0;			
	}

	
	/////////////////////////
	// accessors and mutators
	/////////////////////////
	
	public int getSpeed() {
		return speed;
	}



	public void setSpeed(int speed) {
		this.speed = speed;
	}



	public int getGeneration() {
		return generation;
	}



	public void setGeneration(int generation) {
		this.generation = generation;
	}
	
	
	public int getSporesReleased() {
		return sporesReleased;
	}

	public void setSporesReleased(int sporesReleased) {
		this.sporesReleased = sporesReleased;
	}



	public int getSporeDistance() {
		if (sporeDistance <= 0)
			this.sporeDistance = 1;
		else
			this.sporeDistance = sporeDistance;
		
		return sporeDistance;
	}



	public void setSporeDistance(int sporeDistance) {
		this.sporeDistance = sporeDistance;
	}



	public int getGrowChance() {
		return growChance;
	}



	public void setGrowChance(int growChance) {
		this.growChance = growChance;
	}

	public int getDeathChance() {
		return deathChance;
	}



	public void setDeathChance(int deathChance) {
		this.deathChance = deathChance;
	}

	public int getCatcherHuntChance() {
		return catcherHuntChance;
	}

	public void setCatcherHuntChance(int catcherHuntChance) {
		this.catcherHuntChance = catcherHuntChance;
	}

	public int getMaxAge(int index) {
		return maxAge[index];
	}

	public void setMaxAge(int index, int maxAge) {
		this.maxAge[index] = maxAge;
	}

	public int getMaxMoves(int index) {
		return maxMoves[index];
	}

	public void setMaxMoves(int index, int maxMoves) {
		this.maxMoves[index] = maxMoves;
	}	
	
	
}
