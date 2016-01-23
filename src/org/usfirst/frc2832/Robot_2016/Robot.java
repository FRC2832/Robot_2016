// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by RobotBuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2832.Robot_2016;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.usfirst.frc2832.Robot_2016.HID.GamepadState;
import org.usfirst.frc2832.Robot_2016.HID.SavedStates;
import org.usfirst.frc2832.Robot_2016.HID.VirtualGamepad;
import org.usfirst.frc2832.Robot_2016.commands.AutonomousCommand;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    
    private boolean recording = false, playing = false;
    private VirtualGamepad vg;

    public static OI oi;
    public static BallMotors ballMotors;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    //GITHUB TESSTTTTTTT
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands 
        //(which it very likely will), subsystems are not guaranteed to be 
        // constructed yet. Thus, their requires() statements may grab null 
        // pointers. Bad news. Don't move it.
        oi = new OI();
        ballMotors = new BallMotors();
        System.out.println("GITHUB TEST");
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	// Recordable Autonomous
    	updateRecordStatus();
    	
    	GamepadState state = requestGamepadState();
    	
    	handleInput(state);
    	
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    /**
     * Handles smart dashboard input for recording
     * @author Brendan
     */
    private void updateRecordStatus() {
    	// Update recording status
    	if (SmartDashboard.getBoolean("Record Autonomous", false)) {
			if (!recording) {
				SavedStates.startRecording();
				recording = true;
			}
		} else {
			// Stop recording
			if (recording) {
				SavedStates.stopRecording();
				
				recording = false;
			}
		}
    	
    	// Update save status
    	if (SmartDashboard.getBoolean("Save Autonomous", false)) {
    		SmartDashboard.putBoolean("Save Autonomous", false);
    		
    		try {
				SavedStates.save(SmartDashboard.getString("RecordableAutonomous ID"));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	// Update load status
    	if (SmartDashboard.getBoolean("Load Autonomous", false)) {
    		SmartDashboard.putBoolean("Load Autonomous", false);
    		
    		loadAuton(SmartDashboard.getString("RecordableAutonomous ID"));
    	}
    	// Play if requested
    	if (SmartDashboard.getBoolean("Play Loaded Autonomous", false)) {
    		SmartDashboard.putBoolean("Play Loaded Autonomous", false);
    		
    		playLoadedAuton();
    	}
	}
    
    /**
     * A wrapper for getting the current state of the gamepad.  It handles recordable autonomous, so this can be used
     * without any actual input from the driver's USB gamepad assuming proper load procedures are followed.
     * @return A GamepadState object with the gamepad
     * @author Brendan
     */
    private GamepadState requestGamepadState() {
    	GamepadState realState = GamepadState.makeState(oi.gamepad);
    	if (recording)
    		SavedStates.record(realState);
    	
    	// Play recordable autonomous until done if playing
    	if (playing) {
    		if (!vg.isDone())
    			return (vg.getCurrentState());
    		playing = false;
    	}
    	
    	return (realState);

    }
    /**
     * Load an autonomous file to memory
     * @param name The name, or identifier, of the file (no extension)
     */
    private void loadAuton(String name) {
    	try {
			vg = new VirtualGamepad(SavedStates.load(name));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Play a loaded autonomous file. Fails silently if none has been loaded.
     * @see Robot.loadAuton
     */
    private void playLoadedAuton() {
    	if (vg != null && !playing) {
    		playing = true;
    		vg.start();
    	}
    }
    
    private void handleInput(GamepadState gs) {
    	RobotMap.driveTrain.arcadeDrive(
    			gs.axes[GamepadState.AXIS_LY], 
    			gs.axes[GamepadState.AXIS_RX]);
    }
}
