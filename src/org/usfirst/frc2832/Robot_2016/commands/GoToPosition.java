package org.usfirst.frc2832.Robot_2016.commands;

import org.usfirst.frc2832.Robot_2016.Aimer;
import org.usfirst.frc2832.Robot_2016.DeltaPID;
import org.usfirst.frc2832.Robot_2016.RobotMap;
import org.usfirst.frc2832.Robot_2016.Aimer.Levels;
import org.usfirst.frc2832.Robot_2016.TrajectoryController;

import edu.wpi.first.wpilibj.command.Command;

//Will set Lenny to a position or fixed level. 
public class GoToPosition extends Command {

	int setPoint, initPoint;
	int isUp; //is 1 if we are going up, is -1 if we are going down
	
	double kminLowSpeed = 0.8, //constants to multiply by when setting up motion profile
	       kminHighSpeed = 1,
	       kmaxLowSpeed = 1.2,
	       kmaxHighSpeed = 1.8,
		   kSpeedUp = 2.5; //Raise speed when going up
	//DeltaPID changeLevel = new DeltaPID(RobotMap.winchMotor, RobotMap.winchMotor);
	TrajectoryController motionProfile;
	
	private static long timeStart;
	private static final long TIMEOUT = 4000;
	
	public GoToPosition(int encoderCount)
	{
		if(encoderCount < Levels.GROUND.getSetpoint())
			setPoint = Levels.GROUND.getSetpoint();
		else if(encoderCount > Levels.START.getSetpoint())
			setPoint = Levels.START.getSetpoint();
		else
			setPoint = encoderCount;
	}
	
	public GoToPosition(Levels level) 
	{
		setPoint = level.getSetpoint();
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Aimer.loadPreferences();
		timeStart = System.currentTimeMillis();
		//if(setPoint == Levels.START.getSetpoint() || setPoint == Levels.GROUND.getSetpoint())
			//Aimer.toChangingPositionMode();
		//else
		//	Aimer.toPositionMode();
		initPoint = RobotMap.winchMotor.getEncPosition();
		Aimer.sentinel = false;
		if(Math.abs(initPoint - Levels.START.getSetpoint()) < Aimer.TOLERANCE &&
		   Math.abs(setPoint - Levels.START.getSetpoint()) < Aimer.TOLERANCE)
					Aimer.sentinel = true;
				
		if(Math.abs(initPoint - Levels.GROUND.getSetpoint()) < Aimer.TOLERANCE &&
		   Math.abs(setPoint - Levels.GROUND.getSetpoint()) < Aimer.TOLERANCE)
					Aimer.sentinel = true;
		if(!Aimer.sentinel) //Then you have the green light to go ahead and move
		{
			if(setPoint > initPoint) //Which direction are we going?
			{
				Aimer.upSpeedMode();
				isUp = 1;
			}
			else
			{
				Aimer.downSpeedMode();
				isUp = -1;
			}
			
			if(setPoint == Levels.START.getSetpoint() || setPoint == Levels.GROUND.getSetpoint()) //use FAST MOTION PROFILE
				motionProfile = new TrajectoryController(Math.abs(setPoint - initPoint), 
				kminHighSpeed * Aimer.MOVE_SPEED_UP, kminHighSpeed * Aimer.MOVE_SPEED_UP, kmaxHighSpeed * Aimer.MOVE_SPEED_UP, 1.5, -1.5);
			else //USE SLOWER (MORE NORMAL) MOTION PROFILE
				motionProfile = new TrajectoryController(Math.abs(setPoint - initPoint), 
				kminLowSpeed * Aimer.MOVE_SPEED_UP, kminLowSpeed * Aimer.MOVE_SPEED_UP, kmaxLowSpeed * Aimer.MOVE_SPEED_UP, 0.9, -0.9);
			RobotMap.winchMotor.enableControl();
			if(isUp == 1)
				RobotMap.winchMotor.set(kSpeedUp * isUp * motionProfile.get(initPoint));
			else
				RobotMap.winchMotor.set(isUp * motionProfile.get(initPoint));
		}
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		RobotMap.winchMotor.set(isUp * motionProfile.get(Math.abs(RobotMap.winchMotor.getEncPosition() - initPoint)));
		
		if(Math.abs(RobotMap.winchMotor.getEncPosition() - Levels.START.getSetpoint()) < Aimer.TOLERANCE &&
		   Math.abs(setPoint - Levels.START.getSetpoint()) < Aimer.TOLERANCE)
			Aimer.sentinel = true;
		
		if(Math.abs(RobotMap.winchMotor.getEncPosition() - Levels.GROUND.getSetpoint()) < Aimer.TOLERANCE &&
		   Math.abs(setPoint - Levels.GROUND.getSetpoint()) < Aimer.TOLERANCE)
			Aimer.sentinel = true;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Math.abs(RobotMap.winchMotor.getEncPosition() - setPoint) < Aimer.TOLERANCE 
				|| Aimer.sentinel 
				|| TIMEOUT + timeStart < System.currentTimeMillis()
				|| RobotMap.winchMotor.getEncPosition()*isUp > setPoint*isUp;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Aimer.toPositionMode();
		RobotMap.winchMotor.set(setPoint);
		if(Aimer.sentinel)
			RobotMap.winchMotor.disableControl();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Aimer.toPositionMode();
		RobotMap.winchMotor.set(setPoint);
	}

}
