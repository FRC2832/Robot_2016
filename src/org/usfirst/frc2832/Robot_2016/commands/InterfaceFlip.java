package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
/**
 * Controls whether the control interface is flipped when the driver wants to
 * drive the robot in reverse
 * @author Andrew
 *
 */
public class InterfaceFlip extends Command {
	
	public static boolean isFlipped = true;

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		isFlipped = !isFlipped;
		
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
