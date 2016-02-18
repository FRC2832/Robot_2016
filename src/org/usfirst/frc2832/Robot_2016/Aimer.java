package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Aimer extends Subsystem {
	public static int currentLevel = 0;
	public static int[] levelPositions = {0,10000,20000,30000};
	public static final int MOVE_SPEED_UP = 75;
	public static String mode = "position";
	public static final int MOVE_SPEED_DOWN = -30;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public static void upSpeedMode()
	{
		RobotMap.winchMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		RobotMap.winchMotor.setPID(15, 0, 0);
		RobotMap.winchMotor.setAllowableClosedLoopErr(0);
	}
	public static void downSpeedMode()
	{
		RobotMap.winchMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
		RobotMap.winchMotor.setPID(.2, 0.02, 0);
		RobotMap.winchMotor.setAllowableClosedLoopErr(0);
	}
	public static void toPositionMode()
	{
		RobotMap.winchMotor.changeControlMode(CANTalon.TalonControlMode.Position);
		RobotMap.winchMotor.setPID(20, 0, 0);
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
