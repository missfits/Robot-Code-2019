/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.commands.Teleop;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  final TalonSRX frontLeft = new TalonSRX(2);
	final TalonSRX rearLeft = new TalonSRX(3);
	final TalonSRX frontRight = new TalonSRX(1);
  final TalonSRX rearRight = new TalonSRX(4);
  final TalonSRX midLeft = new TalonSRX(5);
  final TalonSRX midRight = new TalonSRX(6);

  public DriveTrain(){
		frontRight.setInverted(true);
    rearRight.setInverted(true);
    midRight.setInverted(true);
  }
  
  public void driveStraight(double speed) {
		frontLeft.set(ControlMode.PercentOutput, speed);
		rearLeft.set(ControlMode.PercentOutput, speed);
		frontRight.set(ControlMode.PercentOutput, speed);
    rearRight.set(ControlMode.PercentOutput, speed);
    midRight.set(ControlMode.PercentOutput, speed);
    midLeft.set(ControlMode.PercentOutput, speed);
  }
  
  public void tankDrive(double lSpeed, double rSpeed){
    frontLeft.set(ControlMode.PercentOutput, lSpeed);
    rearLeft.set(ControlMode.PercentOutput, lSpeed);
    midLeft.set(ControlMode.PercentOutput, lSpeed);
		frontRight.set(ControlMode.PercentOutput, rSpeed);
    rearRight.set(ControlMode.PercentOutput, rSpeed);
    midRight.set(ControlMode.PercentOutput, rSpeed);
  }
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Teleop());
  }
}
