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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.command.Command;

public class ApproachTarget extends BetterCommand {
  double targetDistance;
  double startingDistance;
  TargetSpot target;
  public ApproachTarget(TargetSpot t) {
    requires(Robot.driveTrain);
    target = t;
    targetDistance = t==TargetSpot.CENTER? 18 : 48;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
   }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Starting Approach Target");
    Robot.vision.setVisionMode(true);
    startingDistance = Robot.vision.getDistance() - targetDistance;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void betterExecute() {
    double offset = Robot.vision.getOffset(target);
    System.out.println("Offset: " + offset);
    double distanceMultiplier = (Robot.vision.getDistance() - targetDistance) > 50? 1 : (Robot.vision.getDistance() - targetDistance)/50;
    //SmartDashboard.putNumber("Distance Multiplier",distanceMultiplier);
    double baseSpeed = (0.3*distanceMultiplier) >= 0.15 && distanceMultiplier > 0? 0.3*distanceMultiplier : 0.15;
    double speedVariation = baseSpeed  *(1+ 4*Math.abs(Robot.vision.getOffset(target)));
    SmartDashboard.putNumber("Base Speed",baseSpeed);
    SmartDashboard.putNumber("Speed Variation",speedVariation);
    System.out.println("Base Speed: " + baseSpeed + " Speed Variation: " + speedVariation);
    //positive offset = steer left
  
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
      if(speedVariation > .5){
        speedVariation = .3;
        baseSpeed = .2;
      }
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
