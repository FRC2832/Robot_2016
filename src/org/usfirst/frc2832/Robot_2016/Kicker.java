package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Kicker extends Subsystem{

	static double defaultLaunchAngle = 90;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public static void launch()
	{
		double startAngle = RobotMap.kicker.getAngle();
		RobotMap.kicker.setAngle(defaultLaunchAngle + startAngle);
		
	}
	
	public static void resetAfterLaunch()
	{
		double currentAngle = RobotMap.kicker.getAngle();
		RobotMap.kicker.setAngle(currentAngle - defaultLaunchAngle);
	}
	
	public static void reset()
	{
		RobotMap.kicker.setAngle(Robot.defaultAngle);
	}

}
