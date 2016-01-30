package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.BallMotors;
import org.usfirst.frc2832.Robot_2016.Kicker;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Pushes out the ball assuming the ball motors are already running
 * Turns it off after timeout.
 * NOTE: The timeout isn't really a "timeout", it's just time between launching and resetting
 * This is because the servo is stable, it needs no tolerances, relative measurements, or timeouts
 */
public class Shoot extends Command {

	private long timeStart;
	//length of timeout, in milliseconds
	private static final long TIMEOUT = 1000;
	
	public Shoot()
	{
		//TODO: what does this require?
	}
	
	
	protected void initialize() {
		Kicker.launch();
		BallMotors.expel();
		//record time of command start
		timeStart = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		//Kicker.resetAfterLaunch();
	}

	@Override
	protected boolean isFinished() {
		//returns true only when timeStart + TIMEOUT < current time
		return ((timeStart + TIMEOUT) < System.currentTimeMillis());
	}

	@Override
	protected void end() {
		Kicker.resetAfterLaunch();
		BallMotors.stopMotors();
	}

	@Override
	protected void interrupted() {
		//Kicker.reset(); //fail-safe
		BallMotors.stopMotors();
	}

}
