/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.InvertType;

import frc.robot.RobotMap;
import frc.robot.commands.TeleopElevator;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  final TalonSRX motor1 = new TalonSRX(RobotMap.elevator1);
  final TalonSRX motor2 = new TalonSRX(RobotMap.elevator2);

  public Elevator() {
    motor1.setInverted(true);
    motor2.setInverted(true);
  }

  public enum Height {
    GROUND, HOLDING_BALL, BOTTOM_ROCKET, MIDDLE_ROCKET, CARGO_SHIP, BALL_PICKUP;
  }

  public void elevate(double speed) {
    SmartDashboard.putNumber("Elevator Speed",speed);
    motor1.set(ControlMode.PercentOutput, speed);
    motor2.set(ControlMode.PercentOutput, speed);
  }

  
  public double getPosition() {
    return motor1.getSelectedSensorPosition();
  }
  
  public void zeroPosition(){
    motor1.setSelectedSensorPosition(0);
  }
 

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopElevator());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
