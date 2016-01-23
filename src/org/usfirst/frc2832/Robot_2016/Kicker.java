<<<<<<< HEAD

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
=======
package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Kicker extends Subsystem {

	static double defaultLaunchAngle = 90;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	//push boulder out at a default angle
	public static void launch()
	{
		double startAngle = RobotMap.kicker.getAngle();
		RobotMap.kicker.setAngle(defaultLaunchAngle + startAngle);
	}
	//return after using default launch method
	public static void resetAfterLaunch()
	{
		double currentAngle = RobotMap.kicker.getAngle();
		RobotMap.kicker.setAngle(currentAngle - defaultLaunchAngle);
	}
	//reset back to position acquired in robotInit()
	public static void reset()
	{
		RobotMap.kicker.setAngle(Robot.defaultAngle);
	}
	
	//accessor
	public static double getAngle() {return RobotMap.kicker.getAngle();}
	//accessor
	public static void setAngle(double angle) {RobotMap.kicker.setAngle(angle);}
	//simple method to move some angle
	public static void moveAngle(double angle)
	{
		RobotMap.kicker.setAngle(RobotMap.kicker.getAngle() + angle);
	}

}
>>>>>>> branch 'master' of https://github.com/FRC2832/Robot_2016.git
