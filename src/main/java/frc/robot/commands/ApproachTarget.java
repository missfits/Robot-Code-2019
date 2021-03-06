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

public class ApproachTarget extends Command {
  double targetDistance = 18;
  public ApproachTarget() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
   }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.vision.setVisionMode(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double offset = Robot.vision.getOffset();
    //System.out.println("Offset: " + offset);
    //positive offset = steer left
    if(offset < -0.02){
      //System.out.println("going right");
      Robot.driveTrain.tankDrive(0.5*(1 + 4*Math.abs(Robot.vision.getOffset())), 0.5);
    }else if(offset > 0.02){
      //System.out.println("going left");
      Robot.driveTrain.tankDrive(0.5, 0.5*(1 + 4*Math.abs(Robot.vision.getOffset())));
    }else{
      //System.out.println("straight");
      Robot.driveTrain.tankDrive(.5, .5);
    }
  }

  // Make this return true when this Command no longer needs to run execute()

  @Override
  protected boolean isFinished() { 
    return Robot.vision.getDistance()<=targetDistance;
   }



  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.driveStraight(0);
    Robot.vision.setVisionMode(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
