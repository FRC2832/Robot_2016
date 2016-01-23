package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Kicker;
import edu.wpi.first.wpilibj.command.Command;

/*
 * Pushes out the ball assuming the ballmotors are already running
 * Turns it off after timeout.
 */
public class Shoot extends Command {

	private long timeStart;
	//length of timeout, in milliseconds
	private static final long TIMEOUT = 1000;
	private static double startAngle;
	private static final double ANGLE_TOLERANCE = 0.05; //how many degrees it wants to return within
	
	public Shoot()
	{
		//what does this require?
		
	}
	
	
	protected void initialize() {
		startAngle = Kicker.get();
		Kicker.launch();
		//record time of command start
		timeStart = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		//Kicker.resetAfterLaunch();
	}

	@Override
	protected boolean isFinished() {
		//returns true only when timeStart + TIMEOUT < current time OR when the angle is within the TOLERANCE
		return ((timeStart + TIMEOUT) < System.currentTimeMillis());
	}

	@Override
	protected void end() {
		//if it didn't reach the tolerance (used the time-out maybe) then do the hard reset
		//if(Math.abs(Kicker.getAngle() - startAngle) > ANGLE_TOLERANCE)
			//Kicker.reset();
		Kicker.resetAfterLaunch();
	}

	@Override
	protected void interrupted() {
		//Kicker.reset(); //fail-safe
	}

}
