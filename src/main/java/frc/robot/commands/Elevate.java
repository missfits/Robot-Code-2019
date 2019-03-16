/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator.Height;

public class Elevate extends Command {
  boolean goingUp;
  double targetPosition;
  
  public Elevate(Height h) {
    switch(h){
      case GROUND:
        targetPosition = 0;
        break;
      case BOTTOM_BALL:
        targetPosition = 1;
        break;
      case BOTTOM_HATCH:
        targetPosition = 2;
        break;
      case MIDDLE_BALL:
        targetPosition = 3;
        break;
      case MIDDLE_HATCH:
        targetPosition = 4;
        break;
      case TOP_BALL:
        targetPosition = 5;
        break;
      case TOP_HATCH:
        targetPosition = 6;
        break;
      case START_CLIMB:
        targetPosition = 7;
        break;
    }
    requires(Robot.elevator);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    goingUp = Robot.elevator.getPosition() < targetPosition;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.elevator.elevate(goingUp? 0.5 : -0.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return goingUp? Robot.elevator.getPosition() >= targetPosition: Robot.elevator.getPosition() <= targetPosition;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
   Robot.elevator.elevate(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
