package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.ImageProcessing;
import org.usfirst.frc2832.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
//DEBUG USE ONLY
//DO NOT USE FOR ANY LEGITIMATE PROCESS
//THANKS :)
public class ImagingTest extends Command {

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		ImageProcessing.process();
		RobotMap.driveTrain.arcadeDrive(0, ImageProcessing.targetOffset[0]);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
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
