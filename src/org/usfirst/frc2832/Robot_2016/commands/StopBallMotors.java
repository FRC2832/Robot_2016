package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.BallMotors;
import org.usfirst.frc2832.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Stops the intake/expel motors from running.
 */
public class StopBallMotors extends Command {

	public StopBallMotors()
	{

		requires(Robot.ballMotors);
	}
	
	protected void initialize() {
		//stop the motors
		BallMotors.stopMotors();
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {

	}

	protected void interrupted() {

	}

}
