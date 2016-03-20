package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.RobotMap;
import org.usfirst.frc2832.Robot_2016.commands.autonomous.RotateAngle;
import org.usfirst.frc2832.Robot_2016.vision.ImageProcessing;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//DEBUG USE ONLY
//DO NOT USE FOR ANY LEGITIMATE PROCESS
//THANKS :)
public class ImagingTest extends Command {

	private static final int FOV_ANGLE=22;
	@Override
	protected void initialize() {
		ImageProcessing.process();
		Scheduler.getInstance().add(new RotateAngle(-ImageProcessing.targetOffset[0]*FOV_ANGLE));
		SmartDashboard.putNumber("angle", -ImageProcessing.targetOffset[0]*FOV_ANGLE);
		SmartDashboard.putNumber("offset X", ImageProcessing.targetOffset[0]);
		SmartDashboard.putNumber("offset Y", ImageProcessing.targetOffset[1]);
		SmartDashboard.putNumber("target depth", ImageProcessing.depth);
		SmartDashboard.putNumber("height", ImageProcessing.height);
	}

	@Override
	protected void execute() {
	}
	private double rotate(double offset)
	{
		if(offset>0.5)
			return -0.5;
		if(offset<-0.5)
			return 0.5;
		if(offset>0)
			return -0.6*offset-0.2;
		if(offset<0)
			return -0.6*offset+0.2;
		return 0;
	}		
		
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;//Math.abs(ImageProcessing.targetOffset[0])<0.05;
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
