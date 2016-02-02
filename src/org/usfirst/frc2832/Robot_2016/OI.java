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

import java.io.IOException;

import org.usfirst.frc2832.Robot_2016.HID.RecordableGamepad;
import org.usfirst.frc2832.Robot_2016.HID.SavedStates;
import org.usfirst.frc2832.Robot_2016.commands.AutonomousCommand;
import org.usfirst.frc2832.Robot_2016.commands.GoToLevel;
import org.usfirst.frc2832.Robot_2016.commands.InterfaceFlip;
import org.usfirst.frc2832.Robot_2016.commands.MoveAimerDown;
import org.usfirst.frc2832.Robot_2016.commands.MoveAimerUp;
import org.usfirst.frc2832.Robot_2016.commands.Shoot;
import org.usfirst.frc2832.Robot_2016.commands.autonomous.MoveForward;
import org.usfirst.frc2832.Robot_2016.commands.autonomous.RotateAngle;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    
	
	public RecordableGamepad gamepad;
	public JoystickButton aButton;
	public JoystickButton bButton;
	public JoystickButton xButton;
	public JoystickButton yButton;
	public JoystickButton leftBumper;
	public JoystickButton rightBumper;
	
	public SendableChooser index;
	
    public OI() {
    	
    	//GamePad Settings
    	gamepad = new RecordableGamepad(0);
	    
    	aButton = new JoystickButton(gamepad,1);
	    aButton.whenPressed(new InterfaceFlip());
	    
	    bButton = new JoystickButton(gamepad,2);
	    bButton.whileHeld(null);
	    
	    xButton = new JoystickButton(gamepad,3);
	    xButton.whenPressed(null);
	    
	    yButton = new JoystickButton(gamepad,4);
	    yButton.whenPressed(null);
	    
	    leftBumper = new JoystickButton(gamepad,5);
	    leftBumper.whileHeld(new MoveAimerDown());
	    
	    rightBumper = new JoystickButton(gamepad,6);
	    rightBumper.whileHeld(new MoveAimerUp()  );
	    
	    RecordableGamepad.dashboardSetup();
	    
	    createRecordedAutonIndex();
    	
		
    	
		DashboardOutput.putOneTimeData(); //this method contains all the buttons for commands

    }

	private void createRecordedAutonIndex() {		
		
		index = new SendableChooser();
		
		try {
			SavedStates.loadIndex();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String key : SavedStates.getIndex())
			index.addObject(key, key);
        
		SmartDashboard.putData("Recorded Selection", index);
	}
    
}

