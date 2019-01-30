/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  final TalonSRX tilt = new TalonSRX(2);
  final VictorSP wheelsFront = new VictorSP(5);
  final VictorSP wheelsBack = new VictorSP(8);
public void wheelsIn (){
  wheelsFront.set(0.5);
  wheelsBack.set(-0.5); 
}
public void wheelsOut (){
  wheelsFront.set(-0.5);
  wheelsBack.set(0.5);
}
public void wheelsStop(){
  wheelsFront.set(0);
  wheelsBack.set(0);
}
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
