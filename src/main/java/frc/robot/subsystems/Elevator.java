/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.commands.TeleopElevator;
/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  final TalonSRX motor1 = new TalonSRX(8);
  final TalonSRX motor2 = new TalonSRX(7);
  public enum Height{
    BOTTOM_BALL, BOTTOM_HATCH, MIDDLE_BALL, MIDDLE_HATCH, TOP_BALL, TOP_HATCH;
  }
 
  public void elevate(double speed){
    motor1.set(ControlMode.PercentOutput, speed);
    motor2.set(ControlMode.PercentOutput, speed);
  }

  
  public double getPosition() {
    return motor1.getSelectedSensorPosition()/4096;
  }
 

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopElevator());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
