package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Aimer;
import org.usfirst.frc2832.Robot_2016.Robot;
import org.usfirst.frc2832.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class GoToLevel extends Command {
	private final int DIST_THRESHOLD = 20;
	private int level;
	private int direction;
	public GoToLevel (int level){
		this.level = level;
		requires (Robot.aimer);
		if (RobotMap.winchMotor.getEncPosition() > Aimer.levelPositions[level])
		{
			direction = -1;
		}
		else
			direction = 1;
		
	}
	
	@Override
	protected void initialize() {
		//TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		RobotMap.winchMotor.set(direction*.8);
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if (Math.abs(Aimer.levelPositions[level]- RobotMap.winchMotor.getEncPosition())>DIST_THRESHOLD)
			return false;
		else 
			return true;
	}

	@Override
	protected void end() {
		//turn off winch
		RobotMap.winchMotor.set(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
