/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

/**
 * Add your docs here.
 */

public class MoveWings extends InstantCommand {
 
/**
   * Add your docs here.
   */
  Value currentPosition;
  public MoveWings() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    currentPosition = Robot.hatchIntake.getWingsPosition();
    switch(currentPosition){
      case kForward:
        Robot.hatchIntake.wingsClosed();
        break;
      case kReverse:
        Robot.hatchIntake.wingsOpen();
        break;
      default:
        Robot.hatchIntake.wingsClosed();
        break;
    }
  }

}
