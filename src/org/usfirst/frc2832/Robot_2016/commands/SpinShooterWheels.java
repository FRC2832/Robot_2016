package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.BallMotors;
import org.usfirst.frc2832.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpinShooterWheels extends Command {
	private static final int SPEED = 1;

	@Override
	protected void initialize() {
		BallMotors.expel(SPEED);
		Robot.isSpinning = true;

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
	
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.isSpinning = false;
	}

	@Override
	protected void interrupted() {
		BallMotors.stopMotors();
		Robot.isSpinning = false;
	}

}
