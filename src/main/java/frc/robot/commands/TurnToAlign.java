/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.BetterCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Vision.TargetSpot;

public class TurnToAlign extends BetterCommand {
  double startingOffset,deadband;
  public TurnToAlign() {
    requires(Robot.driveTrain);
    deadband = 0.02;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.vision.setVisionMode(true);
    startingOffset = Math.abs(Robot.vision.getOffset(TargetSpot.CENTER)) - deadband;
    SmartDashboard.putNumber("Starting Offset",startingOffset);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void betterExecute() {
    System.out.println("Offset: " + Robot.vision.getOffset(TargetSpot.CENTER));
    //double distFromTargetOffset = Math.abs(Robot.vision.getOffset(TargetSpot.CENTER)) - 0.03;
    //double offsetDiff = Robot.vision.getOffset(TargetSpot.CENTER) 
    double accelerationMultiplier = (Math.abs(Robot.vision.getOffset(TargetSpot.CENTER)) - deadband)/startingOffset;
    double speed =  0.1*accelerationMultiplier;
    SmartDashboard.putNumber("Turn Speed",speed);
    System.out.println("Turn Speed: " + speed + " Acceleration Multiplier: " + accelerationMultiplier);
    if(Robot.vision.getOffset(TargetSpot.CENTER) > 0){
      Robot.driveTrain.tankDrive(-speed,speed);
    }else{
      Robot.driveTrain.tankDrive(speed, -speed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.vision.getOffset(TargetSpot.CENTER))<=deadband || Robot.vision.getContourNumber() < 2;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
   // Robot.vision.setVisionMode(false);
    System.out.println("ending turn");
    Robot.driveTrain.driveStraight(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}