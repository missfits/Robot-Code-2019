/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TeleopIntake extends Command {
  public TeleopIntake() {
    requires(Robot.cargoIntake);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Math.abs(Robot.oi.xBoxLeftStickY()) > 0.3){
      if(!Robot.cargoIntake.tiltLimitPressed() || Robot.oi.xBoxLeftStickY() > 0){
        Robot.cargoIntake.testTilt(Math.signum(Robot.oi.xBoxLeftStickY())*0.5);
      }
      if(Robot.oi.xBoxLeftStickY() < 0 && !Robot.cargoIntake.tiltLimitPressed()){
        Robot.cargoIntake.wheelsIn(0.4);
      }else{
        Robot.cargoIntake.wheelsStop();
      }
    }else{
      Robot.cargoIntake.stopTilt();
      if(!Robot.oi.xBoxLeftTrigger.get() && !Robot.oi.xBoxRightTrigger.get()){
        Robot.cargoIntake.wheelsStop();
      }
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
