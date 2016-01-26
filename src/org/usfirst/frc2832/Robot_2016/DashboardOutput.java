package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardOutput {

	//This method is invoked in teleopPeriodic to display all the crap we put on the dashboard
	public static void putData()
	{
		SmartDashboard.putNumber("laser",RobotMap.laser.pidGet()); //this value may not be right value?
		SmartDashboard.putNumber("Left Encoder", DriveEncoders.getLeftValue());
		SmartDashboard.putNumber("Right Encoder", DriveEncoders.getRightValue());
		SmartDashboard.putNumber("Combined Enc.", DriveEncoders.getAbsoluteValue());
		SmartDashboard.putNumber("Servo val", Kicker.get());
	}
}
