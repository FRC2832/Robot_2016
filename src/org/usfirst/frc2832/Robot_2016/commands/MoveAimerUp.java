package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Robot;
import org.usfirst.frc2832.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveAimerUp extends Command {
	
	public MoveAimerUp() {
		requires (Robot.aimer);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		SmartDashboard.putNumber("goto",RobotMap.winchMotor.getEncPosition()-100);
		RobotMap.winchMotor.set(RobotMap.winchMotor.getEncPosition()-100);

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
