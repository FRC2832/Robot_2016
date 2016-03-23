package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.RobotMap;
import org.usfirst.frc2832.Robot_2016.commands.autonomous.RotateAngle;
import org.usfirst.frc2832.Robot_2016.vision.ImageProcessing;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//DEBUG USE ONLY
//DO NOT USE FOR ANY LEGITIMATE PROCESS
//THANKS :)
public class VisionAimHoriz extends CommandGroup {

	private static final int FOV_ANGLE=22;
	
	public VisionAimHoriz() {
		ImageProcessing.process();
		double angle = -ImageProcessing.targetOffset[0]*FOV_ANGLE;
		if(Math.abs(angle)>1.5)
			addSequential(new RotateAngle(angle));
		
		addSequential(new RotatePID(angle));
		SmartDashboard.putNumber("angle", angle);
		SmartDashboard.putNumber("offset X", ImageProcessing.targetOffset[0]);
		SmartDashboard.putNumber("offset Y", ImageProcessing.targetOffset[1]);
		SmartDashboard.putNumber("target depth", ImageProcessing.depth);
		SmartDashboard.putNumber("height", ImageProcessing.height);
	}

}
