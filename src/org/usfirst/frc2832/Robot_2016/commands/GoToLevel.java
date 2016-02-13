package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Aimer;
import org.usfirst.frc2832.Robot_2016.Robot;
import org.usfirst.frc2832.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GoToLevel extends Command {
	private int SLOW_DOWN_POINT;
	private int SLOW_DOWN_MORE_POINT;
	private int MIN_SPEED;
	private int MAX_SPEED;
	private final int DIST_THRESHOLD = 500;
	private int level;
	private int direction;
	public GoToLevel (int level) {
		this.level = level;
		requires (Robot.aimer);
		if(Aimer.levelPositions[level]>RobotMap.winchMotor.getEncPosition())
			direction = 1;
		else
			direction = -1;
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {

		SLOW_DOWN_POINT = Preferences.getInstance().getInt("Slow Down Aimer Distance", 6300);
		SLOW_DOWN_MORE_POINT = Preferences.getInstance().getInt("Slow Down More Aimer Distance", 2100);
		MIN_SPEED = Preferences.getInstance().getInt("Min Aimer Speed", 300);
		MAX_SPEED = Preferences.getInstance().getInt("Max Speed", 1000); // You must change this one in the code, it doesn't work in Preferences
		
		
		
		double speed = MAX_SPEED;
		double position = RobotMap.winchMotor.getEncPosition();
		//When the position is a certain distance away from the level (SLOW_DOWN_POINT) it decelerates from MAX_SPEED 
		//When it reaches a closer distance (SLOW_DOWN_MORE_POINT) it goes to MIN_SPEED.
		
		if (Math.abs(Aimer.levelPositions[level] - position) < SLOW_DOWN_MORE_POINT)
		{
			speed = MIN_SPEED;
		}
		else if (Math.abs(Aimer.levelPositions[level] - position) <  SLOW_DOWN_POINT)
		{
			position = Math.abs(Aimer.levelPositions[level] - position) - SLOW_DOWN_POINT;
			position = position / (SLOW_DOWN_MORE_POINT - SLOW_DOWN_POINT);
			speed = (1-position) * MAX_SPEED + position * MIN_SPEED;
		}
		
		
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
