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
	public Relay spikeTop, spikeLeft, spikeRight, compressorSpike;
	public Compressor compressorCAN;
	
	
	public CannonShot() {
		spikeTop = new Relay(RobotMap.Drivetrain.SPIKE_TOP_PORT);
		spikeLeft = new Relay(RobotMap.Drivetrain.SPIKE_LEFT_PORT);
		spikeRight = new Relay(RobotMap.Drivetrain.SPIKE_RIGHT_PORT);
		compressorSpike = new Relay(RobotMap.Drivetrain.SPIKE_COMPRESSOR_PORT);
		compressorCAN = new Compressor(RobotMap.Drivetrain.COMPRESS_LIMIT_CAN);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void compressorSwitch(boolean on) {
    	if (on) {
    		compressorSpike.set(Relay.Value.kForward);
    	} else {
    		compressorSpike.set(Relay.Value.kOff);
    	}
    }
    public void fireStuffTop(boolean ready) {
    	if (ready) {
    		spikeTop.set(Relay.Value.kForward);
    	} else {
    		spikeTop.set(Relay.Value.kOff);
    	}
    }
    
    public void fireStuffRight(boolean ready) {
    	if (ready) {
    		spikeRight.set(Relay.Value.kForward);
    	} else {
    		spikeRight.set(Relay.Value.kOff);
    	}
    }
    
    public void fireStuffLeft(boolean ready) {
    	if (ready) {
    		spikeLeft.set(Relay.Value.kForward);
    	} else {
    		spikeLeft.set(Relay.Value.kOff);
    	}
    }
    
    public boolean checkFireStateTop() {
    	if (spikeTop.get() == Relay.Value.kForward) {
    		return true;
    	}
    	return false;
    }
    
    public boolean checkFireStateLeft() {
    	if (spikeLeft.get() == Relay.Value.kForward) {
    		return true;
    	}
    	return false;
    }

    public boolean checkFireStateRight() {
    	if (spikeRight.get() == Relay.Value.kForward) {
    		return true;
    	}
    	return false;
    }

}

