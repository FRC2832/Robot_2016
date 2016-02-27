package org.usfirst.frc2832.Robot_2016;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;

public class CANTalonCurrentSafety extends CANTalon {

	private class MonitorThread extends Thread {
		@Override
		public void run() {
			while (true) {
				if (getOutputCurrent() > 25) {
					disableControl();
				}else{
					enableControl();
				}
				Timer.delay(0.1);
			}
		}
	}
	public CANTalonCurrentSafety(int deviceNumber) {
		super(deviceNumber);
		(new MonitorThread()).start();
	}

	@Override
	public String getSmartDashboardType() {
		return null;
	}

}
