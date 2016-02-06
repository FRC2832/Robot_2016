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

import org.usfirst.frc2832.Robot_2016.HID.GamepadState;
import org.usfirst.frc2832.Robot_2016.commands.Intake;
import org.usfirst.frc2832.Robot_2016.commands.InterfaceFlip;
import org.usfirst.frc2832.Robot_2016.commands.Shoot;
import org.usfirst.frc2832.Robot_2016.commands.autonomous.MoveForward;
import org.usfirst.frc2832.Robot_2016.commands.autonomous.RotateAngle;

import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    private static boolean recordedAuton = false;

	Command autonomousCommand;
    
    
    public static OI oi;
    public static BallMotors ballMotors = new BallMotors();
    public static Aimer aimer = new Aimer();
    public static double defaultAngle;
    public static USBCamera camera1, camera2;
    public boolean leftTriggerPressed = false;
    public boolean rightTriggerPressed = false;
    public boolean shooterNotActive = true;

	private String recordedID;
    public static SendableChooser autonomous;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
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
        try {
	        camera1 = new USBCamera("cam0");
	        camera2 = new USBCamera("cam1");
	        
	        camera1.setFPS(15);
	        camera1.setSize(320, 240);
	        
	        camera2.setFPS(15);
	        camera2.setSize(320, 240);
	        
	        CameraServer2832 cameraServer = CameraServer2832.getInstance();
	        cameraServer.startAutomaticCapture(camera1, camera2);
        } catch (VisionException e) {
        	e.printStackTrace();
        }

        autonomous = new SendableChooser();
        autonomous.addObject("Do nothing at all", null);
        autonomous.addObject("Rotate 45",  new RotateAngle(45));
        autonomous.addDefault("Move Forward 5", new MoveForward(5));
        SmartDashboard.putData("Autonomous Selection", Robot.autonomous);
        
        RobotMap.winchMotor.setEncPosition(0);
        
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	if (recordedAuton)
    		oi.gamepad.loadVirtualGamepad(recordedID);
    	RobotMap.winchMotor.setEncPosition(0);
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        
        recordedID = (String) (oi.index.getSelected());
        recordedAuton = SmartDashboard.getBoolean("Use Recorded Autonomous");
        
        DashboardOutput.putPeriodicData();
    }

    public void autonomousInit() {
    	
    	if (recordedAuton) {
    		oi.gamepad.startVirtualGamepad();
    	} else {
		    // schedule the autonomous command (example)
			autonomousCommand = (Command)autonomous.getSelected();
			autonomousCommand.start();
    	}
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	if (recordedAuton)
    		handleInput(oi.gamepad);
    	
        Scheduler.getInstance().run();
        DashboardOutput.putPeriodicData();//this is a method to contain all the "putNumber" crap we put to the Dashboard
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //if (autonomousCommand != null) autonomousCommand.cancel();
    
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	// Recordable Autonomous
    	oi.gamepad.updateRecordStatus();
    	
    	DashboardOutput.putPeriodicData();
    	
    	handleInput(oi.gamepad);
    	
        Scheduler.getInstance().run();
        
      //D-Pad Controls
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void handleInput(GenericHID g) {
    	RobotMap.driveTrain.arcadeDrive(
    			g.getRawAxis(GamepadState.AXIS_LY) * (InterfaceFlip.isFlipped ? 1 : -1), 
    			g.getRawAxis(GamepadState.AXIS_RX));
    	
    	//D-Pad Controls
        boolean povPressed = false;

        switch (oi.gamepad.getPOV()) {
		//D-pad right
		case 90:
			if (!povPressed) {
				Scheduler.getInstance().add(new InterfaceFlip());
			}
			povPressed = true;
			break;
		//D-pad left
		case 270:
			if (!povPressed) {
				Scheduler.getInstance().add(null);
			}
			povPressed = true;
			break;
		//D-pad up
		case 0:
			// Use speed mode if not currently used
			if (!povPressed) {
				Scheduler.getInstance().add(null);
			}
			povPressed = true;
			break;
		//D-pad down
		case 180:
			if (!povPressed) {
				Scheduler.getInstance().add(null);
			}
			povPressed = true;
			break;
		case -1:
		povPressed = false;
			break;
		}
        
       //left Trigger Code
        if (oi.gamepad.getRawAxis(GamepadState.AXIS_LT) >= .9) {
        	Scheduler.getInstance().add(new Intake());
        	
			leftTriggerPressed = true;
		} else {
			leftTriggerPressed = false;
		}
        //right Trigger Code
        if (oi.gamepad.getRawAxis(GamepadState.AXIS_RT) >= .9) {   	
        	Scheduler.getInstance().add(new Shoot());
        	
        	rightTriggerPressed = true;
		} else {
			rightTriggerPressed = false;
			shooterNotActive = true;
		}
        if (rightTriggerPressed && shooterNotActive) {
        	Scheduler.getInstance().add(new Shoot());
        	shooterNotActive = false;
        }
        
        
    }
}
