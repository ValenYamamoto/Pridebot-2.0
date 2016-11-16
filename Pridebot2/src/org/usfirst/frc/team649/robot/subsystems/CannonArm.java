package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CannonArm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Victor CannonArm;
	
	public CannonArm() {
		CannonArm = new Victor(RobotMap.CannonArm.CANNON_MOTOR_PORT);
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void liftCannon(double speed) {
    	CannonArm.set(speed);
    }
}

