/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  final VictorSPX motor1 = new VictorSPX(8);
  final VictorSPX motor2 = new VictorSPX(9);
  final Servo arms = new Servo(3);
  final DigitalInput topLimitSwitch = new DigitalInput(1);
  final DigitalInput bottomLimitSwitch = new DigitalInput(1);
  //arms will be run by servos

  public void climb(double speed){
    motor1.set(ControlMode.PercentOutput, speed);
    motor2.set(ControlMode.PercentOutput, speed);
  }

  public void deployArms(){
    arms.set(1);
  }
  public boolean reachedTop(){
    return topLimitSwitch.get();
  }

  public boolean reachedBottom(){
    return bottomLimitSwitch.get();
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
