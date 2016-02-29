package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.RobotMap;
import org.usfirst.frc2832.Robot_2016.vision.ImageProcessing;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToImage extends Command {

	private static final double IMG_CENTER = 0;
	private static final double MIN_OFFSET = .05;
    public TurnToImage() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	ImageProcessing.process();
		RobotMap.driveTrain.arcadeDrive(0, (ImageProcessing.targetOffset[0]-IMG_CENTER)*8);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(ImageProcessing.targetOffset[0]-IMG_CENTER)<MIN_OFFSET;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
