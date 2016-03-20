package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Aimer extends Subsystem {
	public static int currentLevel = 0;
	public static boolean sentinel = false; //if true the TALON should be disabled
	public static final int TOLERANCE = 200; //in encoder counts
	public static enum Levels {
		START (0), 
		HIGH (-240), 
		LOW (-1700), 
		GROUND (-3200); // -1600 originally, this way we can quickly recalibrate
		
		private final int POSITION;
		Levels(int pos) {
			POSITION = pos;
		}
		
		public int getSetpoint() {return POSITION;}
	}
	//public static int[] levelPositions = {0,2000,5000,5500};
	//Level 0: 'Stowed' Position; Starting Position
	//Level 1: Shooting on Batter in front of Tower
	//Level 2: Low Goal, or 'just above the bottom'
	//Level 3: On the ground
	
	//public static final int MOVE_SPEED_UP = 200;
	public static String mode = "position";
	//public static final int MOVE_SPEED_DOWN = -175;
	public static double UP_PID_P, UP_PID_I, UP_PID_D,
							   DOWN_PID_P, DOWN_PID_I, DOWN_PID_D;
	public static int MOVE_SPEED_UP, MOVE_SPEED_DOWN;
			
	
	public static void loadPreferences() {
		UP_PID_P = Preferences.getInstance().getDouble("Aimer Up kP", 1.0);
		UP_PID_I = Preferences.getInstance().getDouble("Aimer Up kI", 0.001);
		UP_PID_D = Preferences.getInstance().getDouble("Aimer Up kD", 0.0);
		DOWN_PID_P = Preferences.getInstance().getDouble("Aimer Down kP", 0.5);
		DOWN_PID_I = Preferences.getInstance().getDouble("Aimer Down kI", 0.02);
		DOWN_PID_D = Preferences.getInstance().getDouble("Aimer Down kD", 0.0);
		
		MOVE_SPEED_UP = Preferences.getInstance().getInt("Aimer Up Speed", 300);
		MOVE_SPEED_DOWN = Preferences.getInstance().getInt("Aimer Down Speed",-175);
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
		RobotMap.winchMotor.setPID(1.6, 0.01, 0);
		RobotMap.winchMotor.setAllowableClosedLoopErr(0);
	}
	//to be used for snapping to START or GROUND positions
	public static void toProfiledVelocityMode()
	{
		RobotMap.winchMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		RobotMap.winchMotor.setPID(1, 0.1, 0);
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
