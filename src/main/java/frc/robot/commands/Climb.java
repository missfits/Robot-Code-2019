/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.commands.IntakeTilt.TiltPosition;
import frc.robot.commands.MoveClimber.ClimbDirection;
import frc.robot.commands.RunIntakeWheels.WheelDirection;
import frc.robot.subsystems.Elevator.Height;

public class Climb extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Climb() {
    /*
    Auto climb:
    - raise elevator to Z1
    - tilt intake down to climb position (and hold position)
    - lower elevator and climber together until elevator is at Z0
    - stop both
    - run intake wheels in + run drive train fwd 
    - maybe leave pulling up the climber for a manual procedure?
    */ 
    addSequential(new Elevate(Height.START_CLIMB));
    addSequential(new IntakeTilt(TiltPosition.CLIMBING));
    addParallel(new KeepIntakeTilted());
    addParallel(new MoveClimber(ClimbDirection.UP));
    addSequential(new Elevate(Height.GROUND));
    addSequential(new InterruptClimb());
    addParallel(new RunIntakeWheels(WheelDirection.IN));
    addParallel(new TimedDriveStraight(5));
    /*addSequential(new IntakeTilt(TiltPosition.CLIMBING));
    addParallel(new DriveStraight(1234567890));
    addSequential(new TimedCommand(2));
    addSequential(new MoveClimber(ClimbDirection.UP));
    addSequential(new ApproachWall());
    addSequential(new MoveClimber(ClimbDirection.DOWN));*/
  }
}
