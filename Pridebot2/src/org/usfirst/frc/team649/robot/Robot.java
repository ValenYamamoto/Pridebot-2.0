
package org.usfirst.frc.team649.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team649.robot.commands.LeftCannonFire;
import org.usfirst.frc.team649.robot.commands.RightCannonFire;
import org.usfirst.frc.team649.robot.commands.RunCompressor;
import org.usfirst.frc.team649.robot.commands.TopCannonFire;
import org.usfirst.frc.team649.robot.subsystems.CannonArm;
import org.usfirst.frc.team649.robot.subsystems.CannonShot;
import org.usfirst.frc.team649.robot.subsystems.Drivetrain;
import org.usfirst.frc.team649.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static Drivetrain drive;
	public static CannonArm cannon;
	public static CannonShot cannonShot;
	public static boolean compressor_prev_state = false;
	public static boolean testState = false;
	public Timer timer, topTimer, leftTimer, rightTimer;
	TopCannonFire cannonFire;
	OI oi;

	
    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		drive = new Drivetrain();
    	cannon = new CannonArm();
    	cannonShot = new CannonShot();
    	timer = new Timer();
    	topTimer = new Timer();
    	leftTimer = new Timer();
    	rightTimer = new Timer();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        timer.start();
        topTimer.start();
        leftTimer.start();
        rightTimer.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    	
    	if (testState) {
    		if(oi.driver.getSafety()) {
    			if(oi.driver.getTopShot()) {
    				cannonShot.fireStuffTop(true);
    			} else {
    				cannonShot.fireStuffTop(false);
    			}
    			if(oi.driver.getLeftShot()) {
    				cannonShot.fireStuffLeft(true);
    			} else {
    				cannonShot.fireStuffLeft(false);
    			}
    			if(oi.driver.getRightShot()) {
    				cannonShot.fireStuffRight(true);
    			} else {
    				cannonShot.fireStuffRight(false);
    			}
    		}
    		
    	} else  {
    		if(oi.driver.getSafety()) {
    			if(oi.driver.getTopShot() && topTimer.get() > 0.5) {
    				new TopCannonFire().start();
    				SmartDashboard.putBoolean("Top", true);
    				topTimer.reset();
    			}
    			if(oi.driver.getRightShot() && rightTimer.get() > 0.5) {
    				new RightCannonFire().start();
    				SmartDashboard.putBoolean("Right", true);
    				rightTimer.reset();
    			}
    			if(oi.driver.getLeftShot() && leftTimer.get() > 0.5) {
    				new LeftCannonFire().start();
    				SmartDashboard.putBoolean("Left", true);
    				leftTimer.reset();
    			}
    		}
    	}
    
    	if (oi.driver.compressor()) {
    		if (timer.get() > 0.5) {
    			new RunCompressor().start();
    			timer.reset();
    		}
    	}
    	if (oi.driver.isShift()){
    		drive.shift(true);
    	}else{
    		drive.shift(false);
    	}
//    	if(oi.driver.shiftDown()){
//    		drive.shift(false);
//    	}
//    	if(oi.driver.shiftUp()) {
//    		drive.shift(true);
//    	}
    	drive.driveFwdRot(oi.driver.getForward(), oi.driver.getRotation());
    	cannon.liftCannon(oi.driver.liftArm());
    	
    	SmartDashboard.putBoolean("Get Safety", oi.driver.getSafety());
    	SmartDashboard.putBoolean("Get Right", oi.driver.getRightShot());
    	SmartDashboard.putBoolean("Get left", oi.driver.getLeftShot());
    	SmartDashboard.putBoolean("Get top", oi.driver.getTopShot());
    	SmartDashboard.putBoolean("Compressor", oi.driver.compressor());
    	SmartDashboard.putNumber("Lift Arm", oi.driver.liftArm());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
