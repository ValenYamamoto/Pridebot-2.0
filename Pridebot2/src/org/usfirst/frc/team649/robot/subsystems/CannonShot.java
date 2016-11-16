package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CannonShot extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Relay spike;
	public Compressor compressorCAN;
	
	
	public CannonShot() {
		spike = new Relay(RobotMap.Drivetrain.SPIKE_PORT);
		compressorCAN = new Compressor(RobotMap.Drivetrain.COMPRESS_LIMIT_CAN);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void compressorSwitch() {
    	if (compressorCAN.getPressureSwitchValue()) {
    		spike.set(Relay.Value.kOff);
    	} else {
    		spike.set(Relay.Value.kOn);
    	}
    }
    public void fireStuff(boolean ready) {
    	if (ready) {
    		spike.set(Relay.Value.kOn);
    	} else {
    		spike.set(Relay.Value.kOff);
    	}
    }
}

