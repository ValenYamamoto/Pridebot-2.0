package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Victor [] motors;
	public static final double powerRatio = 0.97;
	public DoubleSolenoid driveSol;
	
	
	public Drivetrain() {

		
		motors = new Victor[4];
		//FR,BR,BL,BR
		for(int i =0; i < motors.length; i++) {
			motors[i] = new Victor(RobotMap.Drivetrain.MOTOR_PORTS	[i]);
		}
		driveSol  = new DoubleSolenoid(RobotMap.Drivetrain.DRIVE_SOL[0],
				RobotMap.Drivetrain.DRIVE_SOL[1]);
		
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveFwdRot(double fwd, double rot) {
		double left = fwd + rot, right = fwd - rot;
        double max = Math.max(1, Math.max(Math.abs(left), Math.abs(right)));
        left /= max;
        right /= max;
        rawDrive(left, right);
    }
	public void shift(Boolean highGear){
		driveSol.set(highGear ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
	}
	

    public void rawDrive(double left, double right) {
        motors[0].set(powerRatio * right);
        motors[1].set(powerRatio * right);
        motors[2].set(-left);
        motors[3].set(-left);
//        
//    
    }
}

