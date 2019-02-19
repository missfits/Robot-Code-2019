/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.IntakeTilt.TiltPosition;

public class PickUpHatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public PickUpHatch() {
    addSequential(new IntakeTilt(TiltPosition.HATCH_PICKUP));
    addSequential(new LowerIntakeArm());
    addSequential(new DriveStraight(5));
    addSequential(new RaiseIntakeArm());
    addSequential(new DriveStraight(-5));
  }
}
