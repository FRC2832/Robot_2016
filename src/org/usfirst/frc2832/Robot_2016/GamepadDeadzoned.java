package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.Joystick;

public class GamepadDeadzoned extends Joystick {
	
	private double range = 0.15;
	
	private int[] leftstickAxis = {0, 1};
	private int[] rightstickAxis = {4, 5};
	
	public GamepadDeadzoned(int port) {
		super(port);
	}

	private double deadzone(double in) {
		return (Math.abs(in) < range ? 0 : in);
	}
	
	private boolean arrContains(int[] arr, int x) {
		for(int a : arr) {
			if (a == x)
				return (true);
		}
		
		return (false);
	}
	
	
	/**
	 * Use getRawAxis with your axis ID instead of this method
	 * 0 and 1 for left stick
	 * 2 and 3 for triggers
	 * 4 and 5 for right stick
	 */
	@Deprecated
	@Override
	public double getAxis(AxisType axis) {
		return (getRawAxis(axis.value));
	}
	
	/**
	 * Radial deadzoning helper method
	 * @param x
	 * @param y
	 * @return
	 */
	private Coordinate deadzone2D(double x, double y) {
		double magnitude = Math.sqrt(x * x + y * y);
		if (magnitude <= range)
			return new Coordinate(0,0);
		double scale = (magnitude - range) / (1 - range);
		return (new Coordinate(x / magnitude * scale, y / magnitude * scale));
	}
	
	@Override
	public double getRawAxis(int axis) {
		if (arrContains(leftstickAxis, axis)) {
			Coordinate p = (deadzone2D(
					super.getRawAxis(leftstickAxis[0]), 
					super.getRawAxis(leftstickAxis[1])));
			if (leftstickAxis[0] == axis)
				return (p.x);
			else
				return (p.y);
		}
		if (arrContains(rightstickAxis, axis)) {
			Coordinate p = (deadzone2D(
					super.getRawAxis(rightstickAxis[0]), 
					super.getRawAxis(rightstickAxis[1])));
			if (rightstickAxis[0] == axis)
				return (p.x);
			else
				return (p.y);
		}
		
		return (deadzone(super.getRawAxis(axis)));
	}
	
	public double getDebugAxis(int axis) {
		return super.getRawAxis(axis);
	}
}
