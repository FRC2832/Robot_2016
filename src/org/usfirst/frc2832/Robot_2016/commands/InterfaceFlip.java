package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class InterfaceFlip extends Command {
	
	public static boolean isFlipped = false;

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		isFlipped = !isFlipped;
		RobotMap.frontLeftMotor.setInverted(isFlipped);
		RobotMap.frontRightMotor.setInverted(isFlipped);

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
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
