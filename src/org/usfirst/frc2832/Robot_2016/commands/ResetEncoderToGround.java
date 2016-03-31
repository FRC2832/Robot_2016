package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.RobotMap;
import org.usfirst.frc2832.Robot_2016.Aimer.Levels;

import edu.wpi.first.wpilibj.command.Command;

/**
 *This is a failsafe in case something goes wrong. i.e. if the limit switch is triggered while the head is down and the encoder is zeroed.
 *When this command runs it will set the head back to assuming it's on the ground
 *This will NOT work if the limit switch is permanently triggered (i.e. the switch is broken)
 *An example is if a rogue ball lands on our robot and flips the switch while the head is down, this command needs to be run or else we are screwed
 */
public class ResetEncoderToGround extends Command {

    public ResetEncoderToGround() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.winchMotor.setEncPosition(Levels.GROUND.getSetpoint());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
