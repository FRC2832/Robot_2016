package org.usfirst.frc2832.Robot_2016.commands.autonomous;

import org.usfirst.frc2832.Robot_2016.DriveEncoders;
import org.usfirst.frc2832.Robot_2016.RobotMap;
import org.usfirst.frc2832.Robot_2016.TrajectoryController;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateAngle extends Command {
	static double angle, initVal;
	static final double TOLERANCE = 2;
	static TrajectoryController tc;
	
	//IN DEGREES
    public RotateAngle(double theta) {
        // Use requires() here to declare subsystem dependencies
        // e.g. requires(chassis);
    	angle = theta;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initVal = RobotMap.imu.getYaw();
    	tc = new TrajectoryController(angle, 0.3, 0.4, 0.5, 1, -1); //TO-DO: would be nice to test these numbers!
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.driveTrain.arcadeDrive(0, tc.get(RobotMap.imu.getYaw())); 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(RobotMap.imu.getYaw() - initVal - angle) < TOLERANCE;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveTrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
