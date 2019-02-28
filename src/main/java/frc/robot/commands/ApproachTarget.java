/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.BetterCommand;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.command.Command;

public class ApproachTarget extends BetterCommand {
  double targetDistance = 18;
  public ApproachTarget() {
    requires(Robot.driveTrain);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
   }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Starting Approach Target");
    Robot.vision.setVisionMode(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void betterExecute() {
    double offset = Robot.vision.getOffset();
    System.out.println("Offset: " + offset);
    double distanceMultiplier = Robot.vision.getDistance() > targetDistance? (Robot.vision.getDistance() - targetDistance)/50 : 0;
    SmartDashboard.putNumber("Distance Multiplier",distanceMultiplier);
    double baseSpeed = (0.4*distanceMultiplier) >= 0.125 && distanceMultiplier > 0? 0.4*distanceMultiplier : 0.125;
    //positive offset = steer left
    if(offset < -0.02){
      //System.out.println("going right");
      Robot.driveTrain.tankDrive(baseSpeed*(1 + 4*Math.abs(Robot.vision.getOffset())),baseSpeed);
    }else if(offset > 0.02){
      //System.out.println("going left");
      Robot.driveTrain.tankDrive(baseSpeed,baseSpeed*(1 + 4*Math.abs(Robot.vision.getOffset())));
    }else{
      //System.out.println("straight");
      Robot.driveTrain.tankDrive(baseSpeed, baseSpeed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()

  @Override
  protected boolean isFinished() { 
    SmartDashboard.putNumber("Checking Dist", Robot.vision.getDistance());
    return Robot.vision.getDistance()<=targetDistance || Robot.vision.getContourNumber() < 2;
   }



  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putNumber("Done Driving",Robot.vision.getDistance());
    System.out.println("Done Approaching Target  due to " + (Robot.vision.getContourNumber() < 2? "contours" : "distance"));
    Robot.driveTrain.driveStraight(0);
    //Robot.vision.setVisionMode(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.driveTrain.driveStraight(0);
  }
}
