package org.usfirst.frc2832.Robot_2016.commands.autonomous;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;

//helper class to read the SendableChooser inputs and eventually start autonomous
public class ParseInput {

	static ArrayList<Command> auto_Commands;
	
	public static ArrayList<Command> takeInput(String movement, boolean isRev, int shooting)
	{
		auto_Commands = new ArrayList<Command>(0);
		if(movement.charAt(0) == 'f')
			auto_Commands.add(new MoveForward(Double.valueOf(movement.substring(1))));
		else if(movement.charAt(0) == 'r')
			auto_Commands.add(new RotateAngle(Double.valueOf(movement.substring(1))));
		else if(movement.charAt(0) == 's')
			auto_Commands.add(new SpyBot(shooting));
		
		if(isRev == true && movement.charAt(0) == 'f')
			auto_Commands.add(new MoveForward(-1*Double.valueOf(movement.substring(1))));
		
			
		return auto_Commands;
	}
	
}
