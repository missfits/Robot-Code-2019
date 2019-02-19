/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class RunIntakeWheels extends TimedCommand {
  public enum Direction{
    IN, OUT
  }
  /**
   * Add your docs here.
   */
  public Direction direction;
  public RunIntakeWheels(double timeout, Direction d) {
    super(timeout);
    direction = d;
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
    if(direction == Direction.IN){
      Robot.intake.wheelsIn();
    }else{
      Robot.intake.wheelsOut();
    }
    
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.intake.wheelsStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.intake.wheelsStop();
  }
}
