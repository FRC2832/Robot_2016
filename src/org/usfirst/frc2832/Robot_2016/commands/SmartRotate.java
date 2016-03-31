package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartRotate extends Command {

	private static double MAX_OUTPUT=0.8;
	private static double MIN_OUTPUT=0.5;
	private static double MAX_INPUT=30;
	private static double MIN_INPUT=1;
	private static final int TIMEOUT = 5000;

	private double theta;
	private boolean finished;
	private int zeroCount;
	
    public SmartRotate(double angle) {
        theta = angle;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	finished = false;
    	RobotMap.imu.zeroYaw();
    	zeroCount=0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.driveTrain.arcadeDrive(0,-f(RobotMap.imu.getYaw()-theta));
    }
    private double f(double angle)
    {
    	SmartDashboard.putNumber("angle_smart", angle);
    	double sign = Math.signum(angle);
    	angle = Math.abs(angle);
    	if(angle>MAX_INPUT)
    	{
    		zeroCount=0;
    		return sign*MAX_OUTPUT;
    	}
    	if(angle>MIN_INPUT)
    	{
    		zeroCount=0;
    		return sign*(MIN_OUTPUT+(MAX_OUTPUT-MIN_OUTPUT)/(MAX_INPUT-MIN_INPUT)*(angle-MIN_INPUT));
    	}
    	zeroCount++;
    	return 0;
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return zeroCount>10;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
