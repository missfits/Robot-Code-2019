/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeTilt extends Command {
  boolean goingForward;
  double targetPosition;
  public enum TiltPosition {
    CLIMBING, HATCH_PICKUP, BALL_PICKUP, BALL_SHOOT_POSITION
  }
  //Positions: UP(hatch pickup), 45 degrees or less (ball pickup), a little higher than 45 degrees (ball carrying/shooting) DOWN further than horizontal (climbing)
  public IntakeTilt(TiltPosition p) {
    switch(p){
      case CLIMBING:
        targetPosition = 1;
        break;
      case HATCH_PICKUP:
        targetPosition = 2;
        break;
      case BALL_PICKUP:
        targetPosition = 3;
        break;
      case BALL_SHOOT_POSITION:
        targetPosition = 4;
        break;
    }

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    goingForward = Robot.intake.getTiltPosition() < targetPosition;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(goingForward){
      Robot.intake.tiltOut();
    }else{
      Robot.intake.tiltIn();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return goingForward? Robot.elevator.getPosition() >= targetPosition: Robot.elevator.getPosition() <= targetPosition;
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
