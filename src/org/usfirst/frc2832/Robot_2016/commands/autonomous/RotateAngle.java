package org.usfirst.frc2832.Robot_2016.commands.autonomous;

import org.usfirst.frc2832.Robot_2016.RobotMap;
import org.usfirst.frc2832.Robot_2016.TrajectoryController;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class RotateAngle extends Command {
	static double angle, initVal;
	//static final double TOLERANCE = 2;
	static TrajectoryController tc;
	private double isPos, curVal, curDisplacement = 0;
	//isPos: turns into a 1 is moving positive angle, -1 if moving negative angle
	//curVal: the current (NOT adjusted) value of our angle
	//curDisplacement: the adjustment factor due to gyro wraparound
	
	
	//IN DEGREES
    public RotateAngle(double theta) {
        // Use requires() here to declare subsystem dependencies
        // e.g. requires(chassis);
    	angle = theta;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initVal = RobotMap.imu.getYaw();
    	tc = new TrajectoryController(angle, 0.4, 0.4, 0.8, 0.9, -0.9); //TO-DO: would be nice to test these numbers!
    	isPos = Math.signum(angle);
    	curVal = initVal;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (curVal * isPos > RobotMap.imu.getYaw() * isPos) //This will be true iff the gyro val has just wrapped around
    		curDisplacement += 180*isPos;
    			
    	RobotMap.driveTrain.arcadeDrive(0, tc.get(curVal + curDisplacement));
    	
    	curVal = RobotMap.imu.getYaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (curVal + curDisplacement - initVal) * isPos > angle * isPos;
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
