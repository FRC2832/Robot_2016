package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Kick used to expel the ball from the holder
 * @author Zach O.
 * 
 */
//values changed because of the kicker's new physical limits on the robot
public class Kicker extends Subsystem{

	public static double LAUNCH_ANGLE = .05;
	public static double REST_ANGLE = 0;
	public static double NEUTRAL_ANGLE = 0;

	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	//push boulder out at a default angle
	public static void launch()
	{
		LAUNCH_ANGLE = .15;//Preferences.getInstance().getDouble("Kicker Launch Angle", .25);
		RobotMap.kicker.set(LAUNCH_ANGLE);
		
	}
	//return after using default launch method
	public static void resetAfterLaunch()
	{
		REST_ANGLE = Preferences.getInstance().getDouble("Kicker Rest Angle", 0.05);
		RobotMap.kicker.set(0.05);
		
	}
	//reset back to position acquired in robotInit()
	public static void reset()
	{
		REST_ANGLE = Preferences.getInstance().getDouble("Kicker Rest Angle", 0.05);
		RobotMap.kicker.set(0.05);
		
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
