package org.usfirst.frc2832.Robot_2016.commands.autonomous;

import org.usfirst.frc2832.Robot_2016.commands.GoToLevel;
import org.usfirst.frc2832.Robot_2016.commands.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

//run as spy bot by assuming starting at 45 degree angle. moves forward, might shoot, and reach pos 2 defense
public class SpyBot extends CommandGroup {
	
	public SpyBot(int shooting)
	{
		addSequential(new MoveForward(2));
		if(shooting == 1 || shooting == 2)
			addSequential(new RotateAngle(-90));
		if(shooting == 1)
			addSequential(new GoToLevel(2));
		else if(shooting == 2)
			addSequential(new GoToLevel(1));
		if(shooting == 1 || shooting == 2)
		{
			addSequential(new Shoot());
			addSequential(new GoToLevel(0));
			addSequential(new RotateAngle(135));
		} else 
			addSequential(new RotateAngle(45));
		addSequential(new MoveForward(6));
		
		
			
		
	}
}
