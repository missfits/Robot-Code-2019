/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.FalconPathPlanner;
import frc.robot.Robot;

public class DriveCurve extends Command {
  FalconPathPlanner pathPlanner;
  int step;
  public DriveCurve() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    double[][] autoPath = new double[][] {
      {0, 4},
      {5.5, 9},
      {3.5, 14.5}
    };

    double totalTime = 5;
    double timeStep = 0.2;
    double robotTrackWidth = 2;

    pathPlanner = new FalconPathPlanner(autoPath);
    pathPlanner.calculate(totalTime, timeStep, robotTrackWidth);
    step = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveTrain.tankDrive(pathPlanner.smoothLeftVelocity[step][1], pathPlanner.smoothRightVelocity[step][1]);
    step++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return step == pathPlanner.smoothLeftVelocity.length;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.driveStraight(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
