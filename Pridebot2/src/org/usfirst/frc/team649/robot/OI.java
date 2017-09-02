package org.usfirst.frc.team649.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick operatorJoystick;
	public Driver driver;
	
	
	public OI() {
		operatorJoystick = new Joystick(RobotMap.OPERATOR_JOYSTICK);
		driver = new Driver();
	}
	
	public class Driver {

		public double getForward() {
			// TODO Auto-generated method stub
			if(operatorJoystick.getRawAxis(1) >= 0.05 || operatorJoystick.getRawAxis(1) <= -0.05){
				return operatorJoystick.getRawAxis(1);
			}else{
				return 0.0;
			}
			
		}
		public double getRotation() {
			if(operatorJoystick.getRawAxis(4) >= 0.05 || operatorJoystick.getRawAxis(4) <= -0.05){
				return -operatorJoystick.getRawAxis(4);
			}else{
				return 0.0;
			}
		}
		public Boolean getTopShot() {
			return operatorJoystick.getRawButton(4);
		}
		
		public Boolean getRightShot() {
			return operatorJoystick.getRawButton(2);
		}
		
		public Boolean getLeftShot() {
			return operatorJoystick.getRawButton(3);
		}
		
//		public Boolean shiftUp(){
//			return operatorJoystick.getRawButton(3);
//		}
//		public Boolean shiftDown(){
//			return operatorJoystick.getRawButton(1);
//		}
		public boolean getSafety() {
			if (operatorJoystick.getRawButton(5) && operatorJoystick.getRawButton(6)) {
				return true;
			}
			return false;
		}
		
		public boolean compressor() {
			return operatorJoystick.getRawButton(1);
		}
		public boolean isShift(){
			return operatorJoystick.getRawAxis(2) > 0.7 || operatorJoystick.getRawAxis(3) > 0.7;
		}
		
		public double liftArm() {
			//  6 = left 7 = right
			if(operatorJoystick.getPOV() == 0) {
				return 0.25;
			}
			else if (operatorJoystick.getPOV() == 180) {
				return -0.25;
			}
			return 0;
//			if (operatorJoystick.getRawAxis(2) >= operatorJoystick.getRawAxis(3) ){
//				if(operatorJoystick.getRawAxis(2) >= 0.05){
//					System.out.println("Down");
//					return -operatorJoystick.getRawAxis(2);
//				}else {
//					return 0.0;
//				}
//			}else{
//				if(operatorJoystick.getRawAxis(3) >= 0.05){
//					System.out.println("Up");
//					return operatorJoystick.getRawAxis(3);
//				}else {
//					return 0.0;
//				}
//			}
		}
	}
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

