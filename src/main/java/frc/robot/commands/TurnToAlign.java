/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.BetterCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Vision.TargetSpot;

public class TurnToAlign extends BetterCommand {
  public TurnToAlign() {
    requires(Robot.driveTrain);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void betterExecute() {
    if(Robot.vision.getOffset(TargetSpot.CENTER) > 0){
      Robot.driveTrain.tankDrive(-0.3,0.3);
    }else{
      Robot.driveTrain.tankDrive(0.3, -0.3);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.vision.getOffset(TargetSpot.CENTER))<= 0.02;
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
