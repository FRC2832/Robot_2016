package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CANTalonLoggable extends CANTalon {

	/**
	 * A thread that logs rs this motor's current.
	 * It DOES NOT DISABLE the motor temporarily if the current goes too high.
	 * @author Brendan
	 *
	 */
	private class LogThread extends Thread {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				SmartDashboard.putNumber("CAN ID #" + getDeviceID(), getOutputCurrent());
			}
		}
	}
	private Thread th;
	
	public CANTalonLoggable(int deviceNumber) {
		super(deviceNumber);
		th = new LogThread();
		th.start();
	}

	@Override
	public String getSmartDashboardType() {
		return null;
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		th.interrupt();
	}
}
