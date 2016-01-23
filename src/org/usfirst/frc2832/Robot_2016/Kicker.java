package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Kick used to expel the ball from the holder
 * @author Zach O.
 * 
 */

public class Kicker extends Subsystem{

	private static final double LAUNCH_ANGLE = 1.0;
	private static final double REST_ANGLE = 0.0;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	//push boulder out at a default angle
	public static void launch()
	{
		RobotMap.kicker.set(LAUNCH_ANGLE);
	}
	//return after using default launch method
	public static void resetAfterLaunch()
	{
		RobotMap.kicker.set(REST_ANGLE);
	}
	//reset back to position acquired in robotInit()
	public static void reset()
	{
		RobotMap.kicker.set(REST_ANGLE);
	}
	
	//accessor
	public static double get() {
		return RobotMap.kicker.get();
		}
	//accessor
	public static void set(double angle) {
		RobotMap.kicker.set(angle);
		}
	//simple method to move some angle
	public static void moveToPos(double pos){
		RobotMap.kicker.set(pos);
	}

}
