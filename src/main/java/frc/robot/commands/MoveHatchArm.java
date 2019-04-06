/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class MoveHatchArm extends InstantCommand {
  /**
   * Add your docs here.
   */
  Value currentPosition;
  public MoveHatchArm() {
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
        Robot.hatchIntake.armSolenoidIn();
        break;
      case kReverse:
        Robot.hatchIntake.armSolenoidOut();
        break;
      default:
        Robot.hatchIntake.armSolenoidOut();
        break;
    }
  }

}
