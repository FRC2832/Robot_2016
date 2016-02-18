package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.BallMotors;
import org.usfirst.frc2832.Robot_2016.Robot;
import org.usfirst.frc2832.Robot_2016.HID.GamepadState;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Runs the intake on the ball motor system.
 * Turns it off after timeout.
 * TODO: Add limit switch/sensor when it is decided on.
 */
public class Intake extends Command {

	private long timeStart;
	//length of timeout, in milliseconds
	private static final long TIMEOUT = 200;
	
	public Intake()
	{
		//this command is interrupted if another intake/expel command is run
		requires(Robot.ballMotors);
	}
	
	
	protected void initialize() {
		//intake motors
		Robot.sendEvent(new byte[]{4});
		BallMotors.intake(Robot.oi.gamepad.getRawAxis(GamepadState.AXIS_LT));
		//record time of command start
		timeStart = System.currentTimeMillis();
	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		//returns true only when timeStart + TIMEOUT < current time
		return (timeStart + TIMEOUT) < System.currentTimeMillis();
	}

	@Override
	protected void end() {
		//turn off motors when done.
		Robot.sendEvent(new byte[]{0});
		BallMotors.stopMotors();
		if (Robot.isAuton) {
			Robot.gameMode = 0;
		}
		else {
			Robot.gameMode = 1;
		}
	}

	@Override
	protected void interrupted() {
		Robot.sendEvent(new byte[]{0});
		if (Robot.isAuton) {
			Robot.gameMode = 0;
		}
		else {
			Robot.gameMode = 1;
		}
	}

}
