/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class KeepIntakeTilted extends Command {
  double holdPosition;
  public KeepIntakeTilted() {
    requires(Robot.intake);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    holdPosition = Robot.intake.getTiltPosition();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double currentPosition = Robot.intake.getTiltPosition();
    if(currentPosition <= holdPosition - 10){
      Robot.intake.tiltOut(0.2);
    }else if(currentPosition >= holdPosition + 10){
      Robot.intake.tiltIn(0.2);
    }else{
      Robot.elevator.elevate(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.intake.stopTilt();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
