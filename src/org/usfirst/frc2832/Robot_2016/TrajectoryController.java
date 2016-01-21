package org.usfirst.frc2832.Robot_2016;


public class TrajectoryController {

	private double target;
	private double start;
	private double max;
	private double min;
	private double slope1;
	private double slope2;
	
	private double maxMoment = 1.0 / 3.0;
	private double slowMoment = 2.0 / 3.0;
	
	private boolean isLerped;
	
	/**
	 * Constructor that doesn't use given slopes
	 * @param target The distance to travel (in whatever units you are able to input, like meters or degrees)
	 * @param start The inital output, usually measured in voltage. Do NOT make it 0.
	 * @param min The minimum output, make it high enough to still register on the motors
	 * @param max The max saturation speed, measured in voltage
	 */
	public TrajectoryController(double target, double start, double min, double max) {
		this.target = target;
		this.start = start;
		this.min = min;
		this.max = max;
		
		isLerped = true;
	}
	/**
	 * Constructor that takes in slopes to control acceleration and deceleration
	 * @param target The distance to travel (in whatever units you are able to input, like meters or degrees)
	 * @param start The inital output, usually measured in voltage. Do NOT make it 0.
	 * @param min The minimum output, make it high enough to still register on the motors
	 * @param max The max saturation speed, measured in voltage
	 * @param slope1 The acceleration speed, should be positive, measured in output units per input units (i.e. voltage per meter)
	 * @param slope2 The deceleration speed, sign does not matter, measured in same units as slope1
	 */
	public TrajectoryController(double target, double start, double min, double max, double slope1, double slope2) {
		this.target = target;
		this.start = start;
		this.min = min;
		this.max = max;
		this.slope1 = slope1;
		this.slope2 = slope2;
		
		isLerped = false;
	}
	/**
	 * Linear Interpolation method
	 * @param The first y-value
	 * @param The second y-value
	 * @param Ratio of x between endpoints
	 * @return y-value
	 */
	private double lerp(double x, double y, double r) {
		return (x * (1 - r) + y * r);
	}
	/**
	 * Calculates the proper output at given input
	 * @param currentValue The current input, usually from encoders
	 * @return The desired output, usually in voltage
	 */
	public double get(double currentValue) {
		if (isLerped)
			return getLerped(currentValue);
		return getLined(currentValue);
	}
	private double getLerped(double currentValue) {
		double r = currentValue / target;
		if (r < maxMoment) {
			return lerp(start, max, r / maxMoment);
		} else if (r < slowMoment) {
			return max;
		} else {
			return lerp(max, min, (r - slowMoment) / (1 - slowMoment));
		}
	}
	
	/**
	 * A more classic trajectory curve
	 * @param currentValue Current value from input device
	 * @return
	 */
	private double getLined(double currentValue) {
		double output;
		if (accelLine(currentValue) < decelLine(currentValue))
			output = accelLine(currentValue);
		else
			output = decelLine(currentValue);
		if(output > max)
			output = max;
		return output;
	}
	
	private double accelLine(double currentValue) {
		return slope1 * currentValue + start;
	}
	
	private double decelLine(double currentValue) {
		return Math.abs(slope2) * -1 * (currentValue - target);
	}
	
	public static void main(String[] args) {
		double dist = 2.0;
		
		TrajectoryController tc = new TrajectoryController(dist, 0.2, 0, 0.5, 0.5, 0.5);
		
		for(double feedValue = 0; feedValue < dist; feedValue += 0.05) {
			System.out.println(tc.get(feedValue));
		}
	}
	
	
}
