package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Robot;
import org.usfirst.frc2832.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveAimerDown extends Command {

	public MoveAimerDown() {
		requires (Robot.aimer);
	}
	protected void initialize() {
		// TODO Auto-generated method stub
		RobotMap.winchMotor.set(0.8);
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
		
	}

	@Override
	protected void interrupted() {
		RobotMap.winchMotor.set(0);

	}

}
