package org.usfirst.frc.team649.robot.commands;


import org.usfirst.frc.team649.robot.Robot;
import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CannonFire extends Command {
	
	Timer timer;
	Boolean done;
    public CannonFire() {
    	timer = new Timer();
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	done = false;
    	Robot.drive.driveFwdRot(0, 0);
    	Robot.cannon.liftCannon(0);
    	Robot.cannonShot.fireStuff(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timer.get() >= RobotMap.CannonArm.COMPRESSOR_TIME) {
    		done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.cannonShot.fireStuff(false);
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
