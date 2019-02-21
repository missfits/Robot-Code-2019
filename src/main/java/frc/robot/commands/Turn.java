/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command {
    private double angle;
    public Turn(double a) {
        angle = a;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
       }
    
 // Called just before this Command runs the first time
 @Override
 protected void initialize() {
   
 }

 // Called repeatedly when this Command is scheduled to run
 @Override
 protected void execute() {
    if(angle > 0){
        Robot.driveTrain.tankDrive(1, -1);
    } else {
        Robot.driveTrain.tankDrive(-1, 1);
    }
    
 } 

 // Make this return true when this Command no longer needs to run execute()

 @Override
 protected boolean isFinished() { 
  return Math.abs(Robot.driveTrain.getAngle()) >= Math.abs(angle);
  }



 // Called once after isFinished returns true
 @Override
 protected void end() {
     Robot.driveTrain.driveStraight(0);
   }

 // Called when another command which requires one or more of the same
 // subsystems is scheduled to run
 @Override
 protected void interrupted() {
    end();
 }
}
