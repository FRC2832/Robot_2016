package org.usfirst.frc2832.Robot_2016.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc2832.Robot_2016.BallMotors;
import org.usfirst.frc2832.Robot_2016.Kicker;
import org.usfirst.frc2832.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * Pushes out the ball assuming the ball motors are already running
 * Turns it off after timeout.
 * NOTE: The timeout isn't really a "timeout", it's just time between launching and resetting
 * This is because the servo is stable, it needs no tolerances, relative measurements, or timeouts
 */
public class ShootHighGoal extends Command {

	private long timeStart;
	//length of timeout, in milliseconds
	private static final long TIMEOUT = 1500;
	private static double startAngle;
	private static final double ANGLE_TOLERANCE = 0.05; //how many degrees it wants to return within
	private static double  DELAY = 1000; //milliseconds that the ball motors move for before the kicker kicks in
	
	public ShootHighGoal()
	{
		//TODO: what does this require?
	}
	
	
	protected void initialize() {
		
		//DELAY = Preferences.getInstance().getDouble("Shooter Delay (Seconds)", 1);//this does not display
		BallMotors.expel(0.3);
		//record time of command start
		timeStart = System.currentTimeMillis();
		
	
	}

	@Override
	protected void execute() {
		//Kicker.resetAfterLaunch();
		if ((timeStart + DELAY) < System.currentTimeMillis())
			Kicker.launch();
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
