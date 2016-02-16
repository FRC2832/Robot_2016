package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Aimer;
import org.usfirst.frc2832.Robot_2016.Robot;
import org.usfirst.frc2832.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveAimerUp extends Command {
	
	public MoveAimerUp() {
		requires (Robot.aimer);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Aimer.upSpeedMode();
		RobotMap.winchMotor.set(Aimer.MOVE_SPEED);

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

	}

}
