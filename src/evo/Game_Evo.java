/*****************************************************************************
 * Name: Aaron Whitmer, Joseph Stevens, Andrew Pryor
 * Class Name: Game_Evo
 * Purpose: This is the main game portion of the Evo project. It includes functions for the critters
 * behavior including moving, hunting, and breeding.
 * 
 * This class extends JPanel to manipulate elements on the screen. It contains
 * an object of the class Game_World to set parameters for the game. It also contains a timer
 * that performs the game functions on an interval.
*****************************************************************************/

package evo;

import evo.critters.*;

import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class Game_Evo extends JPanel{
	
	private int width; 
	private int height;
	// Speed of game


	private Random generator;
	public final int SEEDS = 20; // critters to start with or spawn if everything dies
	
	// arraylist to hold individual species
	private ArrayList<Deeproot> deeproot;
	private ArrayList<Catcher> catcher;
	private ArrayList<Chingling> chingling;
	private ArrayList<Marmot> marmot;
	private ArrayList<Zylot> zylot;
	private ArrayList<Spore> spore;
	
	
	// arraylist of arraylists. each species is an index in the arraylist

	// 0  - deeproot
	// 1 - catcher
	// 2 - chingling
	// n-1 - spore - keep as last element in array		
	private ArrayList<ArrayList> critList;
	
	protected Game_World world;
	
	private Critter currentCritter;
	
	protected Timer timer;		
	
	// status panel and labels
	private JPanel status;
	private JLabel curWorldAge;
	private JLabel curCatcher;
	private JLabel curDeeproot;
	private JLabel curChingling;
	private JLabel curMarmot;
	private JLabel curZylot;
	private JLabel curCritter;
	private JLabel curSpore;
	private int totCrits;
	
	public Game_Evo()
	{
		// load parameters for the game world
		// could derive classes from this for different testing and balancing
		world = new Game_World();
		
		PanelSize();		
		
		timer = new Timer(world.getSpeed(), new TimerListener());
		
		// create deeproot arraylist and add it to total critter arraylist
		////**********************
		// need to add all species to this as they are implemented
		////**********************
		deeproot = new ArrayList<Deeproot>();	
		catcher = new ArrayList<Catcher>();
		chingling = new ArrayList<Chingling>();
		marmot = new ArrayList<Marmot>();
		zylot = new ArrayList<Zylot>();
		spore = new ArrayList<Spore>();
		critList = new ArrayList<ArrayList>();				

		critList.add(deeproot);
		critList.add(catcher);
		critList.add(chingling);
		critList.add(marmot);
		critList.add(zylot);
		// MAKE SURE SPORE IS ADDED TO THE ARRAYLIST LAST!
		critList.add(spore);
		
		generator = new Random();	
		
		this.setBackground(new Color(137, 255, 137));

	}
	
	
	public void createStatusPanel(){
		//creates the status panel, which outputs the statuses of the critters and world
//		FlowLayout flow = new FlowLayout();


//		top= new JPanel(new FlowLayout());
		curWorldAge= new JLabel();
		curCritter= new JLabel();
		curCatcher= new JLabel();
		curDeeproot= new JLabel();
		curChingling= new JLabel();
		curMarmot= new JLabel();
		curZylot= new JLabel();
		curSpore= new JLabel();
		status = new JPanel(new FlowLayout());
		
		status.add( curWorldAge);
		status.add( curCatcher);
		status.add( curDeeproot);
		status.add( curChingling);
		status.add( curMarmot);
		status.add( curZylot);
		status.add( curSpore);	
		status.add( curCritter);

	}
	
	public JPanel getStatusPanel()
	{
		return status;
	}
	
	public void setStatus(JPanel pStatus)
	{
		this.status = pStatus;
	}

	/*********************************
	 * THIS IS THE MAIN GAME FUNCTION.
	 * 
	 * CALLED EACH TIME THE TIMER TICKS
	 *********************************/
	private class TimerListener implements ActionListener
	{
		
		//The listener that triggered when the timer goes off
		public void actionPerformed(ActionEvent arg0) 
		{			
			// perform one cycle of the game

			PanelSize();
			
			Populate();

			CritterBehavior();
		
			// age the world by 1
			world.setGeneration(world.getGeneration() + 1);
			
			curWorldAge.setText("WorldAge:"+ world.getGeneration()) ;
			curCritter.setText("TotalCrit:" + totCrits ) ;
			curCatcher.setText("Catch:" + catcher.size() ) ;
			curDeeproot.setText("Deep:" + deeproot.size()) ;
			curChingling.setText("Ching:" + chingling.size()) ;
			curMarmot.setText("Marm:" + marmot.size()) ;
			curZylot.setText("Zylot:" + zylot.size()) ;
			curSpore.setText("Spore:" + spore.size()) ;

			status.repaint(); // paint the status panel		
			
			repaint(); // calls paintComponent and draws all of the creatures to the screen
			
			System.out.println("Generation: " + world.getGeneration());		
		}
	
	
	}
	
	/*********************************
	 * CritterBehavior - loops through all critters currently in the game. Called by the timer
	 */
	public void CritterBehavior()
	{
		///// foreach loop
		// age up
		// check for growth
		// resize if growing
		// full check
		// if mobile, move or eat
		// if > phase 2, create spores 
		// die if too old
		// draw
		// 
		
		for (int i = 0; i < critList.size(); i++)
		{
			Critter myCritter;
			
			ArrayList<Critter> speciesList = (ArrayList<Critter>)critList.get(i);			
			
			if (speciesList != null)  // makes sure this will not cause an error when a species goes extinct
			{
				for (int j = 0; j < speciesList.size(); j++)
				{
					
					boolean dead = false;	
					boolean old = false;
					myCritter = speciesList.get(j);					
					
					//digest food
					CritterDigest(myCritter);						
					
					// AGE 1 YEAR				
					int vAge = CritterAgeUp(myCritter, myCritter.age);	
					
					if (vAge > world.getMaxAge(i))// critter has reached maxage. chance it will die	
					{			
						// after a certain age they will no longer reproduce or try to phase up. each cycle there is a chance they will die and be removed						
						if (generator.nextInt(100) < world.getDeathChance())
						{
							// could make this affected by weather, temp zone, species						
							myCritter = null;
							speciesList.remove(j);
							j--;
							dead = true;
						}	
						else // critter did no die but it is too old to do some things
							old = true;
					}
					
					if (!dead) // false if ready to die
					{	
						if (!myCritter.species.equals("spore")) // everyone except spores 	
						{
							int phaseUpInterval = world.getMaxAge(i) / 5;	
							
							// TRY TO PHASE UP
							if (vAge % phaseUpInterval == 0 && !old)
							{								
								if (myCritter.phase < 5)
								{	
									if((myCritter.full && myCritter.type.equals("mobile")) || myCritter.type.equals("stationary"))
									{
										if (CritterPhaseUp(myCritter))		// try to grow
										{
											// change output image. concatenate a string for the filename
											myCritter.setFilename("../images/" + myCritter.species + myCritter.phase + ".png");
											myCritter.loadImage();	
										}	
									}
								}									
							}
							

							
							// movement
							if (myCritter.type.equals("mobile"))
							{
								// get max moves for critter and randomize allowed moves. critter will get 1 to n moves where n is maxMoves
								int currentMoves = generator.nextInt(world.getMaxMoves(i)) + 1;
								
								
								for (int k = 0; k < currentMoves; k++)
								{
									// hunters that aren't full will try to hunt
									// else they will try to move
									if (!myCritter.full)
									{
										// try to hunt
										// boolean here. if false kill myCritter
										if (!CritterMoveHunt(myCritter))
										{											
											// critter got eaten while trying to hunt
											// remove from array decrement array counter									
											myCritter = null;
											speciesList.remove(j);											
											j--;
											dead = true;
											break;
										}
									}
									else
									{										
										// if no collide, move is goood
										CritterMove(myCritter);	
									}
									
									if (dead)
										break;	
								}
							}
							
							// a chance to reproduce
							if (!dead && !old)
							{
					            if (myCritter.phase > 2)	// create spores if they are past the 2 phase
					            {
					            	if (myCritter.full && myCritter.type.equals("mobile"))					            	
					            		Reproduce(myCritter);						                
					            	
					            	else if (myCritter.type.equals("stationary") && vAge % phaseUpInterval == 0)					            	
					            		Reproduce(myCritter);						            						            	
					            }
							}
				            
						}	
						else // spore behavior
						{	
							if (SporeBehavior(myCritter))
							{								
				                // delete spore
				                spore.remove(j);
				                j--;
							}								
						}
					}				
				} // end critter loop
			}	
		}			
	} // end CritterBehavior
	
	
	public boolean SporeBehavior(Critter pCritter)
	{
		
    	int mutate = generator.nextInt(100); // 0 to 99. could parameterize this and make the chance of mutation go up or down based on conditions or temp zone
   	
    	// trying up 2, down 1 for evolve for now
        // species can only mutate 2 above and 1 below itself           
        // spore will mutate into a new species if random number is within range  
        if (mutate <= 5 && ((pCritter.parent.equals("zylot") || pCritter.parent.equals("marmot"))))	// 5% chance to mutate to zylot
        {
        	// if the creature is able to phase up, that means there was no collision and it's safe to be "born"
			if (CritterPhaseUp(pCritter))
			{
				// create the baby
                currentCritter = new Zylot(pCritter.xLoc, pCritter.yLoc, pCritter.phase, pCritter.digest, 0, pCritter.full, "zylot", "../images/zylot1.png", "mobile");

                // birth it into the world
				zylot.add((Zylot)currentCritter);	
				
				return true;
			}		
        }    	
        else if (mutate <= 10 && ((pCritter.parent.equals("zylot") || pCritter.parent.equals("marmot") || pCritter.parent.equals("chingling"))))	// 5% chance to mutate to marmot
        {
        	// if the creature is able to phase up, that means there was no collision and it's safe to be "born"
			if (CritterPhaseUp(pCritter))
			{
				// create the baby
                currentCritter = new Marmot(pCritter.xLoc, pCritter.yLoc, pCritter.phase, pCritter.digest, 0, pCritter.full, "marmot", "../images/marmot1.png", "mobile");

                // birth it into the world
				marmot.add((Marmot)currentCritter);	
				
				return true;
			}		
        }    	
        else if (mutate <= 15 && ((pCritter.parent.equals("marmot") || pCritter.parent.equals("chingling") || pCritter.parent.equals("catcher"))))	// 5% chance to mutate to chingling
        {
        	// if the creature is able to phase up, that means there was no collision and it's safe to be "born"
			if (CritterPhaseUp(pCritter))
			{
				// create the baby
                currentCritter = new Chingling(pCritter.xLoc, pCritter.yLoc, pCritter.phase, pCritter.digest, 0, pCritter.full, "chingling", "../images/chingling1.png", "mobile");

                // birth it into the world
				chingling.add((Chingling)currentCritter);	
				
				return true;
			}		
        }    	
        else if (mutate <= 20 && ((pCritter.parent.equals("chingling") || pCritter.parent.equals("catcher") || pCritter.parent.equals("deeproot"))))	// 5% chance to mutate to catcher
        {
        	// if the creature is able to phase up, that means there was no collision and it's safe to be "born"
			if (CritterPhaseUp(pCritter))
			{
				// create the baby
                currentCritter = new Catcher(pCritter.xLoc, pCritter.yLoc, pCritter.phase, pCritter.digest, 0, pCritter.full, "catcher", "../images/catcher1.png", "stationary");

                // birth it into the world
				catcher.add((Catcher)currentCritter);	
				
				return true;
			}
        }	
        else if (mutate <= world.getGrowChance() && (pCritter.parent.equals("deeproot") || pCritter.parent.equals("catcher")))  // chance to grow into deeproot, based on user control
        {
        	// if the creature is able to phase up, that means there was no collision and it's safe to be "born"
			if (CritterPhaseUp(pCritter))
			{
				// create the baby
                currentCritter = new Deeproot(pCritter.xLoc, pCritter.yLoc, pCritter.phase, pCritter.digest, 0, pCritter.full, "deeproot", "../images/deeproot1.png", "stationary");

                // birth it into the world
				deeproot.add((Deeproot)currentCritter);	

				return true;
			}
        }				
		
		return false;				
	}
		
	
	/******************************
	 * Populate - at start of game and if there are less than 20 deeproots in the game,
	 * 20 deeproots will be populated
	 ******************************/
	public void Populate()
	{		
		int stuckCount = 0; // safeguard to avoid an infinite loop if there is no room
		
		// might want to change condition here. could make it spawn spores rather than deeproots
		while (deeproot.size() < SEEDS && stuckCount < 1000)			
		{
			int startX = 0;
			int startY = 0;			

			startX = generator.nextInt(width); // 0 to (n-1)
			startY = generator.nextInt(height);			
	
			currentCritter = new Deeproot(startX, startY, 1, 0, 0, false, "deeproot", "../images/deeproot1.png", "stationary");
			
			// check for collisions and room to be added
			if (!CollisionDetect(currentCritter))
			{				
				//currentCritter.loadImage();
				//currentCritter.setImage(currentCritter.getImage());
				
				deeproot.add((Deeproot)currentCritter);	// add to arraylist.	cast it as a deeproot				
			}
			
			stuckCount++;			
		}
	}	
	
	/*************************
	 * DePopulate - Kills all creatures on the screen, AKA the Jody Bomb
	 */
	public void DePopulate()
	{		
		for (int i = 0; i < critList.size(); i++)
		{	
			// POLYMORPHISM!
			ArrayList<Critter> speciesList = (ArrayList<Critter>)critList.get(i);
			
			speciesList.clear();
		}	
		
		
	}
	
	public void Reproduce(Critter pCritter)	
	{		
	
	    // 100 means 1 spore, 150 means 1.5 spores, etc
		
		int sporesOut = world.getSporesReleased();
		
        if ((sporesOut >= 50 && sporesOut < 100) && world.getGeneration() % 2 == 0)         					            		
        	CreateSpore(pCritter);	
        else if (sporesOut >= 100 && sporesOut < 150)
        	CreateSpore(pCritter);
        else if (sporesOut >= 150 && sporesOut < 200)
        {
        	CreateSpore(pCritter);
        	
        	if (world.getGeneration() % 2 == 0)
        		CreateSpore(pCritter);						                	
        }
        else if (sporesOut >= 200)
        {
        	CreateSpore(pCritter);
        	CreateSpore(pCritter);        	
        }		
	}
	
	
	
	/************************
	 * CreateSpore - called when an existing critter wants to release a spore.
	 * a creature is passed in and a spore of that species will be born
	 * 
	 * @param pParent
	 * @param pSpecies
	 ***********************/
	public void CreateSpore(Critter pParent)
	{
		int spawnX = 0;
		int spawnY = 0;
		int dirX, dirY;
		boolean collision = false; // local flag
		int distance = world.getSporeDistance(); // distance spore can travel away from parent based on game world parameter
		
		// find a random spawn point from 0-distance away from parent.
		// tacks on parent size based on phase so that spore does not spawn on top of parent
		switch (pParent.getPhase())
		{
			case 1:
	            spawnX = generator.nextInt(distance) + 2;	
	            spawnY = generator.nextInt(distance) + 2;	
				break;
			case 2:
	            spawnX = generator.nextInt(distance) + 3;	
	            spawnY = generator.nextInt(distance) + 3;	
				break;
			case 3:
	            spawnX = generator.nextInt(distance) + 5;	
	            spawnY = generator.nextInt(distance) + 5;	
				break;
			case 4:
	            spawnX = generator.nextInt(distance) + 7;	
	            spawnY = generator.nextInt(distance) + 7;	
				break;
			case 5:
	            spawnX = generator.nextInt(distance) + 9;	
	            spawnY = generator.nextInt(distance) + 9;	
				break;	
		}
		
		// determine if the spore moves north/south and east/west
	    dirX = generator.nextInt(2);
	    dirY = generator.nextInt(2);
	    
	    if (dirX == 0)	// east
	    {
	        spawnX = pParent.getxCenter() + spawnX;
	        if (dirY == 0)	// north						
	            spawnY = pParent.getyCenter() - spawnY;						
	        else	// south						
	            spawnY = pParent.getyCenter() + spawnY;						
	    }
	    else	// west
	    {
	        spawnX = pParent.getxCenter() - spawnX;
	        if (dirY == 0)	// north						
	            spawnY = pParent.getyCenter() - spawnY;						
	        else	// south						
	            spawnY = pParent.getyCenter() + spawnY;						
	    } 
		
	    // create spore. not added to arraylist until we're 
	    //sure it won't collide with an existing creature
	    // or spawn out of bounds
	    currentCritter = new Spore(spawnX, spawnY, 0, 0, 0, false, "spore", "../images/spore.png", "stationary", pParent.species);
	    
	    if (spawnX >= 0 && spawnX < width && spawnY >= 0 && spawnY < height) // make sure spawn loc is within borders
	    	collision = false; 	    
	    else
	    	collision = true;	    

	    if (!collision)
	    	collision = CollisionDetect(currentCritter); // try to collide with all of the other creatures	    
	    
	    // if all good, add to spore arraylist
	    if(!collision)	    
	    	spore.add((Spore)currentCritter);		
	}	
	
	/************************
	 * CritterMove - move one pixel in a random direction
	 * 
	 * 
	 * 
	 * @param pCritter
	 ************************/
	public void CritterMove(Critter pCritter)
	{
		// choose a direction. 0 - north, 1 - south, 2 - west, 3 - east
		int dir = generator.nextInt(4);			
		
		// tentatively move in the new direction
		if (dir == 0)
			pCritter.setyLoc(pCritter.getyLoc() - 1);
		else if (dir == 1)
			pCritter.setyLoc(pCritter.getyLoc() + 1);
		else if (dir == 2)
			pCritter.setxLoc(pCritter.getxLoc() - 1);
		else
			pCritter.setxLoc(pCritter.getxLoc() + 1);
		
		pCritter.updateSprite();
		
		// try to collide with border and other critters
		if (pCritter.xLoc < 0 || pCritter.yLoc < 0 || pCritter.xLoc >= (width + pCritter.pixels) 
				|| pCritter.yLoc >= (height + pCritter.pixels)
				|| CollisionDetect(pCritter)) 
		{
			// revert changes
			if (dir == 0)
				pCritter.setyLoc(pCritter.getyLoc() + 1);
			else if (dir == 1)
				pCritter.setyLoc(pCritter.getyLoc() - 1);
			else if (dir == 2)
				pCritter.setxLoc(pCritter.getxLoc() + 1);
			else
				pCritter.setxLoc(pCritter.getxLoc() - 1);
			
			pCritter.updateSprite();											
		}			
	}
	
	/************************
	 * CritterMoveHunt - for hunting. move one pixel in a random direction
	 * 
	 * 
	 * 
	 * @param pCritter
	 ************************/
	public boolean CritterMoveHunt(Critter pCritter)
	{
		// choose a direction. 0 - north, 1 - south, 2 - west, 3 - east
		int dir = generator.nextInt(4);	
		String actionTaken;
		
		// tentatively move in the new direction
		if (dir == 0)
			pCritter.setyLoc(pCritter.getyLoc() - 1);
		else if (dir == 1)
			pCritter.setyLoc(pCritter.getyLoc() + 1);
		else if (dir == 2)
			pCritter.setxLoc(pCritter.getxLoc() - 1);
		else
			pCritter.setxLoc(pCritter.getxLoc() + 1);
		
		pCritter.updateSprite();
		
		// try to collide with border
		if (pCritter.xLoc < 0 || pCritter.yLoc < 0 || pCritter.xLoc >= (width + pCritter.pixels) || pCritter.yLoc >= (height + pCritter.pixels)) 					
			actionTaken = "revert";			
		else // hunt			
			actionTaken = CollisionHunt(pCritter);	
		
		
		if (actionTaken.equals("revert"))	
		{			
			// revert changes
			if (dir == 0)
				pCritter.setyLoc(pCritter.getyLoc() + 1);
			else if (dir == 1)
				pCritter.setyLoc(pCritter.getyLoc() - 1);
			else if (dir == 2)
				pCritter.setxLoc(pCritter.getxLoc() + 1);
			else
				pCritter.setxLoc(pCritter.getxLoc() - 1);
			
			pCritter.updateSprite();
		}
		else if (actionTaken.equals("eaten"))
		{
			// revert changes
			if (dir == 0)
				pCritter.setyLoc(pCritter.getyLoc() + 1);
			else if (dir == 1)
				pCritter.setyLoc(pCritter.getyLoc() - 1);
			else if (dir == 2)
				pCritter.setxLoc(pCritter.getxLoc() + 1);
			else
				pCritter.setxLoc(pCritter.getxLoc() - 1);
			
			pCritter.updateSprite();	
			
			return false; // got eaten while hunting
		}
		
		
		// return true as long as the critter was not eaten while hunting
		return true;
		
		
	}
	
	
	/*******************
	 * CollisionDetect - passed in critter will try to collide with all other creatures.
	 * used for reproducing, phasing up, moving, and eating
	 * critter will not check against itself
	 * 
	 * @param pMovingCritter - the critter trying to collide
	 * @return - true if there is a collision, false if there is not
	 *********************/
	public boolean CollisionDetect(Critter pMovingCritter)
	{
		Critter daGuy; // critter trying to be collided with.
		
		// this loop will pull in every critter in the game try to collide with it
		for (int i = 0; i < critList.size(); i++)
		{			
			// POLYMORPHISM!
			ArrayList<Critter> speciesList = (ArrayList<Critter>)critList.get(i);
			
			for (int j = 0; j < speciesList.size(); j++)
			{				
				daGuy = speciesList.get(j);				
				
				if (!pMovingCritter.equals(daGuy)) // this seems to work as it should. keeps from colliding with itself
					if (pMovingCritter.boundingBox.intersects(daGuy.boundingBox))	// calling the intersects function of Rectangle Class			    	
			    		return true; // we have a collision
			}			
		}	

		return false; // no collision
	}
	
	public String CollisionHunt(Critter pMovingCritter)
	{
		Critter daGuy; // critter trying to be collided with.
		
		// this loop will pull in every critter in the game try to collide with it
		for (int i = 0; i < critList.size(); i++)
		{			
			// POLYMORPHISM!
			ArrayList<Critter> speciesList = (ArrayList<Critter>)critList.get(i);
			
			for (int j = 0; j < speciesList.size(); j++)
			{				
				daGuy = speciesList.get(j);				
				
				if (!pMovingCritter.equals(daGuy)) // this seems to work as it should. keeps from colliding with itself		
				{	
					if (pMovingCritter.boundingBox.intersects(daGuy.boundingBox))	// calling the intersects function of Rectangle Class	
					{
						if (pMovingCritter.species.equals(daGuy.species)) // can't eat their own kind
						{
							return "revert";							
						}
						
						String hunter = pMovingCritter.getSpecies();
						String prey = daGuy.getSpecies();
						
						
						// try to hunt
						if (hunter.equals("chingling"))
						{
							if (prey.equals("deeproot") && pMovingCritter.phase >= daGuy.phase) // eat deeproot
							{
								// eat the prey. remove prey from its array. set critter to full
								speciesList.remove(j);
								pMovingCritter.setFull(true);
								pMovingCritter.setDigest(10);
								return "ate";								
							}
							else if(prey.equals("catcher")) // try to eat catcher
							{
								// may eat or get eaten
								int outcome = generator.nextInt(100);
								
								if (outcome > world.getCatcherHuntChance() && pMovingCritter.phase >= daGuy.phase)
								{
									// eat the prey. remove prey from its array. set critter to full
									speciesList.remove(j);
									pMovingCritter.setFull(true);
									pMovingCritter.setDigest(10);
									return "ate";	
								}
								else if(daGuy.getFull() == false && pMovingCritter.phase <= daGuy.phase) // critter got eaten	
								{								
									daGuy.setFull(true);
									daGuy.setDigest(15);
									return "eaten";		
								}
								else
									return "revert"; // could have got eaten but catcher was full
							}
							else   // can't move here, can't eat it	
							{
								return "revert"; 
							}		
						}
						else if (hunter.equals("marmot"))
						{	
							if (prey.equals("deeproot") && pMovingCritter.phase >= daGuy.phase) // eat deeproot
							{
								// eat the prey. remove prey from its array. set critter to full
								speciesList.remove(j);
								pMovingCritter.setFull(true);
								pMovingCritter.setDigest(10);
								return "ate";								
							}
							else if (prey.equals("chingling") && pMovingCritter.phase >= daGuy.phase) // eat chingling
							{
								// eat the prey. remove prey from its array. set critter to full
								speciesList.remove(j);
								pMovingCritter.setFull(true);
								pMovingCritter.setDigest(10);
								return "ate";								
							}
							else if(prey.equals("catcher")) // try to eat catcher
							{
								// may eat or get eaten
								int outcome = generator.nextInt(100);//world.getCatcherHuntChance());
								
								if (outcome > world.getCatcherHuntChance() && pMovingCritter.phase >= daGuy.phase)
								{
									// eat the prey. remove prey from its array. set critter to full
									speciesList.remove(j);
									pMovingCritter.setFull(true);
									pMovingCritter.setDigest(10);
									return "ate";	
								}
								else if(daGuy.getFull() == false && pMovingCritter.phase <= daGuy.phase) // critter got eaten	
								{								
									daGuy.setFull(true);
									daGuy.setDigest(15);
									return "eaten";		
								}	
								else
									return "revert"; // could have got eaten but catcher was full
							}
							else   // can't move here, can't eat it	
							{
								return "revert"; 
							}	
						}
						else if (hunter.equals("zylot"))
						{
							if ((prey.equals("marmot") || prey.equals("chingling")) &&  pMovingCritter.phase >= daGuy.phase) // eat marmot or chingling
							{
								// eat the prey. remove prey from its array. set critter to full
								speciesList.remove(j);
								pMovingCritter.setFull(true);
								pMovingCritter.setDigest(10);
								return "ate";								
							}	
							else   // can't move here, can't eat it	
							{
								return "revert"; 
							}
						}						
					}
				}
			}			
		}	

		return "move"; // no collision
	} // end CollisionHunt
	
	
	
	/***********************
	 * CritterPhaseUp - critter will try to reach a new phase by increasing in size.
	 * first it will phase up and then try to collide to make sure there was room
	 * if there is a collision or phasing up would put it out of game bounds it will phase back down
	 * 
	 * @param pCritter - the critter trying to phase up
	 * @return false if it was unable to phase up, true if it was
	 */
	public boolean CritterPhaseUp(Critter pCritter)
	{
		// phase up
		pCritter.setPhase(pCritter.getPhase() + 1);
		// move critter up and left 1 space to keep growth radius centered											
		pCritter.setxLoc(pCritter.getxLoc() - 1);
		pCritter.setyLoc(pCritter.getyLoc() - 1);
		pCritter.updateSprite();		

		// try to collide. if there is a collision, reset phase and size
		if(CollisionDetect(pCritter) || pCritter.getxLoc() < 0 || pCritter.getxLoc() >= width || pCritter.getyLoc() < 0 || pCritter.getyLoc() >= height)
		{
			pCritter.setPhase(pCritter.getPhase() - 1);
			pCritter.setxLoc(pCritter.getxLoc() + 1);
			pCritter.setyLoc(pCritter.getyLoc() + 1);

			pCritter.updateSprite();

			return false;	// unable to phase up
		}
				
		return true; // phase up successful
	}
	
	/************************
	 * CritterAgeUp - increment the passed in critter's age 1 year and return the value
	 * the age is set here but also returned to use for other purposes
	 * 
	 * @param pCritter - critter that is aging
	 * @param pAge - current age
	 * @return - new age
	 ***********************/
	public int CritterAgeUp(Critter pCritter, int pAge)
	{
		pAge++;		
		pCritter.setAge(pAge);	
		return pAge;		
	}
	
	
	//Start the timer object
	public void startTimer()
	{
		timer.start();	
	}
	
	// Stop the timer object
	public void stopTimer()
	{
		timer.stop();
	}
	
	public void CritterDigest(Critter pCritter)
	{
		int vDigest = pCritter.getDigest();
		
		// digest food if full
		if (vDigest > 0 && pCritter.getFull())
		{
			vDigest--;
			pCritter.setDigest(vDigest);
			
			if (vDigest == 0) // set full to empty if food is fully digested
				pCritter.setFull(false);
			
		}	
		
		
	}
	
	
	/**********************
	 * paintComponent - draw the creatures to the screen at the end of every cycle of the timer
	 * 
	 * 
	 *********************/
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		totCrits = 0;
		
		Critter c;
		
		for (int i = 0; i < critList.size(); i++)
		{	
			// POLYMORPHISM!
			ArrayList<Critter> speciesList = (ArrayList<Critter>)critList.get(i);
			
			for (int j = 0; j < speciesList.size(); j++)
			{
				totCrits++;
				c = speciesList.get(j);
				c.draw(g);
			}			
		}		
	}
	
	/*********************
	 * PanelSize - assigns the game's panel to the max size within the JFrame it sits in
	 * several pixels are subtracted from the width and height to prevent critters from
	 * getting outside of the borders. 
	 *********************/
	public void PanelSize()
	{
		// reducing this by 6 keeps them within the actual window border
		width = this.getWidth() - 10;
		height = this.getHeight() - 10;		
	}	
}
