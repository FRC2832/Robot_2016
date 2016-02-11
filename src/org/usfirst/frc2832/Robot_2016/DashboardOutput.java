package org.usfirst.frc2832.Robot_2016;

import org.usfirst.frc2832.Robot_2016.commands.GoToLevel;
import org.usfirst.frc2832.Robot_2016.commands.ImagingTest;
import org.usfirst.frc2832.Robot_2016.commands.AutonomousCommand;
import org.usfirst.frc2832.Robot_2016.commands.InterfaceFlip;
import org.usfirst.frc2832.Robot_2016.commands.Shoot;
import org.usfirst.frc2832.Robot_2016.commands.autonomous.MoveForward;
import org.usfirst.frc2832.Robot_2016.commands.autonomous.RotateAngle;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Zach
 * Helper class which encapsulates all the stuff we need to throw at the Dashboard
 */
public class DashboardOutput {

	//This method is invoked in teleopPeriodic to display all the crap we put on the dashboard
	public static void putPeriodicData()
	{
		SmartDashboard.putNumber("laser",RobotMap.laser.pidGet()); //this value may not be right value?
		SmartDashboard.putNumber("Left Encoder", DriveEncoders.getLeftValue());
		SmartDashboard.putNumber("Right Encoder", DriveEncoders.getRightValue());
		SmartDashboard.putNumber("Combined Enc.", DriveEncoders.getAbsoluteValue());
		SmartDashboard.putNumber("Servo val", Kicker.get());
		SmartDashboard.putNumber("Winch Pos", RobotMap.winchMotor.getEncPosition());
		SmartDashboard.putNumber("Winch Motor Value", RobotMap.winchMotor.get());
		SmartDashboard.putNumber("Gyro.Yaw", RobotMap.imu.getYaw());
		SmartDashboard.putNumber("Gyro.Pitch", RobotMap.imu.getPitch());
		SmartDashboard.putNumber("Gyro.Roll", RobotMap.imu.getRoll());
		SmartDashboard.putNumber("Proximity Sensor", RobotMap.proxSensor.pidGet());
		
		CameraServer2832.getInstance().setSelectedCamera(InterfaceFlip.isFlipped ? 1 : 0);
		
		SmartDashboard.putData(Scheduler.getInstance());
	}
	
	//This method is invoked in the OI constructor to add one-time things (command buttons, etc.)
	public static void putOneTimeData()
	{
		SmartDashboard.putBoolean("Use Recorded Autonomous", false);
    	SmartDashboard.putData("Autonomous Command", new AutonomousCommand()); 
        SmartDashboard.putData("shoot", new Shoot());  
      	SmartDashboard.putData("Flip Motors", new InterfaceFlip());
      	SmartDashboard.putData("Move Forward", new MoveForward(5));
      	SmartDashboard.putData("Rotate Angle", new RotateAngle(90));
      	SmartDashboard.putData("GoToLevel 0", new GoToLevel(0));
		SmartDashboard.putData("GoToLevel 1", new GoToLevel(1));
		SmartDashboard.putData("GoToLevel 2", new GoToLevel(2));
		SmartDashboard.putData("GoToLevel 3", new GoToLevel(3));
		SmartDashboard.putData("ImagingTest", new ImagingTest());
	}
}
