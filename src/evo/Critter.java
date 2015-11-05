/*****************************************************************************
 * Name: Aaron Whitmer, Joseph Stevens, Andrew Pryor
 * Class Name: Critter
 * Purpose: This is an abstract class that works as the template for all critters in the game evo.
 * It extends Image_Entity in order to have a graphic associated with the critter.
 * All of the important specific information for the critters is defined here.
 * Using the awesome power of polymorphism, we can use this class to use run through arraylists of critters
*****************************************************************************/

package evo;

import evo.Image_Entity;

import java.awt.Rectangle;

// make abstract
public abstract class Critter extends Image_Entity {

	protected int phase;
	protected int digest;
	protected int age;	
	protected boolean full;
	protected String species;	
	protected String parent;
	protected String type;	
	protected int pixels;
    protected Rectangle boundingBox;
    
    public Critter()
    {
    	phase = 0;
    	age = 0;
    	digest = 0;
    	full = false;
    	species = "";  	
    	pixels = 1;
    	boundingBox = null;
    	parent = "";
    	type = "";
    }
    
	// parameterized constructor
    public Critter (int pX, int pY, int pPhase, int pDigest, int pAge, boolean pFull, String pSpecies, String pFileName, String pType)
    {    	
    	super(pX, pY, pFileName);

    	setPhase(pPhase);
    	setAge(pAge);
    	setDigest(pDigest);
    	setFull(pFull);
    	setSpecies(pSpecies);   	
    	setType(pType);
    	updateSprite(); // find x/y centers and height/width in pixels
    }
    
    // called when a creature phases up to accomodate for their increased size
    // also updates the center point for the critter
	public void updateSprite()
	{		
    	setxCenter();
    	setyCenter();
    	
    	// sets the critters size in pixels based on its phase
    	switch (phase)
    	{
    		case 0:
    			setPixels(1);
    			break;
    		case 1:
    			setPixels(3);
    			break;
    		case 2:
    			setPixels(5);
    			break;
    		case 3:
    			setPixels(7);
    			break;
    		case 4:
    			setPixels(9);
    			break;
    		case 5:
    			setPixels(11);
    			break;    	
    	}
    	
    	// remake bounding box with new size
    	setBoundingBox();
	}    
    

    // accessors and mutators
	
	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public int getDigest() {
		return digest;
	}

	public void setDigest(int digest) {
		this.digest = digest;
	}

	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	public boolean getFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}



	public String getSpecies() {
		return species;
	}



	public void setSpecies(String species) {
		this.species = species;
	}
    
	public int getxCenter() {
		return xCenter;
	}

	public void setxCenter() {
		// the value of the phase + current xloc = center (due to pixel size of creatures)		
		int tempMidX = phase + xLoc;
		this.xCenter = tempMidX;
	}

	public int getyCenter() {
		return yCenter;
	}

	public void setyCenter() {		
		// the value of the phase + current yloc = center (due to pixel size of creatures)		
		int tempMidY = phase + yLoc;
		this.yCenter = tempMidY;
	}
	
    
    public int getPixels() {
		return pixels;
	}



	public void setPixels(int pixels) {
		this.pixels = pixels;
	}

    //bounding rectangle
    public Rectangle getBoundingBox() {
        return boundingBox;
    }
    
    public void setBoundingBox() {
    	Rectangle myBox = new Rectangle(this.xLoc, this.yLoc, this.pixels, this.pixels);
    	this.boundingBox = myBox;
    } 
    
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}
    
    
}
