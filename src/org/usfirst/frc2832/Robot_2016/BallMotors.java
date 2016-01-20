package org.usfirst.frc2832.Robot_2016;

/*
 * Helper class for the intake/expel motor system.
 * Use these class instead of accessing the motors directly
 * for readability.
 */
public class BallMotors {
	
	private static final double SPEED = 1; //speed of motors, from 0 to 1.  
	
	public BallMotors()
	{
		
	}
	/*
	 * Spins the wheels inwards, intaking a boulder.
	 */
	public static void intake()
	{
		RobotMap.ballIngestLeft.set(SPEED);
		RobotMap.ballIngestLeft.set(-SPEED);
	}
	/*
	 * Spins the wheels outwards, expelling a boulder.
	 */
	public static void expel()
	{
		RobotMap.ballIngestLeft.set(-SPEED);
		RobotMap.ballIngestLeft.set(SPEED);
	}
	/*
	 * Stops the wheels from spinning.
	 */
	public static void stopMotors()
	{
		RobotMap.ballIngestLeft.set(0);
		RobotMap.ballIngestLeft.set(0);
	}
}
