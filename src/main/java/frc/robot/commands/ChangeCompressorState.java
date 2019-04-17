/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class ChangeCompressorState extends InstantCommand {
  public enum CompressorState{
    ON, OFF
  }
  CompressorState state;
  /**
   * Add your docs here.
   */
  public ChangeCompressorState(CompressorState state) {
    super();
    this.state = state;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    switch(state){
      case ON:
        Robot.hatchIntake.compressorOn();
      break;
      case OFF:
        Robot.hatchIntake.compressorOff();
      break;
    }
  }

}
