package org.usfirst.frc2832.Robot_2016.HID;

import java.io.Serializable;

import edu.wpi.first.wpilibj.Joystick;

/**
 * <p>
 * An immutable gamepad object.  Treat this as a gamepad "frozen in time".
 * This is to be used instead of the regular wpilib Joystick or GamepadDeadzoned
 * classes, for use with recordable autonomous.
 * </p>
 * @author Brendan
 *
 */
public class GamepadState implements Serializable {
	private static final long serialVersionUID = 9198171031103864921L;
	
	/**
	 * Indices for Xbox-based controllers
	 */
	public static final transient int
		BUTTON_A  = 0,
		BUTTON_B  = 1,
		BUTTON_X  = 2,
		BUTTON_Y  = 3,
		BUTTON_LB = 4,
		BUTTON_RB = 5,
		BUTTON_BACK = 6,
		BUTTON_START = 7,
		
		AXIS_RX = 0,
		AXIS_RY = 1,
		AXIS_LT = 2,
		AXIS_RT = 3,
		AXIS_LX = 4,
		AXIS_LY = 5;
	
	public final double axes[];
	public final boolean buttons[];
	public final int pov;
	
	/**
	 * Creates a new gamepad state based off of raw data. You probably don't want to use this, use makeState instead.
	 * @param axes
	 * @param buttons
	 * @param pov
	 * @see makeState
	 */
	public GamepadState(double axes[], boolean buttons[], int pov) {
		this.axes = axes.clone();
		this.buttons = buttons.clone();
		this.pov = pov;
	}
	
	/**
	 * Creates a new GamepadState based off of a gamepad's current state.
	 * @param j The gamepad object
	 * @return A gamepad state
	 */
	public static GamepadState makeState(Joystick j) {
		double[] axes = new double[j.getAxisCount()];
		for(int i = 0; i < axes.length; i ++)
			axes[i] = j.getRawAxis(i);
		
		boolean[] buttons = new boolean[j.getButtonCount()];
		for(int i = 0; i < buttons.length; i ++)
			buttons[i] = j.getRawButton(i + 1);
		
		return (new GamepadState(axes, buttons, j.getPOV()));
	}
}
