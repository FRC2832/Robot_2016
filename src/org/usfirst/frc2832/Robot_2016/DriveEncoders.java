package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveEncoders extends Subsystem {

	static double left;
	static double right;
	static double[] vals = new double[2];
	static final double TOLERANCE = 0.1;
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public static double getAbsoluteValue()
	{
		vals = getBothValues();
		if (Math.abs(vals[0]) < TOLERANCE)
			return vals[1];
		else if (Math.abs(vals[1]) < TOLERANCE)
			return vals[0];
		else
			return (vals[0] - vals[1])/2; //CURRENTLY ONE IS OPPOSITE OF OTHER. IF ENCODER VALUE IS NOT CHANGING, CONSIDER REPLACING MINUS WITH PLUS
	}
	
	public static double getLeftValue()
	{
		return RobotMap.frontLeftMotor.getEncPosition() / RobotMap.ENCODER_PULSE_PER_METER;
	}
	
	public static double getRightValue()
	{
		return RobotMap.frontRightMotor.getEncPosition() / RobotMap.ENCODER_PULSE_PER_METER;
	}
	
	public static double[] getBothValues()
	{
		vals[0] = RobotMap.frontLeftMotor.getEncPosition() / RobotMap.ENCODER_PULSE_PER_METER;
		vals[1] = RobotMap.frontRightMotor.getEncPosition() / RobotMap.ENCODER_PULSE_PER_METER;
		return vals;
	}
	
	

}
