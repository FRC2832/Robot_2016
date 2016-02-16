package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Kick used to expel the ball from the holder
 * @author Zach O.
 * 
 */

public class Kicker extends Subsystem{

	public static double LAUNCH_ANGLE = 0;
	public static double REST_ANGLE = 0.5;

	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	//push boulder out at a default angle
	public static void launch()
	{
		LAUNCH_ANGLE = Preferences.getInstance().getDouble("Kicker Launch Angle", 0);
		RobotMap.kicker.set(LAUNCH_ANGLE);
		
	}
	//return after using default launch method
	public static void resetAfterLaunch()
	{
		REST_ANGLE = Preferences.getInstance().getDouble("Kicker Rest Angle", .5);
		RobotMap.kicker.set(REST_ANGLE);
		
	}
	//reset back to position acquired in robotInit()
	public static void reset()
	{
		REST_ANGLE = Preferences.getInstance().getDouble("Kicker Rest Angle", .5);
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
