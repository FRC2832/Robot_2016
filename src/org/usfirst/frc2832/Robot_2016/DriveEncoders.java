package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveEncoders extends Subsystem {

	static double left;
	static double right;
	static double[] vals = new double[2];
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public static double getAbsoluteValue()
	{
		vals = getBothValues();
		return Math.sqrt(vals[0]*vals[1]);
	}
	
	public static double getLeftValue()
	{
		return RobotMap.frontLeftMotor.getEncPosition();
	}
	
	public static double getRightValue()
	{
		return RobotMap.frontRightMotor.getEncPosition();
	}
	
	public static double[] getBothValues()
	{
		vals[0] = RobotMap.frontLeftMotor.getEncPosition();
		vals[1] = RobotMap.frontRightMotor.getEncPosition();
		return vals;
	}
	
	

}
