/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.IntakeTilt.TiltPosition;
import frc.robot.commands.RunIntakeWheels.Direction;

public class PickUpBall extends CommandGroup {
  /**
   * Add your docs here.
   */
  public PickUpBall() {
    addSequential(new IntakeTilt(TiltPosition.BALL_PICKUP));
    addSequential(new RunIntakeWheels(3, Direction.IN));
    addSequential(new IntakeTilt(TiltPosition.BALL_SHOOT));
  }
}
