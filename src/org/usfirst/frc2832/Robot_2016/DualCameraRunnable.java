package org.usfirst.frc2832.Robot_2016;

import org.usfirst.frc2832.Robot_2016.commands.InterfaceFlip;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

public class DualCameraRunnable implements Runnable {

	@Override
	public void run() {
		Image frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		while (true) {
			try {
				if ( InterfaceFlip.isFlipped ) {
				    Robot.camera2.getImage( frame );
				} else {
					Robot.camera1.getImage( frame );
				}
				
				Robot.cameraServer.setImage(frame);
				
				
				Thread.sleep(67);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
