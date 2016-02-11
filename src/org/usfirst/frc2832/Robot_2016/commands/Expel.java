package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.BallMotors;
import org.usfirst.frc2832.Robot_2016.Kicker;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Expel extends Command {
	
//so this is literally the cut-and-pasted shoot command
//Except minus the time delay and it allows you to set the speed
	
	private long timeStart;
	//length of timeout, in milliseconds
	private static final long TIMEOUT = 1000;
	private static double startAngle;
	private static final double ANGLE_TOLERANCE = 0.05; //how many degrees it wants to return within
	private static final double SPEED = 0.3; //sets the speed the ball expels at
	
	


	public Expel() {
		// TODO Auto-generated constructor stub
	}



	protected void initialize() {
		
		
		BallMotors.expel(SPEED);
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
