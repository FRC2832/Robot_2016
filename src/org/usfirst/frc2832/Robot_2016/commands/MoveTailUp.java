package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Robot;
import org.usfirst.frc2832.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveTailUp extends Command {
	public static final double SPEED = .25;

	public MoveTailUp() {
	}
	@Override
	protected void initialize() {

		RobotMap.tail.enableControl();
		RobotMap.tail.set(SPEED);
	
		

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished()
	{
		return RobotMap.tail.isFwdLimitSwitchClosed() || !Robot.povPressed;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		RobotMap.tail.set(0);
		RobotMap.tail.disableControl();
	}

	@Override
	protected void interrupted() {
		RobotMap.tail.set(0);
		RobotMap.tail.disableControl();
	}

}

