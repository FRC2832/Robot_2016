package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Aimer;
import org.usfirst.frc2832.Robot_2016.Robot;
import org.usfirst.frc2832.Robot_2016.RobotMap;
import org.usfirst.frc2832.Robot_2016.Aimer.Levels;

import edu.wpi.first.wpilibj.command.Command;

public class MoveAimerDown extends Command {

	
	public MoveAimerDown() {
		requires (Robot.aimer);
	}
	protected void initialize() {
		// TODO Auto-generated method stub
		Aimer.downSpeedMode();
		Aimer.sentinel = false;
		if(Math.abs(RobotMap.winchMotor.getEncPosition() - Levels.GROUND.getSetpoint()) < Aimer.TOLERANCE)
			Aimer.sentinel = true;
		
		if(!Aimer.sentinel)
		{
			RobotMap.winchMotor.enable();
			RobotMap.winchMotor.set(Aimer.MOVE_SPEED_DOWN);
		}

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
		if(Aimer.sentinel)
			RobotMap.winchMotor.disable();
	}

	@Override
	protected void interrupted() {

	}

}
