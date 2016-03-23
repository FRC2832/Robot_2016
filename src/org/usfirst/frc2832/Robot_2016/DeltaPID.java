package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import nav6.frc.IMUAdvanced;

public class DeltaPID extends PIDController {

	private static double velP = 1;
	private static double velI = 0.01;
	private static double velD = 0;
	
	private static double posP = 1;
	private static double posI = 0.01;
	private static double posD = 0;
	
	private static enum PIDMode {VELOCITY, POSITION}
	
	private PIDMode pidMode;
	
	private static double prevControl = 0;
	private static double prevTime = System.currentTimeMillis();
	private static double prevError = 0;
	private static double prevYaw = 0;
	
	public DeltaPID(IMUAdvanced source, PIDOutput output) 
	{
		super(velP,velI,velD, 0, source, output);
		prevYaw=RobotMap.imu.getYaw();
	}
	
	@Override protected void calculate()
	{
		//calculate difference in time from last calculation
		double dT = 1/1000d * (System.currentTimeMillis() - prevTime);
		//set prevTime so that it works next loop
		prevTime = System.currentTimeMillis();
		
		//calculate delta (the actual PID calculation)
		double delta = 0;
		if(pidMode == PIDMode.VELOCITY)
		{
			delta = velI * dT * ((IMUAdvanced)m_pidInput).getYaw()-prevYaw + (velP*(getError()-prevError))/dT;
			prevYaw = ((IMUAdvanced)m_pidInput).getYaw();
		}	
		else if (pidMode == PIDMode.POSITION)
			delta = posI * dT * ((IMUAdvanced)m_pidInput).getYaw() + (posP*(getError()-prevError))/dT;
		
		//set prevError and prevControl for next loop
		prevError = getError();
		prevControl = prevControl + delta;
		
		//output calculated value
		m_pidOutput.pidWrite(prevControl);
	}
	public void toPositionMode()
	{
		pidMode = PIDMode.POSITION;
	}
	public void toVelocityMode()
	{
		pidMode = PIDMode.VELOCITY;
	}
}
