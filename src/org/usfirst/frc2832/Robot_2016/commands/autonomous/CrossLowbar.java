package org.usfirst.frc2832.Robot_2016.commands.autonomous;

import org.usfirst.frc2832.Robot_2016.Aimer.Levels;
import org.usfirst.frc2832.Robot_2016.RobotMap;
import org.usfirst.frc2832.Robot_2016.commands.GoToPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowbar extends CommandGroup{
	
	public CrossLowbar(){
		
		addSequential(new GoToPosition(Levels.GROUND.getSetpoint()));
		if(RobotMap.winchMotor.getEncPosition() < 0.9 * Levels.GROUND.getSetpoint()) //did the head actually lower?
			addSequential(new MoveForward(-6.5));
		else
			addSequential(new MoveForward(-1.5)); //just go for the reach
		
	}

}
