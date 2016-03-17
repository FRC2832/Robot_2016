package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Aimer;
import org.usfirst.frc2832.Robot_2016.Robot;
import org.usfirst.frc2832.Robot_2016.RobotMap;
import org.usfirst.frc2832.Robot_2016.Aimer.Levels;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveAimerUp extends Command {
	
	
	public MoveAimerUp() {
		requires (Robot.aimer);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Aimer.upSpeedMode();
		Aimer.sentinel = false;
		if(Math.abs(RobotMap.winchMotor.getEncPosition() - Levels.START.getSetpoint()) < Aimer.TOLERANCE)
			Aimer.sentinel = true;
		
		if(!Aimer.sentinel)
		{
			RobotMap.winchMotor.enableControl();
			RobotMap.winchMotor.set(Aimer.MOVE_SPEED_UP);
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
			RobotMap.winchMotor.disableControl();
	}

	@Override
	protected void interrupted() {

	}

}
