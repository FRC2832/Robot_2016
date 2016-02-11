package org.usfirst.frc2832.Robot_2016;

import org.usfirst.frc2832.Robot_2016.HID.GamepadState;

import edu.wpi.first.wpilibj.command.Subsystem;

/*
 * Helper class for the intake/expel motor system.
 * Use these class instead of accessing the motors directly
 * for readability.
 */
public class BallMotors extends Subsystem {
	
	
	
	public BallMotors()
	{
		
	}
	/*
	 * Spins the wheels inwards to take in a boulder.
	 */
	public static void intake(double speed)
	{
		RobotMap.ballIngestLeft.set(speed);
		RobotMap.ballIngestRight.set(-speed);
	}
	/*
	 * Spins the wheels outwards to expel a boulder.
	 */
	public static void expel(double speed)
	{
		RobotMap.ballIngestLeft.set(-speed);
		RobotMap.ballIngestRight.set(speed);
	}
	public static void expel(double num)
	{
		RobotMap.ballIngestLeft.set(-num);
		RobotMap.ballIngestRight.set(num);
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
