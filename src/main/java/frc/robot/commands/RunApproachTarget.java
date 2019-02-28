/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;

public class RunApproachTarget extends ConditionalCommand {
  public RunApproachTarget() {
    super(new ApproachTarget());
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  public boolean condition(){
    return Robot.vision.getDistance()<=18 || Robot.vision.getContourNumber() < 2;
  }
}
