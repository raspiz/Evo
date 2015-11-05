/*****************************************************************************
 * Name: Aaron Whitmer, Joseph Stevens, Andrew Pryor
 * Class Name: Image_Entity
 * Purpose: This is a generic class that is used by evo in order to create critters used in the game.
 * It creates an image and assigns x and y values based on passed in parameters.
*****************************************************************************/

package evo;

import java.awt.*;
import java.net.*;



// might want to make abstract
public class Image_Entity{
    //variables
    protected Image image;
    protected String filename;
       
	protected int xLoc;
	protected int yLoc;
    
    protected int xCenter;
    protected int yCenter;    
   
    //default constructor
    public Image_Entity() {
       
    }
    
    public Image_Entity(int pxLoc, int pyLoc, String pFileName)
    {
    	filename = pFileName;
    	loadImage();
    	setxLoc(pxLoc);
    	setyLoc(pyLoc);
    }
    
    public Image getImage() { return image; }

    public void setImage(Image image) {
        this.image = image;
        
    }
  

    public void loadImage() {
    	URL url = null;
    	Toolkit tk = Toolkit.getDefaultToolkit();
        url = this.getClass().getResource(filename);
        image = tk.getImage(url);
    }

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;    	
		g2d.drawImage(image, getxLoc(), getyLoc(), null);
		
	}
	
	/////////////////////////
	// accessors and mutators

	public int getxLoc() {
		return xLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
