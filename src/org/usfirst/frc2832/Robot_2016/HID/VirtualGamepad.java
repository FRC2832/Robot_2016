package org.usfirst.frc2832.Robot_2016.HID;

import java.util.ArrayList;

public class VirtualGamepad {
	private ArrayList<GamepadState> states;
	
	// Millisecond timings
	long startTime, recordedOffset;
	int lastIndex = 0;
	
	public VirtualGamepad(ArrayList<GamepadState> states) {
		this.states = states;
	}
	
	public void start() {
		startTime = System.currentTimeMillis();
		recordedOffset = states.get(0).timestamp;
	}
	
	public GamepadState getCurrentState() {
		long time = System.currentTimeMillis();
		
		// Interpolate the times, using the last recorded gamepad state.
		while (states.get(lastIndex).timestamp - recordedOffset <= time - startTime)
			lastIndex++;
		lastIndex--;
		// lastIndex now has the latest recorded data from the gamepad at this current time.
		
		return states.get(lastIndex);
	}
}
