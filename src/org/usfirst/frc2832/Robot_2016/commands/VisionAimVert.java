package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Robot;
import org.usfirst.frc2832.Robot_2016.RobotMap;
import org.usfirst.frc2832.Robot_2016.vision.ImageProcessing;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionAimVert extends Command {

	@Override
	protected void initialize() {
		ImageProcessing.process();
		double winchPos = Robot.lookupTable.get(ImageProcessing.height);
		if(Math.abs(winchPos-RobotMap.winchMotor.getEncPosition())>30)
			Scheduler.getInstance().add(new GoToPosition((int)winchPos));
		SmartDashboard.putNumber("target enc val", -winchPos);
		SmartDashboard.putNumber("offset X", ImageProcessing.targetOffset[0]);
		SmartDashboard.putNumber("offset Y", ImageProcessing.targetOffset[1]);
		SmartDashboard.putNumber("target depth", ImageProcessing.depth);
		SmartDashboard.putNumber("height", ImageProcessing.height);
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
