/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator.Height;

public class Elevate extends Command {
  boolean goingUp;
  double targetPosition;
  /*
  1231 -  bottom rocket
  -10985 - ground
  7621 - cargo ship
  - middle rocket

   */
  public Elevate(Height h) {
    switch(h){
      case GROUND:
        targetPosition = -4458;
        break;
      case CARGO_SHIP:
        targetPosition = 7621;
        break;
      case BOTTOM_ROCKET:
        targetPosition = 1231;
        break;
      case MIDDLE_ROCKET:
        targetPosition = 15737;
        break;
      case HOLDING_BALL:
        targetPosition = 0;
        break;
      case BALL_PICKUP:
        targetPosition = -1473;
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
    SmartDashboard.putBoolean("Elevating", true);
    double speed =  Math.abs(Robot.elevator.getPosition() - targetPosition)/1000;
    SmartDashboard.putNumber("Elevator Speed", speed);
    if (speed > 0.8){
      speed = 0.8;
    }else if(speed < 0.1){
      speed = 0.1;
    }
    Robot.elevator.elevate(goingUp? -speed : speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double currentPosition = Robot.elevator.getPosition();
    return currentPosition >= targetPosition - 50 && currentPosition <= targetPosition + 50;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putBoolean("Elevating", false);
   Robot.elevator.elevate(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
