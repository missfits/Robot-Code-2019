/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  final VictorSP tilt = new VictorSP(0);
  final VictorSP wheels = new VictorSP(2);
  final VictorSP arm = new VictorSP(1);

public void tiltUp(int direction){
  tilt.set(direction * 0.4);
}

public void armUp(int direction){
  arm.set(direction * 1);
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
