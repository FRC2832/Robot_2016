package org.usfirst.frc2832.Robot_2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionShoot extends CommandGroup {
    
    public  VisionShoot() {
        addSequential(new VisionAimHoriz(),10);
        //addSequential(new VisionAimVert(),10);
        addSequential(new Shoot());
    }
}
