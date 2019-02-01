/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  final TalonSRX tilt = new TalonSRX(2);
  final VictorSP wheels = new VictorSP(8);

public void tiltUp(){
  tilt.set(ControlMode.Position, 0);
}
public void tiltBackwards(){
  tilt.set(ControlMode.Position, -1000);
}
public void tiltAngled(){
  tilt.set(ControlMode.Position, 500);
}
public void tiltDown(){
  tilt.set(ControlMode.Position, 1000);
}

public void wheelsIn(){
  wheels.set(0.5); 
}
public void wheelsOut(){
  wheels.set(-0.5);
}
public void wheelsStop(){
  wheels.set(0);
}
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
