package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

	static CANTalon climbingWinch; //the motor that actually drives the climber
	static Servo release; //the servo that latches onto the gear and holds it in one direction when actually climbing
	static Servo motorLatch;
	static double releaseHoldAngle = 0; //the angle the release servo is for the duration of match
	static double releaseLetGoAngle = 0.5; //the angle the release servo is for letting go when about to climb
	static double motorLatchNotTouchingAngle = 0; //the angle the latch is for the duration of the match
	static double motorLatchTouchingAngle = 0.5; //the angle the latch is for touching the gear when actually moving up
	static final double CLIMBER_UP_SPEED = 0.7;
	static final double CLIMBER_DOWN_SPEED = -0.7;
	static boolean isReleased = false; //this is true when the release servo is released
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public static void initialize(){
		climbingWinch = new CANTalon(8); //this value is guessed as of 3/21
		release = new Servo(2); //this value is guessed as of 3/21
		motorLatch = new Servo(3); //this value is guessed as of 3/21
		climbingWinch.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		climbingWinch.enableBrakeMode(true);
	}
	
	public static void releaseLatch()
	{
		motorLatch.set(motorLatchTouchingAngle);
	}
	
	public static void resetLatch()
	{
		motorLatch.set(motorLatchNotTouchingAngle);
	}
	
	public static void releaseRelease()
	{
		release.set(releaseLetGoAngle);
	}
	
	public static void resetRelease()
	{
		release.set(releaseHoldAngle);
	}
	
	public static void moveClimberUp()
	{
		climbingWinch.set(CLIMBER_UP_SPEED);
	}
	
	public static void moveClimberDown()
	{
		climbingWinch.set(CLIMBER_DOWN_SPEED);
	}
	
	public static void stopClimber()
	{
		climbingWinch.set(0.0);
	}

}
