package org.usfirst.frc.team649.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final int OPERATOR_JOYSTICK = 0;
	
	public static class Drivetrain {
		public static final int[] MOTOR_PORTS = { 9, 8, 7, 6 };
		public static final int[] DRIVE_SOL = {4,5};
		public static final int COMPRESS_LIMIT_CAN = 9;
		public static final int SPIKE_RIGHT_PORT = 1; //right
		public static final int SPIKE_TOP_PORT = 2; // middle
		public static final int SPIKE_LEFT_PORT = 3; // left
		public static final int SPIKE_COMPRESSOR_PORT = 0;
	}
	
	public static class CannonArm {
		public static final int CANNON_MOTOR_PORT = 3;
		public static final double COMPRESSOR_TIME = 0.2;
		
	}

    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
