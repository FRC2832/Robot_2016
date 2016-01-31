package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Aimer;
import org.usfirst.frc2832.Robot_2016.Robot;
import org.usfirst.frc2832.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GoToLevel extends Command {
	private final int SLOW_DOWN_POINT = 6300;
	private final int SLOW_DOWN_MORE_POINT = 2100;
	private final int MIN_SPEED = 300;
	private final int MAX_SPEED = 500;
	private final int DIST_THRESHOLD = 500;
	private int level;
	private int direction;
	public GoToLevel (int level) {
		this.level = level;
		requires (Robot.aimer);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		double speed = MAX_SPEED;
		double position = RobotMap.winchMotor.getEncPosition();
		SmartDashboard.putNumber("GoToLevelIfStatement", 0);
		
		//When the position is a certain distance away from the level (SLOW_DOWN_POINT) it decelerates from MAX_SPEED 
		//When it reaches a closer distance (SLOW_DOWN_MORE_POINT) it goes to MIN_SPEED.
		
		if (Math.abs(Aimer.levelPositions[level] - position) < SLOW_DOWN_MORE_POINT)
		{
			speed = MIN_SPEED;
			SmartDashboard.putNumber("GoToLevelIfStatement", 1);
		}
		else if (Math.abs(Aimer.levelPositions[level] - position) <  SLOW_DOWN_POINT)
		{
			position = Math.abs(Aimer.levelPositions[level] - position) - SLOW_DOWN_POINT;
			position = position / (SLOW_DOWN_MORE_POINT - SLOW_DOWN_POINT);
			speed = (1-position) * MAX_SPEED + position * MIN_SPEED;
			SmartDashboard.putNumber("GoToLevelIfStatement", 2);
		}
		else 
			SmartDashboard.putNumber("GoToLevelIfStatement", 0);
		
		RobotMap.winchMotor.set(direction * speed);
		
	}

	@Override
	protected boolean isFinished() {
		if (Math.abs(Aimer.levelPositions[level]- RobotMap.winchMotor.getEncPosition())>DIST_THRESHOLD)
			return false;
		return true;
	}

	@Override
	protected void end() {
		//turn off winch
		RobotMap.winchMotor.set(0);
	}

	@Override
	protected void interrupted() {
		RobotMap.winchMotor.set(0);
	}

}
