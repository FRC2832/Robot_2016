package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.command.Subsystem;

/*
 * Helper class for the intake/expel motor system.
 * Use these class instead of accessing the motors directly
 * for readability.
 */
public class BallMotors extends Subsystem {
	
	private static final double SPEED = 1; //speed of motors, from 0 to 1.  
	
	public BallMotors()
	{
		
	}
	/*
	 * Spins the wheels inwards to take in a boulder.
	 */
	public static void intake()
	{
		RobotMap.ballIngestLeft.set(SPEED);
		RobotMap.ballIngestRight.set(-SPEED);
	}
	/*
	 * Spins the wheels outwards to expel a boulder.
	 */
	public static void expel()
	{
		RobotMap.ballIngestLeft.set(-SPEED);
		RobotMap.ballIngestRight.set(SPEED);
	}
	/*
	 * Stops the wheels from spinning.
	 */
	public static void stopMotors()
	{
		RobotMap.ballIngestLeft.set(0);
		RobotMap.ballIngestRight.set(0);
	}
	@Override
	protected void initDefaultCommand() {
		//Do nothing. REQUIRED for Subsystem extension.
		
	}
}
