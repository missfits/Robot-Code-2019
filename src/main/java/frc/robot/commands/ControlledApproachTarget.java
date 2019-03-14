/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Vision.TargetSpot;

public class ControlledApproachTarget extends Command {
  public ControlledApproachTarget() {
    requires(Robot.driveTrain);
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
    double offset = Robot.vision.getOffset(TargetSpot.CENTER);
    double baseSpeed = (Robot.oi.leftStickY() + Robot.oi.rightStickY())/2;
    double speedVariation = baseSpeed  *(1+ 4*Math.abs(offset));
    if(offset < -0.02){
      //System.out.println("going right");
      Robot.driveTrain.tankDrive(speedVariation, baseSpeed);
      /*if(speedVariation > .5){
        speedVariation = .2;
        baseSpeed = .3;
      }*/
    }else if(offset > 0.02){
      //System.out.println("going left");
      Robot.driveTrain.tankDrive(baseSpeed, speedVariation);
    }else{
      //System.out.println("straight");
      Robot.driveTrain.tankDrive(baseSpeed, baseSpeed);
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
