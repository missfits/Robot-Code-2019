/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class Teleop extends Command {
  public Teleop() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveTrain.tankDrive(Robot.oi.leftStickY(), Robot.oi.rightStickY());
    if(Math.abs(Robot.oi.xBoxLeftStickY()) > 0.2){
      System.out.println(Robot.oi.xBoxLeftStickY());
      Robot.elevator.elevate(Robot.oi.xBoxLeftStickY());
    }else{
      Robot.elevator.elevate(0);
    }
    //Robot.elevator.elevate(Robot.oi.xBoxLeftStickY());
    if(Math.abs(Robot.oi.xBoxRightStickY()) > 0.2){
      Robot.climber.climb(-1*Robot.oi.xBoxRightStickY());
    }else{
      Robot.climber.climb(0);
    }
    if(Robot.oi.leftTriggerPressed()){
      Robot.intake.wheelsIn();
    }else if(Robot.oi.rightTriggerPressed()){
      Robot.intake.wheelsOut();
    }else{
      Robot.intake.wheelsStop();
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}