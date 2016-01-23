package org.usfirst.frc2832.Robot_2016.HID;

import java.util.ArrayList;

public class VirtualGamepad {
	private ArrayList<GamepadState> states;
	private boolean done;
	
	
	// Millisecond timings
	long startTime, recordedOffset;
	int lastIndex = 0;
	
	public VirtualGamepad(ArrayList<GamepadState> states) {
		this.states = states;
	}
	
	public void start() {
		startTime = System.currentTimeMillis();
		recordedOffset = states.get(0).timestamp;
		
		done = false;
		lastIndex = 0;
	}
	
	public boolean isDone() {
		return (done);
	}
	
	public GamepadState getCurrentState() {
		long time = System.currentTimeMillis();
		
		// Interpolate the times, using the last recorded gamepad state.
		while (lastIndex < states.size()) {
			if (states.get(lastIndex).timestamp - recordedOffset <= time - startTime)
				lastIndex++;
		}
		
		if (lastIndex >= states.size()) {
			done = true;
			return (null);
		}
		
		// We overshot in that interpolation stage, that was the first time it "failed"
		lastIndex--;
		
		// lastIndex now has the latest recorded data from the gamepad at this current time.
		return (states.get(lastIndex));
	}
}
