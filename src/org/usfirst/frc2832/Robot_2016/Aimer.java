package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Aimer extends Subsystem {
	public static int currentLevel = 0;
	public static int[] levelPositions = {0,3000,4500,5500};
	//public static final int MOVE_SPEED_UP = 200;
	public static String mode = "position";
	//public static final int MOVE_SPEED_DOWN = -175;
	public static double UP_PID_P, UP_PID_I, UP_PID_D,
							   DOWN_PID_P, DOWN_PID_I, DOWN_PID_D;
	public static int MOVE_SPEED_UP, MOVE_SPEED_DOWN;
			
	
	public static void loadPreferences() {
		UP_PID_P = Preferences.getInstance().getDouble("Aimer Up kP", 5.0);
		UP_PID_I = Preferences.getInstance().getDouble("Aimer Up kI", 0.001);
		UP_PID_D = Preferences.getInstance().getDouble("Aimer Up kD", 0.0);
		DOWN_PID_P = Preferences.getInstance().getDouble("Aimer Down kP", 2.0);
		DOWN_PID_I = Preferences.getInstance().getDouble("Aimer Down kI", 0.02);
		DOWN_PID_D = Preferences.getInstance().getDouble("Aimer Down kD", 0.0);
		
		MOVE_SPEED_UP = Preferences.getInstance().getInt("Aimer Up Speed", 200);
		MOVE_SPEED_DOWN = Preferences.getInstance().getInt("Aimer Down Speed", -175);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public static void upSpeedMode()
	{
		RobotMap.winchMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		loadPreferences();
		RobotMap.winchMotor.setPID(UP_PID_P, UP_PID_I, UP_PID_D);
		RobotMap.winchMotor.setAllowableClosedLoopErr(0);
	}
	public static void downSpeedMode()
	{
		RobotMap.winchMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		loadPreferences();
		RobotMap.winchMotor.setPID(DOWN_PID_P, DOWN_PID_I, DOWN_PID_D);
		RobotMap.winchMotor.setAllowableClosedLoopErr(0);
	}
	public static void toPositionMode()
	{
		RobotMap.winchMotor.changeControlMode(CANTalon.TalonControlMode.Position);
		RobotMap.winchMotor.setPID(2, 0, 0);
		RobotMap.winchMotor.setAllowableClosedLoopErr(0);
	}
	/**
	 * Position mode ONLY
	 * "freezes" the winch in its current position
	 */
	public static void stop()
	{
		toPositionMode();
		RobotMap.winchMotor.set(RobotMap.winchMotor.getEncPosition());
	}
}
