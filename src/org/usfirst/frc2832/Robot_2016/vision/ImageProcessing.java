package org.usfirst.frc2832.Robot_2016.vision;

import org.usfirst.frc2832.Robot_2016.Robot;

public class ImageProcessing {
	//resolution
	private static final int RES_X = 320;
	private static final int RES_Y = 240;
	//view angle of camera, in radians
	private static final double VIEW_ANG = 67* Math.PI / 180;
	
	//height/width of target, in meters
	public static final double TOWER_HEIGHT = 1.220;//.3048;
	public static final double TOWER_WIDTH = .508;
	//distance from bottom of target to ground, in meters
	public static final double DIST_TO_GROUND = 2.159;
	
	
	//each coord is from -1 to 1 and says how far away the last-processed
	//target is. [0,0] is center. [-1,-1] is top left corner.
	public static double[] targetOffset = {0,0};
	
	//how far away the target is, in meters.
	public static double depth = 0;
	public static double height = 1;
	/*The contours report has the following values:
	 * centerX
	 * centerY
	 * width
	 * area
	 * solidity (likely not helpful; determines how "convex" it is)
	 * height
	 */
	
	
	
	/*Steps for processing:
	 * 1) Find largest contour (i.e., the target)
	 * 2) Construct new array that has values from this contour only
	 * 3) Determine target's offset from center
	 * 4) Determine how far away the target it
	 */
	public static void process()
	{
		//first, find largest contour with respect to area
		double[] areas = Robot.table.getNumberArray("area", new double[0]);
		if(areas.length < 1)
			return;
		int largest = 0;
		for(int i = 1; i < areas.length; i++)
			if(areas[i]>areas[largest])
				largest=i;
		//the largest contour is now at index "largest" in all arrays
		//now, let's make a new array containing all vals at index largest
		double[] contour = new double[6];
		String[] names = {"centerX","centerY","width","area","solidity","height"};
		for(int i = 0; i < names.length;i++)
			contour[i]=Robot.table.getNumberArray(names[i], new double[6])[largest];
		//now contour contains all values (in order) of largest
		/*next let's determine how far off the target is from center.
		*We will do this using [x,y] where x,y range from -1 to 1, and [0,0]
		*being image center. This should thus tell us how far we must turn.*/
		targetOffset[0] = (contour[0]-RES_X/2)/(RES_X/2);
		targetOffset[1] = (contour[0]-RES_Y/2)/(RES_Y/2);
		
		height = contour[5];
		//finally let's figure out how far away we are from the target.
		//TODO: make this work for nonzero angle tilts
		double pixelsToFeet = TOWER_HEIGHT / contour[5];
		depth=(pixelsToFeet * RES_X/2)/Math.tan(VIEW_ANG/2);
		
	}
}
