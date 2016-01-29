package org.usfirst.frc2832.Robot_2016;

import org.usfirst.frc2832.Robot_2016.commands.GoToLevel;

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
		SmartDashboard.putNumber("Winch Pos", RobotMap.winchMotor.getEncPosition());
		SmartDashboard.putData("GoToLevel 0", new GoToLevel(0));
		SmartDashboard.putData("GoToLevel 1", new GoToLevel(1));
		SmartDashboard.putData("GoToLevel 2", new GoToLevel(2));
		SmartDashboard.putData("GoToLevel 3", new GoToLevel(3));
		SmartDashboard.putNumber("Winch Motor Value", RobotMap.winchMotor.get());
		
	}
}
