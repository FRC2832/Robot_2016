package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Kicker;

import edu.wpi.first.wpilibj.command.Command;
//runs the kicker
public class Kick extends Command {


	public Kick() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		Kicker.launch();

	}

	@Override
	protected void execute() {
		//Kicker.launch();

	}

	@Override
	protected boolean isFinished() {
		return (Kicker.get() == Kicker.LAUNCH_ANGLE);
	}	




	@Override
	protected void end() {
		Kicker.resetAfterLaunch();
		
	}

	@Override
	protected void interrupted() {
		Kicker.reset();
		
	}
}