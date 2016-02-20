package org.usfirst.frc2832.Robot_2016.HID;

import java.io.IOException;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The ultimate in gamepad handling; combines both GamepadDeadzoned and VirtualGamepad
 * into a seamless, encapsulated system.
 * @author Brendan
 *
 */
public class RecordableGamepad extends GamepadDeadzoned {

	private VirtualGamepad vg;
	
	private boolean recording = false;
	
	public enum Mode {
		VIRTUAL, REAL
	}
	private Mode mode = Mode.REAL;
	public Mode getMode() {
		return mode;
	}
	
	public RecordableGamepad(int port) {
		super(port);
	}
	
	public static void dashboardSetup() {
		// RecordableAuton controls
    	SmartDashboard.putBoolean("Record Autonomous", false);
    	SmartDashboard.putBoolean("Save Autonomous", false);
    	SmartDashboard.putBoolean("Load Autonomous", false);
    	SmartDashboard.putString("RecordableAutonomous ID", "Name file etc");
    	SmartDashboard.putBoolean("Play Loaded Autonomous", false);
	}
	
	public void saveRecordedGamepad(String name) {
		try {
			SavedStates.save(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadVirtualGamepad(String name) {
		ArrayList<GamepadState> states = null;
		
		try {
			states = SavedStates.load(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (states == null)
			return;
		
		vg = new VirtualGamepad(states);
	}
	public void startVirtualGamepad() {
		if (vg != null) {
			vg.start();
			mode = Mode.VIRTUAL;
		}
	}
	/**
	 * FULL STOP, CAPTAIN
	 * This method returns all control to the driver.
	 */
	public void cancelPlayback() {
		mode = Mode.REAL;
		vg = null;
	}
	
	/**
     * Handles recording
     * @author Brendan
     */
    public void updateRecordStatus() {
    	// Update recording status
    	if (SmartDashboard.getBoolean("Record Autonomous", false)) {
			if (!recording)
				startRecording();
		} else if (recording)
			stopRecording();
    	
    	// Update save status
    	if (SmartDashboard.getBoolean("Save Autonomous", false)) {
    		SmartDashboard.putBoolean("Save Autonomous", false);
    		
    		saveRecordedGamepad(SmartDashboard.getString("RecordableAutonomous ID"));
    	}
    	// Update load status
    	if (SmartDashboard.getBoolean("Load Autonomous", false)) {
    		SmartDashboard.putBoolean("Load Autonomous", false);
    		
    		loadVirtualGamepad(SmartDashboard.getString("RecordableAutonomous ID"));
    	}
    	// Play if requested
    	if (SmartDashboard.getBoolean("Play Loaded Autonomous", false)) {
    		SmartDashboard.putBoolean("Play Loaded Autonomous", false);
    		
    		startVirtualGamepad();
    	}
    	
    	if (recording && mode == Mode.REAL)
    		SavedStates.record(GamepadState.makeState(this));
	}
	public void startRecording() {
		recording = SavedStates.startRecording();
	}
	public void stopRecording() {
		if (recording) {
			SavedStates.stopRecording();
			
			recording = false;
		}
	}
    
	// please don't use these, unimplemented because we don't use them on a gamepad
	@Deprecated
	@Override
	public double getX(Hand hand) {
		return 0;
	}
	@Deprecated
	@Override
	public double getY(Hand hand) {
		return 0;
	}
	@Deprecated
	@Override
	public double getZ(Hand hand) {
		return 0;
	}
	@Deprecated
	@Override
	public double getTwist() {
		return 0;
	}
	@Deprecated
	@Override
	public double getThrottle() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Deprecated
	@Override
	public boolean getTrigger(Hand hand) {
		return false;
	}
	@Deprecated
	@Override
	public boolean getTop(Hand hand) {
		return false;
	}
	@Deprecated
	@Override
	public boolean getBumper(Hand hand) {
		return false;
	}
	
	@Override
	public double getRawAxis(int axis) {
		if (mode == Mode.REAL)
			return super.getRawAxis(axis);
		else if (mode == Mode.VIRTUAL) {
			GamepadState gs = vg.getCurrentState();
			// If done or something was done improperly, default to normal control
			if (vg.isDone() || gs == null)
				mode = Mode.REAL;
			else
				return gs.axes[axis];
		}
		
		return 0;
	}


	@Override
	public boolean getRawButton(int button) {
		if (mode == Mode.REAL)
			return super.getRawButton(button);
		else if (mode == Mode.VIRTUAL) {
			GamepadState gs = vg.getCurrentState();
			// If done or something was done improperly, default to normal control
			if (vg.isDone() || gs == null)
				mode = Mode.REAL;
			else
				return gs.buttons[button-1];
		}
		
		return false;
	}

	@Override
	public int getPOV(int pov) {
		if (mode == Mode.REAL)
			return super.getPOV(pov);
		else if (mode == Mode.VIRTUAL) {
			GamepadState gs = vg.getCurrentState();
			// If done or something was done improperly, default to normal control
			if (vg.isDone() || gs == null)
				mode = Mode.REAL;
			else
				return gs.pov;
		}
		
		return -1;
	}
	
	

}
