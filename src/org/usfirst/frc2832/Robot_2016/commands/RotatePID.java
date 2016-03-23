package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Robot;
import org.usfirst.frc2832.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotatePID extends Command {

	private double theta;
	private long startTime;
	private final double TIMEOUT = 2000;
	
    public RotatePID(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	theta=angle;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    	Robot.rotatePID.setSetpoint(theta);
    	Robot.rotatePID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(RobotMap.imu.getYaw() - theta)<1)||(System.currentTimeMillis()-startTime>TIMEOUT);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rotatePID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.rotatePID.disable();
    }
}
