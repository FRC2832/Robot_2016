package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Kicker extends Subsystem {

	static double defaultLaunchAngle = 90;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	//push boulder out at a default angle
	public static void launch()
	{
		double startAngle = RobotMap.kicker.getAngle();
		RobotMap.kicker.setAngle(defaultLaunchAngle + startAngle);
	}
	//return after using default launch method
	public static void resetAfterLaunch()
	{
		double currentAngle = RobotMap.kicker.getAngle();
		RobotMap.kicker.setAngle(currentAngle - defaultLaunchAngle);
	}
	//reset back to position acquired in robotInit()
	public static void reset()
	{
		RobotMap.kicker.setAngle(Robot.defaultAngle);
	}
	
	//accessor
	public static double getAngle() {return RobotMap.kicker.getAngle();}
	//accessor
	public static void setAngle(double angle) {RobotMap.kicker.setAngle(angle);}
	//simple method to move some angle
	public static void moveAngle(double angle)
	{
		RobotMap.kicker.setAngle(RobotMap.kicker.getAngle() + angle);
	}

}
