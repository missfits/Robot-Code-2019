/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.BetterCommand;
import frc.robot.Robot;

public class IntakeTilt extends BetterCommand {
  boolean goingForward;
  double targetPosition, positionOffset;
  public enum TiltPosition {
    CLIMBING, HATCH_PICKUP, BALL_PICKUP, BALL_SHOOT_POSITION
  }
  //Positions: UP(hatch pickup), 45 degrees or less (ball pickup), a little higher than 45 degrees (ball carrying/shooting) DOWN further than horizontal (climbing)
  public IntakeTilt(TiltPosition p) {
    switch(p){
      case CLIMBING:
        targetPosition = 715;
        break;
      case HATCH_PICKUP:
        targetPosition = 85;
        break;
      case BALL_PICKUP:
        targetPosition = 420;
        break;
      case BALL_SHOOT_POSITION:
        targetPosition = 300;
        break;
    }

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    positionOffset = Robot.cargoIntake.getTiltPosition() - targetPosition;
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void betterExecute() {
    if(positionOffset < 0){
      Robot.cargoIntake.tiltOut(Math.abs(positionOffset)/400);
    }else{
      Robot.cargoIntake.tiltIn(Math.abs(positionOffset)/400);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double currentPosition = Robot.cargoIntake.getTiltPosition();
    return goingForward? (currentPosition >= targetPosition - 10 && currentPosition <= targetPosition + 10) : (currentPosition <= targetPosition + 10 && currentPosition >= targetPosition - 10);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.cargoIntake.stopTilt();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
