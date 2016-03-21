package org.usfirst.frc2832.Robot_2016.commands;

import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command {

	public double finishTime = 0;
	
	public void delay(double Time) {
		finishTime = System.currentTimeMillis() + Time;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if (System.currentTimeMillis() == finishTime) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
